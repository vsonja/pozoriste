package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja sistemsku operaciju za prikaz podataka o svim glumcima u pozoristu.
 *
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOVratiSveGlumce extends OpstaSO {

	/**
	 * Lista glumaca koji predstavljaju rezultat sistemske operacije.
	 */
    List<Glumac> glumci;

    /**
     * Neparametarski konstruktor koji poziva konstruktor nadklase i inicijalizuje listu glumaca.
     */
    public SOVratiSveGlumce() {
        super();
        glumci = new ArrayList<>();
    }

    /**
     * Poziva se metoda koja vraca sve glumaca iz baze podataka.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> lista = dbb.vratiSveObjekteBezUslova(odo);

        if (lista == null || lista.isEmpty()) {
            setPoruka("Greska! Sistem ne može da ucita glumce.");
            setStatus(Status.FAILURE);
        } else {
            setStatus(Status.SUCCESS);

            List<Glumac> l = new ArrayList(lista);

            glumci = l;
        }
    }

    /**
     * Vraca listu glumaca koji predstavljaju rezultat sistemske operacije.
     * @return Svi glumci koji postoje u bazi.
     */
    @Override
    public List vratiListu() {
        return glumci;
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
