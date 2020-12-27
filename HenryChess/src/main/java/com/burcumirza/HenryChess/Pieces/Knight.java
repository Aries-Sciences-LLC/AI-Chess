package com.burcumirza.HenryChess.Pieces;

import com.burcumirza.HenryChess.Logic.*;

import java.util.*;

public class Knight extends Piece {

    public Knight(Chessboard board, int row, int col, int color) {
        super(board, row, col, color, 1, 3, "Knight");
        abbreviation = "N";
    }

    public void findMoves(ArrayList<Move> moves, boolean actualMove) {
        int toRow, toCol;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                toRow = myRow + 1 - 2 * i;
                toCol = myCol + 2 * (1 - 2 * j);
                if (board.canAdd(this, toRow, toCol, actualMove)) {
                    moves.add(new Move(this, toRow, toCol, false));
                }
                toRow = myRow + 2 * (1 - 2 * i);
                toCol = myCol + 1 - 2 * j;
                if (board.canAdd(this, toRow, toCol, actualMove)) {
                    moves.add(new Move(this, toRow, toCol, false));
                }
            }
        }
    }
}
