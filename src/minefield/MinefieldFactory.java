package minefield;

import mvc.*;

public class MinefieldFactory implements AppFactory {

    public Model makeModel() {
        return new Minefield();
    }

    public View makeView(Model m) {
        return new MinefieldView((Minefield) m);
    }

    public String[] getEditCommands() {
        return new String[] { "N", "S", "E", "W", "NE", "NW", "SW", "SE" };

    }

    // source added 3/15 to support text fields
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type == "N")
            return new MoveCommand(model);
        else if (type == "S")
            return new MoveCommand(model);
        else if (type == "E")
            return new MoveCommand(model);
        else if (type == "W")
            return new MoveCommand(model);
        else if (type == "NW")
            return new MoveCommand(model);
        else if (type == "NE")
            return new MoveCommand(model);
        else if (type == "SW")
            return new MoveCommand(model);
        else if (type == "SE")
            return new MoveCommand(model);
        return null;
    }

    public String getTitle() {
        return "Minefield";
    }

    public String[] getHelp() {
        return new String[] { "click Change to cycle through colors" };
    }

    public String about() {
        return "Minefield version 3.0. Copyright 2020 by Team 2";
    }

}