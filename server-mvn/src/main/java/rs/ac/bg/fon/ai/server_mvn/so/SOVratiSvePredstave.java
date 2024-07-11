package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class SOVratiSvePredstave extends OpstaSO {

    List<Predstava> predstave;

    public SOVratiSvePredstave() throws Exception {
        super();
        predstave = new ArrayList<>();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        List<OpstiDomenskiObjekat> lista = dbb.vratiSveObjekteBezUslova(odo);

        if (lista == null || lista.isEmpty()) {
            setPoruka("Greška! Sistem ne može da učita predstave.");
            setStatus(Status.FAILURE);
        } else {
            setStatus(Status.SUCCESS);

            List<Predstava> l = new ArrayList(lista);

            predstave = l;
        }
    }

    @Override
    public List vratiListu() {
        return predstave;
    }

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
