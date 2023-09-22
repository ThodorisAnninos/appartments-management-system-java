package gui.Dashboard;

import api.Accommodation;
import api.Customer;
import api.Exceptions.UnexpectedError;
import api.Rating;
import gui.AccommodationControllers.AccommodationController;
import gui.AccommodationControllers.RatingController;
import gui.AccommodationControllers.RatingTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class DisplayAccommodation extends JDialog {
    private JPanel main = new JPanel();
    JScrollPane scroll;
    private JButton addRatingButton;
    private JButton editRatingButton;
    private JPanel dataPanel;
    private JButton deleteButton;
    private JLabel descriptionLabel;
    private JLabel descriptionValue;
    private JPanel jPanel1;
    private JLabel ratingLabel;
    private JLabel ratingsLabel;
    private JTable table;
    private JScrollPane tablePanel;
    private JLabel titleLabel;
    private Accommodation acc;
    private DashboardScreen dashboardScreen;
    private JPanel facilitiesFields, facilitiesValues;
    private JLabel viewLabel, bathroomLabel, laundryLabel, entertainmentLabel, airConditioningLabel, internetLabel, kitchenLabel, outdoorsLabel, parkingLabel;
    private JLabel viewFields, bathroomFields, laundryFields, entertainmentFields, airConditioningFields, internetFields, kitchenFields, outdoorsFields, parkingFields;


    public void loadTable() {
        RatingTableModel model = new RatingTableModel(acc);
        table.setModel(model);
    }

    public DisplayAccommodation(DashboardScreen dashboardScreen, Accommodation acc) {
        this.dashboardScreen = dashboardScreen;
        this.acc = acc;
        initComponents();
        setVisible(true);
        myActionListeners();
        setTitle("Προβολή καταλύματος: " + acc.getName());

        setLocationRelativeTo(null);
        buttonsControl();
    }


    private void initComponents() {
        titleLabel = new JLabel();
        ratingLabel = new JLabel();
        dataPanel = new JPanel();
        //dataPanel.add(new JLabel("Hello World"));
        descriptionLabel = new JLabel();
        descriptionValue = new JLabel();
        ratingsLabel = new JLabel();
        tablePanel = new JScrollPane();
        table = new JTable();
        jPanel1 = new JPanel();
        editRatingButton = new JButton();
        addRatingButton = new JButton();
        deleteButton = new JButton();

        viewLabel = new JLabel("Θέα:");
        bathroomLabel = new JLabel("Μπάνιο:");
        laundryLabel = new JLabel("Πλύσιμο ρούχων:");
        entertainmentLabel = new JLabel("Ψυχαγωγία:");
        airConditioningLabel = new JLabel("Θέρμανση και κλιματισμός:");
        internetLabel = new JLabel("Διαδίκτυο:");
        kitchenLabel = new JLabel("Κουζίνα και τραπεζαρία:");
        outdoorsLabel = new JLabel("Εξωτερικός χώρος:");
        parkingLabel = new JLabel("Χώρος στάθμευσης:");

        if (acc.getFacilities().getView() != null)
            viewFields = new JLabel(acc.getFacilities().getView().toString());
        else
            viewFields = new JLabel("-");

        if (acc.getFacilities().getBathroom() != null)
            bathroomFields = new JLabel(acc.getFacilities().getBathroom().toString());
        else
            bathroomFields = new JLabel("-");

        if (acc.getFacilities().getLaundry() != null)
            laundryFields = new JLabel(acc.getFacilities().getLaundry().toString());
        else
            laundryFields = new JLabel("-");

        if (acc.getFacilities().getEntertainment() != null)
            entertainmentFields = new JLabel(acc.getFacilities().getEntertainment().toString());
        else
            entertainmentFields = new JLabel("-");

        if (acc.getFacilities().getAirConditioning() != null)
            airConditioningFields = new JLabel(acc.getFacilities().getAirConditioning().toString());
        else
            airConditioningFields = new JLabel("-");

        if (acc.getFacilities().getInternet() != null)
            internetFields = new JLabel(acc.getFacilities().getInternet().toString());
        else
            internetFields = new JLabel("-");

        if (acc.getFacilities().getKitchen() != null)
            kitchenFields = new JLabel(acc.getFacilities().getKitchen().toString());
        else
            kitchenFields = new JLabel("-");

        if (acc.getFacilities().getOutdoors() != null)
            outdoorsFields = new JLabel(acc.getFacilities().getOutdoors().toString());
        else
            outdoorsFields = new JLabel("-");

        if (acc.getFacilities().getParking() != null)
            parkingFields = new JLabel(acc.getFacilities().getParking().toString());
        else
            parkingFields = new JLabel("-");

        facilitiesFields = new JPanel(new GridLayout(0, 1));


        facilitiesFields.add(new JLabel("Τύπος:"));
        facilitiesFields.add(new JLabel("Τοποθεσία:"));
        facilitiesFields.add(new JLabel(""));

        facilitiesFields.add(viewLabel);
        facilitiesFields.add(bathroomLabel);
        facilitiesFields.add(laundryLabel);
        facilitiesFields.add(entertainmentLabel);
        facilitiesFields.add(airConditioningLabel);
        facilitiesFields.add(internetLabel);
        facilitiesFields.add(kitchenLabel);
        facilitiesFields.add(outdoorsLabel);
        facilitiesFields.add(parkingLabel);

        facilitiesValues = new JPanel(new GridLayout(0, 1));

        String type = "";
        switch (acc.getType()){
            case 1: type = "Ξενοδοχείο"; break;
            case 2: type = "Διαμέρισμα"; break;
            case 3: type = "Μεζονέτα"; break;
        }
        facilitiesValues.add(new JLabel(type));
        facilitiesValues.add(new JLabel(acc.getAddress() + ", " + acc.getCity() + ", " + acc.getPostalCode()));
        facilitiesValues.add(new JLabel(""));

        facilitiesValues.add(viewFields);
        facilitiesValues.add(bathroomFields);
        facilitiesValues.add(laundryFields);
        facilitiesValues.add(entertainmentFields);
        facilitiesValues.add(airConditioningFields);
        facilitiesValues.add(internetFields);
        facilitiesValues.add(kitchenFields);
        facilitiesValues.add(outdoorsFields);
        facilitiesValues.add(parkingFields);


        dataPanel.add(facilitiesFields, BorderLayout.LINE_START);
        dataPanel.add(facilitiesValues, BorderLayout.CENTER);





        titleLabel.setFont(new Font("Verdana", 1, 24));
        //titleLabel.setText("Title");
        titleLabel.setText(acc.getName());

        ratingLabel.setFont(new Font("Verdana", 0, 18));
        URL ratingIconUrl = getClass().getClassLoader().getResource("Resources/star_black.png");
        ratingLabel.setIcon(new ImageIcon(ratingIconUrl));
        ratingLabel.setText(acc.getAccommodationRating() + " αστέρια");
        //ratingLabel.setText();

        GroupLayout dataPanelLayout = new GroupLayout(dataPanel);
        //dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1239, Short.MAX_VALUE)
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 71, Short.MAX_VALUE)
        );

        descriptionLabel.setFont(new Font("Verdana", 1, 14));
        descriptionLabel.setText("Περιγραφή");

        descriptionValue.setFont(new Font("Verdana", 0, 14));
        //descriptionValue.setText("description");
        descriptionValue.setText(acc.getDescription());
        descriptionValue.setVerticalAlignment(SwingConstants.TOP);

        ratingsLabel.setFont(new Font("Verdana", 1, 14));
        ratingsLabel.setText("Αξιολογήσεις");

        tablePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        table.setFont(new Font("Verdana", 0, 14));

