package mychess;
import java.util.*;

public class Castle extends Piece {
	private int castleIndex;
		
	public Castle (Chessboard board, int row, int col, int color){
		super(board, row, col, color, 3, 5, "Castle");
		castleIndex = row/7;
                abbreviation = "C"; 
	}
	
	public void findMoves(ArrayList moves, boolean actualMove){
		moveRectilinearly(moves, actualMove);
	}
	
	public int castleIndex(){
		return castleIndex;
	}



}

	