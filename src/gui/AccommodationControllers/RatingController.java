package gui.AccommodationControllers;

import api.Accommodation;
import api.Customer;
import api.Database;
import api.Exceptions.EmptyInputException;
import api.Exceptions.UnexpectedError;
import api.Rating;
import gui.Dashboard.DashboardScreen;
import gui.Dashboard.DisplayAccommodation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RatingController extends JDialog {
    JLabel title, starsLabel, descriptionLabel;
    JTextArea descriptionField;
    StarRater stars;

    private JPanel labelsPanel, fieldsPanel, controllers;
    private JButton saveBtn, cancelBtn;

    private JPanel starsPanel;
    private DashboardScreen master;
    private Rating edit;
    private Accommodation accommodation;
    DisplayAccommodation d;

    public RatingController(DashboardScreen master, DisplayAccommodation d, Rating edit, Accommodation acc){
        this.d = d;
        this.accommodation = acc;
        this.edit = edit;
        creatingComponents();
        addingComponents();
        actionListeners();
        getToEdit();
        buildFrame();
        this.master = master;
    }


    private void buildFrame(){
        setTitle("Προσθήκη αξιολόγησης");

        add(title, BorderLayout.PAGE_START);
        add(labelsPanel, BorderLayout.LINE_START);
        add(fieldsPanel, BorderLayout.CENTER);
        add(controllers, BorderLayout.PAGE_END);

        setLocationRelativeTo(null);
        pack();
        //setSize(400, getPreferredSize().height);

        setVisible(true);
    }

    private void creatingComponents(){
        title = new JLabel("Προσθήκη αξιολόγησης");
        title.setFont(new Font("Verdana", Font.BOLD, 20));

        saveBtn = new JButton("Αποθήκευση");
        cancelBtn = new JButton("Ακύρωση");
        controllers = new JPanel();

        starsLabel = new JLabel("Επιλέξτε βαθμολογία:");
        descriptionLabel = new JLabel("Περιγραφή:");

        stars = new StarRater(5);
        starsPanel = new JPanel();
        starsPanel.add(stars);
        descriptionField = new JTextArea();

        labelsPanel = new JPanel();
        labelsPanel.setLayout(new GridLayout(0, 1));
        fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(0, 1));

    }

    public void getToEdit() {
        if (edit != null) {
            descriptionField.setText(edit.getDescription());
            stars.setRating(edit.getStars());
        }
    }

    private void addingComponents() {
        controllers.add(saveBtn);
        controllers.add(cancelBtn);

        labelsPanel.add(starsLabel);
        labelsPanel.add(descriptionLabel);

        fieldsPanel.add(starsPanel);
        fieldsPanel.add(descriptionField);
    }

    private void actionListeners() {
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rating r = new Rating((Customer) master.getLoggedInUser(), accommodation, stars.getSelection(), descriptionField.getText());
                boolean b = false;
                if (edit == null){
                    try {
                        b = r.addRating();
                    } catch (EmptyInputException | UnexpectedError err){
                        JOptionPane.showMessageDialog(null, err.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }

                    if (b && d != null) {
                        d.loadTable();
                        d.buttonsControl();
                    }
                }
                else{
                    try {
                        b = r.editRating(edit);
                    } catch (EmptyInputException | UnexpectedError err){
                        JOptionPane.showMessageDialog(null, err.getMessage(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                    }
                    if (b && d != null) {
                        d.loadTable();
                        d.buttonsControl();
                    }
                }
                if (b)
                    dispose();
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
