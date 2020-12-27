package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class PhilidorsDefence extends Opening {
    public PhilidorsDefence(Chessboard board) {
        super(23);

        openingMove = new Move[23];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(4, 6), 4, 4, true);
        openingMove[2] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[3] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[4] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[5] = new Move(board.objectAt(1, 7), 3, 6, true);
        openingMove[6] = new Move(board.objectAt(5, 0), 2, 3, true);
        openingMove[7] = new Move(board.objectAt(2, 6), 2, 5, true);
        openingMove[8] = new Move(board.objectAt(1, 0), 2, 2, true);
        openingMove[9] = new Move(board.objectAt(5, 7), 4, 6, true);
        openingMove[10] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[10].changeCastleIndex(1);
        openingMove[11] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[12] = new Move(board.objectAt(0, 1), 0, 3, true);
        openingMove[13] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[13].changeCastleIndex(1);
        openingMove[14] = new Move(board.objectAt(3, 0), 4, 1, true);
        openingMove[15] = new Move(board.objectAt(7, 6), 7, 5, true);
        openingMove[16] = new Move(board.objectAt(5, 0), 1, 2, true);
        openingMove[17] = new Move(board.objectAt(3, 7), 2, 6, true);
        openingMove[18] = new Move(board.objectAt(7, 1), 7, 2, true);
        openingMove[19] = new Move(board.objectAt(6, 7), 7, 6, true);
        openingMove[20] = new Move(board.objectAt(2, 0), 4, 2, true);
        openingMove[21] = new Move(board.objectAt(6, 6), 6, 5, true);
        openingMove[22] = new Move(board.objectAt(0, 0), 3, 0, true);

    }
}
