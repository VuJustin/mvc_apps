package minefield;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MinefieldShape {
    private Minefield minefield;
    private int size, xc, yc;
    private Rectangle2D.Double mine;
    private static final int BORDER_THICKNESS = 1;

    public MinefieldShape(Minefield minefield, int size) {
        this.minefield = minefield;
        this.size = size;
        mine = new Rectangle2D.Double(300, 300, size, size);

    }

    public MinefieldShape(Minefield minefield) {
        this(minefield, 15);
    }

    public void draw(Graphics2D gc) {
        Color oldColor = gc.getColor();
        for (int i = 0; i < 20; i++) {
            gc.setColor(Color.BLACK);
            gc.fillRect(xc, yc, size, size);
            gc.setColor(oldColor);
            gc.fillRect(xc - BORDER_THICKNESS, yc - BORDER_THICKNESS, size - 1, size - 1);
            xc += 15;
        }

    }
}
