package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Repertoar;

/**
 *
 * @author Sonja
 */
public class SOZapamtiRepertoar extends OpstaSO {

    public SOZapamtiRepertoar() throws Exception {
        super();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = dbb.UbaciNoviObjekat(odo);

        if (uspesno == false) {
            setPoruka("Greška! Repertoar nije sačuvan.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Repertoar je uspešno sačuvan.");
            setStatus(Status.SUCCESS);
        }
    }

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null) {
            throw new NullPointerException("Prosledjeni objekat je null!");
        }

        if (!(odo instanceof Repertoar)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Repertoar!");
        }
    }

}
