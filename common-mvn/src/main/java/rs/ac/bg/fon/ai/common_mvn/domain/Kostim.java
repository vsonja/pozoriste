package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class Kostim extends OpstiDomenskiObjekat implements Serializable {

    private Integer kostimID;
    private String tip;
    private String velicina;
    private boolean zauzet;
    private Predstava predstava;

    public Kostim() {
    }

    public Kostim(Integer kostimID, String tip, String velicina, boolean zauzet, Predstava predstava) {
        this.kostimID = kostimID;
        this.tip = tip;
        this.velicina = velicina;
        this.zauzet = zauzet;
        this.predstava = predstava;
    }

    public Predstava getPredstava() {
        return predstava;
    }

    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    public Integer getKostimID() {
        return kostimID;
    }

    public void setKostimID(Integer kostimID) {
        this.kostimID = kostimID;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getVelicina() {
        return velicina;
    }

    public void setVelicina(String velicina) {
        this.velicina = velicina;
    }

    public boolean isZauzet() {
        return zauzet;
    }

    public void setZauzet(boolean zauzet) {
        this.zauzet = zauzet;
    }

    @Override
    public String toString() {
        return kostimID + " - " + tip + " (" + velicina + ")";
    }

    @Override
    public String getTableName() {
        return "kostim";
    }

    @Override
    public String getColumnsForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getParamsForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getWhereContidion(UslovWhere kriterijum) {
        String where = "";
        switch (kriterijum) {
            case PRIMARNI_KLJUC:
                where += "kostimID = " + kostimID;
                break;
        }

        return where;
    }

    @Override
    public void setAutoIncrementPK(int primaryKey) {
        setKostimID(primaryKey);
    }

    @Override
    public String postaviVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Predstava p = new Predstava();
            p.setPredstavaID(rs.getInt("predstavaID"));
            lista.add(new Kostim(rs.getInt("kostimID"), rs.getString("tip"), rs.getString("velicina"), rs.getBoolean("zauzet"), p));
        }
        return lista;
    }

}
