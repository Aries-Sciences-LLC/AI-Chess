package mychess;
public class RuyLopez extends Opening{
		private Chessboard board;
                private boolean morphyMainline = true, morphyVar1 = false,
                        steinitz =  false, steinitzVarB = false;
		
	public RuyLopez (Chessboard board){
		super(18);
		this.board = board;
		openingMove = new Move[27];
                openingMove[0] = new Move(board.objectAt (4,1),4,3, true);
                openingMove[1] = new Move(board.objectAt (4,6),4,4, true);
                openingMove[2] = new Move(board.objectAt (6,0),5,2, true);
                openingMove[3] = new Move(board.objectAt (1,7),2,5, true);
                openingMove[4] = new Move(board.objectAt (5,0),1,4, true);
                openingMove[5] = new Move(board.objectAt (0,6),0,5, true);
                openingMove[6] = new Move(board.objectAt (5,0),0,3, true);
                openingMove[7] = new Move(board.objectAt (3,6),3,5, true);
                openingMove[8] = new Move(board.objectAt (3,1),3,3, true);
                openingMove[9] = new Move(board.objectAt (1,6),1,4, true);
                openingMove[10] = new Move(board.objectAt (5,0),1,2, true);
                openingMove[11] = new Move(board.objectAt (1,7),3,3, true);
                openingMove [11].addCapturedPiece(board.objectAt(3,1));
                openingMove[12] = new Move(board.objectAt (6,0),3,3, true);
                openingMove [12].addCapturedPiece(board.objectAt(1,7));
                openingMove[13] = new Move(board.objectAt (4,6),3,3, true);
                openingMove [13].addCapturedPiece(board.objectAt(6,0));
                openingMove[14] = new Move(board.objectAt (5,0),3,4, true);
                openingMove[15] = new Move(board.objectAt (0,7),1,7, true);
                openingMove[16] = new Move(board.objectAt (5,0),2,5, true);
                openingMove[17] = new Move(board.objectAt (2,7),3,6, true);
	}
        
    public boolean checkAlterations(int m){
        boolean alteration = false;
        if (morphyMainline && m==8 && !board.isEmpty(5,5) && board.objectAt(5,5).equals(1,1)){
            adjustForMorphyVar1(true);
            alteration = true;
        } 
        else if (morphyMainline && m==9 && !board.isEmpty(2,2) && board.objectAt(2,2).equals(0,0)){
            adjustForSteinitzDeferred(true);
            alteration = true;
        }    
        else if (morphyVar1 && m==11 && !board.isEmpty(4,0) && board.objectAt(4,0).equals(3,0)){
            adjustForMorphyVar2(true);                                                                                                                                                                                                                                                                                                                                                                          
            alteration = true;
        }
        else if (morphyMainline && m==11 && !board.isEmpty(6,0) && board.objectAt(6,0).equals(5,0)){
            adjustForSteinitzVarA(true);
            alteration = true;
        }
        else if (steinitz && m==12 && !board.isEmpty(6,5) && board.objectAt(6,5).equals(0,1)){
            adjustForSteinitzVarB(true);
            alteration = true;
        }
        else if (steinitzVarB && m==15 && board.objectAt(4,4).equals(0,0)){
            adjustForSteinitzVarC(true);
            alteration = true;
        }
        else if (steinitz && m==11 && !board.isEmpty(6,0) && board.objectAt(6,0).equals(5,0)){
            adjustForSteinitzVarD(true);
            alteration = true;
        }
        else if (morphyMainline && m==9){
            if (board.objectAt(2,5).equals(2,0)){
                adjustForSteinitzVarI(true);
                alteration = true;
            }    
            else if (!board.isEmpty(3,3) && board.objectAt(3,3).equals(0,0)){
                adjustForSteinitzVarII(true);
                alteration = true;
            }
            else if (!board.isEmpty(2,3) && board.objectAt(2,3).equals(0,0)){
                adjustForSteinitzVarIII(true);
                alteration = true;
            }
            else if (!board.isEmpty(6,0) && board.objectAt(6,0).equals(5,0)){
                adjustForSteinitzVarIV(true);
                alteration = true;
            }
            else if (!board.isEmpty(2,2) && board.objectAt(2,2).equals(1,0)){
                adjustForSteinitzVarV(true);
                alteration = true;
            }
        }
        return alteration;
    }
        
