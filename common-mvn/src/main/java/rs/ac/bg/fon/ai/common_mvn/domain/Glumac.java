package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sonja
 */
public class Glumac extends OpstiDomenskiObjekat implements Serializable {

    private Integer glumacID;
    private String ime;
    private String prezime;
    private String telefon;

    public Glumac() {
    }

    public Glumac(Integer glumacID, String ime, String prezime, String telefon) {
        this.glumacID = glumacID;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
    }

    public Glumac(String ime, String prezime, String telefon) {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
    }

    public Integer getGlumacID() {
        return glumacID;
    }

    public void setGlumacID(Integer glumacID) {
        this.glumacID = glumacID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getTableName() {
        return "glumac";
    }

    @Override
    public String getColumnsForInsert() {
        return "ime, prezime, telefon";
    }

    @Override
    public String getParamsForInsert() {
        return "(?, ?, ?)";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat) throws SQLException {
        Glumac glumac = (Glumac) domenskiObjekat;
        statement.setString(1, glumac.getIme());
        statement.setString(2, glumac.getPrezime());
        statement.setString(3, glumac.getTelefon());
    }

    @Override
    public void setAutoIncrementPK(int primaryKey) {
        setGlumacID(primaryKey);
    }

    @Override
    public String getWhereContidion(UslovWhere kriterijum) {
        String where = "";
        switch (kriterijum) {
            case PRIMARNI_KLJUC:
                where += "glumacID = " + glumacID;
                break;
        }

        return where;
    }

    @Override
    public String postaviVrednostiAtributa() {
        return "ime = '" + ime + "'" + ", prezime = '" + prezime + "', telefon = '" + telefon + "'";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Glumac g = new Glumac();
            lista.add(new Glumac(rs.getInt("glumacID"), rs.getString("ime"), rs.getString("prezime"), rs.getString("telefon")));
        }
        return lista;
    }
    
}
