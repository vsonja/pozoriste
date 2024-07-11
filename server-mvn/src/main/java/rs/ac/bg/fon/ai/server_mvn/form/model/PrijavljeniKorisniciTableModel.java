package rs.ac.bg.fon.ai.server_mvn.form.model;

import rs.ac.bg.fon.ai.common_mvn.domain.Korisnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sonja
 */
public class PrijavljeniKorisniciTableModel extends AbstractTableModel {

    private List<Korisnik> korisnici;
    String[] columnNames = {"Korisničko ime", "IP adresa", "Vreme prijave"};

    public PrijavljeniKorisniciTableModel(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    @Override
    public int getRowCount() {
        return korisnici == null ? 0 : korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = korisnici.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return k.getKorisnickoIme();
            case 1:
                return k.getKlijentIP();
            case 2:
                return k.getVreme();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Korisnik k = korisnici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                k.setKorisnickoIme(value.toString());
                break;
            case 1:
                k.setKlijentIP(value.toString());
                break;
            case 2:
                k.setVreme(value.toString());
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column > columnNames.length) {
            return "N/A";
        }

        return columnNames[column];
    }

    public void dodajKorisnika(Korisnik korisnik) {
        korisnici.add(korisnik);
        fireTableDataChanged();
    }

    public void izbaciKorisnika(Korisnik korisnik) {
        korisnici.remove(korisnik);
        fireTableDataChanged();
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

}
