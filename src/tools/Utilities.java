package tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

public class Utilities {

    public static boolean confirm(String query) {
        int result = JOptionPane.showConfirmDialog((Component)null, query, "Confirm", JOptionPane.OK_CANCEL_OPTION);
        return result == JOptionPane.OK_OPTION;
    }

    public static String ask(String query) {
        return JOptionPane.showInputDialog((Component)null, query);
    }

    public static void inform(String info) {
        JOptionPane.showMessageDialog((Component)null, info);
    }

    public static void inform(String[] items) {
        String helpString = "";

        for(int i = 0; i < items.length; ++i) {
            helpString = helpString + "\n" + items[i];
        }

        inform(helpString);
    }

    public static void error(String gripe) {
        JOptionPane.showMessageDialog((Component)null, gripe, "OOPS!", 0);
    }

    public static void error(Exception gripe) {
        gripe.printStackTrace();
        JOptionPane.showMessageDialog((Component)null, gripe.getMessage(), "OOPS!", 0);
    }

    public static JMenu makeMenu(String name, String[] items, ActionListener handler) {
        JMenu result = new JMenu(name);

        for(int i = 0; i < items.length; ++i) {
            JMenuItem item = new JMenuItem(items[i]);
            item.addActionListener(handler);
            result.add(item);
        }

        return result;
    }

    public static String getFileName(String fName, Boolean open) {
        JFileChooser chooser = new JFileChooser();
        String result = null;
        if (fName != null) {
            chooser.setCurrentDirectory(new File(fName));
        }

        int returnVal;
        if (open) {
            returnVal = chooser.showOpenDialog((Component)null);
            if (returnVal == 0) {
                result = chooser.getSelectedFile().getPath();
            }
        } else {
            returnVal = chooser.showSaveDialog((Component)null);
            if (returnVal == 0) {
                result = chooser.getSelectedFile().getPath();
            }
        }

        return result;
    }
}