    public void checkVariations(int m){
        if (m==7 && morphyMainline && rGen.nextInt(2)==0){
             adjustForMorphyVar1(false);
        } 
        else if (morphyVar1 && m==10 && rGen.nextInt(2)==0){            
             adjustForMorphyVar2(false);
        } 
        else if (m==10 && morphyMainline && rGen.nextInt(5)==0){
             adjustForSteinitzVarA(false);
        }   
        else if (m==11 && steinitz && rGen.nextInt(2)==0){
             adjustForSteinitzVarB(false);
        }  
        else if (m==14 && steinitzVarB && rGen.nextInt(2)==0){
             adjustForSteinitzVarC(false);
        }  
        else if (m==10 && morphyMainline && rGen.nextInt(5)==0){
             adjustForSteinitzVarD(false);
        }  
        else if (morphyMainline && m==8){
             int temp = rGen.nextInt(7);
             switch (temp){
                 case 0:
                     adjustForSteinitzDeferred(false);
                     break;
                 case 1:		
                    adjustForSteinitzVarI(false); 
                    break;
                 case 2:		
                    adjustForSteinitzVarII(false); 
                    break;
                 case 3:		
                    adjustForSteinitzVarIII(false); 
                    break;                    
                 case 4:		
                    adjustForSteinitzVarIV(false); 
                    break;  
                 case 5:  
                    adjustForSteinitzVarV(false);
                    break;
             }      
         }     
    }
    
    public void adjustForMorphyVar1(boolean response){
        System.out.println("Adjusting For Morphy Variation 1 (Open defence)");
        if (!response){
            openingMove[7] = new Move(board.objectAt (6,7),5,5, true);        
            openingMove [9] = new Move(board.objectAt(6,7),4,3, true);
        }
        else{
            openingMove[9] = new Move(board.objectAt(5,5),4,3, true);
        }
        openingMove[8] = new Move(board.objectAt (4,0),6,0, true);
        openingMove [8].changeCastleIndex(1);
        openingMove [9].addCapturedPiece(board.objectAt(4,3));
        openingMove[10] = new Move(board.objectAt (3,1),3,3, true);
        openingMove[11] = new Move(board.objectAt (1,6),1,4, true);
        openingMove[12] = new Move(board.objectAt (0,3),1,2, true);
        openingMove[13] = new Move(board.objectAt (3,6),3,4, true);
        openingMove[14] = new Move(board.objectAt (3,1),4,4, true);
        openingMove [14].addCapturedPiece(board.objectAt(4,4));
        openingMove[15] = new Move(board.objectAt (2,7),4,5, true);
        openingMove[16] = new Move(board.objectAt (2,1),2,2, true);
        size = 17;
        morphyMainline = false;
        morphyVar1 = true;
    } 
    
        public void adjustForMorphyVar2(boolean response){
        System.out.println("Adjusting For Morphy Variation 2");
        if (!response){
            openingMove[10] = new Move(board.objectAt (5,0),4,0, true);
        }
        openingMove[11] = new Move(board.objectAt (4,3),2,4, true);
        openingMove[12] = new Move(board.objectAt (0,3),2,5, true);
        openingMove [12].addCapturedPiece(board.objectAt(2,5));
        openingMove[13] = new Move(board.objectAt (3,6),2,5, true);
        openingMove [13].addCapturedPiece(board.objectAt(0,3));
        openingMove[14] = new Move(board.objectAt (3,1),3,3, true);
        openingMove[15] = new Move(board.objectAt (4,3),4,5, true);
        openingMove[16] = new Move(board.objectAt (5,2),4,4, true);
        openingMove [16].addCapturedPiece(board.objectAt(4,4));
        openingMove[17] = new Move(board.objectAt (5,7),4,6, true);
        size = 18;
        morphyVar1 = false;
    } 
        
