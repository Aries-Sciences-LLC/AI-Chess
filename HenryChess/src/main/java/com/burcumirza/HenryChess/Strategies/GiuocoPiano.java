/*
 * Created on March 5, 2008, 9:34 PM by MHenry
 *
 */

package com.burcumirza.HenryChess.Strategies;

import com.burcumirza.HenryChess.Logic.*;

public class GiuocoPiano extends Opening {
    private Chessboard board;
    public boolean mainline = true, branch1 = false, branch2 = false, branch3 = false;

    public GiuocoPiano(Chessboard board) {
        super(16);
        this.board = board;
        openingMove = new Move[32];
        openingMove[0] = new Move(board.objectAt(4, 1), 4, 3, true);
        openingMove[1] = new Move(board.objectAt(4, 6), 4, 4, true);
        openingMove[2] = new Move(board.objectAt(6, 0), 5, 2, true);
        openingMove[3] = new Move(board.objectAt(1, 7), 2, 5, true);
        openingMove[4] = new Move(board.objectAt(5, 0), 2, 3, true);
        openingMove[5] = new Move(board.objectAt(5, 7), 2, 4, true);
        openingMove[6] = new Move(board.objectAt(2, 1), 2, 2, true);
        openingMove[7] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[8] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[9] = new Move(board.objectAt(4, 6), 3, 3, true);
        openingMove[9].addCapturedPiece(board.objectAt(3, 1));
        openingMove[10] = new Move(board.objectAt(2, 1), 3, 3, true);
        openingMove[10].addCapturedPiece(board.objectAt(4, 6));
        openingMove[11] = new Move(board.objectAt(5, 7), 1, 3, true);
        openingMove[12] = new Move(board.objectAt(2, 0), 3, 1, true);
        openingMove[13] = new Move(board.objectAt(5, 7), 3, 1, true);
        openingMove[13].addCapturedPiece(board.objectAt(2, 0));
        openingMove[14] = new Move(board.objectAt(1, 0), 3, 1, true);
        openingMove[14].addCapturedPiece(board.objectAt(5, 7));
        openingMove[15] = new Move(board.objectAt(3, 6), 3, 4, true);
    }

    public boolean checkAlterations(int m) {
        boolean alteration = false;
        if (mainline) {
            if (m == 8 && !board.isEmpty(3, 5) && board.objectAt(3, 5).equals(0, 1)) {
                adjustForMisOne(true);
                alteration = true;
            } else if (m == 10 && board.objectAt(3, 3).equals(0, 1)) {
                adjustForMisTwo(true);
                alteration = true;
            } else if (m == 12 && !board.isEmpty(2, 2) && board.objectAt(4, 3).equals(1, 1)) {
                adjustForMisThree(true);
                alteration = true;
            } else if (m == 13 && !board.isEmpty(2, 2) && board.objectAt(2, 2).equals(1, 0)) {
                adjustForVarOne(true);
                alteration = true;
            } else if (m == 8 && !board.isEmpty(1, 5) && board.objectAt(1, 5).equals(2, 1)) {
                adjustForVarFour(true);
                alteration = true;
            } else if (m == 7 && !board.isEmpty(3, 2) && board.objectAt(3, 2).equals(0, 0)) {
                adjustForVarSix(true);
                alteration = true;
            } else if (m == 12 && !board.isEmpty(3, 4) && board.objectAt(3, 4).equals(0, 1)) {
                adjustForVarSeven(true);
                alteration = true;
            }
        } else if (branch1) {
            if (m == 16 && board.objectAt(2, 2).equals(2, 1)) {
                adjustForVarTwo(true);
                alteration = true;
            } else if (m == 14 && !board.isEmpty(3, 4) && board.objectAt(3, 4).equals(0, 1)) {
                adjustForMisFour(true);
                alteration = true;
            }
        } else if (branch2 && m == 18 && !board.isEmpty(5, 5) && board.objectAt(5, 5).equals(2, 1)) {
            adjustForVarThree(true);
            alteration = true;
        } else if (branch3 && m == 11 && !board.isEmpty(6, 0) && board.objectAt(6, 0).equals(5, 0)) {
            adjustForVarFive(true);
            alteration = true;
        }
        return alteration;
    }

