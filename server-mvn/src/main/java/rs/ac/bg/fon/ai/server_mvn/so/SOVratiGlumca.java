package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;

/**
 *
 * @author Sonja
 */
public class SOVratiGlumca extends OpstaSO {

    UslovWhere uslov;

    public SOVratiGlumca(UslovWhere uslov) throws Exception {
        super();
        this.uslov = uslov;
    }

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
