package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class ColleSystem extends Opening {
    public ColleSystem(Chessboard board) {
        super(36);

        openingMove = new Move[36];
        openingMove[0] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[1] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[2] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[3] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[4] = new Move(board.objectAt(4, 1), 4, 2, true);
        openingMove[5] = new Move(board.objectAt(2, 6), 2, 4, true);
        openingMove[6] = new Move(board.objectAt(2, 1), 2, 2, true);
        openingMove[7] = new Move(board.objectAt(4, 6), 4, 5, true);
        openingMove[8] = new Move(board.objectAt(1, 0), 3, 1, true);
        openingMove[9] = new Move(board.objectAt(1, 7), 2, 5, true);
        openingMove[10] = new Move(board.objectAt(5, 0), 3, 2, true);
        openingMove[11] = new Move(board.objectAt(5, 7), 3, 5, true);
        openingMove[12] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[12].changeCastleIndex(1);
        openingMove[13] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[13].changeCastleIndex(1);
        openingMove[14] = new Move(board.objectAt(3, 1), 2, 4, true);
        openingMove[14].addCapturedPiece(board.objectAt(2, 6));
        openingMove[15] = new Move(board.objectAt(5, 7), 2, 4, true);
        openingMove[15].addCapturedPiece(board.objectAt(3, 1));
        openingMove[16] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[17] = new Move(board.objectAt(3, 7), 2, 6, true);
        openingMove[18] = new Move(board.objectAt(3, 0), 4, 1, true);
        openingMove[19] = new Move(board.objectAt(5, 7), 3, 5, true);
        openingMove[20] = new Move(board.objectAt(7, 0), 4, 0, true);
        openingMove[21] = new Move(board.objectAt(6, 7), 6, 3, true);
        openingMove[22] = new Move(board.objectAt(7, 1), 7, 2, true);
        openingMove[23] = new Move(board.objectAt(6, 7), 4, 4, true);
        openingMove[24] = new Move(board.objectAt(6, 0), 4, 4, true);
        openingMove[24].addCapturedPiece(board.objectAt(6, 7));
        openingMove[25] = new Move(board.objectAt(1, 7), 4, 4, true);
        openingMove[25].addCapturedPiece(board.objectAt(6, 0));
        openingMove[26] = new Move(board.objectAt(4, 1), 3, 4, true);
        openingMove[26].addCapturedPiece(board.objectAt(3, 6));
        openingMove[27] = new Move(board.objectAt(4, 6), 3, 4, true);
        openingMove[27].addCapturedPiece(board.objectAt(4, 1));
        openingMove[28] = new Move(board.objectAt(1, 0), 5, 2, true);
        openingMove[29] = new Move(board.objectAt(1, 7), 3, 2, true);
        openingMove[29].addCapturedPiece(board.objectAt(5, 0));
        openingMove[30] = new Move(board.objectAt(3, 0), 3, 2, true);
        openingMove[30].addCapturedPiece(board.objectAt(1, 7));
        openingMove[31] = new Move(board.objectAt(3, 7), 2, 3, true);
        openingMove[32] = new Move(board.objectAt(7, 0), 3, 0, true);
        openingMove[33] = new Move(board.objectAt(3, 7), 3, 2, true);
        openingMove[33].addCapturedPiece(board.objectAt(3, 0));
        openingMove[34] = new Move(board.objectAt(7, 0), 3, 2, true);
        openingMove[34].addCapturedPiece(board.objectAt(3, 7));
        openingMove[35] = new Move(board.objectAt(7, 7), 3, 7, true);
    }
}
