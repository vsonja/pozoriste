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
public class Uloga extends OpstiDomenskiObjekat implements Serializable {

    private Glumac glumac;
    private Predstava predstava;
    private String nazivUloge;

    public Uloga() {
    }

    public Uloga(Glumac glumac, Predstava predstava, String nazivUloge) {
        this.glumac = glumac;
        this.predstava = predstava;
        this.nazivUloge = nazivUloge;
    }

    public String getNazivUloge() {
        return nazivUloge;
    }

    public void setNazivUloge(String nazivUloge) {
        this.nazivUloge = nazivUloge;
    }

    public Glumac getGlumac() {
        return glumac;
    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }

    public Predstava getPredstava() {
        return predstava;
    }

    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    @Override
    public String getTableName() {
        return "uloga";
    }

    @Override
    public String getColumnsForInsert() {
        return "glumacID, predstavaID, nazivUloge";
    }

    @Override
    public String getParamsForInsert() {
        return "(?, ?, ?)";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat) throws SQLException {
        Uloga uloga = (Uloga) domenskiObjekat;
        statement.setInt(1, uloga.getGlumac().getGlumacID());
        statement.setInt(2, uloga.getPredstava().getPredstavaID());
        statement.setString(3, uloga.getNazivUloge());
    }

    @Override
    public boolean containsAutoIncrementPK() {
        return false;
    }

    @Override
    public void setAutoIncrementPK(int primaryKey) {

    }

    @Override
    public String getWhereContidion(UslovWhere kriterijum) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
