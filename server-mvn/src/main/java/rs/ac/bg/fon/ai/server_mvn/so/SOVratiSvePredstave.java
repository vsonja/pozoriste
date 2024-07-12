package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja sistemsku operaciju za prikaz podataka o svim predstavama u pozoristu.
 *
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOVratiSvePredstave extends OpstaSO {

	/**
	 * Lista predstava koje predstavljaju rezultat sistemske operacije.
	 */
    List<Predstava> predstave;

    /**
     * Neparametarski konstruktor koji poziva konstruktor nadklase i inicijalizuje listu predstava.
     */
    public SOVratiSvePredstave() throws Exception {
        super();
        predstave = new ArrayList<>();
    }

    /**
     * Poziva se metoda koja vraca sve predstave iz baze podataka.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> lista = dbb.vratiSveObjekteBezUslova(odo);

        if (lista == null || lista.isEmpty()) {
            setPoruka("Greska! Sistem ne može da ucita predstave.");
            setStatus(Status.FAILURE);
        } else {
            setStatus(Status.SUCCESS);

            List<Predstava> l = new ArrayList(lista);

            predstave = l;
        }
    }

    /**
     * Vraca listu predstava koje predstavljaju rezultat sistemske operacije.
     * @return Sve predstave koje postoje u bazi.
     */
    @Override
    public List vratiListu() {
        return predstave;
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
