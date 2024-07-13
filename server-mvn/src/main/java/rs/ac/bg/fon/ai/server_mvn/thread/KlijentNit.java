package rs.ac.bg.fon.ai.server_mvn.thread;

import rs.ac.bg.fon.ai.common_mvn.communication.Odgovor;
import rs.ac.bg.fon.ai.common_mvn.communication.Zahtev;
import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.server_mvn.controller.Kontroler;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.Korisnik;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.common_mvn.domain.Repertoar;
import rs.ac.bg.fon.ai.common_mvn.domain.StavkaRepertoara;
import rs.ac.bg.fon.ai.common_mvn.domain.Uloga;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Sonja
 */
public class KlijentNit extends Thread {

    private Socket klijentSocket;
    private ServerNit serverNit;
    Korisnik korisnik;

    public KlijentNit(Socket klijentSocket, ServerNit serverNit) {
        this.klijentSocket = klijentSocket;
        this.serverNit = serverNit;
    }

    @Override
    public void run() {
        while (!klijentSocket.isClosed()) {
            try {
                Zahtev zahtev = primiZahtev();
                OpstiDomenskiObjekat odo = (OpstiDomenskiObjekat) zahtev.getArgument();
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case PRIJAVI: {
                        odgovor = Kontroler.getInstance().ulogujKorisnika((Korisnik) odo);
                        if (odgovor.getStatus() == Status.SUCCESS) {
                            korisnik = (Korisnik) odo;
                            korisnik.setKlijentIP(klijentSocket.getInetAddress().getHostAddress());
                            korisnik.setVreme(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")));
                            Kontroler.getInstance().dodajKorisnika(korisnik);
                        }
                        break;
                    }
                    case ODJAVI:
                        Kontroler.getInstance().izbaciKorisnika(korisnik);
                        serverNit.izbaciKlijenta(this);
                        klijentSocket.close();
                        interrupt();
                        System.out.println(korisnik.getKorisnickoIme());
                        return;
                    case KREIRAJ_GLUMCA:
                        odgovor = Kontroler.getInstance().sacuvajGlumca((Glumac) odo);
                        break;
                    case VRATI_SVE_GLUMCE:
                        odgovor = Kontroler.getInstance().vratiSveGlumce();
                        break;
                    case VRATI_GLUMCA:
                        odgovor = Kontroler.getInstance().vratiGlumca((Glumac) odo, UslovWhere.PRIMARNI_KLJUC);
                        break;
                    case OBRISI_GLUMCA:
                        odgovor = Kontroler.getInstance().obrisiGlumca((Glumac) odo);
                        break;
                    case KREIRAJ_PREDSTAVU:
                        odgovor = Kontroler.getInstance().sacuvajPredstavu((Predstava) odo);
                        break;
                    case VRATI_SVE_PREDSTAVE:
                        odgovor = Kontroler.getInstance().vratiSvePredstave();
                        break;
                    case VRATI_PREDSTAVU:
                        odgovor = Kontroler.getInstance().vratiPredstavu((Predstava) odo, UslovWhere.PRIMARNI_KLJUC);
                        break;
                    case OBRISI_PREDSTAVU:
                        odgovor = Kontroler.getInstance().obrisiPredstavu((Predstava) odo);
                        break;
                    case KREIRAJ_ULOGU:
                        odgovor = Kontroler.getInstance().sacuvajUlogu((Uloga) odo);
                        break;
                    case VRATI_SVE_SALE:
                        odgovor = Kontroler.getInstance().vratiSveSale();
                        break;
                    case KREIRAJ_REPERTOAR:
                        odgovor = Kontroler.getInstance().sacuvajRepertoar((Repertoar) odo);
                        break;
                    case KREIRAJ_STAVKU_REPERTOARA:
                        odgovor = Kontroler.getInstance().sacuvajStavkuRepertoara((StavkaRepertoara) odo);
                        break;
                    case VRATI_SVE_KOSTIME:
                        odgovor = Kontroler.getInstance().vratiSveKostime();
                        break;
                }

                posaljiOdgovor(odgovor);
            } catch (Exception ex) {
                // ex.printStackTrace();
            }
        }
        serverNit.izbaciKlijenta(this);
    }

    public Socket getKlijentSocket() {
        return klijentSocket;
    }

    private Zahtev primiZahtev() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(klijentSocket.getInputStream());
        return (Zahtev) in.readObject();
    }

    private void posaljiOdgovor(Odgovor odgovor) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(klijentSocket.getOutputStream());
        out.writeObject(odgovor);
        out.flush();
    }

}
