package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Uloga;

/**
 *
 * @author Sonja
 */
public class SOZapamtiUlogu extends OpstaSO {

    public SOZapamtiUlogu() throws Exception {
        super();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = dbb.UbaciNoviObjekat(odo);

        if (uspesno == false) {
            setPoruka("Greška! Uloga nije sačuvana.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Uloga je uspešno sačuvana.");
            setStatus(Status.SUCCESS);
        }
    }

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null) {
            throw new NullPointerException("Prosledjeni objekat je null!");
        }

        if (!(odo instanceof Uloga)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Repertoar!");
        }
    }

}