    public void checkVariations(int m) {
        if (rGen.nextInt(4) == 0) {
            if (mainline) {
                if (m == 12) {
                    adjustForVarOne(false);
                } else if (m == 7 && rGen.nextInt(2) == 0) {
                    adjustForVarFour(false);

                } else if (m == 6) {
                    adjustForVarSix(false);
                } else if (m == 11) {
                    adjustForVarSeven(false);
                }
            } else if (branch1 && m == 15) {
                adjustForVarTwo(false);
            } else if (branch2 && m == 17) {
                adjustForVarThree(false);
            } else if (branch3 && m == 10) {
                adjustForVarFive(false);
            }
        }
    }

    public void adjustForMisOne(boolean response) {
        if (!response) {
            openingMove[8] = new Move(board.objectAt(3, 1), 3, 3, true);
        }
        openingMove[9] = new Move(board.objectAt(4, 4), 3, 3, true);
        openingMove[9].addCapturedPiece(board.objectAt(3, 1));
        openingMove[10] = new Move(board.objectAt(2, 2), 3, 3, true);
        openingMove[10].addCapturedPiece(board.objectAt(4, 4));
        openingMove[11] = new Move(board.objectAt(2, 4), 1, 5, true);
        openingMove[12] = new Move(board.objectAt(1, 0), 2, 2, true);
        openingMove[13] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[14] = new Move(board.objectAt(3, 0), 3, 2, true);
        openingMove[15] = new Move(board.objectAt(2, 7), 6, 3, true);
        openingMove[16] = new Move(board.objectAt(2, 0), 4, 2, true);
        openingMove[17] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[17].changeCastleIndex(1);
        openingMove[18] = new Move(board.objectAt(0, 1), 0, 2, true);
        openingMove[19] = new Move(board.objectAt(5, 7), 4, 7, true);
        openingMove[20] = new Move(board.objectAt(2, 3), 1, 2, true);
        openingMove[21] = new Move(board.objectAt(3, 7), 3, 6, true);
        openingMove[22] = new Move(board.objectAt(5, 2), 3, 1, true);
        openingMove[23] = new Move(board.objectAt(7, 7), 4, 6, true);
        openingMove[24] = new Move(board.objectAt(5, 1), 5, 2, true);
        openingMove[25] = new Move(board.objectAt(2, 7), 7, 4, true);
        openingMove[26] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[26].changeCastleIndex(1);
        size = 27;
        mainline = false;
    }

    public void adjustForMisTwo(boolean response) {
        if (!response) {
            openingMove[10] = new Move(board.objectAt(2, 2), 3, 3, true);
            openingMove[10].addCapturedPiece(board.objectAt(3, 3));
        }
        openingMove[11] = new Move(board.objectAt(2, 4), 1, 5, true);
        openingMove[12] = new Move(board.objectAt(2, 2), 3, 4, true);
        openingMove[13] = new Move(board.objectAt(2, 5), 1, 7, true);
        openingMove[14] = new Move(board.objectAt(4, 3), 4, 4, true);
        openingMove[15] = new Move(board.objectAt(5, 5), 6, 7, true);
        openingMove[16] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[17] = new Move(board.objectAt(5, 5), 4, 6, true);
        openingMove[18] = new Move(board.objectAt(2, 2), 3, 5, true);
        openingMove[19] = new Move(board.objectAt(5, 5), 6, 5, true);
        openingMove[20] = new Move(board.objectAt(5, 2), 6, 4, true);
        openingMove[21] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[21].changeCastleIndex(1);
        openingMove[22] = new Move(board.objectAt(3, 0), 7, 4, true);
        size = 23;
        mainline = false;
    }

