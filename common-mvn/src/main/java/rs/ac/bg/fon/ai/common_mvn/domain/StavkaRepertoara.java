package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Predstavlja stavku repertoara, odnosno konkretno izvodjenje predstave.
 * Stavka repertoara ima stavkaRepertoaraID, repertoar kom pripada, datum, vreme pocetka, vreme zavrsetka i predstavu koja se izvodi.
 *
 * @author Sonja
 * @version 1.0
 */
public class StavkaRepertoara extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Jedinstveni identifikator stavke repertoara kao Integer.
	 */
    private Integer stavkaRepertoaraID;
    
    /**
     * Repertoar kom stavka pripada kao objekat klase Repertoar.
     */
    private Repertoar repertoar;
    
    /**
     * Datum konkretnog izvodjenja predstave kao LocalDate.
     */
    private LocalDate datum;
    
    /**
     * Vreme pocetka konkretnog izvodjenja predstave kao LocalTime.
     */
    private LocalTime vremePocetka;
    
    /**
     * Vreme zavrsetka konkretnog izvodjenja predstave kao LocalTime.
     */
    private LocalTime vremeZavrsetka;
    
    /**
     * Predstava koja se izvodi kao objekat klase Predstava.
     */
    private Predstava predstava;
    
    /**
     * Sala u kojoj se izvodi predstava kao objekat klase Sala.
     */
    private Sala sala;

    /**
     * Pravi nov objekat klase StavkaRepertoara.
     * 
     * Svi njegovi atributi ostaju neinicijalizovani.
     */
    public StavkaRepertoara() {
    }

    /**
     * Pravi nov objekat klase StavkaRepertoara i postavlja vrednosti svih atributa.
     * 
     * @param stavkaRepertoaraID Jedinstveni identifikator stavke repertoara kao Integer.
     * @param repertoar Repertoar kom stavka pripada kao objekat klase Repertoar.
     * @param datum Datum konkretnog izvodjenja predstave kao LocalDate.
     * @param vremePocetka Vreme pocetka konkretnog izvodjenja predstave kao LocalTime.
     * @param vremeZavrsetka Vreme zavrsetka konkretnog izvodjenja predstave kao LocalTime.
     * @param predstava Predstava koja se izvodi kao objekat klase Predstava.
     * @param sala Sala u kojoj se izvodi predstava kao objekat klase Sala.
     */
    public StavkaRepertoara(Integer stavkaRepertoaraID, Repertoar repertoar, LocalDate datum, LocalTime vremePocetka, LocalTime vremeZavrsetka, Predstava predstava, Sala sala) {
        this.stavkaRepertoaraID = stavkaRepertoaraID;
        this.repertoar = repertoar;
        this.datum = datum;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.predstava = predstava;
        this.sala = sala;
    }

    /**
     * Vraca predstavu na koju se odnosi stavka repertoara.
     * 
     * @return Predstava na koju se odnosi stavka repertoara kao objekat klase Predstava.
     */
    public Predstava getPredstava() {
        return predstava;
    }

    /**
     * Postavlja vrednost predstave na koju se odnosi stavka repertoara na unetu vrednost.
     * 
     * @param predstava Predstava na koju se odnosi stavka repertoara kao objekat klase Predstava.
     */
    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    /**
     * Vraca repertoar kom pripada stavka repertoara.
     * 
     * @return Repertoar kom pripada stavka repertoara kao objekat klase Repertoar.
     */
    public Repertoar getRepertoar() {
        return repertoar;
    }

    /**
     * Postavlja vrednost repertoar kom pripada stavka repertoara na unetu vrednost.
     * 
     * @param repertoar Repertoar kom pripada stavka repertoara kao objekat klase Repertoar.
     */
    public void setRepertoar(Repertoar repertoar) {
        this.repertoar = repertoar;
    }

    /**
     * Vraca salu u kojoj se izvodi predstava.
     * 
     * @return Sala u kojoj se izvodi predstava kao objekat klase Sala.
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * Postavlja salu na unetu vrednost.
     * 
     * @param sala Sala u kojoj se izvodi predstava kao objekat klase Sala.
     */
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /**
     * Vraca jedinstveni identifikator stavke repertoara.
     * 
     * @return Trenutni identifikator stavke repertoara kao Integer.
     */
    public Integer getStavkaRepertoaraID() {
        return stavkaRepertoaraID;
    }
    
    /**
     * Postavlja jedinstveni identifikator stavke repertoara na unetu vrednost.
	 * 
     * @param stavkaRepertoaraID Jedinstveni identifikator stavke repertoara kao Integer.
     */
    public void setStavkaRepertoaraID(Integer stavkaRepertoaraID) {
        this.stavkaRepertoaraID = stavkaRepertoaraID;
    }

    /**
     * Vraca datum konkretnog izvodjenja predstave.
     * 
     * @return Datum konkretnog izvodjenja predstave kao LocalDate.
     */
    public LocalDate getDatum() {
        return datum;
    }

    /**
     * Postavlja datum na unetu vrednost.
     * 
     * @param datum datum konkretnog izvodjenja predstave kao LocalDate.
     */
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    /**
     * Vraca vreme pocetka konkretnog izvodjenja predstave.
     * 
     * @return Vreme pocetka konkretnog izvodjenja predstave kao LocalTime.
     */
    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    /**
     * Postavlja vreme pocetka na unetu vrednost.
     * 
     * @param vremePocetka Vreme pocetka konkretnog izvodjenja predstave kao LocalTime.
     */
    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    /**
     * Vraca vreme zavrsetka konkretnog izvodjenja predstave.
     * 
     * @return Vreme zavrsetka konkretnog izvodjenja predstave kao LocalTime.
     */
    public LocalTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

    /**
     * Postavlja vreme zavrsetka na unetu vrednost.
     * 
     * @param vremeZavrsetka Vreme zavrsetka konkretnog izvodjenja predstave kao LocalTime.
     */
    public void setVremeZavrsetka(LocalTime vremeZavrsetka) {
        this.vremeZavrsetka = vremeZavrsetka;
    }

    @Override
    public String getTableName() {
        return "stavkarepertoara";
    }

    @Override
    public String getColumnsForInsert() {
        return "repertoarID, datum, vremePocetka, vremeZavrsetka, predstavaID, salaID";
    }

    @Override
    public String getParamsForInsert() {
        return "(?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat) throws SQLException {
        StavkaRepertoara stavka = (StavkaRepertoara) domenskiObjekat;
        statement.setInt(1, stavka.getRepertoar().getRepertoarID());
        statement.setDate(2, Date.valueOf(datum).valueOf(stavka.getDatum()));
        statement.setTime(3, java.sql.Time.valueOf(stavka.getVremePocetka()));
        statement.setTime(4, java.sql.Time.valueOf(stavka.getVremeZavrsetka()));
        statement.setInt(5, stavka.getPredstava().getPredstavaID());
        statement.setInt(6, stavka.getSala().getSalaID());
    }

    @Override
    public void setAutoIncrementPK(int primaryKey) {
        setStavkaRepertoaraID(primaryKey);
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
