package rs.ac.bg.fon.ai.client_mvn.form.model;

import rs.ac.bg.fon.ai.common_mvn.domain.Predstava;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sonja
 */
public class TableModelPredstava extends AbstractTableModel {

    List<Predstava> predstave;
    String[] columNames = {"Naziv", "Opis"};

    public TableModelPredstava(List<Predstava> predstave) {
        this.predstave = predstave;
    }

    @Override
    public int getRowCount() {
        if (predstave == null) {
            return 0;
        }

        return predstave.size();
    }

    @Override
    public int getColumnCount() {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Predstava predstava = predstave.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return predstava.getNazivPredstave();
            case 1:
                return predstava.getOpis();
            default:
                return "N/A";
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Predstava predstava = predstave.get(rowIndex);

        switch (columnIndex) {
            case 0:
                predstava.setNazivPredstave(value.toString());
                break;
            case 1:
                predstava.setOpis(value.toString());
                break;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columNames[column];
    }

    public void dodaj(Predstava p) {
        predstave.add(p);
        fireTableDataChanged();
    }

    public void izbaci(int rowIndex) {
        predstave.remove(rowIndex);
        fireTableDataChanged();
    }

    public List<Predstava> getPredstave() {
        return predstave;
    }

}
