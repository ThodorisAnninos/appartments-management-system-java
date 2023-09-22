
package gui.Authentication;

import api.Error;
import api.User;
import gui.Dashboard.DashboardScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JPanel {

    private api.Login loginApi;
    private JButton loginButton;
    private JLabel notAMemberLabel;
    private JLabel passwordErrorLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel titleLabel;
    private JLabel usernameErrorLabel;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JLabel emptyFieldErrorLabel;
    Register regPanel;
    AuthenticationScreen auth;

    public Login() {
        initComponents();
        myActionListeners();
    }

    public void setRegisterPanel(Register regPanel){
        this.regPanel = regPanel;
    }
    public void setAuthenticationScreen(AuthenticationScreen auth) {
        this.auth = auth;
    }

    private void initComponents() {

        titleLabel = new JLabel();
        usernameLabel = new JLabel();
        usernameField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        notAMemberLabel = new JLabel();
        passwordErrorLabel = new JLabel();
        usernameErrorLabel = new JLabel();
        emptyFieldErrorLabel = new JLabel();
        usernameErrorLabel.setPreferredSize(new Dimension(50, 20));
        passwordErrorLabel.setPreferredSize(new Dimension(50, 20));
        emptyFieldErrorLabel.setPreferredSize(new Dimension(50, 20));

        usernameErrorLabel.setVisible(false);
        passwordErrorLabel.setVisible(false);
        emptyFieldErrorLabel.setVisible(false);

        setBackground(new Color(255, 255, 255));

        titleLabel.setFont(new Font("Verdana", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("Σύνδεση");

        usernameLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        usernameLabel.setText("Όνομα χρήστη");

        usernameField.setFont(new Font("Verdana", 0, 12)); // NOI18N

        passwordLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        passwordLabel.setText("Κωδικός Πρόσβασης");

        passwordField.setFont(new Font("Verdana", 0, 12)); // NOI18N

        loginButton.setBackground(new Color(204, 204, 204));
        loginButton.setFont(new Font("Verdana", 0, 12)); // NOI18N
        loginButton.setText("Σύνδεση");


        notAMemberLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        notAMemberLabel.setForeground(new Color(0, 51, 255));
        notAMemberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        notAMemberLabel.setText("Δεν είστε μέλος; Κάντε εγγραφή εδώ!");
        notAMemberLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        passwordErrorLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        passwordErrorLabel.setForeground(new Color(255, 0, 0));
        passwordErrorLabel.setText("PasswordError");

        usernameErrorLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        usernameErrorLabel.setForeground(new Color(255, 0, 0));
        usernameErrorLabel.setText("usernameError");

        emptyFieldErrorLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        emptyFieldErrorLabel.setForeground(new Color(255, 0, 0));
        emptyFieldErrorLabel.setText("emptyFieldError");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(notAMemberLabel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(emptyFieldErrorLabel)
                    .addComponent(usernameErrorLabel)
                    .addComponent(passwordErrorLabel)
                    .addComponent(loginButton)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(passwordLabel)
                        .addComponent(usernameLabel)
                        .addComponent(usernameField)
                        .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        notAMemberLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notAMemberLabelMouseClicked(evt);
            }
        });

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(titleLabel)
                .addGap(8, 8, 8)
                .addComponent(emptyFieldErrorLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(usernameErrorLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(passwordErrorLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButton)
                .addGap(27, 27, 27)
                .addComponent(notAMemberLabel)
                .addContainerGap(272, Short.MAX_VALUE))
        );
    }

    private void notAMemberLabelMouseClicked(java.awt.event.MouseEvent evt) {
        regPanel.setVisible(true);
        setVisible(false);

    }

    private User loggedIn;
    private ArrayList<Error> errors;
    private void myActionListeners(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginApi = new api.Login(
                      usernameField.getText(),
                      String.valueOf(passwordField.getPassword())
                );

                loggedIn = loginApi.login();

                if(loggedIn == null){
                    errors = loginApi.getErrors();

                    for (Error error : errors) {
                        error.checkError(0, emptyFieldErrorLabel);
                        error.checkError(1, usernameErrorLabel);
                        error.checkError(2, passwordErrorLabel);
                    }

                } else{
                    // κάνε σύνδεση
                    //System.out.println("Έγινε σύνδεση!");
                    auth.setCurrentUser(loggedIn);
                    auth.dispose();
                    DashboardScreen d = new DashboardScreen(loggedIn);
                }
            }
        });


    }


}
