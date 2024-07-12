package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;

/**
 * Predstavlja sistemsku operaciju za prikaz podataka o izabranoj predstavi.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOVratiPredstavu extends OpstaSO {

	/**
	 * Uslov po kom ce se vrsiti pretraga predstave.
	 */
    UslovWhere uslov;

    /**
     * Neparametarski konstruktor koji poziva konstruktor nadklase i postavlja uslov na unetu vrednost.
     * 
     * @param uslov Uslov po kom ce se vrsiti pretraga.
     */
    public SOVratiPredstavu(UslovWhere uslov) {
        super();
        this.uslov = uslov;
    }

    /**
     * Poziva se operacija koja vraca sve objekte iz baze koji zadovoljavaju odredjeni uslov.
     * U ovom slucaju pretraga se vrsi po primarnom kljucu pa nikad nece biti vraceno vise predstava,
     * vec samo ona koja se trazi.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        odo = (dbb.vratiSveObjekteSaUslovom(odo, uslov)).get(0);
        if (odo == null) {
            setPoruka("Greska! Sistem ne može da ucita predstavu.");
            setStatus(Status.FAILURE);
        } else {
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
