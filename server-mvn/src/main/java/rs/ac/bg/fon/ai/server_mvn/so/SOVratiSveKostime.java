package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Kostim;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class SOVratiSveKostime extends OpstaSO {

    List<Kostim> kostimi;

    public SOVratiSveKostime() throws Exception {
        super();
        kostimi = new ArrayList<>();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> lista = dbb.vratiSveObjekteBezUslova(odo);

        if (lista == null || lista.isEmpty()) {
            setPoruka("Greška! Sistem ne može da učita kostime.");
            setStatus(Status.FAILURE);
        } else {
            setStatus(Status.SUCCESS);

            List<Kostim> l = new ArrayList(lista);

            kostimi = l;
        }
    }

    @Override
    public List vratiListu() {
        return kostimi;
    }

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
