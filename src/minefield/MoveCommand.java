package minefield;

import mvc.*;
import tools.Utilities;

public class MoveCommand extends Command {
    Heading heading;

    public MoveCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() {
        Minefield minefield = (Minefield) model;
        try {
            minefield.move(heading);
        } catch (Exception e) {
            model.changed();
            Utilities.inform(e.getMessage());
        }
    }
}
