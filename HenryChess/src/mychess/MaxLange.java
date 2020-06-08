package mychess;
public class MaxLange extends Opening{
	
	public MaxLange(Chessboard board){
		super(29);
		openingMove = new Move[29];
		openingMove [0] = new Move(board.objectAt(4,1),4,3,true);
		openingMove [1] = new Move(board.objectAt(4,6),4,4,true);
		openingMove [2] = new Move(board.objectAt(6,0),5,2,true);
		openingMove [3] = new Move(board.objectAt(1,7),2,5,true);
		openingMove [4] = new Move(board.objectAt(5,0),2,3,true);
		openingMove [5] = new Move(board.objectAt(5,7),2,4,true);
		openingMove [6] = new Move(board.objectAt(4,0),6,0,true);
		openingMove [6].changeCastleIndex(1);
		openingMove [7] = new Move(board.objectAt(6,7),5,5,true);
		openingMove [8] = new Move(board.objectAt(3,1),3,3,true);
		openingMove [9] = new Move(board.objectAt(4,6),3,3,true);
		openingMove [9].addCapturedPiece(board.objectAt(3,1));
		openingMove [10] = new Move(board.objectAt(4,1),4,4,true);
		openingMove [11] = new Move(board.objectAt(3,6),3,4,true);
		openingMove [12] = new Move(board.objectAt(4,1),5,5,true);
		openingMove [12].addCapturedPiece(board.objectAt(6,7));	
		openingMove [13] = new Move(board.objectAt(3,6),2,3,true);
		openingMove [13].addCapturedPiece(board.objectAt(5,0));
		openingMove [14] = new Move(board.objectAt(7,0),4,0,true);
		openingMove [15] = new Move(board.objectAt(2,7),4,5,true);
		openingMove [16] = new Move(board.objectAt(6,0),6,4,true);
		openingMove [17] = new Move(board.objectAt(3,7),3,4,true);
		openingMove [18] = new Move(board.objectAt(1,0),2,2,true);
		openingMove [19] = new Move(board.objectAt(3,7),5,4,true);
		openingMove [20] = new Move(board.objectAt(1,0),4,3,true);
		openingMove [21] = new Move(board.objectAt(4,7),2,7,true);
		openingMove [21].changeCastleIndex(0);
		openingMove [22] = new Move(board.objectAt(6,0),4,5,true);
		openingMove [22].addCapturedPiece(board.objectAt(2,7));
		openingMove [23] = new Move(board.objectAt(5,6),4,5,true);
		openingMove [23].addCapturedPiece(board.objectAt(5,0));
		openingMove [24] = new Move(board.objectAt(6,1),6,3,true);
		openingMove [25] = new Move(board.objectAt(3,7),4,4,true);
		openingMove [26] = new Move(board.objectAt(4,1),6,6,true);
		openingMove [26].addCapturedPiece(board.objectAt(6,6));
                openingMove [27] = new Move(board.objectAt(7,7),6,7,true);
		openingMove [28] = new Move(board.objectAt(2,0),7,5,true);
	}	
}
