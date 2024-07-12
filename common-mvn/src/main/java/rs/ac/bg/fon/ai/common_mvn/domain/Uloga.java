package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Predstavlja ulogu u konkretnoj predstavi.
 * Uloga ima glumca koji je igra, predstavu kojoj pripada i naziv.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira interfejs Serializable, jer se podaci salju kroz mrezu.
 *
 * @author Sonja
 * @version 1.0
 */
public class Uloga extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Glumac koji igra ulogu kao objekat klase Glumac.
	 */
    private Glumac glumac;
    
    /**
     * Predstava kojoj uloga pripada kao objekat klase Predstava.
     */
    private Predstava predstava;
    
    /**
     * Naziv uloge kao String.
     */
    private String nazivUloge;

    /**
     * Pravi nov objekat klase Uloga.
     * 
     * Svi njegovi atributi (glumac, predstava i nazivUloge) ostaju neinicijalizovani.
     */
    public Uloga() {
    }

    /**
     * Pravi novu ulogu i postavlja sve atribute na unete vrednosti.
     * 
     * @param glumac Glumac koji igra ulogu kao objekat klase Glumac.
     * @param predstava Predstava kojoj uloga pripada kao objekat klase Predstava.
     * @param nazivUloge Naziv uloge kao String.
     */
    public Uloga(Glumac glumac, Predstava predstava, String nazivUloge) {
        this.glumac = glumac;
        this.predstava = predstava;
        this.nazivUloge = nazivUloge;
    }

    /**
     * Vraca naziv uloge.
     * 
     * @return Trenutni naziv uloge kao String.
     */
    public String getNazivUloge() {
        return nazivUloge;
    }

    /**
     * Postavlja naziv uloge na unetu vrednost.
     * 
     * @param nazivUloge Naziv uloge kao String.
     */
    public void setNazivUloge(String nazivUloge) {
        this.nazivUloge = nazivUloge;
    }

    /**
     * Vraca glumca koji igra ulogu.
     * 
     * @return Glumac koji igra ulogu kao objekat klase Glumac.
     */
    public Glumac getGlumac() {
        return glumac;
    }

    /**
     * Postavlja glumca na unetu vrednost.
     * 
     * @param glumac Glumac koji igra ulogu kao objekat klase Glumac.
     */
    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }

    /**
     * Vraca predstavu kojoj uloga priprada.
     * 
     * @return Predstava kojoj uloga pripada kao objekat klase Predstava.
     */
    public Predstava getPredstava() {
        return predstava;
    }

    /**
     * Postavlja predstavu na unetu vrednost.
     * 
     * @param predstava Predstava kojoj uloga pripada kao objekat klase Predstava.
     */
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
