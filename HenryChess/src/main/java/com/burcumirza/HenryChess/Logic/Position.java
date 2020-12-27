package com.burcumirza.HenryChess.Logic;

public class Position {
    private int row, col;

    public Position(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
