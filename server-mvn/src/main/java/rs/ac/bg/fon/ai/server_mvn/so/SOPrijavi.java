package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Korisnik;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;

/**
 *
 * @author Sonja
 */
public class SOPrijavi extends OpstaSO {

    private Korisnik korisnik;

    public SOPrijavi() throws Exception {
        super();
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = dbb.daLiPostojiObjekat(odo, UslovWhere.PRIJAVA);

        if (uspesno) {
            setStatus(Status.SUCCESS);
        } else {
            setPoruka("Pogrešno korisničko ime ili šifra!");
            setStatus(Status.FAILURE);
        }
    }

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
