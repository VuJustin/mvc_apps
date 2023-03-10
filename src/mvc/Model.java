package mvc;

public class Model extends Bean {
private boolean unsavedChanges;
private String fileName;

public Model(){
    unsavedChanges = false;
    fileName = null;
}
public Model(boolean change, String file){
    unsavedChanges = change;
    fileName = file;
}

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }
    public void setUnsavedChanges(boolean change){
    boolean old = this.unsavedChanges;
    this.unsavedChanges = change;
    this.firePropertyChange("unsavedChanges", old, this.unsavedChanges);
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        String old = this.fileName;
        this.fileName = fileName;
        //ask
        this.firePropertyChange("fileName", old, this.fileName);
    }

    // ask
    public void changed(){
    this.unsavedChanges = true;
    firePropertyChange("unSavedChanges", false, true);
    }
}
