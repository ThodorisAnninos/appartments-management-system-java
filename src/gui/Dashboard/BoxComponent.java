package gui.Dashboard;

import java.awt.*;
import javax.swing.*;


public class BoxComponent extends JPanel {

    private JLabel iconLabel;
    private JLabel titleLabel;
    private JLabel valueLabel;
    
    public void setData(String title, String value, Color color, Icon icon){
        titleLabel.setText(title);
        valueLabel.setText(value);
        setBackground(color);
        iconLabel.setIcon(icon);
        iconLabel.setText("");
    }

    public BoxComponent() {
        initComponents();
        //setOpaque(false);
    }


    private void initComponents() {

        titleLabel = new JLabel();
        valueLabel = new JLabel();
        iconLabel = new JLabel();

        setBackground(new Color(255, 204, 204));

        titleLabel.setFont(new Font("Verdana", 1, 24));
        titleLabel.setText("title");

        valueLabel.setFont(new Font("Verdana", 1, 48));
        valueLabel.setText("value");

        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        iconLabel.setText("icon");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(iconLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(18, 18, 18)
                        .addComponent(valueLabel)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }




}
