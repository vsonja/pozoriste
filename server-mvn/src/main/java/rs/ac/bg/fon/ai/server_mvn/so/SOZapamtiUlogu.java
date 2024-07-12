package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Uloga;

/**
 * Predstavlja sistemsku operaciju za kreiranje uloge.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOZapamtiUlogu extends OpstaSO {

    public SOZapamtiUlogu() throws Exception {
        super();
    }

    /**
     * Poziva se metoda koja dodaje novu ulogu u bazu. 
     * U odnosu na rezultat izvrsenja operacije postavlja se vrednost statusa i poruke.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = dbb.UbaciNoviObjekat(odo);

        if (uspesno == false) {
            setPoruka("Greska! Uloga nije sacuvana.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Uloga je uspesno sacuvana.");
            setStatus(Status.SUCCESS);
        }
    }

    /**
     * Metoda proverava da li je prosledjeni domenski objekat validan, odnosno razlicit od null i instanca klase Uloga.
     * 
     * @param odo Opsti domenski objekat nad kojim se vrsi validacija.
     * @throws java.lang.NullPointerException Ukoliko je prosledjeni objekat null.
     * @throws java.lang.IllegalArgumentException Ukoliko prosledjeni objekat nije instanca klase Uloga.
     */
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
