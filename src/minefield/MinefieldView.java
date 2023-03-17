package minefield;

import mvc.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;



public class MinefieldView extends View {
    private Cell cells[][];

    public MinefieldView(Minefield minefield) {
        super(minefield);
        initView();

    }

    public void propertyChange(PropertyChangeEvent evt) {
//         if (evt.getPropertyName() != "location") {
//              initView();
//         }
        Minefield mf = (Minefield) model;
        int row = mf.getPlayerXC();
        int col = mf.getPlayerYC();
        cells[row][col].setBackground(Color.RED);
        cells[row][col].setBorder(BorderFactory.createLineBorder(Color.white));
        cells[row][col].setText("" + cells[row][col].patch.numMinedNubrs);
        cells[row][col].repaint();
    }



    private void initView() {
        int dim = ((Minefield) model).getDim();
        cells = new Cell[dim][dim];
        setLayout(new GridLayout(dim, dim));

        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                cells[row][col] = new Cell();
                cells[row][col].setText("?");
                cells[row][col].patch = ((Minefield) model).getPatch(row, col);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.black));
                if (cells[row][col].patch.occupied) {
                    cells[row][col].setBackground(Color.RED);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    cells[row][col].setText("" + cells[row][col].patch.numMinedNubrs);
                }
                if (cells[row][col].patch.goal) {
                    cells[row][col].setBackground(Color.white);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.green));
                }
                this.add(cells[row][col]);
            }
        }

    }
    public void setModel(Minefield mf){
        super.setModel(mf);
        initView();
        System.out.println("setModel in Minefield");
    }
class Cell extends JLabel {
    Patch patch;
}

}
