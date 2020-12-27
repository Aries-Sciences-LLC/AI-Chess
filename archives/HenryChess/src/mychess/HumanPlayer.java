package mychess;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.swing.*;

public class HumanPlayer extends Player implements MouseListener{
	private Chessboard board;
	private Piece movingPiece;
	private List moveList;
	private boolean myTurn,firstClick;

	public HumanPlayer(int color, Chessboard board, ChessGame chessGame){
		super(color,board,false,chessGame);
		this.chessGame = chessGame;
		this.board = board;
		board.addMouseListener(this);
	}

	public void chooseMove(ArrayList moveList){
		this.moveList=moveList;
		aMove=null;
		firstClick = true;
		board.updateCapturing(false);
		myTurn = true;	
	}



 // 	Called automatically when the mouse button is released
	public void mouseReleased(MouseEvent e){
    	if (myTurn){
    		Position position = board.getPosition(e.getX() - board.dx, e.getY() - board.dy);
		    int row = Math.abs(7*myColor*reverse - position.getCol() ) ;
		    int col = 7-Math.abs(7*myColor*reverse - position.getRow());
		    if (firstClick){
		    	if (board.objectAt(row,col)!=null && board.objectAt(row,col).color()==myColor){
		    		movingPiece = board.objectAt(row,col);
		    		firstClick=false;
		    	}	
		    }
		    else{
		    	aMove = new Move(movingPiece,row,col,false);
		    	Iterator iter = moveList.iterator();
		    	Move tempMove = (Move)iter.next();
		    	while(iter.hasNext() && !tempMove.equals(aMove)){
		    		tempMove = (Move)iter.next();
		    	}
		    	if (tempMove.equals(aMove)){
		    		myTurn = false;
		    		aMove=tempMove;
		    		board.move(aMove);
//*************************************************************************************************
                                board.stringDisplay("It's your move!");
		    		setMove(aMove,false,1);
		    	}
		    	else{
		    		firstClick = true;
		    	}
		    }
    	}
	}

  // Not used but required by the MouseListener interface spec:
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
