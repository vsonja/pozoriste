package rs.ac.bg.fon.ai.client_mvn.controller;

import rs.ac.bg.fon.ai.common_mvn.constants.Operacija;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import rs.ac.bg.fon.ai.client_mvn.form.FrmNovaPredstava;
import rs.ac.bg.fon.ai.client_mvn.form.FrmPredstave;
import rs.ac.bg.fon.ai.client_mvn.form.OpstaEkranskaForma;
import rs.ac.bg.fon.ai.client_mvn.form.model.TableModelPredstava;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Sonja
 */
public class KontrolerKIPredstava extends OpstiKontrolerKI {

    public KontrolerKIPredstava(OpstaEkranskaForma oef) {
        this.oef = oef;
    }

    public void zapamtiPredstavu() throws Exception {
        FrmNovaPredstava forma = (FrmNovaPredstava) oef;
        String naziv = forma.getTxtNaziv();
        String opis = forma.getTxtOpis();

        String poruka = "";
        if (naziv.isEmpty()) {
            poruka += "Niste uneli naziv.\n";
        }
        if (opis.isEmpty()) {
            poruka += "Niste uneli opis.\n";
        }

        if (!poruka.isEmpty()) {
            forma.prikaziPoruku(poruka, "Greška");
            return;
        }

        Predstava predstava = new Predstava();
        predstava.setNazivPredstave(naziv);
        predstava.setOpis(opis);
        odo = predstava;

        try {
            odgovor = pozoviSO(Operacija.KREIRAJ_PREDSTAVU, odo);

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
        FrmPredstave forma = (FrmPredstave) oef;

        try {
            odo = new Predstava();

            odgovor = SOPretrazi(Operacija.VRATI_SVE_PREDSTAVE);

            if (odgovor.getStatus() == Status.SUCCESS) {
                List<Predstava> predstave = (List<Predstava>) odgovor.getRezultat();
                TableModelPredstava tmp = new TableModelPredstava(predstave);
                forma.getTblPredstave().setModel(tmp);
                
                try(PrintWriter out = new PrintWriter(new FileWriter("predstave.json"))){
        			Gson gson = new GsonBuilder().setPrettyPrinting().create();

        			out.print(gson.toJson(predstave));
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
                forma.prikaziPoruku("Predstave su sačuvane u JSON fajl.", "JSON");
            } else {
                forma.dispose();
            }

        } catch (Exception ex) {
            forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
            ex.printStackTrace();
        }
    }

    public void prikaziDetalje() {
        FrmPredstave forma = (FrmPredstave) oef;
        int row = forma.getTblPredstave().getSelectedRow();
        if (row == -1) {
            forma.prikaziPoruku("Niste izabrali red.", "");
        } else {
            TableModelPredstava tmp = (TableModelPredstava) forma.getTblPredstave().getModel();
            Predstava p = tmp.getPredstave().get(row);
            odo = p;

            try {
                odgovor = SOPretrazi(Operacija.VRATI_PREDSTAVU);
                if (odgovor.getStatus() == Status.SUCCESS) {
                    FrmNovaPredstava fnp = new FrmNovaPredstava();
                    fnp.setTxtNaziv(((Predstava) odgovor.getRezultat()).getNazivPredstave());
                    fnp.setTxtOpis(((Predstava) odgovor.getRezultat()).getOpis());
                    fnp.setTxtID(((Predstava) odgovor.getRezultat()).getPredstavaID() + "");
                    fnp.getBtnSacuvaj().setVisible(false);
                    fnp.getBtnObrisi().setVisible(true);
                    fnp.setVisible(true);
                } else {
                    forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
                }
            } catch (Exception ex) {
                forma.prikaziPoruku(odgovor.getPoruka(), "Greška");
            }
        }
    }

    public void obrisiPredstavu() {
        FrmNovaPredstava fnp = (FrmNovaPredstava) oef;
        Predstava p = new Predstava();
        p.setPredstavaID(Integer.valueOf(fnp.getTxtID()));
        odo = p;

        try {
            odgovor = SOObrisi(Operacija.OBRISI_PREDSTAVU);
            if (odgovor.getStatus() == Status.SUCCESS) {
                fnp.prikaziPoruku(odgovor.getPoruka(), "Poruka");
                fnp.dispose();
                popuniTabelu();
            } else {
                fnp.prikaziPoruku(odgovor.getPoruka(), "Greška");
            }

        } catch (Exception ex) {
            fnp.prikaziPoruku(odgovor.getPoruka(), "Greška");
        }

    }

}
