package mychess;
import java.util.*;

public class Queen extends Piece {
     
	public Queen (Chessboard board, int row, int col, int color){
		super(board, row, col, color, 4, 9, "Queen");
                abbreviation = "Q"; 
	}
	
	public void findMoves(ArrayList moves, boolean actualMove){
		moveRectilinearly(moves, actualMove);
		moveDiagonally(moves,actualMove);
	}
}

