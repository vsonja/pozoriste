package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Kostim;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja sistemsku operaciju za prikaz podataka o svim kostimima u pozoristu.
 *
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOVratiSveKostime extends OpstaSO {

	/**
	 * Lista kostima koji predstavljaju rezultat sistemske operacije.
	 */
    List<Kostim> kostimi;

    /**
     * Neparametarski konstruktor koji poziva konstruktor nadklase i inicijalizuje listu kostima.
     */
    public SOVratiSveKostime() {
        super();
        kostimi = new ArrayList<>();
    }

    /**
     * Poziva se metoda koja vraca sve kostime iz baze podataka.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> lista = dbb.vratiSveObjekteBezUslova(odo);

        if (lista == null || lista.isEmpty()) {
            setPoruka("Greska! Sistem ne može da ucita kostime.");
            setStatus(Status.FAILURE);
        } else {
            setStatus(Status.SUCCESS);

            List<Kostim> l = new ArrayList(lista);

            kostimi = l;
        }
    }

    /**
     * Vraca listu kostima koji predstavljaju rezultat sistemske operacije.
     * @return Svi kostimi koji postoje u bazi.
     */
    @Override
    public List vratiListu() {
        return kostimi;
    }

    /**
     * Metoda proverava da li je prosledjeni domenski objekat validan, odnosno razlicit od null i instanca klase Kostim.
     * 
     * @param odo Opsti domenski objekat nad kojim se vrsi validacija.
     * @throws java.lang.NullPointerException Ukoliko je prosledjeni objekat null.
     * @throws java.lang.IllegalArgumentException Ukoliko prosledjeni objekat nije instanca klase Kostim.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null) {
            throw new NullPointerException("Prosledjeni objekat je null!");
        }

        if (!(odo instanceof Kostim)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Kostim!");
        }
    }

}
