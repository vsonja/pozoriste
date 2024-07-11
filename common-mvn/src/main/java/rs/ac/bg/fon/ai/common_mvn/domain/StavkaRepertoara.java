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
 *
 * @author Sonja
 */
public class StavkaRepertoara extends OpstiDomenskiObjekat implements Serializable {

    private Integer stavkaRepertoaraID;
    private Repertoar repertoar;
    private LocalDate datum;
    private LocalTime vremePocetka;
    private LocalTime vremeZavrsetka;
    private Predstava predstava;
    Sala sala;

    public StavkaRepertoara() {
    }

    public StavkaRepertoara(Integer stavkaRepertoaraID, Repertoar repertoar, LocalDate datum, LocalTime vremePocetka, LocalTime vremeZavrsetka, Predstava predstava, Sala sala) {
        this.stavkaRepertoaraID = stavkaRepertoaraID;
        this.repertoar = repertoar;
        this.datum = datum;
        this.vremePocetka = vremePocetka;
        this.vremeZavrsetka = vremeZavrsetka;
        this.predstava = predstava;
        this.sala = sala;
    }

    public Predstava getPredstava() {
        return predstava;
    }

    public void setPredstava(Predstava predstava) {
        this.predstava = predstava;
    }

    public Repertoar getRepertoar() {
        return repertoar;
    }

    public void setRepertoar(Repertoar repertoar) {
        this.repertoar = repertoar;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Integer getStavkaRepertoaraID() {
        return stavkaRepertoaraID;
    }

    public void setStavkaRepertoaraID(Integer stavkaRepertoaraID) {
        this.stavkaRepertoaraID = stavkaRepertoaraID;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalTime getVremeZavrsetka() {
        return vremeZavrsetka;
    }

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
