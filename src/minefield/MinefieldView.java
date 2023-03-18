package minefield;

import mvc.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;



public class MinefieldView extends View {
    private Cell cells[][];

    public MinefieldView(Model m) {
        super(m);
        init();


    }

    public void setModel(Model m){
        super.setModel(m);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                this.cells[row][col].setText("?");
                this.cells[row][col].patch = ((Minefield) model).getPatch(row, col);
                this.cells[row][col].setBorder(BorderFactory.createLineBorder(Color.black));
                if (this.cells[row][col].patch.occupied) {
                    this.cells[row][col].setBackground(Color.RED);
                    this.cells[row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    this.cells[row][col].setText("" + cells[row][col].patch.numMinedNubrs);
                }
                if (this.cells[row][col].patch.goal) {
                    this.cells[row][col].setBackground(Color.white);
                    this.cells[row][col].setBorder(BorderFactory.createLineBorder(Color.green));
                }
            }
        }

        Iterator<Minefield.Point> a = ((Minefield) model).getPath();
        Minefield.Point path;
        //If a has the first point
        if(a.hasNext()) {
            path = a.next();
            this.cells[0][0].setBackground(Color.RED);
            this.cells[0][0].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            this.cells[0][0].setText("" + cells[path.getX()][path.getY()].patch.numMinedNubrs);
            // if Path has next points
            while (a.hasNext()) {
                path = a.next();
                this.cells[path.getX()][path.getY()].setBackground(Color.RED);
                this.cells[path.getX()][path.getY()].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                this.cells[path.getX()][path.getY()].setText("" + cells[path.getX()][path.getY()].patch.numMinedNubrs);
            }
        }

    }

    public void init(){
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

    public void propertyChange(PropertyChangeEvent evt) {

        Minefield mf = (Minefield) model;
        int row = mf.getPlayerXC();
        int col = mf.getPlayerYC();
        cells[row][col].setBackground(Color.RED);
        cells[row][col].setBorder(BorderFactory.createLineBorder(Color.white));
        cells[row][col].setText("" + cells[row][col].patch.numMinedNubrs);
        cells[row][col].repaint();

    }





class Cell extends JLabel {
    Patch patch;
}

}
