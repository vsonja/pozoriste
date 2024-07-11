package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;

/**
 *
 * @author Sonja
 */
public class SOZapamtiGlumca extends OpstaSO {

    public SOZapamtiGlumca() throws Exception {
        super();
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = true;
        if (dbb.daLiPostojiObjekat(odo, UslovWhere.PRIMARNI_KLJUC)) {
            uspesno = dbb.azurirajObjekat(odo, UslovWhere.PRIMARNI_KLJUC);
        } else {
            uspesno = dbb.UbaciNoviObjekat(odo);
        }

        if (uspesno == false) {
            setPoruka("Greška! Glumac nije sačuvan.");
            setStatus(Status.FAILURE);

        } else {
            setPoruka("Glumac je uspešno sačuvan.");
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
