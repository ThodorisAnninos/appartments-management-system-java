package gui.Dashboard;


import gui.Authentication.AuthenticationScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Menu extends JPanel {

    private JButton accommodationMenu;
    private JButton dashboardMenu;
    private JPanel jPanel2;
    private JLabel logoLabel;
    private JButton logoutMenu;
    private DashboardScreen dashboardScreen;

    public Menu() {
        initComponents();
        myEventListeners();
    }

    public void getDashboardScreen(DashboardScreen dashboardScreen){
        this.dashboardScreen = dashboardScreen;
    }

    private void initComponents() {

        jPanel2 = new JPanel();
        dashboardMenu = new JButton();
        accommodationMenu = new JButton();
        logoutMenu = new JButton();
        logoLabel = new JLabel();

        setBackground(new Color(0, 153, 255));

        jPanel2.setLayout(new GridLayout(0, 1));

        dashboardMenu.setFont(new Font("Verdana", 0, 14));
        URL speedometerIconUrl = getClass().getClassLoader().getResource("Resources/speedometer2.png");
        dashboardMenu.setIcon(new ImageIcon(speedometerIconUrl));
        dashboardMenu.setText("Πίνακας ελέγχου");
        dashboardMenu.setBorderPainted(false);
        dashboardMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dashboardMenu.setHorizontalAlignment(SwingConstants.LEFT);
        dashboardMenu.setHorizontalTextPosition(SwingConstants.RIGHT);
        dashboardMenu.setMaximumSize(new Dimension(200, 40));
        dashboardMenu.setMinimumSize(new Dimension(200, 40));
        dashboardMenu.setPreferredSize(new Dimension(200, 40));
        dashboardMenu.setSize(new Dimension(200, 40));

        jPanel2.add(dashboardMenu);

        accommodationMenu.setFont(new Font("Verdana", 0, 14));
        URL buildingsIconUrl = getClass().getClassLoader().getResource("Resources/buildings.png");
        accommodationMenu.setIcon(new ImageIcon(buildingsIconUrl));
        accommodationMenu.setText("Καταλύματα");
        accommodationMenu.setBorderPainted(false);
        accommodationMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accommodationMenu.setHorizontalAlignment(SwingConstants.LEFT);
        accommodationMenu.setHorizontalTextPosition(SwingConstants.RIGHT);
        accommodationMenu.setMaximumSize(new Dimension(200, 40));
        accommodationMenu.setMinimumSize(new Dimension(200, 40));
        accommodationMenu.setPreferredSize(new Dimension(200, 40));
        accommodationMenu.setSize(new Dimension(200, 40));

        jPanel2.add(accommodationMenu);

        logoutMenu.setFont(new Font("Verdana", 0, 14));
        URL logoutIconUrl = getClass().getClassLoader().getResource("Resources/logout.png");
        logoutMenu.setIcon(new ImageIcon(logoutIconUrl));
        logoutMenu.setText("Αποσύνδεση");
        logoutMenu.setBorderPainted(false);
        logoutMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutMenu.setHorizontalAlignment(SwingConstants.LEFT);
        logoutMenu.setHorizontalTextPosition(SwingConstants.RIGHT);
        logoutMenu.setMaximumSize(new Dimension(200, 40));
        logoutMenu.setMinimumSize(new Dimension(200, 40));
        logoutMenu.setPreferredSize(new Dimension(200, 40));
        logoutMenu.setSize(new Dimension(200, 40));

        jPanel2.add(logoutMenu);

        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        URL hotelWhiteIconUrl = getClass().getClassLoader().getResource("Resources/hotel_white.png");
        logoLabel.setIcon(new ImageIcon(hotelWhiteIconUrl));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(logoLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(797, Short.MAX_VALUE))
        );
    }


    public void myEventListeners(){
        logoutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboardScreen.dispose();
                AuthenticationScreen auth = new AuthenticationScreen();
            }
        });

        dashboardMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboardScreen.showDashboard();
            }
        });

        accommodationMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dashboardScreen.showAccommodations();
            }
        });
    }


}
