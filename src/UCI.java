import java.util.Scanner;
public class UCI {
	static String immortalGameW="e2e4f2f4f1c4e1f1c4b5g1f3d2d3f3h4h4f5h1g1g2g4h2h4h4h5d1f3c1f4b1c3c3d5f4d6f1e2e4e5f5g7f3f6d6e7";
	static String immortalGameB="e7e5e5f4d8h4b7b5g8f6h4h6f6h5h6g5c7c6c6b5h5f6g5g6g6g5f6g8g5f6f8c5f6b2b2a1c5g1b8a6e8d8g8f6";
	static int imb=0,imw=0;
	static int x=0,y=0;;
    static String ENGINENAME="Start v1";
    public static void uciCommunication() {
        Scanner input = new Scanner(System.in);
        while (true)
        {
            String inputString=input.nextLine();
            if ("uci".equals(inputString))
            {
                inputUCI();
            }
            else if (inputString.startsWith("setoption"))
            {
                inputSetOption(inputString);
            }
            else if ("isready".equals(inputString))
            {
                inputIsReady();
            }
            else if ("ucinewgame".equals(inputString))
            {
                inputUCINewGame();
            }
            else if (inputString.startsWith("position"))
            {
                inputPosition(inputString);
            }
            else if (inputString.startsWith("go"))
            {
                inputGo();
            }
            else if (inputString.equals("quit"))
            {
                inputQuit();
            }
            else if ("print".equals(inputString))
            {
                inputPrint();
            }
        }
    }
    public static void inputUCI() {
        System.out.println("id name "+"Chess Fifth");
        System.out.println("id author Imad&Safwan&Toba");
        //options go here
        System.out.println("uciok");
    }
    public static void inputSetOption(String inputString) {
        //set options
    }
    public static void inputIsReady() {
         System.out.println("readyok");
    }
    public static void inputUCINewGame() {
        //add code here
    }
    public static void inputPosition(String input) {
        input=input.substring(9).concat(" ");
        if (input.contains("startpos ")) {
            input=input.substring(9);
            BoardGeneration.importFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        }
        else if (input.contains("fen")) {
            input=input.substring(4);
            BoardGeneration.importFEN(input);
        }
        if (input.contains("moves")) {
            input=input.substring(input.indexOf("moves")+6);
            while (input.length()>0)
            {
                String moves;
                if (Start.WhiteToMove) {
                    moves=Moves.possibleMovesW(Start.WP,Start.WN,Start.WB,Start.WR,Start.WQ,Start.WK,Start.BP,Start.BN,Start.BB,Start.BR,Start.BQ,Start.BK,Start.EP,Start.CWK,Start.CWQ,Start.CBK,Start.CBQ);
                
                } else {
                    moves=Moves.possibleMovesB(Start.WP,Start.WN,Start.WB,Start.WR,Start.WQ,Start.WK,Start.BP,Start.BN,Start.BB,Start.BR,Start.BQ,Start.BK,Start.EP,Start.CWK,Start.CWQ,Start.CBK,Start.CBQ);
                   // moves=black(moves);
                 //   System.out.print(moves);
                }
                algebraToMove(input,moves);
                input=input.substring(input.indexOf(' ')+1);
            }
        }
    }
    private static String white(String moves) {int rating=Integer.MIN_VALUE,temp=0;String best="";//=moves.substring(0,0+4);
    String moveTemp="",poss="";
    Start.ratWhite=true;
    int tempsafe=Rating.ratesafW(Start.WP,Start.WN,Start.WB,Start.WR,Start.WQ,Start.WK,Start.BP,Start.BN,Start.BB,Start.BR,Start.BQ,Start.BK,Start.EP,Start.CWK,Start.CWQ,Start.CBK,Start.CBQ);
    	
    int tempsafeinmove=0;
    long WPt=0L,WNt=0L,WBt=0L,WRt=0L,WQt=0L,WKt=0L,BPt=0L,BNt=0L,BBt=0L,BRt=0L,BQt=0L,BKt=0L,EPt=0L;
    
    	for (int i=0;i<moves.length();i+=4) {
         //   int score;
    		
            //legal, non-castle move
            WPt=Moves.makeMove(Start.WP, moves.substring(i,i+4), 'P');
            WNt=Moves.makeMove(Start.WN, moves.substring(i,i+4), 'N');
            WBt=Moves.makeMove(Start.WB, moves.substring(i,i+4), 'B');
            WRt=Moves.makeMove(Start.WR, moves.substring(i,i+4), 'R');
            WQt=Moves.makeMove(Start.WQ, moves.substring(i,i+4), 'Q');
            WKt=Moves.makeMove(Start.WK, moves.substring(i,i+4), 'K');
            BPt=Moves.makeMove(Start.BP, moves.substring(i,i+4), 'p');
            BNt=Moves.makeMove(Start.BN, moves.substring(i,i+4), 'n');
            BBt=Moves.makeMove(Start.BB, moves.substring(i,i+4), 'b');
            BRt=Moves.makeMove(Start.BR, moves.substring(i,i+4), 'r');
            BQt=Moves.makeMove(Start.BQ, moves.substring(i,i+4), 'q');
            BKt=Moves.makeMove(Start.BK, moves.substring(i,i+4), 'k');
            EPt=Moves.makeMoveEP(Start.WP|Start.BP,moves.substring(i,i+4));
            WRt=Moves.makeMoveCastle(WRt, Start.WK|Start.BK, moves.substring(i,i+4), 'R');
            BRt=Moves.makeMoveCastle(BRt, Start.WK|Start.BK, moves.substring(i,i+4), 'r');
            boolean CWKt=Start.CWK,CWQt=Start.CWQ,CBKt=Start.CBK,CBQt=Start.CBQ;
            CWKt=Start.CWK;
            CWQt=Start.CWQ;
            CBKt=Start.CBK;
            CBQt=Start.CBQ;
            if (Character.isDigit(moves.charAt(i+3))) {//'regular' move
                int start=(Character.getNumericValue(moves.charAt(i))*8)+(Character.getNumericValue(moves.charAt(i+1)));
                if (((1L<<start)&Start.WK)!=0) {CWKt=false; CWQt=false;}
                else if (((1L<<start)&Start.BK)!=0) {CBKt=false; CBQt=false;}
                else if (((1L<<start)&Start.WR&(1L<<63))!=0) {CWKt=false;}
                else if (((1L<<start)&Start.WR&(1L<<56))!=0) {CWQt=false;}
                else if (((1L<<start)&Start.BR&(1L<<7))!=0) {CBKt=false;}
                else if (((1L<<start)&Start.BR&1L)!=0) {CBQt=false;}
            }
            
            if (((WKt&Moves.unsafeForWhite(WPt,WNt,WBt,WRt,WQt,WKt,BPt,BNt,BBt,BRt,BQt,BKt))==0)){
            
            	poss+=moves.substring(i,i+4);
            	
      int tempt=PrincipalVariation.pvSearch(-10000,10000,WPt,WNt,WBt,WRt,WQt,WKt,BPt,BNt,BBt,BRt,BQt,BKt,EPt,CWKt,CWQt,CBKt,CBQt,false,0);
          
          //   temp=PrincipalVariation.black(WPt, WNt, WBt, WRt, WQt, WKt, BPt, BNt, BBt, BRt, BQt, BKt, EPt, CWKt, CWQt, CBKt, CBQt);
            
         //   	temp=Rating.evaluate(WPt,WNt,WBt,WRt,WQt,WKt,BPt,BNt,BBt,BRt,BQt,BKt,EPt,CWKt,CWQt,CBKt,CBQt,false);
            	
            	tempsafeinmove=tempsafe-Rating.ratesafW(WPt,WNt,WBt,WRt,WQt,WKt,BPt,BNt,BBt,BRt,BQt,BKt,EPt,CWKt,CWQt,CBKt,CBQt);;
            	
            	
          temp=PrincipalVariation.black(WPt, WNt, WBt, WRt, WQt, WKt, BPt, BNt, BBt, BRt, BQt, BKt, EPt, CWKt, CWQt, CBKt, CBQt);
            
          temp-=tempsafeinmove;
          
          tempt-=tempsafeinmove;
          
          System.out.println(temp+" "+moves.substring(i,i+4)+"tempPV"+tempt);
          
            if(temp>rating){moveTemp=best;best=moves.substring(i,i+4);rating=temp;}
            
            for(int j=0;j<Start.moveTempW.length();j+=4){
           	 if(Start.moveTempW.substring(j,j+4).equals(best)){
           		 if(poss.replaceAll(Start.moveTempW.substring(j,j+4), "").length()<poss.length()) best=moveTemp;
           		 }
           	// else Start.movetempW=0;
           	// if(Start.movetempW==1){best=moveTemp;Start.movetempW=0;}
           	 }
           
    	}}
    	 System.out.println(" The move is"+best);
    	
    	 System.out.println("moveTempW="+Start.moveTempW+"---movetempW="+Start.movetempW+"---best="+best+"---movetemp="+moveTemp);
    	return best;
	}
    
    
    private static String black(String moves) {int rating=Integer.MIN_VALUE,temp=0;String best="";//moves.substring(0,0+4);
    String moveTemp="",poss="",last="";
    Start.ratWhite=false;
	long WPt=0L,WNt=0L,WBt=0L,WRt=0L,WQt=0L,WKt=0L,BPt=0L,BNt=0L,BBt=0L,BRt=0L,BQt=0L,BKt=0L,EPt=0L;
	int tempsafe=Rating.ratesafB(Start.WP,Start.WN,Start.WB,Start.WR,Start.WQ,Start.WK,Start.BP,Start.BN,Start.BB,Start.BR,Start.BQ,Start.BK,Start.EP,Start.CWK,Start.CWQ,Start.CBK,Start.CBQ);
	
	int tempsafeinmove=0;
	
	for (int i=0;i<moves.length();i+=4) {
     //   int score;
        
        //legal, non-castle move
        WPt=Moves.makeMove(Start.WP, moves.substring(i,i+4), 'P');
        WNt=Moves.makeMove(Start.WN, moves.substring(i,i+4), 'N');
        WBt=Moves.makeMove(Start.WB, moves.substring(i,i+4), 'B');
        WRt=Moves.makeMove(Start.WR, moves.substring(i,i+4), 'R');
        WQt=Moves.makeMove(Start.WQ, moves.substring(i,i+4), 'Q');
        WKt=Moves.makeMove(Start.WK, moves.substring(i,i+4), 'K');
        BPt=Moves.makeMove(Start.BP, moves.substring(i,i+4), 'p');
        BNt=Moves.makeMove(Start.BN, moves.substring(i,i+4), 'n');
        BBt=Moves.makeMove(Start.BB, moves.substring(i,i+4), 'b');
        BRt=Moves.makeMove(Start.BR, moves.substring(i,i+4), 'r');
        BQt=Moves.makeMove(Start.BQ, moves.substring(i,i+4), 'q');
        BKt=Moves.makeMove(Start.BK, moves.substring(i,i+4), 'k');
        EPt=Moves.makeMoveEP(Start.WP|Start.BP,moves.substring(i,i+4));
        WRt=Moves.makeMoveCastle(WRt, Start.WK|Start.BK, moves.substring(i,i+4), 'R');
        BRt=Moves.makeMoveCastle(BRt, Start.WK|Start.BK, moves.substring(i,i+4), 'r');
        boolean CWKt=Start.CWK,CWQt=Start.CWQ,CBKt=Start.CBK,CBQt=Start.CBQ;
        CWKt=Start.CWK;
        CWQt=Start.CWQ;
        CBKt=Start.CBK;
        CBQt=Start.CBQ;
        if (Character.isDigit(moves.charAt(i+3))) {//'regular' move
            int start=(Character.getNumericValue(moves.charAt(i))*8)+(Character.getNumericValue(moves.charAt(i+1)));
            if (((1L<<start)&Start.WK)!=0) {CWKt=false; CWQt=false;}
            else if (((1L<<start)&Start.BK)!=0) {CBKt=false; CBQt=false;}
            else if (((1L<<start)&Start.WR&(1L<<63))!=0) {CWKt=false;}
            else if (((1L<<start)&Start.WR&(1L<<56))!=0) {CWQt=false;}
            else if (((1L<<start)&Start.BR&(1L<<7))!=0) {CBKt=false;}
            else if (((1L<<start)&Start.BR&1L)!=0) {CBQt=false;}
        }
        
        if((BKt&Moves.unsafeForBlack(WPt,WNt,WBt,WRt,WQt,WKt,BPt,BNt,BBt,BRt,BQt,BKt))==0){
        	
        	poss+=moves.substring(i,i+4);
        
       int tempt=PrincipalVariation.pvSearch(-10000,10000,WPt,WNt,WBt,WRt,WQt,WKt,BPt,BNt,BBt,BRt,BQt,BKt,EPt,CWKt,CWQt,CBKt,CBQt,true,0);
        
      
       temp=PrincipalVariation.white(WPt, WNt, WBt, WRt, WQt, WKt, BPt, BNt, BBt, BRt, BQt, BKt, EPt, CWKt, CWQt, CBKt, CBQt);
       
       
      tempsafeinmove=tempsafe-Rating.ratesafB(WPt,WNt,WBt,WRt,WQt,WKt,BPt,BNt,BBt,BRt,BQt,BKt,EPt,CWKt,CWQt,CBKt,CBQt);;
        	//temp=Rating.evaluate(WPt,WNt,WBt,WRt,WQt,WKt,BPt,BNt,BBt,BRt,BQt,BKt,EPt,CWKt,CWQt,CBKt,CBQt,false);
        	
       temp-=tempsafeinmove;
       
       tempt-=tempsafeinmove;
       
       System.out.println(temp+" "+moves.substring(i,i+4)+"tempPV"+tempt);
        
        if(temp>rating){moveTemp=best;best=moves.substring(i,i+4);rating=temp;}
        
        for(int j=0;j<Start.moveTempB.length();j+=4){
          	 if(Start.moveTempB.substring(j,j+4).equals(best)){
          		 
          		 if(poss.replaceAll(Start.moveTempB.substring(j,j+4), "").length()<poss.length()) best=moveTemp;}
        
	}
	}
	
	//if(Start.moveTempB.equals(best)){Start.movetempB++;}
	// else Start.movetempB=0;
	 //if(Start.movetempB==1){best=moveTemp;Start.movetempB=0;}
	}
	
	 System.out.println("moveTempB="+Start.moveTempB+"---movetempB="+Start.movetempB+"---best="+best+"---movetemp="+moveTemp);
	
	System.out.println(" The move is"+best);
	
	
	
	return best;
}
	public static void inputGo() {
        String move;
        if (Start.WhiteToMove) {
            move=Moves.possibleMovesW(Start.WP,Start.WN,Start.WB,Start.WR,Start.WQ,Start.WK,Start.BP,Start.BN,Start.BB,Start.BR,Start.BQ,Start.BK,Start.EP,Start.CWK,Start.CWQ,Start.CBK,Start.CBQ);
           // System.out.println(move);
            //move=white(move);
           if(x<2){Start.moveTempW+=move;x++;}
           else {Start.moveTempW="";x=0;}
        } else {
            move=Moves.possibleMovesB(Start.WP,Start.WN,Start.WB,Start.WR,Start.WQ,Start.WK,Start.BP,Start.BN,Start.BB,Start.BR,Start.BQ,Start.BK,Start.EP,Start.CWK,Start.CWQ,Start.CBK,Start.CBQ);
           // System.out.print(move);
            
           // move=black(move);
            if(y<2){Start.moveTempB+=move;y++;}
            else {Start.moveTempB="";y=0;}
            
        }
       // System.out.println("rat"+Start.rating+"  "+Start.ratWhite);
       // Start.rating=0;
      //  int index=(int)(Math.floor(Math.random()*(move.length()/4))*4);
     // System.out.println("bestmove "+moveToAlgebra(move));//.substring(index,index+4)));
        
        if(Start.WhiteToMove){System.out.println("bestmove "+immortalGameW.substring(imw,imw+4));imw+=4;}
        else {System.out.println("bestmove "+immortalGameB.substring(imb,imb+4));imb+=4;}
        
    }
    public static String moveToAlgebra(String move) {
        String append="";
        int start=0,end=0;
        if (Character.isDigit(move.charAt(3))) {//'regular' move
            start=(Character.getNumericValue(move.charAt(0))*8)+(Character.getNumericValue(move.charAt(1)));
            end=(Character.getNumericValue(move.charAt(2))*8)+(Character.getNumericValue(move.charAt(3)));
        } else if (move.charAt(3)=='P') {//pawn promotion
            if (Character.isUpperCase(move.charAt(2))) {
                start=Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(0)-'0']&Moves.RankMasks8[1]);
                end=Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(1)-'0']&Moves.RankMasks8[0]);
            } else {
                start=Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(0)-'0']&Moves.RankMasks8[6]);
                end=Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(1)-'0']&Moves.RankMasks8[7]);
            }
            append=""+Character.toLowerCase(move.charAt(2));
        } else if (move.charAt(3)=='E') {//en passant
            if (move.charAt(2)=='W') {
                start=Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(0)-'0']&Moves.RankMasks8[3]);
                end=Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(1)-'0']&Moves.RankMasks8[2]);
            } else {
                start=Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(0)-'0']&Moves.RankMasks8[4]);
                end=Long.numberOfTrailingZeros(Moves.FileMasks8[move.charAt(1)-'0']&Moves.RankMasks8[5]);
            }
        }
        String returnMove="";
        returnMove+=(char)('a'+(start%8));
        returnMove+=(char)('8'-(start/8));
        returnMove+=(char)('a'+(end%8));
        returnMove+=(char)('8'-(end/8));
        returnMove+=append;
        return returnMove;
    }
    public static void algebraToMove(String input,String moves) {
        int start=0,end=0;
        int from=(input.charAt(0)-'a')+(8*('8'-input.charAt(1)));
        int to=(input.charAt(2)-'a')+(8*('8'-input.charAt(3)));
        for (int i=0;i<moves.length();i+=4) {
            if (Character.isDigit(moves.charAt(i+3))) {//'regular' move
                start=(Character.getNumericValue(moves.charAt(i+0))*8)+(Character.getNumericValue(moves.charAt(i+1)));
                end=(Character.getNumericValue(moves.charAt(i+2))*8)+(Character.getNumericValue(moves.charAt(i+3)));
            } else if (moves.charAt(i+3)=='P') {//pawn promotion
                if (Character.isUpperCase(moves.charAt(i+2))) {
                    start=Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i+0)-'0']&Moves.RankMasks8[1]);
                    end=Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i+1)-'0']&Moves.RankMasks8[0]);
                } else {
                    start=Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i+0)-'0']&Moves.RankMasks8[6]);
                    end=Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i+1)-'0']&Moves.RankMasks8[7]);
                }
            } else if (moves.charAt(i+3)=='E') {//en passant
                if (moves.charAt(i+2)=='W') {
                    start=Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i+0)-'0']&Moves.RankMasks8[3]);
                    end=Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i+1)-'0']&Moves.RankMasks8[2]);
                } else {
                    start=Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i+0)-'0']&Moves.RankMasks8[4]);
                    end=Long.numberOfTrailingZeros(Moves.FileMasks8[moves.charAt(i+1)-'0']&Moves.RankMasks8[5]);
                }
            }
            if ((start==from) && (end==to)) {
                if ((input.charAt(4)==' ') || (Character.toUpperCase(input.charAt(4))==Character.toUpperCase(moves.charAt(i+2)))) {
                    if (Character.isDigit(moves.charAt(i+3))) {//'regular' move
                        start=(Character.getNumericValue(moves.charAt(i))*8)+(Character.getNumericValue(moves.charAt(i+1)));
                        if (((1L<<start)&Start.WK)!=0) {Start.CWK=false; Start.CWQ=false;}
                        else if (((1L<<start)&Start.BK)!=0) {Start.CBK=false; Start.CBQ=false;}
                        else if (((1L<<start)&Start.WR&(1L<<63))!=0) {Start.CWK=false;}
                        else if (((1L<<start)&Start.WR&(1L<<56))!=0) {Start.CWQ=false;}
                        else if (((1L<<start)&Start.BR&(1L<<7))!=0) {Start.CBK=false;}
                        else if (((1L<<start)&Start.BR&1L)!=0) {Start.CBQ=false;}
                    }
                    Start.EP=Moves.makeMoveEP(Start.WP|Start.BP,moves.substring(i,i+4));
                    Start.WR=Moves.makeMoveCastle(Start.WR, Start.WK|Start.BK, moves.substring(i,i+4), 'R');
                    Start.BR=Moves.makeMoveCastle(Start.BR, Start.WK|Start.BK, moves.substring(i,i+4), 'r');
                    Start.WP=Moves.makeMove(Start.WP, moves.substring(i,i+4), 'P');
                    Start.WN=Moves.makeMove(Start.WN, moves.substring(i,i+4), 'N');
                    Start.WB=Moves.makeMove(Start.WB, moves.substring(i,i+4), 'B');
                    Start.WR=Moves.makeMove(Start.WR, moves.substring(i,i+4), 'R');
                    Start.WQ=Moves.makeMove(Start.WQ, moves.substring(i,i+4), 'Q');
                    Start.WK=Moves.makeMove(Start.WK, moves.substring(i,i+4), 'K');
                    Start.BP=Moves.makeMove(Start.BP, moves.substring(i,i+4), 'p');
                    Start.BN=Moves.makeMove(Start.BN, moves.substring(i,i+4), 'n');
                    Start.BB=Moves.makeMove(Start.BB, moves.substring(i,i+4), 'b');
                    Start.BR=Moves.makeMove(Start.BR, moves.substring(i,i+4), 'r');
                    Start.BQ=Moves.makeMove(Start.BQ, moves.substring(i,i+4), 'q');
                    Start.BK=Moves.makeMove(Start.BK, moves.substring(i,i+4), 'k');
                    Start.WhiteToMove=!Start.WhiteToMove;
                    break;
                }
            }
        }
    }
    public static void inputQuit() {
        System.exit(0);
    }
    public static void inputPrint() {
        BoardGeneration.drawArray(Start.WP,Start.WN,Start.WB,Start.WR,Start.WQ,Start.WK,Start.BP,Start.BN,Start.BB,Start.BR,Start.BQ,Start.BK);
       // System.out.print("Zobrist Hash: ");
       // System.out.println(Zobrist.getZobristHash(Start.WP,Start.WN,Start.WB,Start.WR,Start.WQ,Start.WK,Start.BP,Start.BN,Start.BB,Start.BR,Start.BQ,Start.BK,Start.EP,Start.CWK,Start.CWQ,Start.CBK,Start.CBQ,Start.WhiteToMove));
    }
}