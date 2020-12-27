package com.burcumirza.HenryChess.Pieces;

import com.burcumirza.HenryChess.Logic.*;

import java.util.*;

public class Queen extends Piece {

    public Queen(Chessboard board, int row, int col, int color) {
        super(board, row, col, color, 4, 9, "Queen");
        abbreviation = "Q";
    }

    public void findMoves(ArrayList<Move> moves, boolean actualMove) {
        moveRectilinearly(moves, actualMove);
        moveDiagonally(moves, actualMove);
    }
}