//        table.setModel();
//        table.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                {null, null, null},
//                {null, null, null},
//                {null, null, null},
//                {null, null, null}
//            },
//            new String [] {
//                "Όνομα Χρήστη", "Βαθμολογία", "Αξιολόγηση"
//            }
//        ) {
//            boolean[] canEdit = new boolean [] {
//                false, false, false
//            };
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit [columnIndex];
//            }
//        });

        loadTable();
        table.setAutoscrolls(false);
        table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        table.setEnabled(false);
        table.setRowHeight(40);
        table.setShowGrid(true);
        table.setShowVerticalLines(false);
        table.getTableHeader().setReorderingAllowed(false);
        tablePanel.setViewportView(table);

        editRatingButton.setBackground(new Color(255, 153, 0));
        editRatingButton.setForeground(new Color(255, 255, 255));
        URL pencilIconUrl = getClass().getClassLoader().getResource("Resources/pencil.png");
        editRatingButton.setIcon(new ImageIcon(pencilIconUrl));
        editRatingButton.setText("Επεξεργασία Αξιολόγησης");
        editRatingButton.setPreferredSize(new Dimension(230, 40));
        jPanel1.add(editRatingButton);

        addRatingButton.setBackground(new Color(0, 153, 255));
        addRatingButton.setForeground(new Color(255, 255, 255));
        URL starIconUrl = getClass().getClassLoader().getResource("Resources/star.png");
        addRatingButton.setIcon(new ImageIcon(starIconUrl));
        addRatingButton.setText("Καταχώρηση Αξιολόγησης");
        addRatingButton.setPreferredSize(new Dimension(230, 40));
        jPanel1.add(addRatingButton);


        deleteButton.setBackground(new Color(204, 0, 0));
        deleteButton.setForeground(new Color(255, 255, 255));
        URL trashIconUrl = getClass().getClassLoader().getResource("Resources/trash.png");
        deleteButton.setIcon(new ImageIcon(trashIconUrl));
        deleteButton.setText("Διαγραφή Αξιολόγησης");
        deleteButton.setPreferredSize(new Dimension(200, 40));
        jPanel1.add(deleteButton);


        GroupLayout layout = new GroupLayout(main);
        main.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(ratingsLabel)
                        .addComponent(descriptionLabel)
                        .addComponent(ratingLabel)
                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 1280, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dataPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(descriptionValue, GroupLayout.PREFERRED_SIZE, 1183, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 1243, GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(titleLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ratingLabel)
                .addGap(18, 18, 18)
                .addComponent(dataPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(descriptionLabel)
                .addGap(18, 18, 18)
                .addComponent(descriptionValue, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ratingsLabel)
                .addGap(18, 18, 18)
                .addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        scroll = new JScrollPane(main);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        pack();
    }

    public void buttonsControl() {
        if (dashboardScreen.getLoggedInUser().isHost()){
            addRatingButton.setVisible(false);
            editRatingButton.setVisible(false);
            deleteButton.setVisible(false);
        } else {
            addRatingButton.setVisible(true);
            editRatingButton.setVisible(true);
            Customer c = (Customer) dashboardScreen.getLoggedInUser();
            if (c.getMyRating(acc) != null){
                addRatingButton.setVisible(false);
                deleteButton.setVisible(true);
            } else {
                deleteButton.setVisible(false);
                editRatingButton.setVisible(false);
                addRatingButton.setVisible(true);
            }
        }
    }

    private DisplayAccommodation getMe(){
        return this;
    }


    private void myActionListeners(){
        addRatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RatingController r = new RatingController(dashboardScreen, getMe(), null, acc);
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c = (Customer) dashboardScreen.getLoggedInUser();
                Rating r = c.getMyRating(acc);
                if (r!= null) {
                    try {
                        r.deleteRating();
                    } catch (UnexpectedError err){
                        JOptionPane.showMessageDialog(null, err.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }
                    loadTable();
                    buttonsControl();
                }
            }
        });


        editRatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c = (Customer) dashboardScreen.getLoggedInUser();
                RatingController r = new RatingController(dashboardScreen, getMe(), c.getMyRating(acc), acc);
                loadTable();
                buttonsControl();
            }
        });
    }
}
