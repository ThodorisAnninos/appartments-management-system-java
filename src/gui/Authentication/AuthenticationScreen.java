
package gui.Authentication;

import api.Database;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class AuthenticationScreen extends JFrame {


    private JLabel hotelIcon;
    private JLabel infoLabel;
    private JPanel jPanel1;
    private Login login1;
    private Register register1;
    private JLabel welcomeLabel;
    private User user;

    public void setCurrentUser(User u){
        this.user = u;
    }

    public User getCurrentUser() {
        return user;
    }

    public AuthenticationScreen() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Σελίδα Αυθεντικοποίησης Χρήστη");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        URL appLogo = getClass().getClassLoader().getResource("Resources/hotel_logo.png");
        ImageIcon appLogoImg = new ImageIcon(appLogo);
        setIconImage(appLogoImg.getImage());
        login1.setRegisterPanel(register1);
        register1.setLoginPanel(login1);
        login1.setAuthenticationScreen(this);
        register1.setAuthenticationScreen(this);
        Database.load();
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                Database.save();
                e.getWindow().dispose();
            }
        });
    }


    private void initComponents() {

        jPanel1 = new JPanel();
        hotelIcon = new JLabel();
        welcomeLabel = new JLabel();
        infoLabel = new JLabel();
        login1 = new Login();
        register1 = new Register();


        setPreferredSize(new Dimension(700, 600));

        jPanel1.setBackground(new Color(0, 153, 255));

        hotelIcon.setHorizontalAlignment(SwingConstants.CENTER);
        URL iconUrl = getClass().getClassLoader().getResource("Resources/hotel_white.png");
        hotelIcon.setIcon(new ImageIcon(iconUrl));

        welcomeLabel.setFont(new Font("Verdana", 1, 24));
        welcomeLabel.setForeground(new Color(255, 255, 255));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setText("Καλώς Ορίσατε!");

        infoLabel.setFont(new Font("Verdana", 0, 13));
        infoLabel.setForeground(new Color(255, 255, 255));
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setText("<html><center>Πραγματοποιήστε σύνδεση ή <br>εγγραφή στην πλατφόρμα</center></html>");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(hotelIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(welcomeLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoLabel)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(hotelIcon, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(welcomeLabel)
                .addGap(18, 18, 18)
                .addComponent(infoLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(login1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(register1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(login1, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(register1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }


    public static void main(String[] args) {
        AuthenticationScreen a = new AuthenticationScreen();
    }

}
