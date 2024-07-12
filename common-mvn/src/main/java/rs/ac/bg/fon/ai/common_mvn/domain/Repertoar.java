package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Predstavlja mesecni repertoar pozorista. Repertoar ima repertoarID, mesec,
 * godinu i listu stavki repertoara.
 * 
 * Ova klasa nasledjuje apstraktnu klasu OpstiDomenskiObjekat i implementira
 * interfejs Serializable, jer se podaci salju kroz mrezu.
 *
 * @author Sonja
 * @version 1.0
 */
public class Repertoar extends OpstiDomenskiObjekat implements Serializable {

	/**
	 * Jedinstveni identifikator repertoara kao Integer.
	 */
	private Integer repertoarID;

	/**
	 * Mesec na koji se repertoar odnosi kao int.
	 */
	private int mesec;

	/**
	 * Godina na koju se repertoar odnosi kao int.
	 */
	private int godina;

	/**
	 * Stavke repertoara kao lista objekata klase StavkaRepertoara.
	 */
	List<StavkaRepertoara> stavke;

	/**
	 * Pravi nov objekat klase Repertoar.
	 * 
	 * Svi njegovi atributi (repertoarID, mesec, godina i stavke) ostaju
	 * neinicijalizovani.
	 */
	public Repertoar() {
	}

	/**
	 * Pravi novi repertoar i postavlja jedinstveni identifikator repertoara, mesec,
	 * godinu i stavke na unete vrednosti.
	 * 
	 * @param repertoarID Jedinstveni identifikator repertoara kao Integer.
	 * @param mesec       Mesec na koji se repertoar odnosi kao int.
	 * @param godina      Godina na koju se repertoar odnosi kao int.
	 * @param stavke      Stavke repertoara kao lista objekata klase
	 *                    StavkaRepertoara.
	 */
	public Repertoar(Integer repertoarID, int mesec, int godina, List<StavkaRepertoara> stavke) {
		this.repertoarID = repertoarID;
		this.mesec = mesec;
		this.godina = godina;
		this.stavke = stavke;
	}

	/**
	 * Vraca godinu na koju se repertoar odnosi.
	 * 
	 * @return Godina na koju se repertoar odnosi kao int.
	 */
	public int getGodina() {
		return godina;
	}

	/**
	 * Postavlja godinu na koju se repertoar odnosi na unetu vrednost.
	 * 
	 * @param godina Godina na koju se repertoar odnosi kao int.
	 */
	public void setGodina(int godina) {
		this.godina = godina;
	}

	/**
	 * Vraca jedinstveni identifikator repertoara.
	 * 
	 * @return Trenutni identifikator repertoara kao Integer.
	 */
	public Integer getRepertoarID() {
		return repertoarID;
	}

	/**
	 * Postavlja jedinstveni identifikator repertoara na unetu vrednost.
	 * 
	 * @param repertoarID Jedinstveni identifikator repertoara kao Integer.
	 */
	public void setRepertoarID(Integer repertoarID) {
		this.repertoarID = repertoarID;
	}

	/**
	 * Vraca mesec na koji se repertoar odnosi.
	 * 
	 * @return Mesec na koji se repertoar odnosi kao int.
	 */
	public int getMesec() {
		return mesec;
	}

	/**
	 * Postavlja mesec na koji se repertoar odnosi na unetu vrednost.
	 * 
	 * @param mesec Mesec na koji se repertoar odnosi kao int.
	 */
	public void setMesec(int mesec) {
		if (mesec < 1 || mesec > 12) {
			throw new IllegalArgumentException();
		}
		this.mesec = mesec;
	}

	/**
	 * Vraca listu stavki repertoara.
	 * 
	 * @return Trenutne stavke repertoara kao lista objekata klase StavkaRepertoara.
	 */
	public List<StavkaRepertoara> getStavke() {
		return stavke;
	}

	/**
	 * Postavlja listu stavki repertoara na unetu vrednost.
	 * 
	 * @param stavke Lista objekata klase StavkaRepertoara.
	 */
	public void setStavke(List<StavkaRepertoara> stavke) {
		this.stavke = stavke;
	}

	@Override
	public String getTableName() {
		return "repertoar";
	}

	@Override
	public String getColumnsForInsert() {
		return "mesec, godina";
	}

	@Override
	public String getParamsForInsert() {
		return "(?, ?)";
	}

	@Override
	public void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat)
			throws SQLException {
		Repertoar repertoar = (Repertoar) domenskiObjekat;
		statement.setInt(1, repertoar.getMesec());
		statement.setInt(2, repertoar.getGodina());
	}

	@Override
	public void setAutoIncrementPK(int primaryKey) {
		setRepertoarID(primaryKey);
	}

	@Override
	public String getWhereContidion(UslovWhere kriterijum) {
		String where = "";
		switch (kriterijum) {
		case MESEC_I_GODINA:
			where += "mesec = " + mesec + " AND godina = " + godina;
			break;
		}
		return where;
	}

	@Override
	public String postaviVrednostiAtributa() {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
