
package gui.Authentication;

import api.Customer;
import api.Error;
import api.Host;
import api.User;
import gui.Dashboard.DashboardScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Struct;
import java.util.ArrayList;

public class Register extends JPanel {

    private JRadioButton customerRadioButton;
    private JLabel existingMemberLabel;
    private JRadioButton hostRadioButton;
    private JTextField nameField;
    private JLabel nameLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JButton registerButton;
    private JLabel repeatPasswordErrorLabel;
    private JPasswordField repeatPasswordField;
    private JLabel repeatPasswordLabel;
    private JLabel roleLabel;
    private JTextField surnameField;
    private JLabel surnameLabel;
    private JLabel titleLabel;
    private JLabel usernameErrorLabel;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel emptyFieldErrorLabel;

    private ButtonGroup groupRoles;
    Login logPanel;
    AuthenticationScreen auth;
    private api.Register registerApi;

    public Register() {
        initComponents();
        myActionListeners();
        //customerRadioButton.setSelected(true);
    }
    
    public void setLoginPanel(Login logPanel){
        this.logPanel = logPanel;
    }

    public void setAuthenticationScreen(AuthenticationScreen auth) {
        this.auth = auth;
    }


    private void initComponents() {

        titleLabel = new JLabel();
        nameLabel = new JLabel();
        nameField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        registerButton = new JButton();
        existingMemberLabel = new JLabel();
        surnameLabel = new JLabel();
        surnameField = new JTextField();
        usernameLabel = new JLabel();
        usernameField = new JTextField();
        roleLabel = new JLabel();
        hostRadioButton = new JRadioButton();
        customerRadioButton = new JRadioButton();
        repeatPasswordLabel = new JLabel();
        repeatPasswordField = new JPasswordField();
        repeatPasswordErrorLabel = new JLabel();
        usernameErrorLabel = new JLabel();
        emptyFieldErrorLabel = new JLabel();
        groupRoles = new ButtonGroup();

        emptyFieldErrorLabel.setPreferredSize(new Dimension(50, 20));
        emptyFieldErrorLabel.setVisible(false);

        usernameErrorLabel.setPreferredSize(new Dimension(50, 20));
        usernameErrorLabel.setVisible(false);

        repeatPasswordErrorLabel.setPreferredSize(new Dimension(50, 20));
        repeatPasswordErrorLabel.setVisible(false);

        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(350, 540));

