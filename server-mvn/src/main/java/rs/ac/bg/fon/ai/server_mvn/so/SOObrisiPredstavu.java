package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;

/**
 *
 * @author Sonja
 */
public class SOObrisiPredstavu extends OpstaSO {

    public SOObrisiPredstavu() throws Exception {
        super();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        if (!dbb.obrisiObjekat(odo)) {
            setPoruka("Greška! Predstava nije obrisan.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Predstava je uspešno obrisana.");
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