      public void adjustForSteinitzDeferred(boolean response){
        System.out.println("Adjusting For Steinitz Defence Deferred");
        if (!response){
            openingMove[8] = new Move(board.objectAt (2,1),2,2, true);
            openingMove[20] = new Move(board.objectAt (2,1),2,3, true);
        }
        else{
            openingMove[20] = new Move(board.objectAt (2,2),2,3, true);
        }
        openingMove[9] = new Move(board.objectAt (2,7),3,6, true);
        openingMove[10] = new Move(board.objectAt (3,1),3,3, true);
        openingMove[11] = new Move(board.objectAt (6,7),5,5, true);
        openingMove[12] = new Move(board.objectAt (3,0),4,1, true);
        openingMove[13] = new Move(board.objectAt (5,7),4,6, true);
        openingMove[14] = new Move(board.objectAt (4,0),6,0, true);
        openingMove [14].changeCastleIndex(1);
        openingMove[15] = new Move(board.objectAt (4,7),6,7, true);
        openingMove [15].changeCastleIndex(1);
        openingMove[16] = new Move(board.objectAt (3,1),3,4, true);
        openingMove[17] = new Move(board.objectAt (2,5),1,7, true);
        openingMove[18] = new Move(board.objectAt (0,3),2,1, true);
        openingMove[19] = new Move(board.objectAt (0,5),0,4, true);
        openingMove[21] = new Move(board.objectAt (2,5),0,5, true);
        openingMove[22] = new Move(board.objectAt (1,0),2,2, true);
        openingMove[23] = new Move(board.objectAt (2,5),2,4, true);
        openingMove[24] = new Move(board.objectAt (2,0),4,2, true);
        size = 25;
        morphyMainline = false;
        steinitz = true;
    } 
      
         
    public void adjustForSteinitzVarI(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation I");
        if (!response){
            openingMove[8] = new Move(board.objectAt (0,3),2,5, true);
            openingMove [8].addCapturedPiece(board.objectAt(2,5));
            openingMove[9] = new Move(board.objectAt (1,6),2,5, true);
            openingMove [9].addCapturedPiece(board.objectAt(0,3));
        }
        else{
            openingMove[9] = new Move(board.objectAt (1,6),2,5, true);
            openingMove [9].addCapturedPiece(board.objectAt(2,5));
        }
        
        openingMove[10] = new Move(board.objectAt (3,1),3,3, true);
        openingMove[11] = new Move(board.objectAt (5,6),5,5, true);
        openingMove[12] = new Move(board.objectAt (2,0),4,2, true);
        openingMove[13] = new Move(board.objectAt (6,6),6,5, true);
        openingMove[14] = new Move(board.objectAt (3,0),3,1, true);
        openingMove[15] = new Move(board.objectAt (5,7),6,6, true);
        openingMove[16] = new Move(board.objectAt (1,0),2,2, true);
        openingMove[17] = new Move(board.objectAt (2,7),3,6, true);
        openingMove[18] = new Move(board.objectAt (4,0),6,0, true);
        openingMove [18].changeCastleIndex(1);
        openingMove[19] = new Move(board.objectAt (6,7),4,6, true);
        openingMove[20] = new Move(board.objectAt (7,1),7,2, true);
        openingMove[21] = new Move(board.objectAt (4,7),6,7, true);
        openingMove [21].changeCastleIndex(1);
        openingMove[22] = new Move(board.objectAt (0,0),3,0, true);
        openingMove[23] = new Move(board.objectAt (3,7),1,7, true);
        openingMove[24] = new Move(board.objectAt (1,1),1,2, true);
        openingMove[25] = new Move(board.objectAt (3,7),1,6, true);
        openingMove[26] = new Move(board.objectAt (2,0),7,5, true);
        size = 27;
        morphyMainline = false;
    }   
    
        public void adjustForSteinitzVarII(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation II");
        if (!response){
            openingMove[8] = new Move(board.objectAt (3,1),3,3, true);
            openingMove [11].addCapturedPiece(board.objectAt(3,1));
        }
        else{
            openingMove [11].addCapturedPiece(board.objectAt(3,3));
        }
        openingMove[9] = new Move(board.objectAt (1,6),1,4, true);
        openingMove[10] = new Move(board.objectAt (0,3),1,2, true);
        openingMove[11] = new Move(board.objectAt (2,5),3,3, true);
        openingMove [11].addCapturedPiece(board.objectAt(3,3));
        openingMove[12] = new Move(board.objectAt (5,2),3,3, true);
        openingMove [12].addCapturedPiece(board.objectAt(2,5));
        openingMove[13] = new Move(board.objectAt (4,4),3,3, true);
        openingMove [13].addCapturedPiece(board.objectAt(5,2));
        openingMove[14] = new Move(board.objectAt (0,3),3,4, true);
        openingMove[15] = new Move(board.objectAt (0,7),1,7, true);
        openingMove[16] = new Move(board.objectAt (0,3),2,5, true);
        openingMove[17] = new Move(board.objectAt (2,7),3,6, true);
        size = 18;
        morphyMainline = false;
    } 
        
