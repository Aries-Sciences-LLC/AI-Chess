package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class KingsGambit extends Opening {
    private Chessboard board;
    private boolean bishopsGambit = true, muzioGambit = false, muzioAlternative = false;

    public KingsGambit(Chessboard board) {
        super(20);
        this.board = board;
        openingMove = new Move[20];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(4, 6), 4, 4, true);
        openingMove[2] = new Move(board.objectAt(5, 1), 5, 3, true);
        openingMove[3] = new Move(board.objectAt(4, 6), 5, 3, true);
        openingMove[3].addCapturedPiece(board.objectAt(5, 1));
        openingMove[4] = new Move(board.objectAt(5, 0), 2, 3, true);
        openingMove[5] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[6] = new Move(board.objectAt(5, 0), 3, 4, true);
        openingMove[6].addCapturedPiece(board.objectAt(3, 6));
        openingMove[7] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[8] = new Move(board.objectAt(1, 0), 2, 2, true);
        openingMove[9] = new Move(board.objectAt(5, 7), 1, 3, true);
        openingMove[10] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[11] = new Move(board.objectAt(5, 7), 2, 2, true);
        openingMove[11].addCapturedPiece(board.objectAt(1, 0));
        openingMove[12] = new Move(board.objectAt(3, 1), 2, 2, true);
        openingMove[12].addCapturedPiece(board.objectAt(5, 7));
        openingMove[13] = new Move(board.objectAt(2, 6), 2, 5, true);
        openingMove[14] = new Move(board.objectAt(5, 0), 2, 3, true);
        openingMove[15] = new Move(board.objectAt(3, 7), 3, 0, true);
        openingMove[15].addCapturedPiece(board.objectAt(3, 0));
        openingMove[16] = new Move(board.objectAt(4, 0), 3, 0, true);
        openingMove[16].addCapturedPiece(board.objectAt(3, 7));
        openingMove[17] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[17].changeCastleIndex(1);
        openingMove[18] = new Move(board.objectAt(2, 0), 5, 3, true);
        openingMove[18].addCapturedPiece(board.objectAt(4, 4));
        openingMove[19] = new Move(board.objectAt(6, 7), 4, 3, true);
        openingMove[19].addCapturedPiece(board.objectAt(4, 1));
    }

    public boolean checkAlterations(int m) {
        boolean alteration = false;
        if (m == 5 && !board.isEmpty(5, 2) && board.objectAt(5, 2).equals(1, 0)) {
            if (rGen.nextInt(2) == 1) {
                adjustForMuzioGambit(true);
            } else {
                adjustForMuzioAlternative(false);
            }
            alteration = true;
        } else if (bishopsGambit && m == 7 && board.objectAt(3, 4).equals(0, 0)) {
            adjustForBishopsGambitMistake();
            alteration = true;
        } else if (muzioGambit && m == 6 && !board.isEmpty(5, 5) && board.objectAt(5, 5).equals(1, 1)) {
            adjustForMuzioAlternative(true);
            alteration = true;
        } else if (muzioAlternative && m == 7 && !board.isEmpty(2, 2) && board.objectAt(2, 2).equals(1, 0)) {
            adjustForMuzioAlternativeMistake();
            alteration = true;
        }
        return alteration;
    }

    private void adjustForBishopsGambitMistake() {
        openingMove[7] = new Move(board.objectAt(3, 7), 7, 3, true);
        openingMove[8] = new Move(board.objectAt(4, 0), 5, 0, true);
        openingMove[9] = new Move(board.objectAt(6, 6), 6, 4, true);
        size = 10;
        bishopsGambit = false;
    }

    private void adjustForMuzioGambit(boolean response) {
        if (!response) {
            openingMove[4] = new Move(board.objectAt(6, 0), 5, 2, true);
            openingMove[9].addCapturedPiece(board.objectAt(6, 0));
        } else {
            openingMove[9].addCapturedPiece(board.objectAt(5, 2));
        }
        openingMove[5] = new Move(board.objectAt(6, 6), 6, 4, true);
        openingMove[6] = new Move(board.objectAt(5, 0), 2, 3, true);
        openingMove[7] = new Move(board.objectAt(6, 6), 6, 3, true);
        openingMove[8] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[8].changeCastleIndex(1);
        openingMove[9] = new Move(board.objectAt(6, 6), 5, 2, true);
        openingMove[10] = new Move(board.objectAt(3, 0), 5, 2, true);
        openingMove[10].addCapturedPiece(board.objectAt(6, 6));
        size = 11;
        bishopsGambit = false;
        muzioGambit = true;
    }

    private void adjustForMuzioAlternative(boolean response) {
        if (!response) {
            openingMove[5] = new Move(board.objectAt(6, 7), 5, 5, true);
            openingMove[7] = new Move(board.objectAt(6, 7), 7, 4, true);
        } else {
            openingMove[7] = new Move(board.objectAt(5, 5), 7, 4, true);
        }
        openingMove[6] = new Move(board.objectAt(4, 3), 4, 4, true);
        openingMove[8] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[9] = new Move(board.objectAt(3, 6), 3, 4, true);
        size = 10;
        bishopsGambit = false;
        muzioAlternative = true;
    }

    private void adjustForMuzioAlternativeMistake() {
        openingMove[7] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[8] = new Move(board.objectAt(4, 3), 3, 4, true);
        openingMove[8].addCapturedPiece(board.objectAt(3, 6));
        openingMove[9] = new Move(board.objectAt(5, 5), 3, 4, true);
        openingMove[9].addCapturedPiece(board.objectAt(4, 3));
        openingMove[10] = new Move(board.objectAt(2, 2), 3, 4, true);
        openingMove[10].addCapturedPiece(board.objectAt(5, 5));
        openingMove[11] = new Move(board.objectAt(3, 7), 3, 4, true);
        openingMove[11].addCapturedPiece(board.objectAt(2, 2));
        openingMove[12] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[13] = new Move(board.objectAt(5, 7), 4, 6, true);
        openingMove[14] = new Move(board.objectAt(5, 0), 3, 2, true);
        openingMove[15] = new Move(board.objectAt(6, 6), 6, 4, true);
        openingMove[16] = new Move(board.objectAt(3, 0), 4, 1, true);
        openingMove[17] = new Move(board.objectAt(2, 7), 5, 4, true);
        size = 18;
    }

    public void checkVariations(int m) {
        if (m == 4 && rGen.nextInt(2) == 1) {
            adjustForMuzioGambit(false);
        }
    }
}
