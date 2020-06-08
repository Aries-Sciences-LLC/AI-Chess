package mychess;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.concurrent.TimeUnit;

public class Chessboard extends JPanel {
	private Piece [][] theBoard;
        private int moveNumber;
	private int pieceCount[] = new int [2];
	private static final int ROWS = 8, COLS= 8;
        private Piece [] castle = new Piece [4];
        public Piece [] king = new Piece [2];
	private final Color WHITE = Color.white;
	private final Color BLACK = Color.black;
	private final Color RED = Color.red;
	private final int CELLSIZE = (600/8) - 15;
	private boolean capturing;
	private boolean gameOver;
	private String stringDisplay1 = "";
	private String stringDisplay2 = "";
	private int displayCount, oldRow, oldCol, newRow, newCol, movesSinceCapture;
	private boolean flash;
	private BoardPosition boardPosition;
	private ArrayList positionList = new ArrayList();
        private ArrayList moveList = new ArrayList();
        private int reverse;
              public int dx;
              public int dy;
     	
	public Chessboard(Dimension bounds){
		theBoard = new Piece [ROWS][COLS];
                resetBoard();
            this.dx = (int) (bounds.getWidth() / 2) - ((CELLSIZE * 8) / 2);
            this.dy = (int) (bounds.getHeight() / 2) - ((CELLSIZE * 8) / 2);
	}
        
        public void resetBoard(){
            reverse = 0;
            moveNumber = 0;
            capturing = false;
	    gameOver = false;
            Piece piece = null;
            pieceCount [0]= 0; pieceCount[1]=0;
            for (int row =0; row<8; row++){
                for (int col =  0; col<8; col++){
                    theBoard[row][col] = null;
                }
            }
            for (int color=0; color<2; color++){
                    for (int i=0; i<8; i++){
                            piece = new Pawn (this, i,1+5*color, color);
                    }
                    for (int i=0; i<2; i++){
                            castle [i+2*color] = new Castle (this, i*7, 7*color, color);
                            piece = new Knight (this, 1+i*5, 7*color, color);
                            piece = new Bishop (this, 2+i*3, 7*color, color);
                    }
                    piece = new Queen (this, 3, 7*color, color);
                    king [color] = new King (this, 4, 7*color, color);
             }
            
        }
                
        public void setReverse(){
            reverse = 1;
        }
	
//	modifier methods

    public void add(Piece piece){
        theBoard[piece.row()][piece.col()] = piece;
        pieceCount[piece.color()]++;
    }   
    
    public void remove(Piece piece){
        if (objectAt(piece.row(),piece.col()) != piece){
        	System.out.println("Queened?");
        }
        theBoard[piece.row()][piece.col()] = null;
        pieceCount[piece.color()]--;
    }
    
    public void recordMove(Piece piece, int newRow, int newCol){
    	theBoard[newRow][newCol] = piece;
    	theBoard[piece.row()][piece.col()] = null;
    }
    
    public void updateMoveNumber(){
        moveNumber++;
    }
    
    public int getMoveNumber(){
        return moveNumber;
    }
    
    public boolean getCanCastle (int color, int castleSide){
         return !(castle[castleSide+2*color].getHasMoved()|| king[color].getHasMoved());
    }
  
    public void move(Move aMove){
        int newRow = aMove.newRow(), newCol = aMove.newCol();
        Piece piece = aMove.piece();
        if (!isEmpty(newRow,newCol)){
            Piece capturedPiece = objectAt(newRow,newCol);
            aMove.addCapturedPiece(capturedPiece);  
        } 
        if (aMove.capturedPiece()!=null){
            aMove.capturedPiece().die();
        }
        if (aMove.queened()){
            Pawn pawn = (Pawn)piece;
            pawn.queenPawn();
        }
        else{
            if (aMove.castleIndex()!=-1){
                    Piece castle = objectAt(7*aMove.castleIndex(),7*piece.color());
                    castle.move(3+2*aMove.castleIndex(), 7*piece.color());
            }
        }
        moveList.add(aMove);
        piece.move(newRow,newCol);
    }
	
    public void gameOver(){
        gameOver = true;
    }	
	
    public void moveBack (Move aMove){
        Piece piece = aMove.piece();
        if (aMove.castleIndex()!=-1){
            Piece castle = objectAt(3+2*aMove.castleIndex(), 7*piece.color());
            castle.move(7*aMove.castleIndex(),7*piece.color());
        }
        if (aMove.queened()){
            Pawn pawn = (Pawn)piece;
            pawn.reverseQueenPawn();	
        }
        piece.move(aMove.oldRow(),aMove.oldCol());
        if (aMove.capturedPiece()!=null){
            aMove.capturedPiece().unDie();
        }
        moveList.remove(moveList.size()-1);
    }
     
    public void updateCapturing (boolean capturing){
    	this.capturing = capturing;
    }
    
    public boolean addPosition(){
    	BoardPosition position = new BoardPosition(theBoard);
    	if (repeatedPosition(position.boardPosition())>=3){
            return false;
    	}
    	else{
            positionList.add(position);
            return true;
    	}
    }
    
    public void setMovesSinceCapture(int m){
    	movesSinceCapture = m;
    }
    
//	accessor methods	
    public boolean isEmpty(int row, int col){
		return theBoard[row][col] == null;
	}
	
    public boolean isValid(int row, int col){
            return row>=0 && row<ROWS
             && col>=0 && col<COLS;
    }
        
