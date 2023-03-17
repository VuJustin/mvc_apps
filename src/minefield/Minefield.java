package minefield;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import mvc.*;

public class Minefield extends Model {
    public static int mine_percent = 5;
    private Patch[][] patches;
    private Point playerLocation;
    private List<Point> path;
    private int dim = 10;

    public Minefield() {
        patches = new Patch[dim][dim];
        Random random = new Random();

        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                patches[row][col] = new Patch();
                if (random.nextInt(100) < mine_percent) {
                    patches[row][col].mined = true;
                }
            }
        }

        playerLocation = new Point(0, 0);
        patches[0][0].occupied = true;
        patches[dim - 1][dim - 1].goal = true;
        patches[dim - 1][dim - 1].mined = false;
        path = new LinkedList<Point>();
        path.add(playerLocation);
        updateMinedNeighbors();
    }

    public void initialize() {

    }
    public Iterator<Point> getPath(){
        return path.iterator();
    }
    private void updateMinedNeighbors() {
        for (int row = 0; row < dim; row++) {
            for (int col = 0; col < dim; col++) {
                int count = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        if (dr == 0 && dc == 0)
                            continue;
                        int nr = row + dr;
                        int nc = col + dc;
                        if (nr >= 0 && nr < dim && nc >= 0 && nc < dim && patches[nr][nc].mined) {
                            count++;
                        }
                    }
                }
                patches[row][col].numMinedNubrs = count;
            }
        }
    }

    public Patch getPatch(int row, int col) {
        return patches[row][col];
    }

    public int getDim() {
        return dim;
    }

    public int getPlayerXC() {
        return (int) playerLocation.getX();
    }

    public int getPlayerYC() {
        return (int) playerLocation.getY();
    }

    public void move(Heading heading) throws Exception {
        int newRow = getPlayerXC();
        int newCol = getPlayerYC();

        switch (heading) {
            case NORTH:
                newRow--;
                break;
            case SOUTH:
                newRow++;
                break;
            case EAST:
                newCol++;
                break;
            case WEST:
                newCol--;
                break;
            case NORTHWEST:
                newRow--;
                newCol--;
                break;
            case NORTHEAST:
                newRow--;
                newCol++;
                break;
            case SOUTHWEST:
                newRow++;
                newCol--;
                break;
            case SOUTHEAST:
                newRow++;
                newCol++;
                break;
        }

        if (newRow < 0 || newRow >= dim || newCol < 0 || newCol >= dim) {
            throw new Exception("You cannot move off the grid.");
        }

        if (patches[newRow][newCol].mined) {
            throw new Exception("You stepped on a mine. Game over.");
        }

        if (patches[newRow][newCol].goal) {
            throw new Exception("You reached the goal. You won!");
        }

        patches[getPlayerXC()][getPlayerYC()].occupied = false;
        playerLocation = new Point(newRow, newCol);
        patches[getPlayerXC()][getPlayerYC()].occupied = true;
        path.add(playerLocation);

        changed();
    }
    class Point implements Serializable {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