        titleLabel.setFont(new Font("Verdana", 1, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("Εγγραφή");

        nameLabel.setFont(new Font("Verdana", 0, 12));
        nameLabel.setText("Όνομα");

        nameField.setFont(new Font("Verdana", 0, 12));

        passwordLabel.setFont(new Font("Verdana", 0, 12));
        passwordLabel.setText("Κωδικός Πρόσβασης");

        passwordField.setFont(new Font("Verdana", 0, 12));

        registerButton.setBackground(new Color(204, 204, 204));
        registerButton.setFont(new Font("Verdana", 0, 12));
        registerButton.setText("Εγγραφή");

        existingMemberLabel.setFont(new Font("Verdana", 0, 12));
        existingMemberLabel.setForeground(new Color(0, 51, 255));
        existingMemberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        existingMemberLabel.setText("Είστε μέλος; Κάντε σύνδεση εδώ!");
        existingMemberLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        existingMemberLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                existingMemberLabelMouseClicked(evt);
            }
        });

        surnameLabel.setFont(new Font("Verdana", 0, 12));
        surnameLabel.setText("Επίθετο");

        surnameField.setFont(new Font("Verdana", 0, 12));

        usernameLabel.setFont(new Font("Verdana", 0, 12));
        usernameLabel.setText("Όνομα χρήστη");

        usernameField.setFont(new Font("Verdana", 0, 12));

        roleLabel.setFont(new Font("Verdana", 0, 12));
        roleLabel.setText("Ρόλος");

        hostRadioButton.setFont(new Font("Verdana", 0, 12));
        hostRadioButton.setText("Ιδιοκτήτης");

        customerRadioButton.setFont(new Font("Verdana", 0, 12));
        customerRadioButton.setText("Επισκέπτης");

        groupRoles.add(hostRadioButton);
        groupRoles.add(customerRadioButton);

        repeatPasswordLabel.setFont(new Font("Verdana", 0, 12));
        repeatPasswordLabel.setText("Επανάληψη Κωδικού Πρόσβασης");

        repeatPasswordField.setFont(new Font("Verdana", 0, 12));

        repeatPasswordErrorLabel.setForeground(new Color(255, 0, 51));
        repeatPasswordErrorLabel.setText("repeatPasswordError");

        usernameErrorLabel.setForeground(new Color(255, 0, 51));
        usernameErrorLabel.setText("usernameExistsError");

        emptyFieldErrorLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        emptyFieldErrorLabel.setForeground(new Color(255, 0, 0));
        emptyFieldErrorLabel.setText("emptyFieldError");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(emptyFieldErrorLabel)
                    .addComponent(usernameErrorLabel)
                    .addComponent(repeatPasswordErrorLabel)
                    .addComponent(registerButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hostRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(customerRadioButton))
                    .addComponent(roleLabel)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(usernameLabel)
                        .addComponent(surnameLabel)
                        .addComponent(passwordLabel)
                        .addComponent(nameLabel)
                        .addComponent(nameField)
                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                        .addComponent(surnameField)
                        .addComponent(usernameField))
                    .addComponent(repeatPasswordLabel)
                    .addComponent(repeatPasswordField, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(existingMemberLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(2, 60)
                .addComponent(titleLabel)
                .addGap(1, 1, 1)
                .addComponent(emptyFieldErrorLabel)
                .addGap(1, 1, 1)
                .addComponent(nameLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(surnameLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernameLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameErrorLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roleLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(hostRadioButton)
                    .addComponent(customerRadioButton))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(repeatPasswordLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repeatPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(repeatPasswordErrorLabel)
                .addGap(3, 3, 3)
                .addComponent(registerButton)
                .addGap(18, 18, 18)
                .addComponent(existingMemberLabel)
                .addGap(32, 32, 32))
        );
    }
    private void existingMemberLabelMouseClicked(java.awt.event.MouseEvent evt) {
        logPanel.setVisible(true);
        setVisible(false);

    }


    private User registered;
    private ArrayList<Error> errors;
    private void myActionListeners() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hostRadioButton.isSelected()){
                    registerApi = new api.Register(new Host(
                            nameField.getText(),
                            surnameField.getText(),
                            usernameField.getText(),
                            String.valueOf(passwordField.getPassword())
                    ), String.valueOf(repeatPasswordField.getPassword()));
                }  else if (customerRadioButton.isSelected()) {
                    registerApi = new api.Register(new Customer(
                            nameField.getText(),
                            surnameField.getText(),
                            usernameField.getText(),
                            String.valueOf(passwordField.getPassword())
                    ), String.valueOf(repeatPasswordField.getPassword()));
                } else {
                    // ΤΙΠΟΤΑ ΔΕΝ ΕΙΝΑΙ ΕΠΙΛΕΓΜΕΝΟ.
                }


                registered = registerApi.addUser();

                if (registered == null) {
                    errors = registerApi.getErrors();
                    for (Error error : errors) {
                        error.checkError(0, repeatPasswordErrorLabel);
                        error.checkError(1, emptyFieldErrorLabel);
                        error.checkError(2, usernameErrorLabel);
                    }
                } else {
                    // κάνε σύνδεση
                    //System.out.println("Έγινε εγγραφή");
                    auth.setCurrentUser(registered);
                    auth.dispose();
                    DashboardScreen d = new DashboardScreen(registered);

                }
            }
        });
    }

}
