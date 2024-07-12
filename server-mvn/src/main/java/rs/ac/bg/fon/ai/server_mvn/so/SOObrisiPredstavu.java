package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;

/**
 * Predstavlja sistemsku operaciju za brisanje predstave.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOObrisiPredstavu extends OpstaSO {

    public SOObrisiPredstavu() {
        super();
    }

    /**
     * Poziva se operacija za brisanje predstave iz baze podataka, koja vraca boolean vrednost u zavisnosti od rezultata izvrsenja.
     * U skladu sa tim se postavlja vrednost poruke i statusa operacije.
     */
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
