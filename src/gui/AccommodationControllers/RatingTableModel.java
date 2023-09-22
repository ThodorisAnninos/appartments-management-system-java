package gui.AccommodationControllers;

import api.Accommodation;
import api.Database;
import api.Rating;
import api.User;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RatingTableModel extends AbstractTableModel {

    private final String[] columnNames;
    private ArrayList<Rating> ratingsData;
    private User u;

    public RatingTableModel(Accommodation a){
        this.u = u;
        ratingsData = new ArrayList<>();
        columnNames = new String[]{"Χρήστης", "Βαθμολογία", "Περιγραφή"};

        ratingsData = a.getRatings();
    }


    @Override
    public int getRowCount() {
        return ratingsData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rating r = ratingsData.get(rowIndex);
        switch (columnIndex){
            case 0: return r.getCustomer().getUsername();
            case 1: return r.getStars() + " αστέρια";
            case 2: return r.getDescription();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Rating getRatingAt(int rowIndex) {
        return ratingsData.get(rowIndex);
    }

    @Override
    public void fireTableDataChanged(){
        TableModelEvent event = new TableModelEvent(this);
    }
}
