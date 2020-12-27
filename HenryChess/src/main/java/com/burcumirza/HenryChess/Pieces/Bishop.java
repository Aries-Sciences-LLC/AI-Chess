package com.burcumirza.HenryChess.Pieces;

import com.burcumirza.HenryChess.Logic.*;

import java.util.*;

public class Bishop extends Piece {

    public Bishop(Chessboard board, int row, int col, int color) {
        super(board, row, col, color, 2, 3, "Bishop");
        abbreviation = "B";
    }

    public void findMoves(ArrayList<Move> moves, boolean actualMove) {
        moveDiagonally(moves, actualMove);
    }
}
