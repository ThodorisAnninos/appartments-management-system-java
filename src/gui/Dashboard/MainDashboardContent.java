package gui.Dashboard;

import api.Host;
import gui.AccommodationControllers.AccommodationTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class MainDashboardContent extends JPanel {

    private BoxComponent box1;
    private BoxComponent box2;
    private JPanel boxesPanel;
    private JLabel infoLabel;
    private JLabel overviewLabel;
    private JTable table;
    private JScrollPane tablePanel;
    private JLabel title;
    DashboardScreen d;

    public MainDashboardContent(DashboardScreen d) {
        this.d = d;
        initComponents();
        if (d.getLoggedInUser().isHost()) {
            Host h = (Host) d.getLoggedInUser();
            box1.setData("Αξιολογήσεις", h.getAmountOfRatings()+"", Color.yellow, null);
            box2.setData("Μέσος Όρος", h.getMyScore()+"%", Color.CYAN, null);
            boxesPanel.add(box1);
            boxesPanel.add(box2);
        } else {
            box2.setData("Μέσος Όρος", d.getLoggedInUser().getMyScore()+"%", Color.yellow, null);
            boxesPanel.add(box2);
        }
        
    }

    private void initComponents() {

        title = new JLabel();
        boxesPanel = new JPanel();
        box1 = new BoxComponent();
        box2 = new BoxComponent();
        overviewLabel = new JLabel();
        tablePanel = new JScrollPane();
        table = new JTable();
        infoLabel = new JLabel();

        setBackground(new Color(255, 255, 255));

        title.setFont(new Font("Verdana", 1, 24));
        title.setText("Πίνακας Ελέγχου");

        boxesPanel.setBackground(new Color(255, 255, 255));
        boxesPanel.setLayout(new GridLayout(1, 0, 50, 0));


        overviewLabel.setFont(new Font("Verdana", 0, 18));
        overviewLabel.setText("Με μια ματιά...");

        table.setFont(new Font("Verdana", 0, 14));
//        table.setModel(new DefaultTableModel(
//            new Object [][] {
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null}
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

        AccommodationTableModel model;
        if (d.getLoggedInUser().isHost())
             model = new AccommodationTableModel(d.getLoggedInUser(), false);
        else {
            model = new AccommodationTableModel(d.getLoggedInUser(), true);
            model.setData(d.getLoggedInUser().getMyAccommodations());
        }
        table.setModel(model);

        table.setRowHeight(40);
        table.setShowGrid(true);
        table.setShowVerticalLines(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setEnabled(false);
        tablePanel.setViewportView(table);

        infoLabel.setFont(new Font("Verdana", 2, 14));
        infoLabel.setText("<html>* Για λειτουργίες επί των καταλυμάτων, περιηγηθείτε στην αντίστοιχη καρτέλα.<br>** Για την ενημέρωση των στατιστικών στον πίνακα ελέγχου, παρακαλώ επανεκκινήστε την εφαρμογή.<html>");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(1453, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(boxesPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 1627, Short.MAX_VALUE))
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(infoLabel)
                            .addComponent(overviewLabel))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(title)
                .addGap(27, 27, 27)
                .addComponent(boxesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(overviewLabel)
                .addGap(18, 18, 18)
                .addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(infoLabel)
                .addContainerGap(16, Short.MAX_VALUE))
        );
    }



}
