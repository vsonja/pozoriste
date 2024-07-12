package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Predstavlja rekvizit koji se koristi u pozoristu.
 * Rekvizit ima rekvizitID, tip, podatak o tome da li je trenutno u upotrebi (zauzet) i za koju predstavu se koristi.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira interfejs Serializable, jer se podaci salju kroz mrezu.
 * 
 * @author Sonja
 * @version 1.0
 */
public class Rekvizit extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Jedinstveni identifikator rekvizita kao Integer.
	 */
    private Integer rekvizitID;
    
    /**
     * Tip rekvizita (sto, stolice i sl.) kao String.
     */
    private String tip;
    
    /**
     * Podatak o tome da li je rekvizit trenutno u upotrebi kao boolean.
     */
    private boolean zauzet;
    
    /**
     * Predstava za koju se rekvizit trenutno koristi kao objekat klase Predstava.
     */
    private Predstava predstava;

    /**
     * Pravi nov objekat klase Rekvizit.
     * 
     * Svi njegovi atributi (rekvizitID, tip, zauzet i predstava) ostaju neinicijalizovani.
     */
    public Rekvizit() {
    }

    /**
     * Pravi novi rekvizit i postavlja sve atribute na unete vrednosti.
     * 
     * @param rekvizitID Jedinstveni identifikator rekvizita kao Integer.
     * @param tip Tip rekvizita kao String.
     * @param zauzet Podatak o tome da li je rekvizit trenutno u upotrebi kao boolean.
     * @param predstava Predstava za koju se rekvizit trenutno koristi kao objekat klase Predstava.
     */
    public Rekvizit(Integer rekvizitID, String tip, boolean zauzet, Predstava predstava) {
        this.rekvizitID = rekvizitID;
        this.tip = tip;
        this.zauzet = zauzet;
        this.predstava = predstava;
    }

    /**
     * Vraca predstavu za koju se rekvizit trenutno koristi.
     * 
     * @return Predstava za koju se rekvizit trenutno koristi kao objekat klase Predstava.
     */
    public Predstava getPredstava() {
        return predstava;
    }

    /**
     * Postavlja vrednost predstave za koju se rekvizit trenutno koristi na unetu vrednost.
     * 
     * @param predstava Predstava za koju se rekvizit trenutno koristi kao objekat klase Predstava.
     */
    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    /**
     * Vraca jedinstveni identifikator rekvizita.
     * 
     * @return Trenutni identifikator rekvizita kao Integer.
     */
    public Integer getRekvizitID() {
        return rekvizitID;
    }

    /**
     * Postavlja jedinstveni identifikator rekvizita na unetu vrednost.
	 * 
     * @param rekvizitID Jedinstveni identifikator rekvizita kao Integer.
     */
    public void setRekvizitID(Integer rekvizitID) {
        this.rekvizitID = rekvizitID;
    }

    /**
     * Vraca tip rekvizita.
     * 
     * @return Tip rekvizita kao String.
     */
    public String getTip() {
        return tip;
    }

    /**
     * Postavlja tip rekvizita na unetu vrednost.
     * 
     * @param tip Tip rekvizita kao String.
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     * Vraca podatak o tome da li je rekvizit trenutno zauzet.
     * 
     * @return Podatak o tome da li je rekvizit trenutno zauzet kao boolean.
     */
    public boolean isZauzet() {
        return zauzet;
    }

    /**
     * Postavlja atribut zauzet na unetu vrednost.
     * 
     * @param zauzet Podatak o tome da li je rekvizit trenutno zauzet.
     */
    public void setZauzet(boolean zauzet) {
        this.zauzet = zauzet;
    }

    @Override
    public String getTableName() {
        return "rekvizit";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
