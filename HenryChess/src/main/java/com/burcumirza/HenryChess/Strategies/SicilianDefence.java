package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class SicilianDefence extends Opening {
    private Chessboard board;

    public SicilianDefence(Chessboard board) {
        super(9);
        this.board = board;
        openingMove = new Move[9];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(2, 6), 2, 4, true);
        openingMove[2] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[3] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[4] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[5] = new Move(board.objectAt(2, 6), 3, 3, true);
        openingMove[5].addCapturedPiece(board.objectAt(3, 1));
        openingMove[6] = new Move(board.objectAt(6, 0), 3, 3, true);
        openingMove[6].addCapturedPiece(board.objectAt(2, 6));
        openingMove[7] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[8] = new Move(board.objectAt(1, 0), 2, 2, true);
    }

    public boolean checkMistakes(int m) {
        if (m == 4 && openingMove[7].piece().row() == 5 && openingMove[7].piece().col() == 5) {
            openingMove[4] = new Move(board.objectAt(1, 0), 2, 2, true);
            openingMove[5] = new Move(board.objectAt(3, 6), 3, 5, true);
            openingMove[6] = new Move(board.objectAt(3, 1), 3, 3, true);
            openingMove[7] = new Move(board.objectAt(2, 4), 3, 3, true);
            openingMove[7].addCapturedPiece(board.objectAt(3, 1));
            openingMove[8] = new Move(board.objectAt(5, 2), 3, 3, true);
            openingMove[8].addCapturedPiece(board.objectAt(2, 4));
            return true;
        }
        return false;
    }

    public void checkAlternates(int m) {
    }
}
