package mychess;
public class Move {
	
	private static Chessboard board;
	private Piece piece, oldPiece, capturedPiece;
	private int newRow, newCol, oldRow, oldCol;
	private int castleIndex, value, numPlayKingMoves, numOppKingMoves;
        private boolean checkingKing;
        private int numRepeatedPositions, numPieceMoves;
	private boolean queened = false, opening = false, checkmated = false,
                gameOver = false;
	private String rowArray[] = {"a", "b", "c", "d", "e", "f","g","h"};
        private int numMoves = 0;
	
	public Move(Chessboard board){
		this.board = board;
	}
	
	public Move (Piece aPiece, int aNewRow, int aNewCol, boolean opening){
		this.opening = opening;
		piece = aPiece;
		newRow = aNewRow;
		newCol = aNewCol;
		oldRow = piece.row();
		oldCol = piece.col();
		castleIndex = -1;
                numRepeatedPositions = 0;
                checkingKing = false;
		value = 0;
                numPlayKingMoves = 0;
                numOppKingMoves = 0;
                numPieceMoves = 0;
	}
	
//	modifier methods

	public void setValue(int newValue){
            value = newValue;
	}
        
        public void addValue(int addedValue){
            value+=addedValue;
        }
        
        public void setCheckingKing(boolean checking){
            checkingKing = checking;
        }
        
        public void setPlayKingMoves(int numMoves){
            numPlayKingMoves = numMoves;
        }
        
        public void setOppKingMoves(int numMoves){
            numOppKingMoves = numMoves;
        }
        
        public void setMovingPieceMoves(int numMoves){
            numPieceMoves = numMoves;
        }
        
        public void setRepeatedPosition(int number){
            numRepeatedPositions = number;
        }
        
        public void setGameOver(){
            gameOver = true;
        }
        
	public void changeQueened(){
		queened = !queened;
	}
		
	public void addCapturedPiece(Piece piece){
		capturedPiece = piece;
	}
			
	//	accessor methods
	
	public Piece piece(){
		return piece;
	}
	
	public int newRow(){
		return newRow;
	}
	
	public int newCol(){
		return newCol;
	}
		
	public int oldRow(){
		return oldRow;
	}
	
	
	public int oldCol(){
		return oldCol;
	}
	
	public int value(){
		return value;
	}
	
        public boolean getCheckingKing(){
            return checkingKing;
        }
        
        public int getPlayKingMoves(){
            return numPlayKingMoves;
        }
        
        public int getOppKingMoves(){
            return numOppKingMoves;
        }
        
        public int getMovingPieceMoves(){
            return numPieceMoves;
        }
        
        public int getRepeatedPositions(){
            return numRepeatedPositions;
        }
        
        public boolean getGameOver(){
            return gameOver;
        }
        
	public boolean queened(){
		return queened;
	}

        public void changeCastleIndex(int index){
		castleIndex = index;
	}

	public int castleIndex(){
		return castleIndex;
	}
		
 	public Move castleMove(Piece castle){
		if (castleIndex==-1){
			throw new IllegalArgumentException("castling error!");
		}
		return new Move(castle, 3+2*castleIndex, oldCol, opening);
	}

	public Piece capturedPiece(){
		return capturedPiece;
	}

	public boolean equals(Move aMove){
		return aMove.piece()==this.piece() && aMove.newRow()==this.newRow()
			&& aMove.newCol()==this.newCol();
	}
        
        public String getOpeningMoveString(){
            int move = board.getMoveNumber();
            String moveString = "";
  //        moveString+= piece.name() + " to " + rowArray[newRow] + (newCol+1) + "\t";
            moveString += "openingMove[" + move + "]" 
                    + " = new Move(board.objectAt (" + oldRow + "," + oldCol + ")," +
                    newRow + "," + newCol + ", true);\n";
            if (castleIndex()!=-1){
                moveString += "openingMove [" + move + "].changeCastleIndex(1);\n";
            }
            else if (capturedPiece!=null){
                moveString += "openingMove [" + move + "].addCapturedPiece(board.objectAt("+
                        newRow + "," + newCol + "));\n";
            }    
        return moveString;
        }
	
	public String toString(){
/*		return piece.name() + " moves from " + rowArray[oldRow] 
		+ (oldCol+1)+ " to " +  + " (" +
                        oldRow + ", "+oldCol+ ") --> "+newRow + ", "+ newCol;
 */
        return piece.getAbbreviation()+ rowArray[newRow] + (newCol+1);
	}
}

