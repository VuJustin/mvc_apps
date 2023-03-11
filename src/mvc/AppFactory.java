package mvc;

public interface AppFactory {
    public Model makeModel();
    public View makeView();
    public String getTitle();
    public String[] getHelp();
    public String about();
    public String[] getEditCommands();
    public Command makeEditCommand(String name);
}
