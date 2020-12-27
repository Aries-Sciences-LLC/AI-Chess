package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class ScotchGame extends Opening {
    private Chessboard board;
    public boolean[] varArray = new boolean[2];
    public boolean[] misArray = new boolean[2];

    public ScotchGame(Chessboard board) {
        super(28);
        this.board = board;
        openingMove = new Move[28];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(4, 6), 4, 4, true);
        openingMove[2] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[3] = new Move(board.objectAt(1, 7), 2, 5, true);
        openingMove[4] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[5] = new Move(board.objectAt(4, 6), 3, 3, true);
        openingMove[5].addCapturedPiece(board.objectAt(3, 1));
        openingMove[6] = new Move(board.objectAt(6, 0), 3, 3, true);
        openingMove[6].addCapturedPiece(board.objectAt(3, 1));
        openingMove[7] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[8] = new Move(board.objectAt(1, 0), 2, 2, true);
        openingMove[9] = new Move(board.objectAt(5, 7), 1, 3, true);
        openingMove[10] = new Move(board.objectAt(6, 0), 2, 5, true);
        openingMove[10].addCapturedPiece(board.objectAt(1, 7));
        openingMove[11] = new Move(board.objectAt(1, 6), 2, 5, true);
        openingMove[11].addCapturedPiece(board.objectAt(6, 0));
        openingMove[12] = new Move(board.objectAt(5, 0), 3, 2, true);
        openingMove[13] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[14] = new Move(board.objectAt(4, 1), 3, 4, true);
        openingMove[14].addCapturedPiece(board.objectAt(3, 6));
        openingMove[15] = new Move(board.objectAt(1, 6), 3, 4, true);
        openingMove[15].addCapturedPiece(board.objectAt(4, 1));
        openingMove[16] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[16].changeCastleIndex(1);
        openingMove[17] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[17].changeCastleIndex(1);
        openingMove[18] = new Move(board.objectAt(2, 0), 6, 4, true);
        openingMove[19] = new Move(board.objectAt(2, 6), 2, 5, true);
        openingMove[20] = new Move(board.objectAt(3, 0), 5, 2, true);
        openingMove[21] = new Move(board.objectAt(5, 7), 4, 6, true);
        openingMove[22] = new Move(board.objectAt(0, 0), 4, 0, true);
        openingMove[23] = new Move(board.objectAt(0, 7), 1, 7, true);
        openingMove[24] = new Move(board.objectAt(1, 0), 3, 0, true);
        openingMove[25] = new Move(board.objectAt(7, 7), 4, 7, true);
        openingMove[26] = new Move(board.objectAt(7, 1), 7, 2, true);
        openingMove[27] = new Move(board.objectAt(2, 7), 4, 5, true);
        varArray[0] = false;
        varArray[1] = false;
        misArray[0] = false;
        misArray[1] = false;
    }

    public boolean checkAlterations(int m) {
        boolean mistake = false;
        if (!varArray[0] && !varArray[1]) {
            if (m == 13 && !board.isEmpty(3, 1) && board.objectAt(3, 1).equals(2, 0)) {
                mistake = true;
                misArray[0] = true;
                openingMove[13] = openingMove[17];
                openingMove[14] = new Move(board.objectAt(5, 0), 3, 2, true);
                openingMove[15] = new Move(board.objectAt(3, 6), 3, 4, true);
                openingMove[16] = new Move(board.objectAt(5, 1), 5, 2, true);
                openingMove[17] = new Move(board.objectAt(3, 6), 4, 3, true);
                openingMove[17].addCapturedPiece(board.objectAt(4, 3));
                openingMove[18] = new Move(board.objectAt(2, 2), 4, 3, true);
                openingMove[18].addCapturedPiece(board.objectAt(3, 6));
                openingMove[19] = new Move(board.objectAt(5, 5), 4, 3, true);
                openingMove[19].addCapturedPiece(board.objectAt(2, 2));
                openingMove[20] = new Move(board.objectAt(5, 1), 4, 3, true);
                openingMove[20].addCapturedPiece(board.objectAt(5, 5));
                openingMove[21] = new Move(board.objectAt(1, 3), 2, 4, true);
                size = 22;
            } else if (m == 9 && board.objectAt(2, 5).equals(1, 0)) {
                mistake = true;
                misArray[1] = true;
                openingMove[9] = new Move(board.objectAt(1, 6), 2, 5, true);
                openingMove[9].addCapturedPiece(board.objectAt(3, 3));
                openingMove[10] = new Move(board.objectAt(5, 1), 5, 2, true);
                openingMove[11] = new Move(board.objectAt(3, 7), 4, 6, true);
                openingMove[12] = new Move(board.objectAt(3, 0), 4, 1, true);
                openingMove[13] = new Move(board.objectAt(5, 5), 3, 4, true);
                openingMove[14] = new Move(board.objectAt(1, 0), 2, 2, true);
                size = 15;
            } else if (m == 8 && !board.isEmpty(2, 4) && board.objectAt(2, 4).equals(2, 1)) {
                adjustForVarZero(m);
                mistake = true;
            } else if (m == 16 && !board.isEmpty(4, 6) && board.objectAt(4, 6).equals(4, 1)) {
                openingMove[16] = new Move(board.objectAt(3, 0), 4, 1, true);
                size = 17;
                mistake = true;
            }
        } else if (m == 13 && !board.isEmpty(3, 1) && board.objectAt(3, 1).equals(4, 0)) {
            adjustForVarOne(m);
            mistake = true;
        }
        return mistake;
    }

    public void checkVariations(int m) {
        if (m == 7 && rGen.nextInt(2) == 0) {
            adjustForVarZero(m);
        }
        if (varArray[0] && m == 12 && rGen.nextInt(2) == 0) {
            adjustForVarOne(m);
        }
    }

    public void adjustForVarZero(int m) {
        if (m == 7) {
            openingMove[7] = new Move(board.objectAt(5, 7), 2, 4, true);
            openingMove[13] = new Move(board.objectAt(5, 7), 4, 2, true);
        } else {
            openingMove[13] = new Move(board.objectAt(2, 4), 4, 2, true);
        }
        openingMove[8] = new Move(board.objectAt(2, 0), 4, 2, true);
        openingMove[9] = new Move(board.objectAt(3, 7), 5, 5, true);
        openingMove[10] = new Move(board.objectAt(2, 1), 2, 2, true);
        openingMove[11] = new Move(board.objectAt(6, 7), 4, 6, true);
        openingMove[12] = new Move(board.objectAt(3, 3), 2, 1, true);
        openingMove[13].addCapturedPiece(board.objectAt(2, 0));
        openingMove[14] = new Move(board.objectAt(3, 3), 4, 2, true);
        openingMove[14].addCapturedPiece(board.objectAt(5, 7));
        openingMove[15] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[15].changeCastleIndex(1);
        openingMove[16] = new Move(board.objectAt(5, 0), 4, 1, true);
        openingMove[17] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[18] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[18].changeCastleIndex(1);
        openingMove[19] = new Move(board.objectAt(2, 7), 4, 5, true);
        openingMove[20] = new Move(board.objectAt(1, 0), 3, 1, true);
        openingMove[21] = new Move(board.objectAt(3, 6), 3, 4, true);
        size = 22;
        varArray[0] = true;
    }

    public void adjustForVarOne(int m) {
        if (m == 12) {
            openingMove[12] = new Move(board.objectAt(3, 0), 3, 1, true);
            openingMove[16] = new Move(board.objectAt(3, 0), 4, 2, true);
        } else {
            openingMove[16] = new Move(board.objectAt(3, 1), 4, 2, true);
        }
        openingMove[13] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[14] = new Move(board.objectAt(3, 3), 1, 4, true);
        openingMove[15] = new Move(board.objectAt(2, 4), 4, 2, true);
        openingMove[15].addCapturedPiece(board.objectAt(4, 2));
        openingMove[16].addCapturedPiece(board.objectAt(2, 4));
        openingMove[17] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[17].changeCastleIndex(1);
        openingMove[18] = new Move(board.objectAt(1, 0), 3, 1, true);
        openingMove[19] = new Move(board.objectAt(3, 6), 4, 3, true);
        openingMove[19].addCapturedPiece(board.objectAt(4, 3));
        openingMove[20] = new Move(board.objectAt(1, 0), 4, 3, true);
        openingMove[20].addCapturedPiece(board.objectAt(3, 6));
        openingMove[21] = new Move(board.objectAt(5, 5), 4, 4, true);
        openingMove[22] = new Move(board.objectAt(4, 0), 0, 0, true);
        openingMove[22].changeCastleIndex(1);
        size = 23;
        varArray[1] = true;
    }
}