    public void adjustForSteinitzVarIII(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation III");
        if (!response){
             openingMove[8] = new Move(board.objectAt (2,1),2,3, true);
        }
        openingMove[9] = new Move(board.objectAt (6,7),5,5, true);
        openingMove[10] = new Move(board.objectAt (1,0),2,2, true);
        openingMove[11] = new Move(board.objectAt (5,7),4,6, true);
        openingMove[12] = new Move(board.objectAt (3,1),3,3, true);
        openingMove[13] = new Move(board.objectAt (4,4),3,3, true);
        openingMove [13].addCapturedPiece(board.objectAt(3,1));
        openingMove[14] = new Move(board.objectAt (5,2),3,3, true);
        openingMove [14].addCapturedPiece(board.objectAt(4,4));
        openingMove[15] = new Move(board.objectAt (2,7),3,6, true);
        openingMove[16] = new Move(board.objectAt (5,2),4,1, true);
        size = 17;
        morphyMainline = false;
    }
      
    public void adjustForSteinitzVarIV(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation IV");
        if (!response){
            openingMove[8] = new Move(board.objectAt (4,0),6,0, true);
            openingMove [8].changeCastleIndex(1);
            openingMove[14] = new Move(board.objectAt (7,0),4,0, true);
        }
        else {
            openingMove[14] = new Move(board.objectAt (5,0),4,0, true);
        }
        openingMove[9] = new Move(board.objectAt (6,7),5,5, true);
        openingMove[10] = new Move(board.objectAt (0,3),2,5, true);
        openingMove [10].addCapturedPiece(board.objectAt(2,5));
        openingMove[11] = new Move(board.objectAt (1,6),2,5, true);
        openingMove [11].addCapturedPiece(board.objectAt(0,3));
        openingMove[12] = new Move(board.objectAt (3,1),3,3, true);
        openingMove[13] = new Move(board.objectAt (6,7),4,3, true);
        openingMove [13].addCapturedPiece(board.objectAt(4,3));
        
        openingMove[15] = new Move(board.objectAt (5,6),5,4, true);
        openingMove[16] = new Move(board.objectAt (3,1),4,4, true);
        openingMove [16].addCapturedPiece(board.objectAt(4,4));
        openingMove[17] = new Move(board.objectAt (3,5),3,4, true);
        openingMove[18] = new Move(board.objectAt (5,2),3,3, true);
        openingMove[19] = new Move(board.objectAt (3,7),7,3, true);
        size = 20;
        morphyMainline = false;
    }
    
    public void adjustForSteinitzVarV(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation V");
        if (!response){
            openingMove[8] = new Move(board.objectAt (1,0),2,2, true);
        }
        openingMove[9] = new Move(board.objectAt (2,7),3,6, true);
        openingMove[10] = new Move(board.objectAt (3,1),3,3, true);
        openingMove[11] = new Move(board.objectAt (4,4),3,3, true);
        openingMove [11].addCapturedPiece(board.objectAt(3,1));
        openingMove[12] = new Move(board.objectAt (5,2),3,3, true);
        openingMove [12].addCapturedPiece(board.objectAt(4,4));
        openingMove[13] = new Move(board.objectAt (6,7),5,5, true);
        openingMove[14] = new Move(board.objectAt (0,3),2,5, true);
        openingMove [14].addCapturedPiece(board.objectAt(2,5));
        openingMove[15] = new Move(board.objectAt (1,6),2,5, true);
        openingMove [15].addCapturedPiece(board.objectAt(2,5));
        openingMove[16] = new Move(board.objectAt (4,0),6,0, true);
        openingMove [16].changeCastleIndex(1);
        openingMove[17] = new Move(board.objectAt (5,7),4,6, true);
        openingMove[18] = new Move(board.objectAt (5,0),4,0, true);
        openingMove[19] = new Move(board.objectAt (4,7),6,7, true);
        openingMove [19].changeCastleIndex(1);
        openingMove[20] = new Move(board.objectAt (1,1),1,2, true);
        size = 21;
        morphyMainline = false;
    }
    
