package com.burcumirza.HenryChess.Logic;

import java.util.*;
import javax.swing.Timer;
import java.awt.event.*;

public abstract class Player implements ActionListener {
    protected int myColor;
    protected Chessboard board;
    public ChessGame chessGame;
    private boolean computerPlayer;
    private Timer clock;
    private int clockCount;
    protected Move aMove;
    protected ChessJApplet applet;
    protected int moveNumber;
    private boolean flash;
    public String openingString = "";
    public int reverse = 1;

    public Player(int aColor, Chessboard aBoard, boolean computerPlayer, ChessGame chessGame) {
        myColor = aColor;
        moveNumber = myColor;
        board = aBoard;
        this.chessGame = chessGame;
        this.computerPlayer = computerPlayer;
        clock = new Timer(250, this);
    }

    public abstract void chooseMove(ArrayList<Move> moveList);

    public boolean computerPlayer() {
        return computerPlayer;
    }

    public void setMove(Move aMove, boolean f, int c) {
        flash = f;
        clockCount = c;
        board.setMove(aMove);
        board.setDisplayCount(clockCount, flash);
        clock.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (clockCount > 0) {
            board.update();
            clockCount--;
        } else {
            clock.stop();
            if (flash) {
                board.move(aMove);
                if (aMove.getCheckingKing()) {
                    board.stringDisplay("You're in check. ");
                } else {
                    // board.stringDisplay("It's your move.");
                }
            }
            moveNumber += 2;
            board.updateCapturing(aMove.capturedPiece() != null);
            chessGame.hasMoved();
        }
    }
}
