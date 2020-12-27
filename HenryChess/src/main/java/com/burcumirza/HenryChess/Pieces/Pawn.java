package com.burcumirza.HenryChess.Pieces;

import com.burcumirza.HenryChess.Logic.*;

import javax.swing.*;
import java.util.*;

public class Pawn extends Piece {
    boolean queened = false;
    ImageIcon queenIcon, pawnIcon;

    public Pawn(Chessboard board, int row, int col, int color) {
        super(board, row, col, color, 0, 1, "Pawn");
        pawnIcon = icon;
        abbreviation = "";
        String iconFile = GIF_BASE_FILENAME + colorString[myColor] + "Queen" + ".gif";
        queenIcon = new ImageIcon(getClass().getResource(iconFile));
    }

    public void findMoves(ArrayList<Move> moves, boolean actualMove) {
        if (!queened) {
            findPawnMoves(moves, actualMove);
        } else {
            findQueenMoves(moves, actualMove);
        }
    }

    public void findPawnMoves(ArrayList<Move> moves, boolean actualMove) {
        Move aMove = null;
        int toRow = myRow, toCol = myCol + (1 - 2 * myColor);
        if (board.isEmpty(toRow, toCol)) {
            aMove = new Move(this, toRow, toCol, false);
            moves.add(aMove);
            if (toCol == 7 * (1 - myColor)) {
                aMove.changeQueened();
            } else if (myCol == 1 + 5 * myColor) {
                toCol = 3 + myColor;
                if (board.isEmpty(toRow, toCol)) {
                    moves.add(new Move(this, toRow, toCol, false));
                }

            }
        }
        toCol = myCol + (1 - 2 * myColor);
        for (int i = 0; i < 2; i++) {
            toRow = myRow + (1 - 2 * i);
            if (board.canAdd(this, toRow, toCol, actualMove) && !board.isEmpty(toRow, toCol)) {
                aMove = new Move(this, toRow, toCol, false);
                moves.add(aMove);
                if (toCol == 7 * (1 - myColor)) {
                    aMove.changeQueened();
                }
            }
        }
        if (myCol == 4 - myColor) {
            aMove = addEnPassant(myRow + 1);
            if (aMove != null) {
                moves.add(aMove);
            }
            aMove = addEnPassant(myRow - 1);
            if (aMove != null) {
                moves.add(aMove);
            }
        }
    }

    public void findQueenMoves(ArrayList<Move> moves, boolean actualMove) {
        moveRectilinearly(moves, actualMove);
        moveDiagonally(moves, actualMove);
    }

    public void queenPawn() {
        queened = true;
        abbreviation = "Q";
        myPiece = 4;
        myValue = 9;
        icon = queenIcon;
    }

    public void reverseQueenPawn() {
        queened = false;
        abbreviation = "";
        myPiece = 0;
        myValue = 1;
        icon = pawnIcon;
    }

    public int value() {
        if (myCol == 7 - myColor * 7) {
            return 9;
        } else if (myCol == 6 - 5 * myColor) {
            return 4;
        } else if (myCol == 5 - 3 * myColor) {
            return 2;
        } else {
            return myValue;
        }
    }

    /*
     * Returns true if there is an opportunity to capture pawn at given row and col
     * = 4-myColor via en passant
     */

    public Move addEnPassant(int row) {
        Move aMove = null;
        int col = 4 - myColor;
        if (row >= 0 && row <= 7 && board.objectAt(row, col) != null) {
            Piece aPiece = board.objectAt(row, col);
            Move lastMove = board.getLastMove();
            if (lastMove.piece().equals(aPiece) && aPiece.piece() == 0 && aPiece.color() != myColor
                    && lastMove.oldCol() == 6 - 5 * myColor) {
                aMove = new Move(this, row, 5 - 3 * myColor, false);
                aMove.addCapturedPiece(aPiece);
            }
        }
        return aMove;
    }
}
