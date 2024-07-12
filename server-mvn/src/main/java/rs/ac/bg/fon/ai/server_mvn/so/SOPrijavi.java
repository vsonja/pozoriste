package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Korisnik;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;

/**
 * Predstavlja sistemsku operaciju za prijavu korisnika na sistem.
 * Proverava se da li korisnik sa unetim parametrima postoji u bazi.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOPrijavi extends OpstaSO {

	/**
	 * Prijavljeni korisnik, rezultat izvrsenja sistemske operacije.
	 */
    private Korisnik korisnik;

    public SOPrijavi() throws Exception {
        super();
    }

    /**
     * Vraca prijavljenog korisnika;
     * 
     * @return Prijavljeni korisnik.
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Poziva se operacija za proveru da li u bazi postoji korisnik sa unetim parametrima, koja vraca boolean vrednost u odnosu na rezultat izvrsenja operacije.
     * U slkladu sa tim se postavlja vrednost statusa i poruke.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = dbb.daLiPostojiObjekat(odo, UslovWhere.PRIJAVA);

        if (uspesno) {
            setStatus(Status.SUCCESS);
        } else {
            setPoruka("Pogresno korisnicko ime ili sifra!");
            setStatus(Status.FAILURE);
        }
    }

    /**
     * Metoda proverava da li je prosledjeni domenski objekat validan, odnosno razlicit od null i instanca klase Korisnik.
     * 
     * @param odo Opsti domenski objekat nad kojim se vrsi validacija.
     * @throws java.lang.NullPointerException Ukoliko je prosledjeni objekat null.
     * @throws java.lang.IllegalArgumentException Ukoliko prosledjeni objekat nije instanca klase Korisnik.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null) {
            throw new NullPointerException("Prosledjeni objekat je null!");
        }
        
        if (!(odo instanceof Korisnik)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

}
