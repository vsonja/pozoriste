package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class Repertoar extends OpstiDomenskiObjekat implements Serializable {

    private Integer repertoarID;
    private int mesec;
    private int godina;
    List<StavkaRepertoara> stavke;

    public Repertoar() {
    }

    public Repertoar(Integer repertoarID, int mesec, int godina, List<StavkaRepertoara> stavke) {
        this.repertoarID = repertoarID;
        this.mesec = mesec;
        this.godina = godina;
        this.stavke = stavke;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public Integer getRepertoarID() {
        return repertoarID;
    }

    public void setRepertoarID(Integer repertoarID) {
        this.repertoarID = repertoarID;
    }

    public int getMesec() {
        return mesec;
    }

    public void setMesec(int mesec) {
        this.mesec = mesec;
    }

    public List<StavkaRepertoara> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRepertoara> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String getTableName() {
        return "repertoar";
    }

    @Override
    public String getColumnsForInsert() {
        return "mesec, godina";
    }

    @Override
    public String getParamsForInsert() {
        return "(?, ?)";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat) throws SQLException {
        Repertoar repertoar = (Repertoar) domenskiObjekat;
        statement.setInt(1, repertoar.getMesec());
        statement.setInt(2, repertoar.getGodina());
    }

    @Override
    public void setAutoIncrementPK(int primaryKey) {
        setRepertoarID(primaryKey);
    }

    @Override
    public String getWhereContidion(UslovWhere kriterijum) {
        String where = "";
        switch (kriterijum) {
            case MESEC_I_GODINA:
                where += "mesec = " + mesec + " AND godina = " + godina;
                break;
        }
        return where;
    }

    @Override
    public String postaviVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
