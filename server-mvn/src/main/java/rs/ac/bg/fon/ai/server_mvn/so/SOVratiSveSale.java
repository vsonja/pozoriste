package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Sala;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja sistemsku operaciju za prikaz podataka o svim salama u pozoristu.
 *
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOVratiSveSale extends OpstaSO {

	/**
	 * Lista sala koje predstavljaju rezultat sistemske operacije.
	 */
    List<Sala> sale;

    /**
     * Neparametarski konstruktor koji poziva konstruktor nadklase i inicijalizuje listu sala.
     */
    public SOVratiSveSale() throws Exception {
        super();
        sale = new ArrayList<>();
    }

    /**
     * Poziva se metoda koja vraca sve sale iz baze podataka.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> lista = dbb.vratiSveObjekteBezUslova(odo);

        if (lista == null || lista.isEmpty()) {
            setPoruka("Greska! Sistem ne može da ucita sale.");
            setStatus(Status.FAILURE);
        } else {
            setStatus(Status.SUCCESS);

            List<Sala> l = new ArrayList(lista);

            sale = l;
        }
    }

    /**
     * Vraca listu sala koje predstavljaju rezultat sistemske operacije.
     * @return Sve sale koje postoje u bazi.
     */
    @Override
    public List vratiListu() {
        return sale;
    }

    /**
     * Metoda proverava da li je prosledjeni domenski objekat validan, odnosno razlicit od null i instanca klase Sala.
     * 
     * @param odo Opsti domenski objekat nad kojim se vrsi validacija.
     * @throws java.lang.NullPointerException Ukoliko je prosledjeni objekat null.
     * @throws java.lang.IllegalArgumentException Ukoliko prosledjeni objekat nije instanca klase Sala.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (odo == null) {
            throw new NullPointerException("Prosledjeni objekat je null!");
        }

        if (!(odo instanceof Sala)) {
            throw new IllegalArgumentException("Prosledjeni objekat nije instanca klase Sala!");
        }
    }

}
