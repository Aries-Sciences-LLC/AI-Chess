/*
 * Created on March 13, 2008, 7:47 PM by MHenry
 *
 */

package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class Scheveningen extends Sicilian {

    public Scheveningen(Chessboard board, LegalMoves legalMoves) {
        super(board, 28, legalMoves);

        openingMove = new Move[28];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(2, 6), 2, 4, true);
        openingMove[2] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[3] = new Move(board.objectAt(4, 6), 4, 5, true);
        openingMove[4] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[5] = new Move(board.objectAt(2, 6), 3, 3, true);
        openingMove[5].addCapturedPiece(board.objectAt(3, 1));
        openingMove[6] = new Move(board.objectAt(6, 0), 3, 3, true);
        openingMove[6].addCapturedPiece(board.objectAt(2, 6));
        openingMove[7] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[8] = new Move(board.objectAt(1, 0), 2, 2, true);
        openingMove[9] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[10] = new Move(board.objectAt(5, 0), 4, 1, true);
        openingMove[11] = new Move(board.objectAt(1, 7), 2, 5, true);
        openingMove[12] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[12].changeCastleIndex(1);
        openingMove[13] = new Move(board.objectAt(5, 7), 4, 6, true);
        openingMove[14] = new Move(board.objectAt(6, 0), 7, 0, true);
        openingMove[15] = new Move(board.objectAt(0, 6), 0, 5, true);
        openingMove[16] = new Move(board.objectAt(5, 1), 5, 3, true);
        openingMove[17] = new Move(board.objectAt(3, 7), 2, 6, true);
        openingMove[18] = new Move(board.objectAt(2, 0), 4, 2, true);
        openingMove[19] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[19].changeCastleIndex(1);
        openingMove[20] = new Move(board.objectAt(3, 0), 4, 0, true);
        openingMove[21] = new Move(board.objectAt(2, 7), 3, 6, true);
        openingMove[22] = new Move(board.objectAt(0, 0), 3, 0, true);
        openingMove[23] = new Move(board.objectAt(1, 6), 1, 4, true);
        openingMove[24] = new Move(board.objectAt(0, 1), 0, 2, true);
        openingMove[25] = new Move(board.objectAt(1, 7), 0, 4, true);
        openingMove[26] = new Move(board.objectAt(3, 0), 6, 2, true);
        openingMove[27] = new Move(board.objectAt(1, 7), 2, 3, true);
    }
}
