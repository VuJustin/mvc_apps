package minefield;

import mvc.*;

public class Minefield extends Model {
    public static int mine_percent = 5;

    private int dimensions;
    private Patch field[][];

    public Minefield(){
        // Setting up field dimensions
        dimensions = 10;
        // Creating new minefield w/ associated dimensions
        field = new Patch[dimensions][dimensions];
        // Initializing all patches
        for(int i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                field[i][j] = new Patch();
            }
        }
    }
    public int getDimensions(){ return dimensions; }
    public void setDimensions(int d){ dimensions = d;}

    public Patch getPatch(int row, int col){
        return field[row][col];
    }
}