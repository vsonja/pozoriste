package rs.ac.bg.fon.ai.client_mvn.controller;

import rs.ac.bg.fon.ai.client_mvn.communication.Komunikacija;
import rs.ac.bg.fon.ai.common_mvn.communication.Zahtev;
import rs.ac.bg.fon.ai.common_mvn.constants.Operacija;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Korisnik;
import rs.ac.bg.fon.ai.client_mvn.form.FrmMain;
import rs.ac.bg.fon.ai.client_mvn.form.FrmPrijava;
import rs.ac.bg.fon.ai.client_mvn.form.OpstaEkranskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sonja
 */
public class KontrolerKIKorisnik extends OpstiKontrolerKI {

    Korisnik korisnik;

    public KontrolerKIKorisnik(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    public void prijavi() throws Exception {
        FrmPrijava forma = (FrmPrijava) oef;
        String korisnickoIme = forma.getTxtKorisnickoIme();
        String sifra = forma.getTxtSifra();

        String greska = "";
        if (korisnickoIme.isEmpty()) {
            greska += "Morate popuniti korisnicko ime.\n";
        }
        if (sifra.isEmpty()) {
            greska += "Morate popuniti polje za sifru.\n";
        }

        if (!greska.isEmpty()) {
            forma.prikaziPoruku(greska, "Greška");
            return;
        }

        otvoriKomunikaciju();

        Korisnik k = new Korisnik();
        k.setKorisnickoIme(korisnickoIme);
        k.setSifra(sifra);
        odo = k;
        odgovor = pozoviSO(Operacija.PRIJAVI, odo);

        if (odgovor.getStatus() == Status.SUCCESS) {
            FrmMain formaMain = new FrmMain();
            formaMain.setLblKorisnik(korisnickoIme);
            formaMain.setVisible(true);
            korisnik = k;
            forma.dispose();
            oef = formaMain;
        } else {
            forma.prikaziPoruku(odgovor.getPoruka(), "Neuspešna prijava");
        }
    }

    public void SOOdjavi() throws Exception {
        try {
            odo = korisnik;
            zahtev = new Zahtev(Operacija.ODJAVI, odo);
            Komunikacija.getInstance().posaljiZahtev(zahtev);
            System.exit(0);
        } catch (Exception ex) {
            Logger.getLogger(KontrolerKIKorisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void otvoriKomunikaciju() {
        Komunikacija.getInstance();
    }

    private boolean daliPostojiSlovo(String string) {
        char[] slova = string.toCharArray();
        boolean postojiSlovo = false;
        for (char c : slova) {
            if (!Character.isDigit(c)) {
                postojiSlovo = true;
            }
        }
        return postojiSlovo;
    }

}
