package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.StavkaRepertoara;

/**
 *
 * @author Sonja
 */
public class SOZapamtiStavkuRepertoara extends OpstaSO {

    public SOZapamtiStavkuRepertoara() throws Exception {
        super();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = dbb.UbaciNoviObjekat(odo);

        if (uspesno == false) {
            setPoruka("Greška! Stavka repertoara nije sačuvana.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Stavka repertoara je uspešno sačuvana.");
            setStatus(Status.SUCCESS);
        }
    }

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null) {
            throw new NullPointerException("Prosledjeni objekat je null!");
        }

        if (!(odo instanceof StavkaRepertoara)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase StavkaRepertoara!");
        }
    }

}
