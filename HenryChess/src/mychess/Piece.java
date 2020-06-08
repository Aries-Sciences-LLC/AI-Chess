package mychess;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;


public abstract class Piece {
	protected Chessboard board;
	protected int myColor; 
	protected int myPiece, myValue;
	public String myName, imageFile, abbreviation;
	protected String colorString [] = {"white", "black"};
	protected int myRow, myCol, newRow, newCol;
	protected ImageIcon icon;
        public boolean hasMoved = false;
        // Jannik: Change filename to be relative within the classpath
	// Make filename a constant the java way
	// public String filename = "C:/Program Files/Gifs/";
	public static final String GIF_BASE_FILENAME = "/Gifs/";
        
         
            	
    public Piece (Chessboard aBoard, int row, int col, 
			int color, int piece, int value, String name){
		board = aBoard;
		myRow = row;
		myCol = col;
		myColor = color;
		myPiece = piece;
		myValue = value;
		myName = colorString[color]+name;
                String iconFile = GIF_BASE_FILENAME + myName + ".gif";

		// Jannik: Load image from within your classpath which only includes the
		// jarfile content
		// icon = new ImageIcon(iconFile, "icon");
	        icon = new ImageIcon(getClass().getResource(iconFile));
                
                
//		String iconFile =  filename + myName + ".gif";    
//		icon = new ImageIcon(iconFile, "icon");
 		addToBoard();	
	}
	
	
//	abstract method	
	public abstract void findMoves (ArrayList theMoves, boolean actualMove); 
	
	//	modifier methods
	public void addToBoard(){
		board.add(this);
	}
			
	public void move(int newRow, int newCol) {
 		board.recordMove(this,newRow, newCol);
 		myRow = newRow;
 		myCol = newCol;
     }
    
    protected void die() {
        board.remove(this);
    }
    
    protected void unDie(){	
    	addToBoard();
    }
    
    
//	accessor methods	
 	public int row(){ 
 		return myRow;
 	}
 	
 	public int col(){
 		return myCol;
 	}
 	
 	public int color(){
		return myColor;
	}
	
	public int value(){
		return myValue;
	}
		
	public int piece(){
		return myPiece;
	}
	
	public String name(){
		return myName;	
	}
        
        public String getAbbreviation(){
            return abbreviation;
        }
	
        public boolean equals(int piece, int color){
        	return piece == myPiece && color == myColor;
        }

     /*  public void setHasMoved(boolean myHasMoved){
            hasMoved = myHasMoved;
        }*/

        public boolean getHasMoved(){
            return hasMoved;
        }

    public ImageIcon icon(){
    	return icon;
    }
    
    public void moveDiagonally(ArrayList moves, boolean actualMove){
    	int toRow,toCol;
		for (int i=0; i<2; i++){
			for (int j=0; j<2; j++){
				toRow = myRow + (1-2*i);
				toCol = myCol + (1-2*j);
				while (board.isValid(toRow,toCol)&&  board.objectAt(toRow,toCol)==null){
					moves.add(new Move (this, toRow, toCol, false));
					toRow+= 1-2*i; toCol+= 1-2*j;	
				}
				if (board.canAdd(this,toRow,toCol,actualMove)){
					moves.add(new Move (this, toRow, toCol, false));
				}
			}
		}
    }
    
    public void moveRectilinearly(ArrayList moves, boolean actualMove){
    	int toRow, toCol;
		for (int i=0; i<2; i++){
			toRow = myRow+(1-2*i); toCol = myCol;
			while(board.isValid(toRow,toCol) && board.isEmpty(toRow,toCol)){
				moves.add(new Move (this, toRow, toCol, false));
				toRow += 1-2*i;
			}
			if (board.canAdd(this,toRow, toCol,actualMove)){
				moves.add(new Move (this, toRow, toCol, false));
			}
			toRow = myRow; toCol = myCol+(1-2*i);
			while(board.isValid(toRow,toCol) && board.isEmpty(toRow,toCol)){
				moves.add(new Move (this, toRow, toCol, false));
				toCol += 1-2*i;
			}
			if (board.canAdd(this,toRow,toCol,actualMove)){
				moves.add(new Move (this, toRow, toCol, false));
			}
		}
	}
 }




 