package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.server_mvn.database.DBBroker;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.List;

/**
 * Apstraktna klasa koja predstavlja opstu sistemsku operaciju, odnosno definise kostur njenog izvrsavanja.
 * 
 * Sve sistemske operacije su izvedene iz ove klase.
 *
 * @author Sonja
 * @version 1.0
 */
public abstract class OpstaSO {

	/**
	 * Broker baze podataka koji vrsi komunikaciju sa bazom i u kom se nalaze svi upiti.
	 */
    static DBBroker dbb;
    
    /**
     * Poruka za detljiniji opis ishoda operacije kao String.
     */
    private String poruka;
    
    /**
     * Status izvrsenja operacije, moze biti SUCCESS ili FAILURE.
     */
    private Status status;
    
    /**
     * Objekat nad kojim se izvrsava operacija kao OpstiDomenskiObjekat.
     */
    OpstiDomenskiObjekat odo;

    /**
     * Neparametarski konstruktor koji inicijalizuje brokera baze podataka.
     */
    public OpstaSO() {
        dbb = new DBBroker();
    }

    /**
     * Vraca poruku operacije.
     * 
     * @return Poruka operacije kao String.
     */
    public String getPoruka() {
        return poruka;
    }

    /**
     * Postavlja poruku na unetu vrednost.
     * 
     * @param poruka Poruka operacije kao String.
     */
    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    /**
     * Vraca status izvrsenja operacije.
     * 
     * @return Status izvrsenja operacije kao Enum Status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Postavlja status na unetu vrednost.
     * 
     * @param status Status izvrsenja operacije kao Enum Status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Opsti nacin izvrsavanja sistemskih operacija.
     * 
     * @param odo Objekat nad kojim ce se vrsiti operacija kao OpstiDomenskiObjekat.
     * @throws Exception U slucaju greske prilikom izvrsavanja sistemske operacije.
     */
    public void execute(OpstiDomenskiObjekat odo) throws Exception {
        try {
            this.odo = odo;
            dbb.connect();
            validate(odo);
            executeOperation(odo);
            commit();
        } catch (Exception ex) {
            rollback();
            throw ex;
        }
    }

    /**
     * Metoda za potvrdjivanje uspesne transakcije.
     * 
     * @throws SQLException U slucaju greske tokom izvrsenja operacije commit nad bazom podataka.
     */
    private void commit() throws SQLException {
        dbb.commit();
    }

    /**
     * Metoda za ponistenje transakcije.
     * 
     * @throws SQLException U slucaju greske tokom izvrsenja operacije rollback nad bazom podataka.
     */
    private void rollback() throws SQLException {
        dbb.rollback();
    }

    /**
     * Metoda za izvrsenje konkretne sistemske operacije.
     * 
     * @param odo Objekat nad kojim ce se vrsiti operacija kao OpstiDomenskiObjekat.
     * @throws Exception U slucaju greske tokom izvrsavanja operacije.
     */
    protected abstract void executeOperation(OpstiDomenskiObjekat odo) throws Exception;

    /**
     * Metoda za validaciju prosledjenog objekta i preduslova za izvrsenje sistemske operacije.
     * 
     * @param odo Objekat nad kojim ce se vrsiti operacija kao OpstiDomenskiObjekat.
     * @throws Exception U slucaju greske tokom izvrsavanja operacije.
     */
    protected abstract void validate(OpstiDomenskiObjekat odo) throws Exception;

    /**
     * Metoda koja vraca rezultat izvrsenja sistemske operacije kao OpstiDomenskiObjekat.
     * 
     * @return Objekat koji je rezultat izvrsenja sistemske operacije kao OpstiDomenskiObjekat.
     */
    public OpstiDomenskiObjekat vratiODO() {
        return odo;
    }

    /**
     * Metoda koja se koristi kada lista objekata predstavlja rezultat sistemske operacije.
     * 
     * @return Lista objekata koji su rezultat izvrsenja sistemske operacije.
     */
    public List vratiListu() {
        return null;
    }

}