    public void adjustForMisThree(boolean response) {
        if (!response) {
            openingMove[12] = new Move(board.objectAt(2, 3), 3, 4, true);
        }
        openingMove[13] = new Move(board.objectAt(5, 6), 5, 4, true);
        openingMove[14] = new Move(board.objectAt(3, 3), 2, 4, true);
        openingMove[14].addCapturedPiece(board.objectAt(2, 4));
        size = 15;
        mainline = false;
    }

    public void adjustForMisFour(boolean response) {
        if (!response) {
            openingMove[14] = new Move(board.objectAt(4, 3), 3, 4, true);
            openingMove[14].addCapturedPiece(board.objectAt(3, 4));
        }
        openingMove[15] = new Move(board.objectAt(5, 5), 3, 4, true);
        openingMove[15].addCapturedPiece(board.objectAt(4, 3));
        openingMove[16] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[16].changeCastleIndex(1);
        openingMove[17] = new Move(board.objectAt(1, 3), 2, 2, true);
        openingMove[17].addCapturedPiece(board.objectAt(2, 2));
        openingMove[18] = new Move(board.objectAt(1, 1), 2, 2, true);
        openingMove[18].addCapturedPiece(board.objectAt(1, 3));
        openingMove[19] = new Move(board.objectAt(2, 7), 4, 5, true);
        openingMove[20] = new Move(board.objectAt(5, 0), 4, 0, true);
        openingMove[21] = new Move(board.objectAt(3, 7), 3, 6, true);
        openingMove[21].changeCastleIndex(1);
        openingMove[22] = new Move(board.objectAt(5, 2), 6, 4, true);
        size = 23;
        branch1 = false;
    }

    public void adjustForVarOne(boolean response) {
        if (response) {
            openingMove[15] = new Move(board.objectAt(5, 5), 2, 2, true);
            openingMove[15].addCapturedPiece(board.objectAt(2, 2));
        } else {
            openingMove[12] = new Move(board.objectAt(1, 0), 2, 2, true);
            openingMove[15] = new Move(board.objectAt(5, 5), 2, 2, true);
            openingMove[15].addCapturedPiece(board.objectAt(1, 0));
        }
        openingMove[13] = new Move(board.objectAt(5, 5), 4, 3, true);
        openingMove[13].addCapturedPiece(board.objectAt(4, 3));
        openingMove[14] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[14].changeCastleIndex(1);
        openingMove[16] = new Move(board.objectAt(1, 1), 2, 2, true);
        openingMove[16].addCapturedPiece(board.objectAt(5, 5));
        openingMove[17] = new Move(board.objectAt(1, 3), 2, 2, true);
        openingMove[17].addCapturedPiece(board.objectAt(1, 1));
        openingMove[18] = new Move(board.objectAt(3, 0), 1, 2, true);
        openingMove[19] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[20] = new Move(board.objectAt(2, 3), 3, 4, true);
        openingMove[20].addCapturedPiece(board.objectAt(3, 6));
        openingMove[21] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[21].changeCastleIndex(1);
        openingMove[22] = new Move(board.objectAt(2, 3), 5, 6, true);
        openingMove[22].addCapturedPiece(board.objectAt(5, 6));
        openingMove[23] = new Move(board.objectAt(4, 7), 7, 7, true);
        openingMove[24] = new Move(board.objectAt(3, 0), 2, 2, true);
        openingMove[24].addCapturedPiece(board.objectAt(1, 3));
        openingMove[25] = new Move(board.objectAt(7, 7), 5, 6, true);
        size = 26;
        mainline = false;
        branch1 = true;
    }

