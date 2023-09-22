package gui.AccommodationControllers;

import api.Accommodation;
import api.AccommodationConstants;
import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;
import api.Facilities;
import api.Host;
import gui.Dashboard.DashboardScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccommodationController extends JDialog {

    private JLabel title;
    private JLabel nameLabel, typeLabel, addressLabel, cityLabel, postalCodeLabel, descriptionLabel;
    private JLabel viewLabel, bathroomLabel, laundryLabel, entertainmentLabel, airConditioningLabel, internetLabel, kitchenLabel, outdoorsLabel, parkingLabel;
    private JTextField nameField, addressField, cityField, postalCodeField;
    private JTextArea descriptionField;
    private JRadioButton hotelRadio, apartmentRadio, maisonetteRadio;
    private String[] viewBoxValues, bathroomBoxValues, laundryBoxValues, entertainmentBoxValues, airConditioningBoxValues, internetBoxValues, kitchenBoxValues, outdoorsBoxValues, parkingBoxValues;
    private JCheckBox[] viewCheckBoxes, bathroomCheckBoxes, laundryCheckBoxes, entertainmentCheckBoxes, airConditioningCheckBoxes, internetCheckBoxes, kitchenCheckBoxes, outdoorsCheckBoxes, parkingCheckBoxes;

    private ButtonGroup typeGroup;
    private JPanel controllers, typePanel, viewPanel, bathroomPanel, laundryPanel, entertainmentPanel, airConditioningPanel, internetPanel, kitchenPanel, outdoorsPanel, parkingPanel;

    private JPanel labelsPanel, fieldsPanel;
    private JButton saveBtn, cancelBtn;


    private DashboardScreen master;
    private Accommodation toEditAccommodation, theAccommodation;

    public AccommodationController(DashboardScreen master, Accommodation toEditAccommodation){
        super(master, "Προσθήκη Καταλύματος", true);
        this.master = master;
        this.toEditAccommodation = toEditAccommodation;
        if (toEditAccommodation!=null)
            setTitle("Επεξεργασία Καταλύματος");
        buildFrame();
        setLocationRelativeTo(null);
    }

    private void buildFrame(){
        creatingComponents();
        addingComponents();

        add(title, BorderLayout.PAGE_START);
        add(labelsPanel, BorderLayout.LINE_START);
        add(fieldsPanel, BorderLayout.CENTER);
        add(controllers, BorderLayout.PAGE_END);

        actionListeners();

        getFieldsToEdit();

        setSize(getPreferredSize().width, 600);
        //pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(master);
        setResizable(false);
        setVisible(true);

    }

    private void creatingComponents(){
        if (toEditAccommodation==null)
            title = new JLabel("Προσθήκη Καταλύματος");
        else
            title = new JLabel("Επεξεργασία Καταλύματος");

        title.setFont(new Font("Verdana", Font.BOLD, 20));

        labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(0, 1));
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(0, 1));


        saveBtn = new JButton("Αποθήκευση");
        cancelBtn = new JButton("Ακύρωση");
        controllers = new JPanel();
        controllers.add(saveBtn);
        controllers.add(cancelBtn);

        nameLabel = new JLabel("Όνομα:");
        typeLabel = new JLabel("Τύπος:");
        addressLabel = new JLabel("Διεύθυνση:");
        cityLabel = new JLabel("Πόλη:");
        postalCodeLabel = new JLabel("Ταχυδρομικός Κώδικας:");
        descriptionLabel =new JLabel("Περιγραφή:");
        viewLabel = new JLabel("Θέα:");
        bathroomLabel = new JLabel("Μπάνιο:");
        laundryLabel = new JLabel("Πλύσιμο ρούχων:");
        entertainmentLabel = new JLabel("Ψυχαγωγία:");
        airConditioningLabel = new JLabel("Θέρμανση και κλιματισμός:");
        internetLabel = new JLabel("Διαδίκτυο:");
        kitchenLabel = new JLabel("Κουζίνα και τραπεζαρία:");
        outdoorsLabel = new JLabel("Εξωτερικός χώρος:");
        parkingLabel = new JLabel("Χώρος στάθμευσης:");
        nameField = new JTextField(20);
        addressField = new JTextField(20);
        cityField = new JTextField(20);
        postalCodeField = new JTextField(20);
        descriptionField = new JTextArea(50, 30);
        hotelRadio = new JRadioButton("Ξενοδοχείο");
        apartmentRadio = new JRadioButton("Διαμέρισμα");
        maisonetteRadio = new JRadioButton("Μεζονέτα");

        viewBoxValues = new String[]{"Θέα σε πισίνα", "θέα σε παραλία", "Θέα στη θάλασσα", "Θέα στο λιμάνι", "Θέα στο βουνό", "Θέα στο δρόμο"};
        viewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        viewCheckBoxes = new JCheckBox[viewBoxValues.length];
        int i = 0;
        for (String viewBoxValue : viewBoxValues) {
            viewCheckBoxes[i] = new JCheckBox(viewBoxValue);
            viewPanel.add(viewCheckBoxes[i]);
            i++;
        }

        bathroomBoxValues = new String[]{"Πιστολάκι μαλλιών"};
        bathroomCheckBoxes = new JCheckBox[bathroomBoxValues.length];
        bathroomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        i = 0;
        for (String bathroomBoxValue : bathroomBoxValues) {
            bathroomCheckBoxes[i] = new JCheckBox(bathroomBoxValue);
            bathroomPanel.add(bathroomCheckBoxes[i]);
            i++;
        }

        laundryBoxValues = new String[]{"Πλυντήριο ρούχων", "Στεγνωτήριο"};
        laundryCheckBoxes = new JCheckBox[laundryBoxValues.length];
        laundryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        i = 0;
        for (String laundryBoxValue : laundryBoxValues) {
            laundryCheckBoxes[i] = new JCheckBox(laundryBoxValue);
            laundryPanel.add(laundryCheckBoxes[i]);
            i++;
        }

        entertainmentBoxValues = new String[]{"Τηλεόραση"};
        entertainmentCheckBoxes = new JCheckBox[entertainmentBoxValues.length];
        entertainmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        i = 0;
        for (String entertainmentValue : entertainmentBoxValues) {
            entertainmentCheckBoxes[i] = new JCheckBox(entertainmentValue);
            entertainmentPanel.add(entertainmentCheckBoxes[i]);
            i++;
        }

        airConditioningBoxValues = new String[]{"Εσωτερικό τζάκι", "Κλιματισμός", "Κεντρική θέρμανση"};
        airConditioningCheckBoxes = new JCheckBox[airConditioningBoxValues.length];
        airConditioningPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        i = 0;
        for (String airConditioningBoxValue : airConditioningBoxValues) {
            airConditioningCheckBoxes[i] = new JCheckBox(airConditioningBoxValue);
            airConditioningPanel.add(airConditioningCheckBoxes[i]);
            i++;
        }

        internetBoxValues = new String[]{"Wi-Fi", "Ethernet"};
        internetCheckBoxes = new JCheckBox[internetBoxValues.length];
        internetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        i = 0;
        for (String internetBoxValue : internetBoxValues) {
            internetCheckBoxes[i] = new JCheckBox(internetBoxValue);
            internetPanel.add(internetCheckBoxes[i]);
            i++;
        }

        kitchenBoxValues = new String[]{"Κουζίνα", "Ψυγείο", "Φούρνος μικροκυμάτων", "Μαγειρικά είδη", "Πιάτα και μαχαιροπίρουνα", "Πλυντήριο πιάτων", "Καφετιέρα"};
        kitchenCheckBoxes = new JCheckBox[kitchenBoxValues.length];
        kitchenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        i = 0;
        for (String kitchenBoxValue : kitchenBoxValues) {
            kitchenCheckBoxes[i] = new JCheckBox(kitchenBoxValue);
            kitchenPanel.add(kitchenCheckBoxes[i]);
            i++;
        }

        outdoorsBoxValues = new String[]{"Μπαλκόνι", "Αυλή"};
        outdoorsCheckBoxes = new JCheckBox[outdoorsBoxValues.length];
        outdoorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        i = 0;
        for (String outdoorsBoxValue : outdoorsBoxValues) {
            outdoorsCheckBoxes[i] = new JCheckBox(outdoorsBoxValue);
            outdoorsPanel.add(outdoorsCheckBoxes[i]);
            i++;
        }

        parkingBoxValues = new String[]{"Δωρεάν χώρος στάθμεσης στην ιδιοκτησία", "Δωρεάν πάρκινγκ στο δρόμο"};
        parkingCheckBoxes = new JCheckBox[parkingBoxValues.length];
        parkingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        i = 0;
        for (String parkingBoxValue : parkingBoxValues) {
            parkingCheckBoxes[i] = new JCheckBox(parkingBoxValue);
            parkingPanel.add(parkingCheckBoxes[i]);
            i++;
        }

        typeGroup = new ButtonGroup();
        typeGroup.add(hotelRadio);
        typeGroup.add(apartmentRadio);
        typeGroup.add(maisonetteRadio);

        typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(hotelRadio);
        typePanel.add(apartmentRadio);
        typePanel.add(maisonetteRadio);

    }

    private void addingComponents(){
        labelsPanel.add(nameLabel);
        labelsPanel.add(typeLabel);
        labelsPanel.add(addressLabel);
        labelsPanel.add(cityLabel);
        labelsPanel.add(postalCodeLabel);
        labelsPanel.add(descriptionLabel);
        labelsPanel.add(viewLabel);
        labelsPanel.add(bathroomLabel);
        labelsPanel.add(laundryLabel);
        labelsPanel.add(entertainmentLabel);
        labelsPanel.add(airConditioningLabel);
        labelsPanel.add(internetLabel);
        labelsPanel.add(kitchenLabel);
        labelsPanel.add(outdoorsLabel);
        labelsPanel.add(parkingLabel);

        fieldsPanel.add(nameField);
        fieldsPanel.add(typePanel);
        fieldsPanel.add(addressField);
        fieldsPanel.add(cityField);
        fieldsPanel.add(postalCodeField);
        fieldsPanel.add(descriptionField);
        fieldsPanel.add(viewPanel);
        fieldsPanel.add(bathroomPanel);
        fieldsPanel.add(laundryPanel);
        fieldsPanel.add(entertainmentPanel);
        fieldsPanel.add(airConditioningPanel);
        fieldsPanel.add(internetPanel);
        fieldsPanel.add(kitchenPanel);
        fieldsPanel.add(outdoorsPanel);
        fieldsPanel.add(parkingPanel);

    }


    private void addingCheckboxesFromList(JPanel panel, JCheckBox[] checkboxes, String[] values) {
        int i = 0;
        for (String value : values) {
            checkboxes[i].setText(value);
            panel.add(checkboxes[i]);
            i++;
        }
    }

    private void actionListeners(){

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int type = 0;
                if (hotelRadio.isSelected())
                    type = AccommodationConstants.Ξενοδοχείο.getI();
                else if (apartmentRadio.isSelected())
                    type = AccommodationConstants.Διαμέρισμα.getI();
                else if (maisonetteRadio.isSelected())
                    type = AccommodationConstants.Μεζονέτα.getI();

                Facilities f = new Facilities();
                for (JCheckBox viewCheckBox : viewCheckBoxes) {
                    if (viewCheckBox.isSelected()){
                        f.addView(viewCheckBox.getText());
                    }
                }

                for (JCheckBox bathroomCheckBox : bathroomCheckBoxes) {
                    if (bathroomCheckBox.isSelected()){
                        f.addBathroom(bathroomCheckBox.getText());
                    }
                }

                for (JCheckBox laundryCheckBox : laundryCheckBoxes) {
                    if (laundryCheckBox.isSelected()){
                        f.addLaundry(laundryCheckBox.getText());
                    }
                }

                for (JCheckBox entertainmentCheckBox : entertainmentCheckBoxes) {
                    if (entertainmentCheckBox.isSelected()){
                        f.addEntertainment(entertainmentCheckBox.getText());
                    }
                }

                for (JCheckBox airConditioningCheckBox : airConditioningCheckBoxes) {
                    if (airConditioningCheckBox.isSelected()){
                        f.addAirConditioning(airConditioningCheckBox.getText());
                    }
                }

                for (JCheckBox internetCheckBox : internetCheckBoxes) {
                    if (internetCheckBox.isSelected()){
                        f.addInternet(internetCheckBox.getText());
                    }
                }

                for (JCheckBox kitchenCheckBox : kitchenCheckBoxes) {
                    if (kitchenCheckBox.isSelected()){
                        f.addKitchen(kitchenCheckBox.getText());
                    }
                }

                for (JCheckBox outdoorsCheckBox : outdoorsCheckBoxes) {
                    if (outdoorsCheckBox.isSelected()){
                        f.addOutdoors(outdoorsCheckBox.getText());
                    }
                }

                for (JCheckBox parkingCheckBox : parkingCheckBoxes) {
                    if (parkingCheckBox.isSelected()){
                        f.addParking(parkingCheckBox.getText());
                    }
                }


                try {
                    int value = Integer.parseInt(postalCodeField.getText());
                    // use the value here
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(null, "Πληκτρολογήστε έγκυρο ταχυδρομικό κώδικα.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                theAccommodation = new Accommodation(
                        (Host)master.getLoggedInUser(),
                        nameField.getText(),
                        type,
                        addressField.getText(),
                        cityField.getText(),
                        Integer.parseInt(postalCodeField.getText()),
                        descriptionField.getText(),
                        f
                );
                if (toEditAccommodation == null) {
                    try {
                        if (theAccommodation.addAccommodation())
                            dispose();
                    } catch (EmptyInputException | UnexpectedError err) {
                        JOptionPane.showMessageDialog(null, err.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    try {
                        if (theAccommodation.editAccommodation(toEditAccommodation))
                            dispose();
                    } catch (EmptyInputException | UnexpectedError err){
                        JOptionPane.showMessageDialog(null, err.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // System.out.println(toSaveAccommodation);

            }
        });
    }

    private void getFieldsToEdit() {
        if (toEditAccommodation != null) {
            nameField.setText(toEditAccommodation.getName());
            switch (toEditAccommodation.getType()){
                case 1: hotelRadio.setSelected(true); break;
                case 2: apartmentRadio.setSelected(true); break;
                case 3: maisonetteRadio.setSelected(true); break;
            }
            addressField.setText(toEditAccommodation.getAddress());
            cityField.setText(toEditAccommodation.getCity());
            postalCodeField.setText(String.valueOf(toEditAccommodation.getPostalCode()));
            descriptionField.setText(toEditAccommodation.getDescription());

            if (toEditAccommodation.getFacilities().getView()!=null) {
                for (JCheckBox viewCheckBox : viewCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getView().contains(viewCheckBox.getText())){
                        viewCheckBox.setSelected(true);
                    }
                }
            }

            if (toEditAccommodation.getFacilities().getBathroom()!=null) {
                for (JCheckBox bathroomCheckBox : bathroomCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getBathroom().contains(bathroomCheckBox.getText())){
                        bathroomCheckBox.setSelected(true);
                    }
                }
            }

            if (toEditAccommodation.getFacilities().getLaundry()!=null) {
                for (JCheckBox laundryCheckBox : laundryCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getLaundry().contains(laundryCheckBox.getText())){
                        laundryCheckBox.setSelected(true);
                    }
                }
            }

            if (toEditAccommodation.getFacilities().getEntertainment()!=null) {
                for (JCheckBox entertainmentCheckBox : entertainmentCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getEntertainment().contains(entertainmentCheckBox.getText())){
                        entertainmentCheckBox.setSelected(true);
                    }
                }
            }

            if (toEditAccommodation.getFacilities().getAirConditioning()!=null) {
                for (JCheckBox airConditioningCheckBox : airConditioningCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getAirConditioning().contains(airConditioningCheckBox.getText())){
                        airConditioningCheckBox.setSelected(true);
                    }
                }
            }

            if (toEditAccommodation.getFacilities().getInternet()!=null) {
                for (JCheckBox internetCheckBox : internetCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getInternet().contains(internetCheckBox.getText())){
                        internetCheckBox.setSelected(true);
                    }
                }
            }

            if (toEditAccommodation.getFacilities().getKitchen()!=null) {
                for (JCheckBox kitchenCheckBox : kitchenCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getKitchen().contains(kitchenCheckBox.getText())){
                        kitchenCheckBox.setSelected(true);
                    }
                }
            }

            if (toEditAccommodation.getFacilities().getOutdoors()!=null) {
                for (JCheckBox outdoorsCheckBox : outdoorsCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getOutdoors().contains(outdoorsCheckBox.getText())){
                        outdoorsCheckBox.setSelected(true);
                    }
                }
            }

            if (toEditAccommodation.getFacilities().getParking()!=null) {
                for (JCheckBox parkingCheckBox : parkingCheckBoxes) {
                    if (toEditAccommodation.getFacilities().getParking().contains(parkingCheckBox.getText())){
                        parkingCheckBox.setSelected(true);
                    }
                }
            }

        }
    }

//    public static void main(String[] args) {
//        Facilities f = new Facilities();
//        f.addView("Θέα σε πισίνα");
//        f.addView("Θέα στο βουνό");
//        f.addBathroom("Πιστολάκι μαλλιών");
//        f.addAirConditioning("Εσωτερικό τζάκι");
//        f.addOutdoors("Αυλή");
//
//        Accommodation acc = new Accommodation(null,"Spiti", 1, "thess", "athens", 23333, "oraio spiti", f);
//        AccommodationController a = new AccommodationController(null, acc);
//    }

}
