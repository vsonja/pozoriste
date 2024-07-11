package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;

/**
 *
 * @author Sonja
 */
public class SOZapamtiPredstavu extends OpstaSO {

    public SOZapamtiPredstavu() throws Exception {
        super();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        if (!dbb.UbaciNoviObjekat(odo)) {
            setPoruka("Greška! Predstava nije sačuvana.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Predstava je uspešno sačuvana.");
            setStatus(Status.SUCCESS);
        }
    }

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null) {
            throw new NullPointerException("Prosledjeni objekat je null!");
        }

        if (!(odo instanceof Predstava)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Predstava!");
        }
    }

}