    public void adjustForVarTwo(boolean response) {
        if (response) {
            openingMove[18] = new Move(board.objectAt(1, 1), 2, 2, true);
            openingMove[18].addCapturedPiece(board.objectAt(2, 2));
        } else {
            openingMove[15] = new Move(board.objectAt(1, 3), 2, 2, true);
            openingMove[18] = new Move(board.objectAt(1, 1), 2, 2, true);
            openingMove[18].addCapturedPiece(board.objectAt(1, 3));
        }
        openingMove[15].addCapturedPiece(board.objectAt(2, 2));
        openingMove[16] = new Move(board.objectAt(3, 3), 3, 4, true);
        openingMove[17] = new Move(board.objectAt(2, 5), 4, 4, true);
        openingMove[19] = new Move(board.objectAt(2, 5), 2, 3, true);
        openingMove[19].addCapturedPiece(board.objectAt(2, 3));
        openingMove[20] = new Move(board.objectAt(3, 0), 3, 3, true);
        openingMove[21] = new Move(board.objectAt(5, 6), 5, 4, true);
        openingMove[22] = new Move(board.objectAt(3, 3), 2, 3, true);
        openingMove[22].addCapturedPiece(board.objectAt(2, 5));
        openingMove[23] = new Move(board.objectAt(3, 6), 3, 5, true);
        size = 24;
        branch1 = false;
        branch2 = true;
    }

    public void adjustForVarThree(boolean response) {
        if (response) {
            openingMove[23] = new Move(board.objectAt(5, 5), 6, 4, true);
            openingMove[23].addCapturedPiece(board.objectAt(2, 0));
            openingMove[24] = new Move(board.objectAt(5, 2), 6, 4, true);
            openingMove[24].addCapturedPiece(board.objectAt(5, 5));
        } else {
            openingMove[17] = new Move(board.objectAt(2, 2), 5, 5, true);
            openingMove[23] = new Move(board.objectAt(2, 2), 6, 4, true);
            openingMove[23].addCapturedPiece(board.objectAt(2, 0));
            openingMove[24] = new Move(board.objectAt(5, 2), 6, 4, true);
            openingMove[24].addCapturedPiece(board.objectAt(2, 2));
        }
        openingMove[18] = new Move(board.objectAt(5, 0), 4, 0, true);
        openingMove[19] = new Move(board.objectAt(2, 5), 4, 6, true);
        openingMove[20] = new Move(board.objectAt(5, 0), 4, 3, true);
        openingMove[20].addCapturedPiece(board.objectAt(4, 3));
        openingMove[21] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[22] = new Move(board.objectAt(2, 0), 6, 4, true);
        openingMove[25] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[25].changeCastleIndex(1);
        openingMove[26] = new Move(board.objectAt(5, 2), 7, 6, true);
        openingMove[26].addCapturedPiece(board.objectAt(7, 6));
        openingMove[27] = new Move(board.objectAt(4, 7), 7, 6, true);
        openingMove[27].addCapturedPiece(board.objectAt(5, 2));
        openingMove[28] = new Move(board.objectAt(3, 0), 7, 4, true);
        openingMove[29] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[30] = new Move(board.objectAt(5, 0), 7, 3, true);
        openingMove[31] = new Move(board.objectAt(5, 6), 5, 4, true);
        size = 32;
        branch2 = false;
    }

    public void adjustForVarFour(boolean response) {
        if (!response) {
            openingMove[7] = new Move(board.objectAt(2, 4), 1, 5, true);
        }
        openingMove[8] = new Move(board.objectAt(3, 1), 3, 3, true);
        openingMove[9] = new Move(board.objectAt(3, 7), 4, 6, true);
        openingMove[10] = new Move(board.objectAt(3, 1), 4, 4, true);
        openingMove[10].addCapturedPiece(board.objectAt(4, 4));
        openingMove[11] = new Move(board.objectAt(2, 5), 4, 4, true);
        openingMove[10].addCapturedPiece(board.objectAt(3, 1));
        openingMove[12] = new Move(board.objectAt(5, 2), 4, 4, true);
        openingMove[12].addCapturedPiece(board.objectAt(2, 5));
        openingMove[13] = new Move(board.objectAt(3, 7), 4, 4, true);
        openingMove[13].addCapturedPiece(board.objectAt(5, 2));
        openingMove[14] = new Move(board.objectAt(4, 0), 6, 0, true);
        openingMove[14].changeCastleIndex(1);
        openingMove[15] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[16] = new Move(board.objectAt(3, 0), 4, 1, true);
        openingMove[17] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[18] = new Move(board.objectAt(1, 0), 3, 1, true);
        openingMove[19] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[19].changeCastleIndex(1);
        openingMove[20] = new Move(board.objectAt(1, 0), 5, 2, true);
        openingMove[21] = new Move(board.objectAt(3, 7), 7, 4, true);
        size = 22;
        mainline = false;
        branch3 = true;
    }

