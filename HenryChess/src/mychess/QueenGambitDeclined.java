package mychess;
public class QueenGambitDeclined extends Opening{
	private Chessboard board;
		
	public QueenGambitDeclined(Chessboard board){
		super(36);
		this.board = board;
		openingMove = new Move[36];
		openingMove [0] = new Move(board.objectAt(3,1),3,3,true);
		openingMove [1] = new Move(board.objectAt(3,6),3,4,true);
		openingMove [2] = new Move(board.objectAt(2,1),2,3,true);
		openingMove [3] = new Move(board.objectAt(4,6),4,5,true);
		openingMove [4] = new Move(board.objectAt(1,0),2,2,true);
		openingMove [5] = new Move(board.objectAt(6,7),5,5,true);
		openingMove [6] = new Move(board.objectAt(2,0),6,4,true);
		openingMove [7] = new Move(board.objectAt(1,7),3,6,true);
		openingMove [8] = new Move(board.objectAt(6,0),5,2,true);
		openingMove [9] = new Move(board.objectAt(5,7),4,6,true);
		openingMove [10] = new Move(board.objectAt(4,1),4,2,true);
		openingMove [11] = new Move(board.objectAt(4,7),6,7,true);
		openingMove [11].changeCastleIndex(1);
                openingMove [12] = new Move(board.objectAt(0,0),2,0,true);
		openingMove [13] = new Move(board.objectAt(2,6),2,5,true);
		openingMove [14] = new Move(board.objectAt(5,0),3,2,true);
		openingMove [15] = new Move(board.objectAt(3,6),2,3,true);
		openingMove [15].addCapturedPiece(board.objectAt(2,1));
		openingMove [16] = new Move(board.objectAt(5,0),2,3,true);
		openingMove [16].addCapturedPiece(board.objectAt(3,6));
		openingMove [17] = new Move(board.objectAt(6,7),3,4,true);
		openingMove [18] = new Move(board.objectAt(2,0),4,6,true);
		openingMove [18].addCapturedPiece(board.objectAt(5,7));
		openingMove [19] = new Move(board.objectAt(3,7),4,6,true);
		openingMove [19].addCapturedPiece(board.objectAt(2,0));
		openingMove [20] = new Move(board.objectAt(4,0),6,0,true);
		openingMove [20].changeCastleIndex(1);
		openingMove [21] = new Move(board.objectAt(6,7),2,2,true);
		openingMove [21].addCapturedPiece(board.objectAt(1,0));	
		openingMove [22] = new Move(board.objectAt(0,0),2,2,true);
		openingMove [22].addCapturedPiece(board.objectAt(6,7));
		openingMove [23] = new Move(board.objectAt(4,6),4,4,true);
		openingMove [24] = new Move(board.objectAt(3,1),4,4,true);
		openingMove [24].addCapturedPiece(board.objectAt(4,6));
		openingMove [25] = new Move(board.objectAt(1,7),4,4,true);
		openingMove [25].addCapturedPiece(board.objectAt(3,1));
		openingMove [26] = new Move(board.objectAt(6,0),4,4,true);
		openingMove [26].addCapturedPiece(board.objectAt(1,7));
		openingMove [27] = new Move(board.objectAt(3,7),4,4,true);
		openingMove [27].addCapturedPiece(board.objectAt(6,0));
		openingMove [28] = new Move(board.objectAt(5,1),5,3,true);
		openingMove [29] = new Move(board.objectAt(3,7),4,3,true);
		openingMove [30] = new Move(board.objectAt(5,0),1,2,true);
		openingMove [31] = new Move(board.objectAt(2,7),5,4,true);
		openingMove [32] = new Move(board.objectAt(3,0),7,4,true);
		openingMove [33] = new Move(board.objectAt(6,6),6,5,true);
		openingMove [34] = new Move(board.objectAt(3,0),7,3,true);
		openingMove [35] = new Move(board.objectAt(0,7),3,7,true);
		
	}
	
	public boolean checkMistakes(int m){
		if (m==13 && openingMove[2].piece().row()==2
			&& openingMove[2].piece().col()==4){
			openingMove [13] = new Move(board.objectAt(5,5),4,3,true);
			openingMove [14] = new Move(board.objectAt(6,4),4,6,true);
			openingMove [14].addCapturedPiece(board.objectAt(4,6));
			openingMove [15] = new Move(board.objectAt(3,7),4,6,true);
			openingMove [15].addCapturedPiece(board.objectAt(6,4));
			openingMove [16] = new Move(board.objectAt(5,0),3,2,true);
			openingMove [17] = new Move(board.objectAt(5,5),2,2,true);
			openingMove [17].addCapturedPiece(board.objectAt(2,2));
			openingMove [18] = new Move(board.objectAt(1,1),2,2,true);
			openingMove [18].addCapturedPiece(board.objectAt(5,5));
			openingMove [19] = new Move(board.objectAt(4,5),4,4,true);
			size=20;
			return true;	
		}
		else if (m==14 && board.isEmpty(2,6) && board.objectAt(2,4)!=null){
			openingMove [14] = new Move(board.objectAt(3,3),2,4,true);
			openingMove [14].addCapturedPiece(board.objectAt(2,4));
			openingMove [15] = new Move(board.objectAt(3,4),2,3,true);
			openingMove [15].addCapturedPiece(board.objectAt(2,3));
			openingMove [16] = new Move(board.objectAt(3,3),2,5,true);
			openingMove [17] = new Move(board.objectAt(3,6),1,5,true);
			openingMove [18] = new Move(board.objectAt(5,2),4,4,true);
			size=19;
			return true;
		}
		else if (m==25 && board.isEmpty(3,0) && board.objectAt(2,1)!=null){
			
			openingMove [25] = new Move(board.objectAt(4,4),3,3,true);
			openingMove [25].addCapturedPiece(board.objectAt(3,3)); 
//			openingMove [25] = new Move(board.objectAt(4,4),4,3,true);			
			size = 26;
			return true;
		}
		
		return false;
	}
}
