package com.burcumirza.HenryChess.Logic;

import com.burcumirza.HenryChess.Pieces.*;

public class BoardPosition {
    private Piece[][] myBoardArray = new Piece[8][8];

    public BoardPosition(Piece[][] theBoard) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                myBoardArray[row][col] = theBoard[row][col];
            }
        }
    }

    public Piece[][] boardPosition() {
        return myBoardArray;
    }

    public boolean equals(Piece[][] boardArray) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((myBoardArray[row][col] != null && boardArray[row][col] != null
                        && !(myBoardArray[row][col] == boardArray[row][col]))
                        || (myBoardArray[row][col] == null && boardArray[row][col] != null)
                        || (myBoardArray[row][col] != null && boardArray[row][col] == null)) {
                    return false;
                }
            }
        }
        return true;
    }
}
