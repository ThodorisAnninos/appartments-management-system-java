package gui.AccommodationControllers;

import api.*;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AccommodationTableModel extends AbstractTableModel {

    private final String[] columnNames;
    private List<Accommodation> accommodationsData;
    private User u;
    private boolean search;


    public AccommodationTableModel(User u, boolean search){
        this.u = u;
        if (!search) {
            accommodationsData = new ArrayList<>();
            if (this.u.isHost()) {
                columnNames = new String[]{"Όνομα", "Τύπος", "Τοποθεσία", "ΜΟ"};
                accommodationsData = u.getMyAccommodations();
            } else {
                columnNames = new String[]{"Όνομα", "Τύπος", "Τοποθεσία", "Αστέρια"};
                accommodationsData = Database.getAccommodations();
            }
        } else{
            columnNames = new String[]{"Όνομα", "Τύπος", "Τοποθεσία", "Αστέρια"};
        }
    }

    public void setData(List<Accommodation> acc){
        this.accommodationsData = acc;
    }

    @Override
    public int getRowCount() {
        return accommodationsData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Accommodation acc = accommodationsData.get(rowIndex);
        String type = "";
        switch (acc.getType()){
            case 1: type = "Ξενοδοχείο"; break;
            case 2: type = "Διαμέρισμα"; break;
            case 3: type = "Μεζονέτα"; break;
        }

        String stars = "";
        if (u.isHost()){
            stars = acc.getAccommodationRating() + " αστέρια";
        } else {
            Customer c = (Customer) u;
            if (c.getMyRating(acc) != null)
                stars = c.getMyRating(acc).getStars() + " αστέρια";
            else
                stars = "-";
        }

        switch (columnIndex){
            case 0: return acc.getName();
            case 1: return type;
            case 2: return acc.getCity();
            case 3: return stars;
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Accommodation getAccommodationAt(int rowIndex) {
        return accommodationsData.get(rowIndex);
    }

}