    public void adjustForVarFive(boolean response) {
        if (response) {
            openingMove[12] = new Move(board.objectAt(5, 0), 4, 0, true);
        } else {
            openingMove[10] = new Move(board.objectAt(4, 0), 6, 0, true);
            openingMove[10].changeCastleIndex(1);
            openingMove[12] = new Move(board.objectAt(7, 0), 4, 0, true);
        }
        openingMove[11] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[13] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[14] = new Move(board.objectAt(0, 1), 0, 3, true);
        openingMove[15] = new Move(board.objectAt(0, 6), 0, 5, true);
        openingMove[16] = new Move(board.objectAt(7, 1), 7, 2, true);
        openingMove[17] = new Move(board.objectAt(4, 7), 6, 7, true);
        openingMove[17].changeCastleIndex(1);
        openingMove[18] = new Move(board.objectAt(1, 1), 1, 3, true);
        openingMove[19] = new Move(board.objectAt(7, 6), 7, 5, true);
        openingMove[20] = new Move(board.objectAt(2, 0), 0, 2, true);
        openingMove[21] = new Move(board.objectAt(6, 7), 3, 6, true);
        openingMove[22] = new Move(board.objectAt(1, 1), 1, 4, true);
        openingMove[23] = new Move(board.objectAt(2, 5), 3, 7, true);
        openingMove[24] = new Move(board.objectAt(1, 0), 3, 1, true);
        size = 25;
        mainline = false;
    }

    public void adjustForVarSix(boolean response) {
        if (!response) {
            openingMove[6] = new Move(board.objectAt(3, 1), 3, 2, true);
        }
        openingMove[7] = new Move(board.objectAt(6, 7), 5, 5, true);
        openingMove[8] = new Move(board.objectAt(1, 0), 2, 2, true);
        openingMove[9] = new Move(board.objectAt(3, 6), 3, 5, true);
        openingMove[10] = new Move(board.objectAt(2, 0), 6, 4, true);
        openingMove[11] = new Move(board.objectAt(7, 6), 7, 5, true);
        openingMove[12] = new Move(board.objectAt(2, 0), 5, 5, true);
        openingMove[12].addCapturedPiece(board.objectAt(6, 7));
        openingMove[13] = new Move(board.objectAt(3, 7), 5, 5, true);
        openingMove[13].addCapturedPiece(board.objectAt(2, 0));
        openingMove[14] = new Move(board.objectAt(1, 0), 3, 4, true);
        openingMove[15] = new Move(board.objectAt(3, 6), 3, 4, true);
        openingMove[16] = new Move(board.objectAt(2, 1), 2, 2, true);
        if (rGen.nextInt(2) == 0) {
            openingMove[17] = new Move(board.objectAt(2, 5), 4, 6, true);
        } else {
            openingMove[17] = new Move(board.objectAt(2, 5), 0, 4, true);
        }
        size = 18;
        mainline = false;
    }

    public void adjustForVarSeven(boolean response) {
        if (!response) {
            openingMove[11] = new Move(board.objectAt(3, 6), 3, 4, true);
        }
        openingMove[12] = new Move(board.objectAt(2, 3), 1, 4, true);
        openingMove[13] = new Move(board.objectAt(5, 5), 4, 3, true);
        openingMove[13].addCapturedPiece(board.objectAt(4, 3));
        size = 14;
        mainline = false;
    }
}
