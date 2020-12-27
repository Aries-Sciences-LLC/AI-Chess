/* ChessGame.java
 * Created on October 22, 2006, 8:52 AM
 * @authormhenry
 */

package mychess;

import java.util.*;
import javax.swing.*;

public class ChessGame {
	private ChessJApplet applet;
	private Chessboard  board;
	private Player [] player = new Player [2];
	private LegalMoves legalMoves;
	private int reverseLevel, playerColor, movesSinceCapture = -1, move=-1, color = 0;
	private boolean gameOver = false;
        private EasyWriter outFile = new EasyWriter("LastGame.txt");
	private String levelArray[] = {"Tadpawn", "Knight of the Square Table", "The Bishop Danced",
        "New Castle", "Queen Bee", "King of Pop"};
	private int numLevels = levelArray.length;
	
	public ChessGame(Chessboard board, ChessJApplet applet, int level){
		this.board = board;
		this.applet = applet;
                reverseLevel = numLevels-level;
		playerColor = 0;
		legalMoves = new LegalMoves(board);
		Move move = new Move(board);
	
                // The "reverseLevel" is used so that the highest level is 0 independent of the number of levels.                


  /*
		if (playerColor==-1){
                    // Here, the computer plays itself. The test player will start out as black.
			player[0] = new ComputerPlayer(0, board, applet, legalMoves, 0, numLevels, false);	
                        player[1] = new ComputerPlayer(1, board, applet, legalMoves, 0, numLevels, true);	
		}
   */
                if (level==-1){
                    System.out.println("Player plays itself");
                    player[0] = new HumanPlayer(0, board, this);
                    player[1] = new HumanPlayer(1, board, this);
                    player[1].reverse = 0;
                }

                else{
                     player[playerColor] = new HumanPlayer(playerColor, board, this);
                     player[(playerColor+1)%2] = new ComputerPlayer((playerColor+1)%2, board, this, legalMoves,
                        reverseLevel, numLevels, false);
                     if (playerColor == 1){
                           board.setReverse();
                     }
                }
	}
	
	public void makeMove(){
            if (move>=0){
                Move lastMove = board.getLastMove();
                Piece piece = lastMove.piece();
                if (piece.myPiece == 3 ||piece.myPiece == 5){
                   piece.hasMoved = true;
                }
 
                System.out.print(lastMove.getOpeningMoveString());
                if (move%2==0){
                   int temp = move/2+1;
                    outFile.print(temp + " " + lastMove);
                }
                else {
                    outFile.println(" " + lastMove);
                }
                board.updateMoveNumber();
            }
                if(!board.addPosition()){
			staleMate();
		}
		else if (board.capturing()){
			movesSinceCapture = 0;
		}
		else {
			movesSinceCapture++;
			if(movesSinceCapture>=50){
				staleMate();
			}
		}
		board.setMovesSinceCapture(movesSinceCapture);
		if (!gameOver){
			move++;
			color = move%2;
			ArrayList moveList = null;
			moveList = legalMoves.getMoveList(color,true);
			if (moveList.size()==0){
				gameOver = true;
                                if (!(legalMoves.inCheck(legalMoves.fillList((color+1)%2, true),color))){
					staleMate();
				}
				else{
					displayWinner((color+1)%2, false);
				}	
			}
			else{
				player[color].chooseMove(moveList);
			}
		}
	}
	
	private void staleMate(){
		gameOver=true;
		displayWinner(-1, true);
	}
	
	private void displayWinner(int color, boolean staleMate){
		String string1 = "", string2 = "";
		if (staleMate){
			string1 = "Stalemate!";
		}
		else if (color == playerColor){
                      string1 = "Congratulations, you've won! ";
                }
                else{
                    string1 = "Checkmate.";
		}
		board.stringDisplay(string1, string2);
                board.gameOver();
                System.out.println("GAME OVER");
        }

        public void reSet(){
             board.resetBoard();
             board.stringDisplay("", "");
             playerColor=(playerColor+1)%2;
             outFile.println("\r\n\r\n");
             if (playerColor ==1){
                    board.setReverse();
             }
             player[playerColor] = new HumanPlayer(playerColor, board, this);
             player[(playerColor+1)%2] = new ComputerPlayer((playerColor+1)%2, board, this, legalMoves,
                       reverseLevel, numLevels, false);
             board.update();
             movesSinceCapture = -1;
             move=-1;
             color = 0;
	     gameOver = false;
             makeMove();
        }

        public void hasMoved(){
		board.update();
                makeMove();
	}

        public void endGames(){
             outFile.close();
        }

}
