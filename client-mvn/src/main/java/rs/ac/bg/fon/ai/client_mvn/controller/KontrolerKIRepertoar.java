package rs.ac.bg.fon.ai.client_mvn.controller;

import rs.ac.bg.fon.ai.common_mvn.constants.Operacija;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.common_mvn.domain.Repertoar;
import rs.ac.bg.fon.ai.common_mvn.domain.Sala;
import rs.ac.bg.fon.ai.common_mvn.domain.StavkaRepertoara;
import rs.ac.bg.fon.ai.common_mvn.domain.Uloga;
import rs.ac.bg.fon.ai.client_mvn.form.FrmNoviRepertoar;
import rs.ac.bg.fon.ai.client_mvn.form.OpstaEkranskaForma;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class KontrolerKIRepertoar extends OpstiKontrolerKI {

    public KontrolerKIRepertoar(OpstaEkranskaForma oef) {
        this.oef = oef;
        popuniCmbPredstave();
        popuniCmbGlumci();
        popuniCmbSale();
    }

    public void zapamtiRepertoar() throws Exception {
        FrmNoviRepertoar forma = (FrmNoviRepertoar) oef;
        int mesec = forma.getCmbMesec();
        int godina = Integer.valueOf(forma.getCmbGodina().getSelectedItem().toString());

        Repertoar repertoar = new Repertoar();
        repertoar.setMesec(mesec);
        repertoar.setGodina(godina);
        odo = repertoar;

        try {
            odgovor = pozoviSO(Operacija.KREIRAJ_REPERTOAR, odo);

            if (odgovor.getStatus() == Status.SUCCESS) {
                forma.prikaziPoruku(odgovor.getPoruka(), "Poruka");
                forma.setTxtID(((Repertoar) odgovor.getRezultat()).getRepertoarID() + "");
            } else {
                forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
            }
        } catch (Exception ex) {
            forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
        }
    }

    private void popuniCmbPredstave() {
        FrmNoviRepertoar fnr = (FrmNoviRepertoar) oef;

        try {
            Predstava p = null;
            odo = new Predstava();
            odgovor = SOPretrazi(Operacija.VRATI_SVE_PREDSTAVE);
            if (odgovor.getStatus() == Status.SUCCESS) {
                List<Predstava> predstave = new ArrayList<>((List) odgovor.getRezultat());
                for (Predstava predstava : predstave) {
                    fnr.getCmbPredstava().addItem(predstava);
                }
            } else {
                fnr.prikaziPoruku(odgovor.getPoruka(), "");
                fnr.dispose();
            }
        } catch (Exception ex) {
            fnr.prikaziPoruku(ex.getMessage(), "Greska");
        }
    }

    private void popuniCmbGlumci() {
        FrmNoviRepertoar fnr = (FrmNoviRepertoar) oef;

        try {
            Glumac g = null;
            odo = new Glumac();
            odgovor = SOPretrazi(Operacija.VRATI_SVE_GLUMCE);
            if (odgovor.getStatus() == Status.SUCCESS) {
                List<Glumac> glumci = new ArrayList<>((List) odgovor.getRezultat());
                for (Glumac glumac : glumci) {
                    fnr.getCmbGlumac().addItem(glumac);
                }
            } else {
                fnr.prikaziPoruku(odgovor.getPoruka(), "");
                fnr.dispose();
            }
        } catch (Exception ex) {
            fnr.prikaziPoruku(ex.getMessage(), "Greska");
        }
    }

    public void zapamtiUlogu() throws Exception {
        FrmNoviRepertoar forma = (FrmNoviRepertoar) oef;
        Glumac glumac = (Glumac) forma.getCmbGlumac().getSelectedItem();
        Predstava predstava = (Predstava) forma.getCmbPredstava().getSelectedItem();
        String naziv = forma.getTxtUloga();

        String poruka = "";
        if (naziv.isEmpty()) {
            poruka += "Niste uneli naziv uloge.\n";
        }

        if (!poruka.isEmpty()) {
            forma.prikaziPoruku(poruka, "Greška");
            return;
        }

        Uloga uloga = new Uloga(glumac, predstava, naziv);
        odo = uloga;

        try {
            odgovor = pozoviSO(Operacija.KREIRAJ_ULOGU, uloga);

            if (odgovor.getStatus() == Status.SUCCESS) {
                forma.prikaziPoruku(odgovor.getPoruka(), "Poruka");
            } else {
                forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
            }
        } catch (Exception ex) {
            forma.prikaziPoruku(ex.getMessage(), "Greška");
        }
    }

    private void popuniCmbSale() {
        FrmNoviRepertoar fnr = (FrmNoviRepertoar) oef;

        try {
            Sala s = null;
            odo = new Sala();
            odgovor = SOPretrazi(Operacija.VRATI_SVE_SALE);
            if (odgovor.getStatus() == Status.SUCCESS) {
                List<Sala> sale = new ArrayList<>((List) odgovor.getRezultat());
                for (Sala sala : sale) {
                    fnr.getCmbSala().addItem(sala);
                }
            } else {
                fnr.prikaziPoruku(odgovor.getPoruka(), "Poruka");

                fnr.dispose();
            }
        } catch (Exception ex) {
            fnr.prikaziPoruku(ex.getMessage(), "Greška");
        }
    }

    public void zapamtiStavkuRepertoara() throws Exception {
        FrmNoviRepertoar forma = (FrmNoviRepertoar) oef;

        String poruka = "";
        if (forma.getTxtID().isEmpty()) {
            poruka += "Niste uneli repertoar.\n";
        }

        if (forma.getTxtVremePocetka().isEmpty()) {
            poruka += "Niste uneli vreme početka.\n";
        }

        if (forma.getTxtVremeZavrsetka().isEmpty()) {
            poruka += "Niste uneli vreme završetka.\n";
        }

        if (!poruka.isEmpty()) {
            forma.prikaziPoruku(poruka, "Greška");
            return;
        }

        Integer repertoarID = Integer.valueOf(forma.getTxtID());
        int mesec = forma.getCmbMesec();
        int godina = Integer.valueOf(forma.getCmbGodina().getSelectedItem().toString());
        LocalDate datum = LocalDate.of(godina, mesec, forma.getDatum().getDay());
        LocalTime vremePocetka = LocalTime.parse(forma.getTxtVremePocetka(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime vremeZavrsetka = LocalTime.parse(forma.getTxtVremeZavrsetka(), DateTimeFormatter.ofPattern("HH:mm"));
        Predstava predstava = (Predstava) forma.getCmbPredstava().getSelectedItem();
        Sala sala = (Sala) forma.getCmbSala().getSelectedItem();

        Repertoar r = new Repertoar();
        r.setRepertoarID(repertoarID);

        StavkaRepertoara stavka = new StavkaRepertoara();
        stavka.setRepertoar(r);
        stavka.setDatum(datum);
        stavka.setVremePocetka(vremePocetka);
        stavka.setVremeZavrsetka(vremeZavrsetka);
        stavka.setSala(sala);
        stavka.setPredstava(predstava);
        odo = stavka;

        try {
            odgovor = pozoviSO(Operacija.KREIRAJ_STAVKU_REPERTOARA, stavka);

            if (odgovor.getStatus() == Status.SUCCESS) {
                forma.prikaziPoruku(odgovor.getPoruka(), "Poruka");
            } else {
                forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
            }
        } catch (Exception ex) {
            forma.prikaziPoruku(ex.getMessage(), "Greška");
        }
    }

}
