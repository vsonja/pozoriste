package rs.ac.bg.fon.ai.server_mvn.database;

import rs.ac.bg.fon.ai.common_mvn.constants.UslovWhere;
import rs.ac.bg.fon.ai.common_mvn.domain.OpstiDomenskiObjekat;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

/**
 *
 * @author Sonja
 */
public class DBBroker {

    private Connection connection;

    public void connect() throws FileNotFoundException, IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/config/config.properties"));
        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");

            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void disconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Konekcija sa bazom uspesno raskinuta!");
            }
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno raskinuta!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void commit() throws SQLException {
        try {
            connection.commit();
            System.out.println("Transakcija uspesno potvrdjena!");
        } catch (SQLException ex) {
            System.out.println("Transakcija nije potvrdjena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void rollback() throws SQLException {
        try {
            connection.rollback();
            System.out.println("Transakcija uspesno ponistena!");
        } catch (SQLException ex) {
            System.out.println("Transakcija nije ponistena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public Object prijaviKorisnika(String username, String password) throws Exception {
        String sql = "SELECT * FROM korisnik WHERE korisnickoIme = ? AND sifra = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            throw new Exception("Pogresno korisnicko ime ili sifra!");
        }
    }

    public boolean UbaciNoviObjekat(OpstiDomenskiObjekat odo) {
        try {
            String upit = "INSERT INTO " + odo.getTableName() + " (" + odo.getColumnsForInsert() + ") VALUES " + odo.getParamsForInsert();
            System.out.println("Upit: " + upit);

            PreparedStatement statement = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

            odo.setParamsForInsert(statement, odo);

            int result = statement.executeUpdate();
            System.out.println("Objekat je uspesno dodat u bazu.");

            if (odo.containsAutoIncrementPK()) {
                ResultSet rsID = statement.getGeneratedKeys();
                if (rsID.next()) {
                    odo.setAutoIncrementPK(rsID.getInt(1));
                }
                rsID.close();
            }

            statement.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Neuspesno dodavanje objekta u bazu!\n" + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean obrisiObjekat(OpstiDomenskiObjekat odo) {
        String upit = "DELETE FROM " + odo.getTableName() + " WHERE " + odo.getWhereContidion(UslovWhere.PRIMARNI_KLJUC);
        System.out.println("Upit: " + upit);
        return izvrsiUpit(upit);
    }

    public boolean azurirajObjekat(OpstiDomenskiObjekat odo, UslovWhere uslov) {
        String upit = "UPDATE " + odo.getTableName() + " SET " + odo.postaviVrednostiAtributa() + " WHERE " + odo.getWhereContidion(uslov);
        System.out.println("Upit: " + upit);
        return izvrsiUpit(upit);
    }

    public boolean daLiPostojiObjekat(OpstiDomenskiObjekat odo, UslovWhere uslov) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT * FROM " + odo.getTableName() + " WHERE " + odo.getWhereContidion(uslov);
        System.out.println("Upit: " + upit);
        try {
            statement = connection.prepareStatement(upit);
            rs = statement.executeQuery(upit);

            if (rs.next() == false) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public List<OpstiDomenskiObjekat> vratiSveObjekteSaUslovom(OpstiDomenskiObjekat odo, UslovWhere uslov) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT * FROM " + odo.getTableName() + " WHERE " + odo.getWhereContidion(uslov);
        System.out.println("Upit: " + upit);
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            statement = connection.prepareStatement(upit);
            rs = statement.executeQuery(upit);

            if (rs != null) {
                lista = odo.vratiSve(rs);
            }

            System.out.println(lista);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public List<OpstiDomenskiObjekat> vratiSveObjekteBezUslova(OpstiDomenskiObjekat odo) {
        ResultSet rs = null;
        Statement statement = null;
        String upit = "SELECT * FROM " + odo.getTableName();
        System.out.println("Upit: " + upit);
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        try {
            statement = connection.prepareStatement(upit);
            rs = statement.executeQuery(upit);

            if (rs != null) {
                lista = odo.vratiSve(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean izvrsiUpit(String upit) {
        Statement statement = null;
        boolean signal = false;
        try {
            statement = connection.createStatement();
            int rowcount = statement.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
            if (rowcount > 0) {
                signal = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class
                    .getName()).log(Level.SEVERE, null, ex);
            signal = false;
        }

        return signal;
    }

}
