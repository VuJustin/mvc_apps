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
    Problems with: New, Save, Open
    Button in wrong place
 */

public class AppPanel extends JPanel implements ActionListener {
    private Model model;
    protected ControlPanel controls;
    private View view;
    private AppFactory appFactory;
    private SafeFrame frame;
    private static int WIDTH = 500;
    private static int HEIGHT = 300;

    public AppPanel(AppFactory appFactory) {
        this.appFactory = appFactory;
        // create model, install controls & view
        this.model = appFactory.makeModel();
        // Setting the name of model
        this.view = appFactory.makeView(model);
        this.view.setBackground(Color.GRAY);
        // Control Panel created
        controls = new ControlPanel();
        controls.setBackground(Color.PINK);
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(view);
        // create my frame with menus and display it
        frame = new SafeFrame();
        frame.setTitle(appFactory.getTitle());
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setSize(WIDTH, HEIGHT);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[] { "New", "Save", "Save As", "Open", "Quit" }, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", appFactory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[] { "About", "Help" }, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {

            // How to execute other commands?
            switch (cmmd) {
                case "Save": {
                    save();
                    break;
                }

                case "Save As": {
                    saveAs();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        model = (Model) is.readObject();
                        // need View class to be done
                        view.setModel(model);
                        is.close();

                    }

                    break;

                }

                case "New": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        model = appFactory.makeModel();
                        view.setModel(model);
                    }
                    break;
                }

                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(appFactory.about());
                    break;
                }

                case "Help": {
                    String[] cmmds = appFactory.getHelp();
                    Utilities.inform(cmmds);
                    break;

                }
                default: {
                    // throw new Exception("Unrecognized command: " + cmmd);
                    Command command = appFactory.makeEditCommand(model, cmmd, this);
                    command.execute();
                }
            }

        } catch (Exception ex) {
            handleException(ex);
        }

    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    private void saveAs() throws Exception {
        String fName = Utilities.getFileName((String) null, false);
        this.model.setFileName(fName);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
        os.writeObject(this.model);
        os.close();
    }
    
    private void save() throws Exception {
        
        if (model.getFileName() == null) {
            saveAs();
        } else {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(model.getFileName()));
            os.writeObject(model);
            os.close();
        }
    }

    public void display() {
        frame.setVisible(true);
    }

    //Added a insertComponent to add components into control panel
    public void insertComponent(JComponent a){
        controls.add(a);
    }
    /*
     * It's often handy to have control panel as an
     * innerclass so its controls can be accessed from
     * outer class and vice-versa. It also encapsulates all the messy layout logic
     */
    class ControlPanel extends JPanel {
        public JPanel controlPanel;
        public ControlPanel() {
            setBackground(Color.PINK);
            controlPanel = new JPanel();
            /* Should Control Panel add buttons for mvc? */
            /* Adding Action Listeners to the buttons and adding it into the panel */
        }
        public void addC(JComponent a){
            controlPanel = new JPanel();
            controlPanel.add(a);
            add(a);
        }
    }

}