     public Piece objectAt(int row, int col){
        return theBoard[row][col];
    }
     
     public boolean checkIfPiece(int pieceValue, int color){
         Iterator iter = pieceList(color).iterator();
         boolean pieceIsFound = false;
         while (!pieceIsFound && iter.hasNext()){
            Piece piece = (Piece)iter.next();
            if (piece.value()==pieceValue){
                pieceIsFound = true;
            }
         }                     
        return pieceIsFound;         
     }
     
    public int getPieceValue(int row, int col, int color) {
        int value = -1;
        if (isValid(row,col) && !isEmpty(row,col) && objectAt(row,col).color()==color){
            value = objectAt(row,col).value();
        }
        return value;
    }
	
    public boolean canAdd(Piece piece, int row, int col, boolean actualMove){
        return isValid(row, col) && (isEmpty(row,col)||!actualMove||objectAt(row,col).color()!=piece.color());
    }
		
    
    public boolean capturing(){
    	return capturing;
    }  
    	
    public int pieceCount(int color){
            return pieceCount[color];
    }
	
    public List pieceList(int color){
            List pieceList = new ArrayList();
            Piece aPiece = null;
            for (int i=0; i<ROWS; i++){
                    for (int j=0; j<COLS; j++){
                            if (!(isEmpty(i,j))){
                                    aPiece = objectAt(i,j);
                                    if (aPiece.color()==color){
                                            pieceList.add(aPiece);
                                    }
                            }
                    }
            }
            return pieceList;
    }
	
    public int repeatedPosition(Piece[][] boardArray){
    	int rePete = 0;
    	if (boardArray==null){
    		boardArray = theBoard;
    	}
       	for (int i=0; i<positionList.size(); i++){
    		if (((BoardPosition)positionList.get(i)).equals(boardArray)){
    			rePete++;	
    		}
    	}
    	return rePete;
    }
    
    public int movesSinceCapture(){
    	return movesSinceCapture;
    }
	
    public Position getPosition(int x, int y){
    	return new Position(y / CELLSIZE, x / CELLSIZE);
    }	
    
    public Move getLastMove(){
        return (Move)moveList.get(moveList.size()-1);
    }
	
	
//	display methods	

    public void setDisplayCount(int count, boolean f){
    	displayCount = count;
    	flash = f;
    }

   public void setMove(Move aMove){
        oldRow = aMove.piece().row();
        oldCol = aMove.piece().col();
        newRow = aMove.newRow();
        newCol = aMove.newCol();
    }
  
    public void update(){
 //       displayBoard();
         repaint();
    }
	
    public void paintComponent(Graphics g){
        int x=0, y=0;
        Piece piece = null;
        Component comp = new Label("icon");
        ImageIcon icon = null;

        ((Graphics2D) g).setPaint(new GradientPaint(400, 0, new Color(55, 98, 103),
        400, 700, new Color(40, 41, 61)));
        ((Graphics2D) g).fill(new Rectangle2D.Double(0, 0, 800, 700));

        for (int row = 0; row<8; row++){
            for (int col = 0; col<8; col++){
                x = (Math.abs(7*reverse - col) * CELLSIZE) + this.dx;
                y = (Math.abs(7*reverse - row) * CELLSIZE) + this.dy;
                if (flash && displayCount!=0 && displayCount%2 !=0 && row==(7-oldCol) && col==oldRow){
                    g.setColor(RED);
                    g.fillRect(x,y,CELLSIZE, CELLSIZE);
                }
                else if (flash && displayCount!=0 && displayCount%2==0 && row==(7-newCol) && col==newRow){
                    g.setColor(RED);
                    g.fillRect(x,y,CELLSIZE, CELLSIZE);
                }
                else if ((row+col)%2==1){
                    g.setColor(BLACK);
                    g.fillRect(x,y,CELLSIZE, CELLSIZE);
                }
                else{
                    g.setColor(WHITE);
                    g.fillRect(x,y,CELLSIZE, CELLSIZE);
                }
            }
        }
        for (int row = 0; row<8; row++){
            for (int col = 0; col<8; col++){
                if(theBoard[row][col]!=null){
                    piece = objectAt(row,col);
                    icon = piece.icon();
                    x = ((CELLSIZE-icon.getIconWidth())/2+CELLSIZE*Math.abs(7*reverse - row)) + this.dx;
                    y = (CELLSIZE-icon.getIconHeight()+CELLSIZE*(7-(Math.abs(7*reverse - col)))) + this.dy;
                    icon.paintIcon (comp, g, x, y);
                }
            }
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(stringDisplay1, this.dx, 60);
        g.drawString(stringDisplay2, this.dx, 60);
        if (displayCount > 0){
            displayCount--;
        }
    }
	
    public void stringDisplay(String string1, String string2){
        gameOver = true;
        stringDisplay1 = string1;
        stringDisplay2 = string2;
    }
	
    public void stringDisplay(String string){
        stringDisplay2 = string;
    }

/*     This method is not currently being used.
	public void displayBoard(){
		String aString = null;
		System.out.print("\n");
		for (int j=7; j>=0; j--){
			System.out.print("\t");
			for (int i=0; i<8; i++){
				if (theBoard[i][j]!=null){
					aString = theBoard[i][j].name().substring(0,1)
						+ theBoard[i][j].name().substring(5);
					System.out.print(aString + "\t");
				}
				else{
					System.out.print("x \t");
				}
			}
			System.out.println("\n");
		}
	}
 *
 */
}
