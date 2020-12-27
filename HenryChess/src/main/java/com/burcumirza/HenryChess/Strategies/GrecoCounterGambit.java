package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class GrecoCounterGambit extends Opening {
    public GrecoCounterGambit(Chessboard board) {
        super(18);
        openingMove = new Move[18];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(4, 6), 4, 4, true);
        openingMove[2] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[3] = new Move(board.objectAt(5, 6), 5, 4, true);
        openingMove[4] = new Move(board.objectAt(6, 0), 4, 4, true);
        openingMove[4].addCapturedPiece(board.objectAt(4, 6));
        openingMove[5] = new Move(board.objectAt(3, 7), 5, 5, true);
        openingMove[6] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[7] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[8] = new Move(board.objectAt(6, 0), 2, 3, true);
        openingMove[9] = new Move(board.objectAt(5, 6), 4, 3, true);
        openingMove[9].addCapturedPiece(board.objectAt(4, 1));
        openingMove[10] = new Move(board.objectAt(1, 0), 2, 2, true);
        openingMove[11] = new Move(board.objectAt(3, 7), 6, 5, true);
        openingMove[12] = new Move(board.objectAt(2, 0), 5, 3, true);
        openingMove[13] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[14] = new Move(board.objectAt(6, 0), 4, 2, true);
        openingMove[15] = new Move(board.objectAt(5, 7), 4, 6, true);
        openingMove[16] = new Move(board.objectAt(5, 0), 2, 3, true);
        openingMove[17] = new Move(board.objectAt(2, 6), 2, 5, true);
    }
}
