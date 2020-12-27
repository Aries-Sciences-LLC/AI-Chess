package com.burcumirza.HenryChess.Pieces;

import com.burcumirza.HenryChess.Logic.*;

import java.util.*;

public class King extends Piece {

    public King(Chessboard board, int row, int col, int color) {
        super(board, row, col, color, 5, 12, "King");
        abbreviation = "K";
    }

    public void addToBoard() {
        board.add(this);
    }

    public void setValue(int value) {
        myValue = value;
    }

    public void move(int newRow, int newCol) {
        board.recordMove(this, newRow, newCol);
        myRow = newRow;
        myCol = newCol;
    }

    public void findMoves(ArrayList<Move> moves, boolean actualMove) {
        int toRow, toCol;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                toRow = myRow + i;
                toCol = myCol + j;
                if (!(i == 0 && j == 0) && board.canAdd(this, toRow, toCol, actualMove)) {
                    addKingMove(moves, new Move(this, toRow, toCol, false), -1);
                }
            }
        }
        if (board.getCanCastle(myColor, 0)) {
            boolean queenSideCastle = true;
            for (int i = 1; i < 4; i++) {
                if (!board.isEmpty(i, myCol)) {
                    queenSideCastle = false;
                }
            }
            if (queenSideCastle) {
                addKingMove(moves, new Move(this, 2, myCol, false), 0);
            }
        }
        if (board.getCanCastle(myColor, 1)) {
            boolean kingSideCastle = true;
            for (int i = 5; i < 7; i++) {
                if (!board.isEmpty(i, myCol)) {
                    kingSideCastle = false;
                }
            }
            if (kingSideCastle) {
                addKingMove(moves, new Move(this, 6, myCol, false), 1);
            }
        }
    }

    private void addKingMove(ArrayList<Move> moves, Move aMove, int index) {
        moves.add(aMove);
        if (index != -1) {
            aMove.changeCastleIndex(index);
        }
    }

}
