package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Sonja
 */
public abstract class OpstiDomenskiObjekat {

    public abstract String getTableName();

    public abstract String getColumnsForInsert();

    public abstract String getParamsForInsert();

    public abstract void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat) throws SQLException;

    public abstract String getWhereContidion(UslovWhere kriterijum);

    public boolean containsAutoIncrementPK() {
        return true;
    }

    public abstract void setAutoIncrementPK(int primaryKey);

    public abstract String postaviVrednostiAtributa();

    public abstract List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException;

}
