package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja korisnika klijentske aplikacije, u ovom slucaju organizatora umetnickog programa.
 * Korisnik ima korisnikID, ime, prezime, korisnicko ime, sifru, IP adresu sa koje se prijavio na sistem i tacno vreme prijave.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira interfejs Serializable, jer se podaci salju kroz mrezu.
 *
 * @author Sonja
 * @version 1.0
 */
public class Korisnik extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Jedinstveni identifikator korisnika kao Integer.
	 */
    private Integer korisnikID;
    
    /**
     * Ime korisnika kao String.
     */
    private String ime;
    
    /**
     * Prezime korisnika kao String.
     */
    private String prezime;
    
    /**
     * Korisnicko ime korisnika za prijavu na sistem kao String.
     */
    private String korisnickoIme;
    
    /**
     * Sifra korisnika za prijavu na sistem kao String.
     */
    private String sifra;
    
    /**
     * IP adresa sa koje se korisnik prijavio na sistem kao String.
     */
    private String klijentIP;
    
    /**
     * Tacno vreme prijave korisnika na sistem kao String.
     */
    private String vreme;

    /**
     * Pravi nov objekat klase Korisnik.
     * 
     * Svi njegovi atributi (korisnikID, ime, prezime, korisnickoIme, sifra, klijentIP i vreme) ostaju neinicijalizovani.
     */
    public Korisnik() {
    }

    /**
     * Pravi novog korisnika i postavlja jedinstveni identifikator korisnika, ime, prezime, korisnicko ime i sifru na unete vrednosti.
     * 
     * @param korisnikID Jedinstveni identifikator korisnika kao Integer.
     * @param ime Ime korisnika kao String.
     * @param prezime Prezime korisnika kao String.
     * @param korisnickoIme Korisnicko ime korisnika kao String.
     * @param sifra Sifra korisnika kao String.
     */
    public Korisnik(Integer korisnikID, String ime, String prezime, String korisnickoIme, String sifra) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    /**
     * Pravi novog korisnika i postavlja jedinstveni identifikator korisnika, korisnicko ime i sifru na unete vrednosti.
     * 
     * @param korisnikID Jedinstveni identifikator korisnika kao Integer.
     * @param korisnickoIme Korisnicko ime korisnika kao String.
     * @param sifra Sifra korisnika kao String.
     */
    public Korisnik(Integer korisnikID, String korisnickoIme, String sifra) {
        this.korisnikID = korisnikID;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    /**
     * Vraca jedinstveni identifikator korisnika.
     * 
     * @return Trenutni identifikator korisnika kao Integer.
     */
    public Integer getKorisnikID() {
        return korisnikID;
    }

    /**
     * Postavlja jedinstveni identifikator korisnika na unetu vrednost.
     * 
     * @param korisnikID Jedinstveni identifikator korisnika kao Integer.
     */
    public void setKorisnikID(Integer korisnikID) {
        this.korisnikID = korisnikID;
    }

    /**
     * Vraca ime korisnika.
     * 
     * @return Trenutno ime korisnika kao String.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime korisnika na unetu vrednost.
     * 
     * @param ime Ime korisnika kao String.
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraca prezime korisnika.
     * 
     * @return Trenutno prezime korisnika kao String.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime korisnika na unetu vrednost.
     * 
     * @param prezime Prezime korisnika kao String.
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraca korisnicko ime korisnika.
     * 
     * @return Trenutno korisnicko ime korisnika kao String.
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Postavlja korisnicko ime korisnika na unetu vrednost.
     * 
     * @param korisnickoIme Korisnicko ime korisnika kao String.
     */
    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Vraca sifru korisnika.
     * 
     * @return Trenutna sifra korisnika kao String.
     */
    public String getSifra() {
        return sifra;
    }

    /**
     * Postavlja sifru korisnika na unetu vrednost.
     * 
     * @param sifra Sifra korisnika kao String.
     */
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    /**
     * Vraca IP adresu korisnika.
     * 
     * @return Trenutno IP adresa korisnika kao String.
     */
    public String getKlijentIP() {
        return klijentIP;
    }

    /**
     * Postavlja IP adresu korisnika na unetu vrednost.
     * 
     * @param klijentIP IP adresa korisnika kao String.
     */
    public void setKlijentIP(String klijentIP) {
        this.klijentIP = klijentIP;
    }

    /**
     * Vraca vreme prijave korisnika na sistem.
     * 
     * @return Vreme prijave korisnika kao String.
     */
    public String getVreme() {
        return vreme;
    }

    /**
    * Postavlja vreme prijave korisnika na unetu vrednost.
    * 
    * @param vreme Vreme prijave korisnika kao String.
    */
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
