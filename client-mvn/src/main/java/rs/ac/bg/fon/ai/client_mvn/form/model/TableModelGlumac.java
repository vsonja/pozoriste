package rs.ac.bg.fon.ai.client_mvn.form.model;

import rs.ac.bg.fon.ai.common_mvn.domain.Glumac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sonja
 */
public class TableModelGlumac extends AbstractTableModel {

    List<Glumac> glumci;
    String[] columNames = {"Ime", "Prezime", "Telefon"};

    public TableModelGlumac(List<Glumac> glumci) {
        this.glumci = glumci;
    }

    @Override
    public int getRowCount() {
        if (glumci == null) {
            return 0;
        }

        return glumci.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Glumac glumac = glumci.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return glumac.getIme();
            case 1:
                return glumac.getPrezime();
            case 2:
                return glumac.getTelefon();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Glumac glumac = glumci.get(rowIndex);

        switch (columnIndex) {
            case 0:
                glumac.setIme(value.toString());
                break;
            case 1:
                glumac.setPrezime(value.toString());
                break;
            case 2:
                glumac.setTelefon(value.toString());
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    public void dodaj(Glumac g) {
        glumci.add(g);
        fireTableDataChanged();
    }

    public void izbaci(int rowIndex) {
        glumci.remove(rowIndex);
        fireTableDataChanged();
    }

    public List<Glumac> getGlumci() {
        return glumci;
    }

}
