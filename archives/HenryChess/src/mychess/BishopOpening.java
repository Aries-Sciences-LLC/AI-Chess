package mychess;
public class BishopOpening extends Opening{
	private Chessboard board;
        private boolean mainline = true, variation5 = false;

	public BishopOpening (Chessboard board){       
		super(12);
		this.board = board;
		openingMove = new Move[17];
                openingMove[0] = new Move(board.objectAt (4,1),4,3, true);
                openingMove[1] = new Move(board.objectAt (4,6),4,4, true);
                openingMove[2] = new Move(board.objectAt (5,0),2,3, true);
                openingMove[3] = new Move(board.objectAt (6,7),5,5, true);
                openingMove[4] = new Move(board.objectAt (3,1),3,2, true);
                openingMove[5] = new Move(board.objectAt (2,6),2,5, true);
                openingMove[6] = new Move(board.objectAt (5,1),5,3, true);
                openingMove[7] = new Move(board.objectAt (4,6),5,3, true);
                openingMove [7].addCapturedPiece(board.objectAt(5,1));
                openingMove[8] = new Move(board.objectAt (2,0),5,3, true);
                openingMove [8].addCapturedPiece(board.objectAt(4,6));
                openingMove[9] = new Move(board.objectAt (3,6),3,4, true);
                openingMove[10] = new Move(board.objectAt (4,1),3,4, true);
                openingMove [10].addCapturedPiece(board.objectAt(3,6));
                openingMove[11] = new Move(board.objectAt (6,7),3,4, true);
                openingMove [11].addCapturedPiece(board.objectAt(4,1));
	}
        
        public boolean checkAlterations (int m){
            boolean alteration = false;
            if (mainline && m==5 && !board.isEmpty(5,3) && board.objectAt(5,3).equals(0,0)){
                adjustForMistakeInMain();
                alteration = true;
                System.out.println("adjusting for mistake in main");
            }
            else if (mainline && m==5 && !board.isEmpty(3,3) && board.objectAt(3,3).equals(0,0)){
                adjustForVariation3(true);
                alteration = true;
                System.out.println("adjusting for variation 3");
                }
             else if (mainline && m == 6 && !board.isEmpty(2,4) && board.objectAt(2,4).equals(2,1)){
                adjustForVariation4(true);
                alteration = true;
                System.out.println("adjusting for variation 4");
            }
            else if (mainline && m == 7 && !board.isEmpty(5,2) && board.objectAt(5,2).equals(1,0)){
                adjustForVariation5(true);
                alteration = true;
                System.out.println("adjusting for variation 5");
            }
            else if (variation5 && m == 12 && !board.isEmpty(3,5) && board.objectAt(3,5).equals(2,1)){
                adjustForMistakeOnVar5();
                alteration = true;
                System.out.println("adjusting for Mistake on variation 5");
            }
            return alteration;
        }

        private void adjustForMistakeOnVar5(){
            openingMove[12] = new Move(board.objectAt (4,0),6,0, true);
            openingMove [12].changeCastleIndex(1);
            openingMove[13] = new Move(board.objectAt (1,7),2,5, true);
            openingMove[14] = new Move(board.objectAt (2,0),6,4, true);
            openingMove[15] = new Move(board.objectAt (2,7),4,5, true);
            openingMove[16] = new Move(board.objectAt (1,0),2,2, true);
            size = 17;
            variation5 = false;
        }

        private void adjustForVariation5(boolean response){
            if (!response){
                openingMove[6] = new Move(board.objectAt (6,0),5,2, true);
            }
            openingMove[7] = new Move(board.objectAt (3,6),3,4, true);
            openingMove[8] = new Move(board.objectAt (4,3),3,4, true);
            openingMove [8].addCapturedPiece(board.objectAt(3,6));
            openingMove[9] = new Move(board.objectAt (2,5),3,4, true);
            openingMove [9].addCapturedPiece(board.objectAt(4,3));
            openingMove[10] = new Move(board.objectAt (2,3),1,2, true);
            openingMove[11] = new Move(board.objectAt (5,7),1,3, true);
            openingMove[12] = new Move(board.objectAt (2,1),2,2, true);
            size = 13;
            mainline = false;
            variation5 = true;
        }


        private void adjustForVariation4(boolean response){
            if (!response){
                openingMove[5] = new Move(board.objectAt (5,7),2,4, true);
            }            
            openingMove[6] = new Move(board.objectAt (1,0),2,2, true);
            openingMove[7] = new Move(board.objectAt (1,7),2,5, true);
            openingMove[8] = new Move(board.objectAt (5,1),5,3, true);
            openingMove[9] = new Move(board.objectAt (3,6),3,5, true);
            openingMove[10] = new Move(board.objectAt (6,0),5,2, true);
 //         Can now transpose to variation of King's Gambit Declined!!
            size = 11;
            mainline = false;
        }
        
        public void adjustForVariation3(boolean response){
            openingMove[5] = new Move(board.objectAt (4,4),3,3, true);
            if (!response){
               openingMove[4] = new Move(board.objectAt (3,1),3,3, true);       
               openingMove [5].addCapturedPiece(board.objectAt(3,1));
            }
            else{
               openingMove [5].addCapturedPiece(board.objectAt(3,3));
            }
            openingMove[6] = new Move(board.objectAt (6,0),5,2, true);
            openingMove[7] = new Move(board.objectAt (5,5),4,3, true);
            openingMove [7].addCapturedPiece(board.objectAt(4,3));
            openingMove[8] = new Move(board.objectAt (3,0),3,3, true);
            openingMove [8].addCapturedPiece(board.objectAt(4,4));
            openingMove[9] = new Move(board.objectAt (5,5),5,5, true);
            openingMove[10] = new Move(board.objectAt (2,0),6,4, true);
            openingMove[11] = new Move(board.objectAt (5,7),4,6, true);
            openingMove[12] = new Move(board.objectAt (1,0),2,2, true);
            openingMove[13] = new Move(board.objectAt (1,7),2,5, true);     
            size = 14;
            mainline = false;
        }
        
        public void adjustForMistakeInMain(){
            System.out.println("Adjusting For Mistake in Main");
            openingMove[5] = new Move(board.objectAt (5,5),4,3, true);
            openingMove [5].addCapturedPiece(board.objectAt(4,3));
            openingMove[6] = new Move(board.objectAt (3,1),3,2, true);
            openingMove[7] = new Move(board.objectAt (5,5),3,5, true);
            openingMove[8] = new Move(board.objectAt (2,3),1,2, true);
            openingMove[9] = new Move(board.objectAt (1,7),2,5, true);
            size = 10;
            mainline = false;
        }

 
    public void checkVariations(int m){
        if (m==3){
            if (rGen.nextInt(40)==1){
                 openingMove[3] = new Move(board.objectAt (2,6),2,5, true);
                 size = 4;
            }
            else if (rGen.nextInt(40)==1){
                openingMove[3] = new Move(board.objectAt(5,7),2,4, true);
                size = 4;
             }
         }
         else if (m==4 && rGen.nextInt(10)==1){
             adjustForVariation3(false);
         }
        else if (m==5 && rGen.nextInt(3)==1){
            adjustForVariation4(false);
        }
        else if (m==6 && rGen.nextInt(20)==1){
            adjustForVariation5(false);
        }
    }
}
