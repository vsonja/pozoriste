package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Sala;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class SOVratiSveSale extends OpstaSO {

    List<Sala> sale;

    public SOVratiSveSale() throws Exception {
        super();
        sale = new ArrayList<>();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> lista = dbb.vratiSveObjekteBezUslova(odo);

        if (lista == null || lista.isEmpty()) {
            setPoruka("Greška! Sistem ne može da učita sale.");
            setStatus(Status.FAILURE);
        } else {
            setStatus(Status.SUCCESS);

            List<Sala> l = new ArrayList(lista);

            sale = l;
        }
    }

    @Override
    public List vratiListu() {
        return sale;
    }

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
