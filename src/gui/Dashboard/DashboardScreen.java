package gui.Dashboard;

import api.Database;
import api.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;


public class DashboardScreen extends JFrame {

    private MainAccommodationsContent accommodationsPanel;
    private MainDashboardContent dashboardPanel;
    private JPanel jPanel1;
    private Menu menu1;
    private CardLayout layout;
    private User loggedInUser;

    public DashboardScreen(User u) {
        this.loggedInUser = u;
//        System.out.println(u);
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Πίνακας Ελέγχου");
        //setUndecorated(true);
        URL appLogo = getClass().getClassLoader().getResource("Resources/hotel_logo.png");
        ImageIcon appLogoImg = new ImageIcon(appLogo);
        setIconImage(appLogoImg.getImage());
        setVisible(true);
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

    public MainAccommodationsContent getAccommodationsPanel() {
        return accommodationsPanel;
    }

    public MainDashboardContent getDashboardPanel() {
        return dashboardPanel;
    }

    private void initComponents() {

        menu1 = new Menu();
        menu1.getDashboardScreen(this);
        jPanel1 = new JPanel();
        dashboardPanel = new MainDashboardContent(this);
        accommodationsPanel = new MainAccommodationsContent(this);
        layout = new CardLayout();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel1.setLayout(layout);
        jPanel1.add(dashboardPanel, "dashboard");
        jPanel1.add(accommodationsPanel, "accommodations");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu1, GroupLayout.PREFERRED_SIZE, 1263, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    public void showDashboard(){
        layout.show(jPanel1, "dashboard");
    }

    public User getLoggedInUser(){
        return loggedInUser;
    }

    public void showAccommodations(){
        layout.show(jPanel1, "accommodations");
    }

//    public static void main(String[] args) {
//        DashboardScreen d = new DashboardScreen(null);
//    }


}
