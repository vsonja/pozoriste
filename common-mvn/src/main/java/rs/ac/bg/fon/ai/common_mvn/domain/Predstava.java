package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja predstavu koja je na repertoaru pozorista.
 * Predstava ima predstavaID, naziv, opis i listu uloga.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira interfejs Serializable, jer se podaci salju kroz mrezu.
 *
 * @author Sonja
 * @version 1.0
 */
public class Predstava extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Jedinstveni identifikator predstave kao Integer.
	 */
    private Integer predstavaID;
    
    /**
     * Naziv predstave kao String.
     */
    private String nazivPredstave;
    
    /**
     * Opis predstave kao String.
     */
    private String opis;
    
    /**
     * Uloge u predstavi kao lista objekata klase Uloga.
     */
    private List<Uloga> uloge;

    /**
     * Pravi nov objekat klase Predstava.
     * 
     * Svi njegovi atributi (predstavaID, nazivPredstave, opis) osim liste uloga ostaju neinicijalizovani.
     */
    public Predstava() {
        uloge = new ArrayList<>();
    }

    /**
     * Pravi novu predstavu i postavlja jedinstveni identifikator predstave, naziv, opis i uloge na unete vrednosti.
     * 
     * @param predstavaID Jedinstveni identifikator predstave kao Integer.
     * @param nazivPredstave Naziv predstave kao String.
     * @param opis Opis predstave kao String.
     * @param uloge Uloge u predstavi kao lista objekata klase Uloga.
     */
    public Predstava(Integer predstavaID, String nazivPredstave, String opis, List<Uloga> uloge) {
        this.predstavaID = predstavaID;
        this.nazivPredstave = nazivPredstave;
        this.opis = opis;
        this.uloge = uloge;
    }
    
    /**
     * Vraca listu uloga u predstavi.
     * 
     * @return Trenutne uloge u predstavi kao lista objekata klase Uloga.
     */
    public List<Uloga> getUloge() {
        return uloge;
    }

    /**
     * Postavlja listu uloga u predstavi na unetu vrednost.
     * 
     * @param uloge Lista objekata klase Uloga.
     */
    public void setUloge(List<Uloga> uloge) {
        this.uloge = uloge;
    }

    /**
     * Vraca jedinstveni identifikator predstave.
     * 
     * @return Trenutni identifikator predstave kao Integer.
     */
    public Integer getPredstavaID() {
        return predstavaID;
    }

    /**
     * Postavlja jedinstveni identifikator predstave na unetu vrednost.
	 * 
     * @param predstavaID Jedinstveni identifikator predstave kao Integer.
     */
    public void setPredstavaID(Integer predstavaID) {
        this.predstavaID = predstavaID;
    }

    /**
     * Vraca naziv predstave.
     * 
     * @return Trenutni naziv predstave kao String.
     */
    public String getNazivPredstave() {
        return nazivPredstave;
    }

    /**
     * Postavlja naziv predstave na unetu vrednost.
     * 
     * @param nazivPredstave Naziv predstave kao String.
     */
    public void setNazivPredstave(String nazivPredstave) {
        this.nazivPredstave = nazivPredstave;
    }

    /**
     * Vraca opis predstave.
     * 
     * @return Trenutni opis predstave kao String.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis predstave na unetu vrednost.
     * 
     * @param opis Opis predstave kao String.
     */
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
