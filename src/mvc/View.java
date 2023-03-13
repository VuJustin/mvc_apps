package mvc;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class View extends JPanel implements PropertyChangeListener {
    protected Model model;

    public View(Model m){
        this.model = m;
        m.addPropertyChangeListener(this);
    }
    public View() {
        model.addPropertyChangeListener(this);
    }

    public void setModel(Model m){
        if(this.model != null){
            this.model.removePropertyChangeListener(this);
        }
        this.model = m;
        this.model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        revalidate();
        updateUI();
        repaint();
    }
}