package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;

/**
 * Predstavlja sistemsku operaciju za prikaz podataka o izabranom glumcu.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOVratiGlumca extends OpstaSO {

	/**
	 * Uslov po kom ce se vrsiti pretraga glumca.
	 */
    UslovWhere uslov;

    /**
     * Neparametarski konstruktor koji poziva konstruktor nadklase i postavlja uslov na unetu vrednost.
     * 
     * @param uslov Uslov po kom ce se vrsiti pretraga.
     */
    public SOVratiGlumca(UslovWhere uslov) {
        super();
        this.uslov = uslov;
    }

    /**
     * Poziva se operacija koja vraca sve objekte iz baze koji zadovoljavaju odredjeni uslov.
     * U ovom slucaju pretraga se vrsi po primarnom kljucu pa nikad nece biti vraceno vise glumaca,
     * vec samo onaj koji se trazi.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        odo = (dbb.vratiSveObjekteSaUslovom(odo, uslov)).get(0);
        if (odo == null) {
            setPoruka("Greška! Sistem ne može da ucita glumca.");
            setStatus(Status.FAILURE);
        } else {
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
