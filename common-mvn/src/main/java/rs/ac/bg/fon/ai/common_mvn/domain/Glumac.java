package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Predstavlja glumca zaposlenog u pozorištu. Glumac ima glumacID, ime, prezime
 * i telefon.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira
 * interfejs Serializable, jer se podaci salju kroz mrezu.
 *
 * @author Sonja
 * @version 1.0
 */
public class Glumac extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Jedinstveni identifikator glumca kao Integer.
	 */
	private Integer glumacID;

	/**
	 * Ime glumca kao String.
	 */
	private String ime;

	/**
	 * Prezime glumca kao String.
	 */
	private String prezime;

	/**
	 * Broj telefona glumca kao String.
	 */
	private String telefon;

	/**
	 * Pravi nov objekat klase Glumac.
	 * 
	 * Svi njegovi atributi (glumacID, ime, prezime i telefon) ostaju
	 * neinicijalizovani.
	 */
	public Glumac() {
	}

	/**
	 * Pravi novog glumca i postavlja jedinstveni identifikator glumca, ime, prezime
	 * i telefon na unete vrednosti.
	 * 
	 * @param glumacID Jedinstveni identifikator glumca kao Integer.
	 * @param ime      Ime glumca kao String.
	 * @param prezime  Prezime glumca kao String.
	 * @param telefon  Broj telefona glumca kao String.
	 */
	public Glumac(Integer glumacID, String ime, String prezime, String telefon) {
		this.glumacID = glumacID;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
	}

	/**
	 * Pravi novog glumca i postavlja ime, prezime i telefon na unete vrednosti.
	 * 
	 * @param ime     Ime glumca kao String.
	 * @param prezime Prezime glumca kao String.
	 * @param telefon Broj telefona glumca kao String.
	 */
	public Glumac(String ime, String prezime, String telefon) {
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
	}

	/**
	 * Vraca jedinstveni identifikator glumca.
	 * 
	 * @return Trenutni identifikator glumca kao Integer.
	 */
	public Integer getGlumacID() {
		return glumacID;
	}

	/**
	 * Postavlja jedinstveni identifikator glumca na unetu vrednost.
	 * 
	 * @param glumacID Jedinstveni identifikator glumca kao Integer.
	 */
	public void setGlumacID(Integer glumacID) {
		this.glumacID = glumacID;
	}

	/**
	 * Vraca ime glumca.
	 * 
	 * @return Trenutno ime glumca kao String.
	 */
	public String getIme() {
		return ime;
	}

	/**
	 * Postavlja ime glumca na unetu vrednost.
	 * 
	 * @param ime Ime glumca kao String.
	 */
	public void setIme(String ime) {
		if (ime != null && !ime.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException();
		}
		this.ime = ime;
	}

	/**
	 * Vraca prezime glumca.
	 * 
	 * @return Trenutno prezime glumca kao String.
	 */
	public String getPrezime() {
		return prezime;
	}

	/**
	 * Postavlja prezime glumca na unetu vrednost.
	 * 
	 * @param prezime Prezime glumca kao String.
	 */
	public void setPrezime(String prezime) {
		if (prezime != null && !prezime.chars().allMatch(Character::isLetter)) {
			throw new IllegalArgumentException();
		}
		this.prezime = prezime;
	}

	/**
	 * Vraca broj telefona glumca.
	 * 
	 * @return Trenutni broj telefona glumca kao String.
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * Postavlja broj telefona glumca na unetu vrednost.
	 * 
	 * @param telefon Broj telefona glumca kao String.
	 * @throws IllegalAccessException 
	 */
	public void setTelefon(String telefon) {
		if (telefon != null && !telefon.chars().allMatch(Character::isDigit)) {
			throw new IllegalArgumentException();
		}
		if (telefon.length() != 9) {
			throw new IllegalArgumentException("Broj telefona mora imati 9 cifara.");
		}
		this.telefon = telefon;
	}

	/**
	 * Vraca String reprezentaciju objekta klase Glumac.
	 * 
	 * @return String reprezentacija objekta klase Glumac u formatu "ime prezime".
	 */
	@Override
	public String toString() {
		return ime + " " + prezime;
	}

	@Override
	public String getTableName() {
		return "glumac";
	}

	@Override
	public String getColumnsForInsert() {
		return "ime, prezime, telefon";
	}

	@Override
	public String getParamsForInsert() {
		return "(?, ?, ?)";
	}

	@Override
	public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat)
			throws SQLException {
		Glumac glumac = (Glumac) domenskiObjekat;
		statement.setString(1, glumac.getIme());
		statement.setString(2, glumac.getPrezime());
		statement.setString(3, glumac.getTelefon());
	}

	@Override
	public void setAutoIncrementPK(int primaryKey) {
		setGlumacID(primaryKey);
	}

	@Override
	public String getWhereContidion(UslovWhere kriterijum) {
		String where = "";
		switch (kriterijum) {
		case PRIMARNI_KLJUC:
			where += "glumacID = " + glumacID;
			break;
		}

		return where;
	}

	@Override
	public String postaviVrednostiAtributa() {
		return "ime = '" + ime + "'" + ", prezime = '" + prezime + "', telefon = '" + telefon + "'";
	}

	@Override
	public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
		List<OpstiDomenskiObjekat> lista = new ArrayList<>();
		while (rs.next()) {
			Glumac g = new Glumac();
			lista.add(new Glumac(rs.getInt("glumacID"), rs.getString("ime"), rs.getString("prezime"),
					rs.getString("telefon")));
		}
		return lista;
	}

}
