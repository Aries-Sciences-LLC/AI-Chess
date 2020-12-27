package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class BenoniCounterGambit extends Opening {
    public BenoniCounterGambit(Chessboard board) {
        super(40);

        openingMove = new Move[40];
        openingMove[0] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[1] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[2] = new Move(board.objectAt(2, 1), 2, 3, true);
        openingMove[3] = new Move(board.objectAt(2, 6), 2, 4, true);
        openingMove[4] = new Move(board.objectAt(3, 1), 3, 4, true);
        openingMove[5] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[6] = new Move(board.objectAt(1, 0), 2, 2, true);
        openingMove[7] = new Move(board.objectAt(6, 6), 6, 5, true);
        openingMove[8] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[9] = new Move(board.objectAt(5, 7), 6, 6, true);
        openingMove[10] = new Move(board.objectAt(5, 0), 3, 2, true);
        openingMove[11] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[11].changeCastleIndex(1);
        openingMove[12] = new Move(board.objectAt(6, 0), 4, 1, true);
        openingMove[13] = new Move(board.objectAt(4, 6), 4, 5, true);
        openingMove[14] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[14].changeCastleIndex(1);
        openingMove[15] = new Move(board.objectAt(4, 6), 3, 4, true);
        openingMove[15].addCapturedPiece(board.objectAt(3, 1));
        openingMove[16] = new Move(board.objectAt(2, 1), 3, 4, true);
        openingMove[16].addCapturedPiece(board.objectAt(4, 6));
        openingMove[17] = new Move(board.objectAt(0, 6), 0, 5, true);
        openingMove[18] = new Move(board.objectAt(0, 1), 0, 3, true);
        openingMove[19] = new Move(board.objectAt(3, 7), 2, 6, true);
        openingMove[20] = new Move(board.objectAt(7, 1), 7, 2, true);
        openingMove[21] = new Move(board.objectAt(1, 7), 3, 6, true);
        openingMove[22] = new Move(board.objectAt(5, 1), 5, 3, true);
        openingMove[23] = new Move(board.objectAt(0, 7), 1, 7, true);
        openingMove[24] = new Move(board.objectAt(2, 0), 4, 2, true);
        openingMove[25] = new Move(board.objectAt(7, 7), 4, 7, true);
        openingMove[26] = new Move(board.objectAt(6, 0), 6, 2, true);
        openingMove[27] = new Move(board.objectAt(2, 6), 2, 3, true);
        openingMove[28] = new Move(board.objectAt(5, 0), 2, 1, true);
        openingMove[29] = new Move(board.objectAt(1, 7), 2, 4, true);
        openingMove[30] = new Move(board.objectAt(3, 0), 5, 2, true);
        openingMove[31] = new Move(board.objectAt(1, 6), 1, 4, true);
        openingMove[32] = new Move(board.objectAt(0, 1), 1, 4, true);
        openingMove[32].addCapturedPiece(board.objectAt(1, 6));
        openingMove[33] = new Move(board.objectAt(0, 6), 1, 4, true);
        openingMove[33].addCapturedPiece(board.objectAt(0, 1));
        openingMove[34] = new Move(board.objectAt(4, 1), 4, 4, true);
        openingMove[35] = new Move(board.objectAt(3, 6), 4, 4, true);
        openingMove[35].addCapturedPiece(board.objectAt(4, 1));
        openingMove[36] = new Move(board.objectAt(5, 1), 4, 4, true);
        openingMove[36].addCapturedPiece(board.objectAt(3, 6));
        openingMove[37] = new Move(board.objectAt(7, 7), 4, 4, true);
        openingMove[37].addCapturedPiece(board.objectAt(5, 1));
        openingMove[38] = new Move(board.objectAt(2, 0), 5, 3, true);
        openingMove[39] = new Move(board.objectAt(6, 7), 3, 6, true);
    }
}
