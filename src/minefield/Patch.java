package minefield;


import java.io.Serializable;

public class Patch implements Serializable {
    private static final long serialVersionUID = 1L;

    boolean mined = false;
    int numMinedNubrs = 0;

    boolean occupied = false;
    boolean goal = false;
}