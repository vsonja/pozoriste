package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;

/**
 * Predstavlja sistemsku operaciju za cuvanje predstave.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOZapamtiPredstavu extends OpstaSO {

    public SOZapamtiPredstavu() throws Exception {
        super();
    }

    /**
     * Poziva se metoda koja dodaje novu predstavu u bazu. 
     * U odnosu na rezultat izvrsenja operacije postavlja se vrednost statusa i poruke.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        if (!dbb.UbaciNoviObjekat(odo)) {
            setPoruka("Greska! Predstava nije sacuvana.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Predstava je uspesno sacuvana.");
            setStatus(Status.SUCCESS);
        }
    }

    /**
     * Metoda proverava da li je prosledjeni domenski objekat validan, odnosno razlicit od null i instanca klase Predstava.
     * 
     * @param odo Opsti domenski objekat nad kojim se vrsi validacija.
     * @throws java.lang.NullPointerException Ukoliko je prosledjeni objekat null.
     * @throws java.lang.IllegalArgumentException Ukoliko prosledjeni objekat nije instanca klase Predstava.
     */
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
