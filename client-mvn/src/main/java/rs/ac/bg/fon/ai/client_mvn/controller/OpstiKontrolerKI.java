package rs.ac.bg.fon.ai.client_mvn.controller;

import rs.ac.bg.fon.ai.client_mvn.communication.Komunikacija;
import rs.ac.bg.fon.ai.common_mvn.communication.Odgovor;
import rs.ac.bg.fon.ai.common_mvn.communication.Zahtev;
import rs.ac.bg.fon.ai.common_mvn.constants.Operacija;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.client_mvn.form.OpstaEkranskaForma;

/**
 *
 * @author Sonja
 */
public abstract class OpstiKontrolerKI {

    OpstaEkranskaForma oef;
    OpstiDomenskiObjekat odo;
    Odgovor odgovor;
    Zahtev zahtev;

    public OpstiKontrolerKI() {
    }

    protected Odgovor pozoviSO(Operacija o, Object odo) throws Exception {
        zahtev = new Zahtev(o, odo);
        Komunikacija.getInstance().posaljiZahtev(zahtev);
        odgovor = Komunikacija.getInstance().primiOdgovor();

        if (odgovor.getException() != null) {
            throw odgovor.getException();

        }

        return odgovor;
    }

    public Odgovor SOPretrazi(Operacija o) throws Exception {
        return pozoviSO(o, odo);
    }

    public Odgovor SOObrisi(Operacija o) throws Exception {
        return pozoviSO(o, odo);
    }

}
