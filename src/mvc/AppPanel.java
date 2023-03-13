package mvc;

import tools.SafeFrame;
import tools.Utilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/*
 * This is the MVC controller.
 */
public class AppPanel extends JPanel implements ActionListener {
    private Model model;
    private ControlPanel controls;
    private View view;

    // note: Stoplight Panel requires factory as a parameter
    public AppPanel(AppFactory factory) {
        // create model, install controls & view
        model = new Model();
        view = new View();
        controls = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(view);
        // create my frame with menus and display it
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(model.getFileName());
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"North", "South", "East", "West", "Pen", "Clear", "Color", "Steps"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        model = (Model) is.readObject();
                        //view.setTurtle(turtle);
                        is.close();
                    }

                    break;

                }

                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        model = new Model();
                        //view.setTurtle(turtle);
                    }
                    break;
                }

                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform("Justin Vu, Assignment: Turtle Graphics, 3/2/23");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "North: Changes Turtle's Direction to North", "South: Changes Turtle's Direction to South",
                            "East: Changes Turtle's Direction to East", "West: Changes Turtle's Direction to West",
                            "Pen: Changes Turtle's Pen", "Steps: Moves turtle based on steps",
                            "Color: Changes Turtle's color", "Clear: Clears out turtle's path"
                    };
                    Utilities.inform(cmmds);
                    break;

                }
                // If command is not recognized, throw out exception
                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }

        } catch (Exception ex) {
            // Prints out exception errors
            Utilities.error(ex);
        }
    }

    /*
     * It's often handy to have control panel as an
     * innerclass so its controls can be accessed from
     * outer class and vice-versa. It also encapsulates all the messy layout logic
     */
    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.PINK);
            JPanel p = new JPanel();
            /* Should Control Panel add buttons for mvc? */
            /* Adding Action Listeners to the buttons and adding it into the panel */
        }


    }

}
