package mychess;
import java.util.*;

public class ComputerPlayer extends Player {
	private static Random rGen = new Random();
	private Strategy strategy;
	private OpeningBank openingBank;
	private boolean opening = true;
	private int reverseLevel;
        private double probability;

		
	public ComputerPlayer(int color, Chessboard board, ChessGame chessGame,
		LegalMoves legalMoves, int reverseLevel, int numLevels, boolean testPlayer){
		super (color, board,true,chessGame);
                this.reverseLevel = reverseLevel;
                probability = 100*(1.0-Math.exp(-2.0*(reverseLevel-1.0)/(numLevels-2.0)));
		openingBank = new OpeningBank(board, legalMoves);
                if (testPlayer == false){
                    strategy = new Strategy(board,legalMoves,reverseLevel, numLevels, testPlayer);
                }
                else{
                    strategy = new TestStrategy(board, legalMoves, reverseLevel, numLevels, testPlayer);
                    System.out.println("Test player.");
                }
		
	}
	
	public void chooseMove(ArrayList moveList){
		if(opening){
			Move move = openingBank.getOpeningMove(moveNumber);
			if (move!=null){
				aMove = move;
// For troubleshooting....
                                board.stringDisplay(openingBank.getOpeningString());
				if (rGen.nextInt(100)<probability){
					opening = false;
				}		
			}	
			else{
// For troubleshooting....
                            board.stringDisplay("");
				opening = false;
			}
		}
		if (!opening){                
			aMove = strategy.chooseMove(moveList);
		}
                board.setMove(aMove);
		setMove(aMove, true, 3);	
	}
}