package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;

/**
 * Predstavlja sistemsku operaciju za cuvanje glumca.
 * 
 * Ova klasa nasledjuje opstu sistemsku operaciju (OpstaSO).
 *
 * @author Sonja
 * @version 1.0
 */
public class SOZapamtiGlumca extends OpstaSO {

    public SOZapamtiGlumca() throws Exception {
        super();
    }

    /**
     * Poziva se metoda koja proverava da li primarni kljuc prosledjenog objekta postoji u bazi.
     * U slucaju da postoji poziva se operacija za azuriranje glumca, a u suprotnom se kreira novi glumac.
     */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        boolean uspesno = true;
        if (dbb.daLiPostojiObjekat(odo, UslovWhere.PRIMARNI_KLJUC)) {
            uspesno = dbb.azurirajObjekat(odo, UslovWhere.PRIMARNI_KLJUC);
        } else {
            uspesno = dbb.UbaciNoviObjekat(odo);
        }

        if (uspesno == false) {
            setPoruka("Greska! Glumac nije sacuvan.");
            setStatus(Status.FAILURE);

        } else {
            setPoruka("Glumac je uspesno sacuvan.");
            setStatus(Status.SUCCESS);
        }
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
