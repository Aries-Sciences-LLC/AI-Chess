package com.burcumirza.HenryChess.Logic;

import com.burcumirza.HenryChess.Pieces.*;

import java.util.*;

public class LegalMoves {

    private static Chessboard board;

    public LegalMoves(Chessboard aBoard) {
        board = aBoard;
    }

    public ArrayList<Move> getMoveList(int playerColor, boolean actualMove) {
        return filterMoves(fillList(playerColor, actualMove), playerColor);
    }

    public ArrayList<Move> fillList(int color, boolean actualMove) {
        ArrayList<Move> moveList = new ArrayList<Move>();
        Iterator<Piece> iter = board.pieceList(color).iterator();
        Piece piece = null;
        while (iter.hasNext()) {
            piece = (Piece) iter.next();
            piece.findMoves(moveList, actualMove);
        }
        return moveList;
    }

    public ArrayList<Move> filterMoves(ArrayList<Move> myMoveList, int color) {
        Move aMove = null;
        boolean inCheck;
        int i = 0;
        while (i < myMoveList.size()) {
            inCheck = false;
            aMove = (Move) myMoveList.get(i);
            Piece piece = aMove.piece();
            if (aMove.castleIndex() != -1) {
                int index = aMove.castleIndex();
                int row = 4;
                while (!inCheck && row != 2 + 4 * index) {
                    inCheck = inCheck(fillList((color + 1) % 2, true), color);
                    row = row + (2 * index - 1);
                    piece.move(row, piece.col());
                }
                piece.move(4, piece.col());
            }
            if (!inCheck) {
                board.move(aMove);
                inCheck = inCheck(fillList((color + 1) % 2, true), color);
                board.moveBack(aMove);
            }
            if (inCheck) {
                myMoveList.remove(i);
            } else {
                i++;
            }
        }
        return myMoveList;
    }

    public boolean inCheck(List<Move> opponentMoveList, int color) {
        Iterator<Move> iter = opponentMoveList.iterator();
        Move aMove = null;
        while (iter.hasNext()) {
            aMove = (Move) iter.next();
            if (board.king[color].row() == aMove.newRow() && board.king[color].col() == aMove.newCol()) {
                return true;
            }
        }
        return false;
    }

}
