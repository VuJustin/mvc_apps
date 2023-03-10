package tools;

import java.beans.*;
import java.io.Serializable;

abstract public class Bean implements Serializable {

    protected PropertyChangeSupport mPcs =
            new PropertyChangeSupport(this);

    protected VetoableChangeSupport mVcs =
            new VetoableChangeSupport(this);


    public void fireVetoableChange(String propertyName, Object oldValue, Object newValue) throws PropertyVetoException {
        mVcs.fireVetoableChange(propertyName, oldValue, newValue);
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        mPcs.firePropertyChange(propertyName, oldValue, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        mVcs.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        mVcs.removeVetoableChangeListener(listener);
    }

}