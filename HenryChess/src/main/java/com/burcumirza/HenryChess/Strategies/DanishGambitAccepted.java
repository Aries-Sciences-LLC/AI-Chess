package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class DanishGambitAccepted extends Opening {

    public DanishGambitAccepted(Chessboard board) {
        super(20);

        openingMove = new Move[20];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(4, 6), 4, 4, true);
        openingMove[2] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[3] = new Move(board.objectAt(4, 6), 3, 3, true);
        openingMove[3].addCapturedPiece(board.objectAt(3, 1));
        openingMove[4] = new Move(board.objectAt(2, 1), 2, 2, true);
        openingMove[5] = new Move(board.objectAt(4, 6), 2, 2, true);
        openingMove[5].addCapturedPiece(board.objectAt(2, 1));
        openingMove[6] = new Move(board.objectAt(5, 0), 2, 3, true);
        openingMove[7] = new Move(board.objectAt(4, 6), 1, 1, true);
        openingMove[7].addCapturedPiece(board.objectAt(1, 1));
        openingMove[8] = new Move(board.objectAt(2, 0), 1, 1, true);
        openingMove[8].addCapturedPiece(board.objectAt(4, 6));
        openingMove[9] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[10] = new Move(board.objectAt(5, 0), 3, 4, true);
        openingMove[10].addCapturedPiece(board.objectAt(3, 6));
        openingMove[11] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[12] = new Move(board.objectAt(5, 0), 5, 6, true);
        openingMove[12].addCapturedPiece(board.objectAt(5, 6));
        openingMove[13] = new Move(board.objectAt(4, 7), 5, 6, true);
        openingMove[13].addCapturedPiece(board.objectAt(5, 0));
        openingMove[14] = new Move(board.objectAt(3, 0), 3, 7, true);
        openingMove[14].addCapturedPiece(board.objectAt(3, 7));
        openingMove[15] = new Move(board.objectAt(5, 7), 1, 3, true);
        openingMove[16] = new Move(board.objectAt(3, 0), 3, 1, true);
        openingMove[17] = new Move(board.objectAt(5, 7), 3, 1, true);
        openingMove[17].addCapturedPiece(board.objectAt(3, 0));
        openingMove[18] = new Move(board.objectAt(1, 0), 3, 1, true);
        openingMove[18].addCapturedPiece(board.objectAt(5, 7));
        openingMove[19] = new Move(board.objectAt(2, 6), 2, 4, true);
    }

}
