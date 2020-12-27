package com.burcumirza.HenryChess.Logic;

import java.util.*;

public abstract class Opening {

    // For debugging purposes, change stillOpen to false. Change back!
    protected boolean stillOpen = true, variations, mistakes;
    protected Move[] openingMove;
    protected int size;
    public static Random rGen = new Random();

    public Opening(int size) {
        this.size = size;
    }

    // variations & mistakes
    public boolean checkAlterations(int m) {
        return false;
    }

    public void checkVariations(int m) {
    }

    // mutator methods
    public void changeBoolean() {
        stillOpen = false;
    }

    // accessor methods
    public Move getMove(int m) {
        return openingMove[m];
    }

    public boolean getBoolean() {
        return stillOpen;
    }

    public int getSize() {
        return size;
    }

}
