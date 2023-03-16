package minefield;
import java.io.Serializable;

class Patch implements Serializable {
    boolean isMined = false;
    int numMinedNbrs = 0;
    boolean occupied = false;
    boolean goal = false;
}