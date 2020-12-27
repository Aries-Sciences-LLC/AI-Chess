package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class EvansGambitDeclined extends Opening {
    public EvansGambitDeclined(Chessboard board) {
        super(12);
        openingMove = new Move[12];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(4, 6), 4, 4, true);
        openingMove[2] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[3] = new Move(board.objectAt(1, 7), 2, 5, true);
        openingMove[4] = new Move(board.objectAt(5, 0), 2, 3, true);
        openingMove[5] = new Move(board.objectAt(5, 7), 2, 4, true);
        openingMove[6] = new Move(board.objectAt(1, 1), 1, 3, true);
        openingMove[7] = new Move(board.objectAt(5, 7), 1, 5, true);
        openingMove[8] = new Move(board.objectAt(1, 1), 1, 4, true);
        openingMove[9] = new Move(board.objectAt(1, 7), 0, 4, true);
        openingMove[10] = new Move(board.objectAt(6, 0), 4, 4, true);
        openingMove[10].addCapturedPiece(board.objectAt(4, 6));
        openingMove[11] = new Move(board.objectAt(6, 7), 7, 5, true);
    }
}
