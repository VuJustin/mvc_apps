package minefield;

import mvc.AppFactory;
import mvc.AppPanel;
import javax.swing.*;

import mvc.*;

public class MinefieldPanel extends AppPanel {
    private JButton northButton, southButton, eastButton, westButton,
            northeastButton, northwestButton, southeastButton, southwestButton;

    public MinefieldPanel(AppFactory factory) {

        super(factory);

        northButton = new JButton("N");
        northButton.addActionListener(this);
        southButton = new JButton("S");
        southButton.addActionListener(this);
        eastButton = new JButton("E");
        eastButton.addActionListener(this);
        westButton = new JButton("W");
        westButton.addActionListener(this);
        northeastButton = new JButton("NE");
        northeastButton.addActionListener(this);
        northwestButton = new JButton("NW");
        northwestButton.addActionListener(this);
        southeastButton = new JButton("SE");
        southeastButton.addActionListener(this);
        southwestButton = new JButton("SW");
        southwestButton.addActionListener(this);

        // Was orginally controlPanel.add, changed to super.add()
        // Fixed
        super.insertComponent(northButton);
        super.insertComponent(southButton);
        super.insertComponent(eastButton);
        super.insertComponent(westButton);
        super.insertComponent(northeastButton);
        super.insertComponent(northwestButton);
        super.insertComponent(southeastButton);
        super.insertComponent(southwestButton);

    }

    public static void main(String[] args) {
        AppFactory factory = new MinefieldFactory();
        AppPanel panel = new MinefieldPanel(factory);
        panel.display();
    }
}