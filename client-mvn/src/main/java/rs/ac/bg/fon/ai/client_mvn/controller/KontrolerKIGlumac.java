package rs.ac.bg.fon.ai.client_mvn.controller;

import rs.ac.bg.fon.ai.common_mvn.constants.Operacija;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.client_mvn.form.FrmGlumci;
import rs.ac.bg.fon.ai.client_mvn.form.FrmNoviGlumac;
import rs.ac.bg.fon.ai.client_mvn.form.OpstaEkranskaForma;
import rs.ac.bg.fon.ai.client_mvn.form.model.TableModelGlumac;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class KontrolerKIGlumac extends OpstiKontrolerKI {

    public KontrolerKIGlumac(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    public void zapamtiGlumca() throws Exception {
        FrmNoviGlumac forma = (FrmNoviGlumac) oef;
        String ime = forma.getTxtIme();
        String prezime = forma.getTxtPrezime();
        String telefon = forma.getTxtTelefon();

        String poruka = "";
        if (ime.isEmpty()) {
            poruka += "Niste uneli ime glumca.\n";
        }

        if (prezime.isEmpty()) {
            poruka += "Niste uneli prezime glumca.\n";
        }

        if (telefon.isEmpty()) {
            poruka += "Niste uneli broj telefona.\n";
        }

        if (!poruka.isEmpty()) {
            forma.prikaziPoruku(poruka, "Greška");
            return;
        }

        Glumac glumac = new Glumac(ime, prezime, telefon);
        odo = glumac;

        try {
            odgovor = pozoviSO(Operacija.KREIRAJ_GLUMCA, glumac);

            if (odgovor.getStatus() == Status.SUCCESS) {
                forma.prikaziPoruku(odgovor.getPoruka(), "Poruka");
                forma.dispose();
            } else {
                forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
            }
        } catch (Exception ex) {
            forma.prikaziPoruku(ex.getMessage(), "Greška");
        }
    }

    public void popuniTabelu() {
        FrmGlumci forma = (FrmGlumci) oef;

        try {
            odo = new Glumac();

            odgovor = SOPretrazi(Operacija.VRATI_SVE_GLUMCE);

            if (odgovor.getStatus() == Status.SUCCESS) {
                List<Glumac> glumci = (List<Glumac>) odgovor.getRezultat();
                TableModelGlumac tmg = new TableModelGlumac(glumci);
                forma.getTblGlumci().setModel(tmg);
            } else {
                forma.prikaziPoruku(odgovor.getPoruka(), "");
                forma.dispose();
            }

        } catch (Exception ex) {
            forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
            ex.printStackTrace();
        }
    }

    public void prikaziDetalje() {
        FrmGlumci forma = (FrmGlumci) oef;
        int row = forma.getTblGlumci().getSelectedRow();
        if (row == -1) {
            forma.prikaziPoruku("Nije selektovan red.", "");
        } else {
            TableModelGlumac tmg = (TableModelGlumac) forma.getTblGlumci().getModel();
            Glumac g = tmg.getGlumci().get(row);
            odo = g;

            try {
                odgovor = SOPretrazi(Operacija.VRATI_GLUMCA);
                if (odgovor.getStatus() == Status.SUCCESS) {
                    FrmNoviGlumac fng = new FrmNoviGlumac();
                    fng.setTxtIme(((Glumac) odgovor.getRezultat()).getIme());
                    fng.setTxtPrezime(((Glumac) odgovor.getRezultat()).getPrezime());
                    fng.setTxtTelefon(((Glumac) odgovor.getRezultat()).getTelefon());
                    fng.setTxtID(((Glumac) odgovor.getRezultat()).getGlumacID() + "");
                    fng.getBtnSacuvaj().setVisible(false);
                    fng.getBtnObrisi().setVisible(true);
                    fng.setVisible(true);
                } else {
                    forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
                }
            } catch (Exception ex) {
                forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
            }
        }
    }

    public void obrisiGlumca() {
        FrmNoviGlumac fng = (FrmNoviGlumac) oef;
        Glumac g = new Glumac();
        g.setGlumacID(Integer.valueOf(fng.getTxtID()));
        odo = g;

        try {
            odgovor = SOObrisi(Operacija.OBRISI_GLUMCA);
            if (odgovor.getStatus() == Status.SUCCESS) {
                fng.prikaziPoruku(odgovor.getPoruka(), "Poruka");
                fng.dispose();
                popuniTabelu();
            } else {
                fng.prikaziPoruku(odgovor.getPoruka(), "Greška");
            }

        } catch (Exception ex) {
            fng.prikaziPoruku(odgovor.getPoruka(), "Greška");
        }
    }

}
