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
public class Predstava extends OpstiDomenskiObjekat implements Serializable {

    private Integer predstavaID;
    private String nazivPredstave;
    private String opis;
    private List<Uloga> uloge;

    public Predstava() {
        uloge = new ArrayList<>();
    }

    public Predstava(Integer predstavaID, String nazivPredstave, String opis, List<Uloga> uloge) {
        this.predstavaID = predstavaID;
        this.nazivPredstave = nazivPredstave;
        this.opis = opis;
        this.uloge = uloge;
    }

    public List<Uloga> getUloge() {
        return uloge;
    }

    public void setUloge(List<Uloga> uloge) {
        this.uloge = uloge;
    }

    public Integer getPredstavaID() {
        return predstavaID;
    }

    public void setPredstavaID(Integer predstavaID) {
        this.predstavaID = predstavaID;
    }

    public String getNazivPredstave() {
        return nazivPredstave;
    }

    public void setNazivPredstave(String nazivPredstave) {
        this.nazivPredstave = nazivPredstave;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return nazivPredstave;
    }

    @Override
    public String getTableName() {
        return "predstava";
    }

    @Override
    public String getColumnsForInsert() {
        return "nazivPredstave, opis";
    }

    @Override
    public String getParamsForInsert() {
        return "(?, ?)";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat) throws SQLException {
        Predstava predstava = (Predstava) domenskiObjekat;
        statement.setString(1, predstava.getNazivPredstave());
        statement.setString(2, predstava.getOpis());
    }

    @Override
    public void setAutoIncrementPK(int primaryKey) {
        setPredstavaID(primaryKey);
    }

    @Override
    public String getWhereContidion(UslovWhere kriterijum) {
        String where = "";
        switch (kriterijum) {
            case PRIMARNI_KLJUC:
                where += "predstavaID = " + predstavaID;
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
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Predstava(rs.getInt("predstavaID"), rs.getString("nazivPredstave"), rs.getString("opis"), null));
        }
        return lista;
    }

}
