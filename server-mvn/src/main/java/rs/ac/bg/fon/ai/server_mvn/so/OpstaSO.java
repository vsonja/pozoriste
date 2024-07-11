package rs.ac.bg.fon.ai.server_mvn.so;

import rs.ac.bg.fon.ai.common_mvn.constants.Status;
import rs.ac.bg.fon.ai.server_mvn.database.DBBroker;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sonja
 */
public abstract class OpstaSO {

    static DBBroker dbb;
    private String poruka;
    private Status status;
    OpstiDomenskiObjekat odo;

    public OpstaSO() throws Exception {
        dbb = new DBBroker();
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

    private void commit() throws SQLException {
        dbb.commit();
    }

    private void rollback() throws SQLException {
        dbb.disconnect();
    }

    protected abstract void executeOperation(OpstiDomenskiObjekat odo) throws Exception;

    protected abstract void validate(OpstiDomenskiObjekat odo) throws Exception;

    public OpstiDomenskiObjekat vratiODO() {
        return odo;
    }

    public List vratiListu() {
        return null;
    }

}
