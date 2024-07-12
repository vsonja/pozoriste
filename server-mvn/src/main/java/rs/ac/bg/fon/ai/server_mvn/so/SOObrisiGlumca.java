package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;

/**
 * Predstavlja sistemsku operaciju za brisanje glumca.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOObrisiGlumca extends OpstaSO {

    public SOObrisiGlumca() throws Exception {
        super();
    }

    /**
     * Poziva se operacija za brisanje glumca iz baze podataka, koja vraca boolean vrednost u zavisnosti od rezultata izvrsenja.
     * U skladu sa tim se postavlja vrednost poruke i statusa operacije.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        if (!dbb.obrisiObjekat(odo)) {
            setPoruka("Greška! Glumac nije obrisan.");
            setStatus(Status.FAILURE);
        } else {
            setPoruka("Glumac je uspešno obrisan.");
            setStatus(Status.SUCCESS);
        }
    }

    /**
     * Metoda proverava da li je prosledjeni domenski objekat validan, odnosno razlicit od null i instanca klase Glumac.
     * 
     * @param odo Opsti domenski objekat nad kojim se vrsi validacija.
     * @throws java.lang.NullPointerException Ukoliko je prosledjeni objekat null.
     * @throws java.lang.IllegalArgumentException Ukoliko prosledjeni objekat nije instanca klase Glumac.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null) {
            throw new NullPointerException("Prosledjeni objekat je null!");
        }
        
        if (!(odo instanceof Glumac)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Glumac!");
        }
    }

}
