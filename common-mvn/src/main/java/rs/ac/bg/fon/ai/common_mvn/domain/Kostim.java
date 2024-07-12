package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja kostim koji se koristi u pozoristu.
 * Kostim ima kostimID, tip, velicinu, podatak o tome da li je trenutno u upotrebi (zauzet) i za koju predstavu se koristi.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira interfejs Serializable, jer se podaci salju kroz mrezu.
 * 
 * @author Sonja
 * @version 1.0
 */
public class Kostim extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Jedinstveni identifikator kostima kao Integer.
	 */
    private Integer kostimID;
    
    /**
     * Tip kostima (haljina, odelo i sl.) kao String.
     */
    private String tip;
    
    /**
     * Velicina kostima kao String (slovna oznaka ili broj).
     */
    private String velicina;
    
    /**
     * Podatak o tome da li je kostim trenutno u upotrebi kao boolean.
     */
    private boolean zauzet;
    
    /**
     * Predstava za koju se kostim trenutno koristi kao objekat klase Predstava.
     */
    private Predstava predstava;

    /**
     * Pravi nov objekat klase Kostim.
     * 
     * Svi njegovi atributi (kostimID, tip, velicina, zauzet i predstava) ostaju neinicijalizovani.
     */
    public Kostim() {
    }

    /**
     * Pravi novi kostim i postavlja inicijalizuje sve atribute na unete vrednosti.
     * 
     * @param kostimID Jedinstveni identifikator kostima kao Integer.
     * @param tip Tip kostima kao String.
     * @param velicina Velicina kostima kao String.
     * @param zauzet Podatak o tome da li je kostim trenutno u upotrebi kao boolean.
     * @param predstava Predstava za koju se kostim trenutno koristi kao objekat klase Predstava.
     */
    public Kostim(Integer kostimID, String tip, String velicina, boolean zauzet, Predstava predstava) {
        this.kostimID = kostimID;
        this.tip = tip;
        this.velicina = velicina;
        this.zauzet = zauzet;
        this.predstava = predstava;
    }

    /**
     * Vraca predstavu za koju se kostim trenutno koristi.
     * 
     * @return Predstava za koju se kostim trenutno koristi kao objekat klase Predstava.
     */
    public Predstava getPredstava() {
        return predstava;
    }

    /**
     * Postavlja vrednost predstave za koju se kostim trenutno koristi na unetu vrednost.
     * 
     * @param predstava Predstava za koju se kostim trenutno koristi kao objekat klase Predstava.
     */
    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    /**
     * Vraca jedinstveni identifikator kostima.
     * 
     * @return Trenutni identifikator kostima kao Integer.
     */
    public Integer getKostimID() {
        return kostimID;
    }

    /**
     * Postavlja jedinstveni identifikator kostima na unetu vrednost.
	 * 
     * @param glumacID Jedinstveni identifikator kostima kao Integer.
     */
    public void setKostimID(Integer kostimID) {
        this.kostimID = kostimID;
    }

    /**
     * Vraca tip kostima.
     * 
     * @return Tip kostima kao String.
     */
    public String getTip() {
        return tip;
    }

    /**
     * Postavlja tip kostima na unetu vrednost.
     * 
     * @param tip Tip kostima kao String.
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     * Vraca velicinu kostima.
     * 
     * @return Velicina kostima kao String.
     */
    public String getVelicina() {
        return velicina;
    }

    /**
     * Postavlja velicinu kostima na unetu vrednost.
     * 
     * @param velicina Velicina kostima kao String.
     */
    public void setVelicina(String velicina) {
        this.velicina = velicina;
    }

    /**
     * Vraca podatak o tome da li je kostim trenutno zauzet.
     * 
     * @return Podatak o tome da li je kostim trenutno zauzet kao boolean.
     */
    public boolean isZauzet() {
        return zauzet;
    }

    /**
     * Postavlja atribut zauzet na unetu vrednost.
     * 
     * @param zauzet Podatak o tome da li je kostim trenutno zauzet.
     */
    public void setZauzet(boolean zauzet) {
        this.zauzet = zauzet;
    }

    /**
     * Vraca String reprezentaciju objekta klase Kostim.
     * 
     * @return String reprezentacija objekta klase Kostim u formatu "kostimID - tip (velicina)".
     */
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
