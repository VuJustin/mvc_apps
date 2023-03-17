package minefield;

import mvc.*;

public class MinefieldFactory implements AppFactory {

    public Model makeModel() {
        return new Minefield();
    }

    public View makeView(Model m) {
        return new MinefieldView(m);
    }

    public String[] getEditCommands() {
        return new String[] { "N", "S", "E", "W", "NE", "NW", "SW", "SE" };

    }

    // source added 3/15 to support text fields
    public Command makeEditCommand(Model model, String type, Object source) {
        Heading heading = null;
        switch (type) {
            case "N":
                heading = Heading.NORTH;
                break;
            case "S":
                heading = Heading.SOUTH;
                break;
            case "E":
                heading = Heading.EAST;
                break;
            case "W":
                heading = Heading.WEST;
                break;
            case "NW":
                heading = Heading.NORTHWEST;
                break;
            case "NE":
                heading = Heading.NORTHEAST;
                break;
            case "SW":
                heading = Heading.SOUTHWEST;
                break;
            case "SE":
                heading = Heading.SOUTHEAST;
                break;
        }
        return new MoveCommand(model, heading);
    }

    public String getTitle() {
        return "Minefield";
    }

    public String[] getHelp() {
        return new String[] { "Click N to move up",
                "Click S to move down",
                "Click W to move left",
                "Click E to move right",
                "Click NW to move upper left",
                "Click NE to move upper right",
                "Click SW to move lower left",
                "Click SE to move lower right" };
    }

    public String about() {
        return "Minefield version 3.0. Copyright 2020 by Team 2";
    }

}