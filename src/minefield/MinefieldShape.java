package minefield;

import java.awt.*;

public class MinefieldShape {
    public MinefieldShape(Minefield minefield) {

    }

    public void draw(Graphics2D gc) {
        Color oldColor = gc.getColor();
        gc.setColor(Color.WHITE);

        gc.setColor(oldColor);
    }
}
