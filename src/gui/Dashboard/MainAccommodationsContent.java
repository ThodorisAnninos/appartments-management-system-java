package gui.Dashboard;

import api.Accommodation;
import api.Customer;
import api.Exceptions.UnexpectedError;
import api.Rating;
import gui.AccommodationControllers.*;

import java.util.List;

import javax.swing.*;
import java.awt.Color;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class MainAccommodationsContent extends JPanel {

    private JScrollPane accommodationsTable;
    private JButton addAccommodationButton;
    private JButton viewAccommodationButton;
    private JButton deleteButton;
    private JButton editAccommodationButton;
    private JPanel jPanel1;
    private JButton searchButton;
    private JTable table;
    private JLabel title;
    public DashboardScreen dashboardScreen;
    private AccommodationTableModel model;
    //private Accommodation theAcc;

    public void loadTable(){
        model = new AccommodationTableModel(dashboardScreen.getLoggedInUser(), false);
        table.setModel(model);

    }

    public void loadTable(List<Accommodation> list){
        model = new AccommodationTableModel(dashboardScreen.getLoggedInUser(), true);
        model.setData(list);
        table.setModel(model);
    }

    public MainAccommodationsContent(DashboardScreen dashboardScreen) {
        this.dashboardScreen = dashboardScreen;
        initComponents();
        myActionListeners();
        loadTable();
        if (dashboardScreen.getLoggedInUser().isHost()){
            searchButton.setVisible(false);
        } else{
            addAccommodationButton.setVisible(false);
            editAccommodationButton.setVisible(false);
            deleteButton.setVisible(false);
        }
    }



    
//    private void loadTable() {
//        DefaultTableModel tbmodel = (DefaultTableModel) table.getModel();
//        Object[] rows = new Object[4];
//        for (Accommodation accommodation : Database.getAccommodations()) {
//            rows[0] = accommodation.getName();
//            rows[1] = accommodation.getType();
//            rows[2] = accommodation.getCity();
//            rows[3] = accommodation.getName();
//            tbmodel.addRow(rows);
//        }
//    }
    private void initComponents() {

        title = new JLabel();
        accommodationsTable = new JScrollPane();
        table = new JTable();
        addAccommodationButton = new JButton();
        jPanel1 = new JPanel();
        viewAccommodationButton = new JButton();
        editAccommodationButton = new JButton();
        deleteButton = new JButton();
        searchButton = new JButton();

        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(1731, 1119));

        title.setFont(new Font("Verdana", 1, 24));
        title.setText("Καταλύματα");

        table.setFont(new Font("Verdana", 0, 14));




//        table.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//            },
//            new String [] {
//                "Όνομα", "Τύπος", "Τοποθεσία", "[ΜΟ|Αστέρια]"
//            }
//        ) {
//            boolean[] canEdit = new boolean [] {
//                false, false, false, false
//            };
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit [columnIndex];
//            }
//        });
        table.setRowHeight(40);
        table.setShowGrid(true);
        table.setShowVerticalLines(false);
        table.getTableHeader().setReorderingAllowed(false);
        accommodationsTable.setViewportView(table);

        addAccommodationButton.setBackground(new Color(51, 204, 0));
        addAccommodationButton.setForeground(new Color(255, 255, 255));
        URL plusIconUrl = getClass().getClassLoader().getResource("Resources/plus.png");
        addAccommodationButton.setIcon(new ImageIcon(plusIconUrl));
        addAccommodationButton.setText("Προσθήκη Καταλύματος");

        jPanel1.setBackground(new Color(255, 255, 255));

        viewAccommodationButton.setBackground(new Color(255, 153, 0));
        viewAccommodationButton.setForeground(new Color(255, 255, 255));
        URL eyeIconUrl = getClass().getClassLoader().getResource("Resources/eye.png");
        viewAccommodationButton.setIcon(new ImageIcon(eyeIconUrl));
        viewAccommodationButton.setText("Προβολή Καταλύματος");
        viewAccommodationButton.setPreferredSize(new Dimension(230, 40));
        jPanel1.add(viewAccommodationButton);

        URL starIconUrl = getClass().getClassLoader().getResource("Resources/pencil.png");

        editAccommodationButton.setBackground(new Color(0, 153, 255));
        editAccommodationButton.setForeground(new Color(255, 255, 255));
        URL pencilIconUrl = getClass().getClassLoader().getResource("Resources/pencil.png");
        editAccommodationButton.setIcon(new ImageIcon(pencilIconUrl));
        editAccommodationButton.setText("Επεξεργασία Καταλύματος");
        editAccommodationButton.setPreferredSize(new Dimension(230, 40));
        jPanel1.add(editAccommodationButton);

        deleteButton.setBackground(new Color(204, 0, 0));
        deleteButton.setForeground(new Color(255, 255, 255));
        URL trashIconUrl = getClass().getClassLoader().getResource("Resources/trash.png");
        deleteButton.setIcon(new ImageIcon(trashIconUrl));
        deleteButton.setText("Διαγραφή Καταλύματος");
        deleteButton.setPreferredSize(new Dimension(200, 40));

        jPanel1.add(deleteButton);

        searchButton.setBackground(new Color(102, 102, 255));
        searchButton.setForeground(new Color(255, 255, 255));
        URL searchIconUrl = getClass().getClassLoader().getResource("Resources/search.png");
        searchButton.setIcon(new ImageIcon(searchIconUrl));
        searchButton.setText("Σύνθετη Αναζήτηση");


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addAccommodationButton)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(accommodationsTable, GroupLayout.DEFAULT_SIZE, 1627, Short.MAX_VALUE))
                        .addGap(52, 52, 52))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(addAccommodationButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(accommodationsTable, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
    }

    public void myActionListeners(){
        addAccommodationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccommodationController a = new AccommodationController(dashboardScreen, null);
                loadTable();
            }
        });
        editAccommodationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    AccommodationController a = new AccommodationController(dashboardScreen, model.getAccommodationAt(selectedRow));
                    loadTable();
                    //model.fireTableDataChanged();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchDialog s = new SearchDialog(dashboardScreen);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        model.getAccommodationAt(selectedRow).deleteAccommodation();
                    } catch (UnexpectedError err) {
                        JOptionPane.showMessageDialog(null, err.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }
                    model.setData(dashboardScreen.getLoggedInUser().getMyAccommodations());
                    loadTable();
                }
            }
        });

        viewAccommodationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    DisplayAccommodation d = new DisplayAccommodation(dashboardScreen, model.getAccommodationAt(selectedRow));
                    //model.fireTableDataChanged();
                }
            }
        });


    }



}
