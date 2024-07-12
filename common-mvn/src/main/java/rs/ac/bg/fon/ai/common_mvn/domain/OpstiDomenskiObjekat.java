package rs.ac.bg.fon.ai.common_mvn.domain;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import java.sql.*;
import java.util.List;

/**
 * Predstavlja apstraktnu klasu koja definise zaglavlje apstraktnih metoda, koriscenih za rad sa upitima nad bazom podataka.
 *
 * @author Sonja
 * @version 1.0
 */
public abstract class OpstiDomenskiObjekat {

	/**
	 * Vraca naziv tabele u bazi podataka.
	 * 
	 * @return Naziv tabele kao String.
	 */
    public abstract String getTableName();

    /**
     * Vraca nazive kolona koje treba popuniti vrednostima prilikom ubacivanja novih podataka u bazu.
     * 
     * @return Nazivi kolona odgovarajuce tabele kao String, odvojeni zarezom i razmakom.
     */
    public abstract String getColumnsForInsert();

    /**
     * Vraca vrednosti koje je potrebno ubaciti u kolone odgovarajuce tabele u bazi.
     * Ovo su vrednosti atributa konkretnog objekta.
     * 
     * @return Vrednosti atributa kao String, odvojene zarezom i razmakom.
     */
    public abstract String getParamsForInsert();

    /**
     * Postavlja vrednosti atributa za odgovarajuce kolone tabele, prilikom kreiranja novog objekta u bazi.
     * 
     * @param statement Objekat pomocu kog se izvode operacije nad bazom podataka. 
     * @param domenskiObjekat Domenski objekat koji se dodaje u bazu, odnosno cijim vrednostima atributa pristupamo.
     * @throws SQLException U slucaju neuspesnog dodavanja objekta u bazu.
     */
    public abstract void setParamsForInsert(PreparedStatement statement, OpstiDomenskiObjekat domenskiObjekat) throws SQLException;

    /**
     * Vraca uslov koji je deo upita.
     * Ovaj uslov se razlikuje u zavisnosti od kriterijuma.
     * 
     * @param kriterijum Kriterijum na osnovu kog se vraca uslov, tipa UslovWhere koji je Enum.
     * @return Uslov koji je deo upta kao String.
     */
    public abstract String getWhereContidion(UslovWhere kriterijum);

    /**
     * Vraca true ili false u zavisnosti od toga da li tabela u bazi ima primarni kljuc koji je Auto Increment.
     * Po difoltu je true.
     * 
     * @return
     * <ul>
	 * 	<li><b>true</b> - Tabela odgovarajuce klase u bazi ima primarni kljuc koji je Auto Increment.</li>
	 * 	<li><b>false</b> - Tabela odgovarajuce klase u bazi nema primarni kljuc koji je Auto Increment. </li>
	 * </ul>
     */
    public boolean containsAutoIncrementPK() {
        return true;
    }

    /**
     * Postavlja vrednost atributa, jedinstvenog identifikatora, na unetu vrednost.
     * 
     * @param primaryKey Vrednost primarnog kljuca u bazi podataka kao int.
     */
    public abstract void setAutoIncrementPK(int primaryKey);

    /**
     * Vraca nazive kolona i vrednosti atributa za azuriranje objekta u bazi.
     * 
     * @return Nazivi kolona sa odgovarajucim vrednostima atributa kao String, koje su im dodeljene znakom jednakosti.
     */
    public abstract String postaviVrednostiAtributa();

    /**
     * Vraca listu domenskih objekata koja je rezultat izvrsavanja operacije u bazi podataka.
     * Ucitava se svaki red tabele i odgovarajuce vrednosti se postavljaju kao atributi objekta koji ce biti dodat u listu.
     * 
     * @param rs Objekat sa podacima iz baze nakon izvrsavanja upita.
     * @return Lista domenskih objekata koji su ucitani iz baze.
     * @throws SQLException U slucaju greske prilikom ucitavanja podataka iz baze.
     */
    public abstract List<OpstiDomenskiObjekat> vratiSve(ResultSet rs) throws SQLException;

}
