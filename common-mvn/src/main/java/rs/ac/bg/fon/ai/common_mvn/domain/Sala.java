package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja salu u kojoj se izvodi predstava.
 * Sala ima salaID, scenu i kapacitet.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira interfejs Serializable, jer se podaci salju kroz mrezu.
 *
 * @author Sonja
 */
public class Sala extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Jedinstveni identifikator sale kao Integer.
	 */
    private Integer salaID;
    
    /**
	 * Scena koja pripada sali kao String.
	 */
    private String scena;
    
    /**
     * Broj sedista u sali kao Integer.
     */
    private Integer kapacitet;

    /**
     * Pravi nov objekat klase Sala.
     * 
     * Svi njegovi atributi (salaID, scena i kapacitet) ostaju neinicijalizovani.
     */
    public Sala() {
    }

    /**
     * Pravi novu salu i postavlja sve atribute na unete vrednosti.
     * 
     * @param salaID Jedinstveni identifikator sale kao Integer.
     * @param scena Scena koja pripada sali kao String.
     * @param kapacitet Broj sedista u sali kao Integer.
     */
    public Sala(Integer salaID, String scena, Integer kapacitet) {
        this.salaID = salaID;
        this.scena = scena;
        this.kapacitet = kapacitet;
    }

    /**
     * Vraca jedinstveni identifikator sale.
     * 
     * @return Trenutni identifikator sale kao Integer.
     */
    public Integer getSalaID() {
        return salaID;
    }

    /**
     * Postavlja jedinstveni identifikator sale na unetu vrednost.
     * 
     * @param salaID Jedinstveni identifikator sale kao Integer.
     */
    public void setSalaID(Integer salaID) {
        this.salaID = salaID;
    }

    /**
     * Vraca scenu koja pripada sali.
     * 
     * @return Scena koja pripada sali kao String.
     */
    public String getScena() {
        return scena;
    }

    /**
     * Postavlja scenu koja pripada sali na unetu vrednost.
     * @param scena Scena koja pripada sali kao String.
     */
    public void setScena(String scena) {
        this.scena = scena;
    }

    /**
     * Vraca kapacitet sale kao Integer.
     * 
     * @return Broj sedista u sali kao Integer.
     */
    public Integer getKapacitet() {
        return kapacitet;
    }

    /**
     * Postavlja kapacitet sale na unetu vrednost.
     * 
     * @param kapacitet Broj sedista u sali kao Integer.
     */
    public void setKapacitet(Integer kapacitet) {
        this.kapacitet = kapacitet;
    }

    /**
     * Vraca String reprezentaciju objekta klase Sala.
     * 
     * @return String reprezentacija objekta klase Sala u formatu "scena - kapacitet".
     */
    @Override
    public String toString() {
        return scena + " - " + kapacitet;
    }

    @Override
    public String getTableName() {
        return "sala";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setAutoIncrementPK(int primaryKey) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String postaviVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Sala s = new Sala();
            lista.add(new Sala(rs.getInt("salaID"), rs.getString("scena"), rs.getInt("kapacitet")));
        }
        return lista;
    }

}