    public void adjustForSteinitzVarA(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation 0");
        if (!response){
            openingMove[10] = new Move(board.objectAt (4,0),6,0, true);
            openingMove [10].changeCastleIndex(1);
        }
        openingMove[11] = new Move(board.objectAt (6,6),6,5, true);
        openingMove[12] = new Move(board.objectAt (3,1),3,3, true);
        openingMove[13] = new Move(board.objectAt (5,7),6,6, true);
        openingMove[14] = new Move(board.objectAt (2,0),4,2, true);
        openingMove[15] = new Move(board.objectAt (6,7),4,6, true);
        openingMove[16] = new Move(board.objectAt (3,1),3,4, true);
        openingMove[17] = new Move(board.objectAt (2,5),1,7, true);
        openingMove[18] = new Move(board.objectAt (0,3),2,1, true);
        openingMove[19] = new Move(board.objectAt (4,7),6,7, true);
        openingMove [19].changeCastleIndex(1);
        openingMove[20] = new Move(board.objectAt (2,2),2,3, true);
        openingMove[21] = new Move(board.objectAt (6,6),6,4, true);
        size = 22;
        morphyMainline = false;
    }   
    
     public void adjustForSteinitzVarB(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation A");
        if (!response){
            openingMove[11] = new Move(board.objectAt (6,6),6,5, true);
        }
        openingMove[12] = new Move(board.objectAt (4,0),6,0, true);
        openingMove [12].changeCastleIndex(1);
        openingMove[13] = new Move(board.objectAt (5,7),6,6, true);
        openingMove[14] = new Move(board.objectAt (2,0),4,2, true);
        openingMove[15] = new Move(board.objectAt (6,7),4,6, true);
        openingMove[16] = new Move(board.objectAt (2,2),2,3, true);
        openingMove[17] = new Move(board.objectAt (4,4),3,3, true);
        openingMove [17].addCapturedPiece(board.objectAt(3,3));
        openingMove[18] = new Move(board.objectAt (5,2),3,3, true);
        openingMove [18].addCapturedPiece(board.objectAt(4,4));
        openingMove[19] = new Move(board.objectAt (4,7),6,7, true);
        openingMove [19].changeCastleIndex(1);
        openingMove[20] = new Move(board.objectAt (1,0),2,2, true);
        openingMove[21] = new Move(board.objectAt (2,5),3,3, true);
        openingMove [21].addCapturedPiece(board.objectAt(5,2));
        openingMove[22] = new Move(board.objectAt (2,0),3,3, true);
        openingMove [22].addCapturedPiece(board.objectAt(2,5));
        size = 23;
        steinitz = false;
        steinitzVarB = true;
    }  
     
     public void adjustForSteinitzVarC(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation C");
        if (!response){
             openingMove[14] = new Move(board.objectAt (3,3),4,4, true);
             openingMove [14].addCapturedPiece(board.objectAt(4,4));
             openingMove[15] = new Move(board.objectAt (3,5),4,4, true);
             openingMove [15].addCapturedPiece(board.objectAt(3,3));
        }
        else{
            openingMove[15] = new Move(board.objectAt (3,5),4,4, true);
            openingMove [15].addCapturedPiece(board.objectAt(4,4));
        }
        openingMove[16] = new Move(board.objectAt (2,0),6,4, true);
        openingMove[17] = new Move(board.objectAt (6,7),4,6, true);
        openingMove[18] = new Move(board.objectAt (3,0),3,2, true);
        openingMove[19] = new Move(board.objectAt (7,6),7,5, true);
        openingMove[20] = new Move(board.objectAt (2,0),4,2, true);
        openingMove[21] = new Move(board.objectAt (3,6),6,3, true);
        openingMove[22] = new Move(board.objectAt (3,0),4,1, true);
        openingMove[23] = new Move(board.objectAt (4,7),6,7, true);
        openingMove [23].changeCastleIndex(1);
        openingMove[24] = new Move(board.objectAt (2,0),2,4, true);
        size = 25;
        steinitzVarB = false;
    }  
    
    public void adjustForSteinitzVarD(boolean response){
        System.out.println("Adjusting For Steinitz Defence Variation 0");
        if (!response){
     
        }
        else{
           
        }

        size = 22;
        steinitz = false;
    }        
          

 
}
