package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Repertoar;

/**
 * Predstavlja sistemsku operaciju za kreiranje repertoara.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOZapamtiRepertoar extends OpstaSO {

    public SOZapamtiRepertoar() throws Exception {
        super();
    }

    /**
     * Poziva se metoda koja dodaje novi repertoar u bazu. 
     * U odnosu na rezultat izvrsenja operacije postavlja se vrednost statusa i poruke.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = dbb.UbaciNoviObjekat(odo);

        if (uspesno == false) {
            setPoruka("Greska! Repertoar nije sacuvan.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Repertoar je uspesno sacuvan.");
            setStatus(Status.SUCCESS);
        }
    }

    /**
     * Metoda proverava da li je prosledjeni domenski objekat validan, odnosno razlicit od null i instanca klase Repertoar.
     * 
     * @param odo Opsti domenski objekat nad kojim se vrsi validacija.
     * @throws java.lang.NullPointerException Ukoliko je prosledjeni objekat null.
     * @throws java.lang.IllegalArgumentException Ukoliko prosledjeni objekat nije instanca klase Repertoar.
     */
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
