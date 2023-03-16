package minefield;

import mvc.*;
import java.awt.*;

public class MinefieldView extends View {
    private Cell cells[][];
    public MinefieldView(Minefield minefield) {
        super(minefield);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        Minefield minefield = (Minefield) model;

        MinefieldShape shape = new MinefieldShape(minefield);

        shape.draw((Graphics2D) gc);
        gc.setColor(oldColor);
    }
}
