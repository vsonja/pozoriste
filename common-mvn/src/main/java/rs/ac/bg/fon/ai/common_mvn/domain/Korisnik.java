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
public class Korisnik extends OpstiDomenskiObjekat implements Serializable {

    private Integer korisnikID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    private String klijentIP;
    private String vreme;

    public Korisnik() {
    }

    public Korisnik(Integer korisnikID, String ime, String prezime, String korisnickoIme, String sifra) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Korisnik(Integer korisnikID, String korisnickoIme, String sifra) {
        this.korisnikID = korisnikID;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Integer getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Integer korisnikID) {
        this.korisnikID = korisnikID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getKlijentIP() {
        return klijentIP;
    }

    public void setKlijentIP(String klijentIP) {
        this.klijentIP = klijentIP;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    @Override
    public String getTableName() {
        return "korisnik";
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
                where += "korisnikid = " + korisnikID;
                break;
            case PRIJAVA:
                where += "korisnickoIme = '" + (korisnickoIme == null ? null : korisnickoIme) + "' AND " + "sifra = '" + (sifra == null ? null : sifra) + "'";
                break;
        }

        return where;
    }

    @Override
    public void setAutoIncrementPK(int primaryKey) {
        setKorisnikID(primaryKey);
    }

    @Override
    public String postaviVrednostiAtributa() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Korisnik(rs.getInt("korisnikID"), rs.getString("korisnickoIme"),
                    rs.getString("sifra")));

        }
        return lista;
    }

}
