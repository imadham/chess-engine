
//position fen r1b1kbnr/pppq1ppp/2n5/3pp3/3P4/2P3Q1/PP1BPPPP/RN2KBNR w KQkq - 3 7 

public class Rating {
	static int im=0;
    public static int evaluate(long WP,long WN,long WB,long WR,long WQ,long WK,long BP,long BN,long BB,long BR,long BQ,long BK,long EP,boolean CWK,boolean CWQ,boolean CBK,boolean CBQ,boolean wtm) {
    	int counterw=0,counterb=0,counter=0;
counterw+=rateposW(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
counterw+=ratematW(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
  //counterw+=ratethreatW(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
  //counterw+=ratesafW(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
counterw+=ratenumW(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
  
counterb+=rateposB(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
counterb+=ratematB(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
  //counterb+=ratethreatB(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
 //counterb+=ratesafB(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
counterb+=ratenumB(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK,EP,CWK,CWQ,CBK,CBQ);
   
   
   if(Start.ratWhite)counter=counterw-counterb;
   else counter=counterb-counterw;
   
  
    
        return counter;
    }

    private static int ratethreatB(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {

    	int counter=0;
		
		
    	if((wK&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)counter+=1000;
    	
		return counter;
	}

	private static int ratethreatW(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {
		
    	int counter=0;
    			
    			
    	if((bK&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)counter+=1000;
    	
		return counter;
	}

	private static int ratenumW(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {
    	int counter=0;
    	
    	counter+=Moves.possibleMovesW(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK,eP,cWK,cWQ,cBK,cBQ).length()/2;
    	
		
    	return counter;
    	
	}
	
	
	private static int ratenumB(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {
    	int counter=0;
    	counter+=Moves.possibleMovesB(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK,eP,cWK,cWQ,cBK,cBQ).length()/2;
    	
		
    	return counter;
    	
	}

 
	public static int ratesafW(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {
		int counter=0;
    	long wp,wn,wb,wr,wq;
    	
    	wp=wP&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	wn=wN&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	wb=wB&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	wr=wR&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	wq=wQ&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	
		for(int i=0;i<64;i++){
			if(((wp>>i)&1)==1) counter-=50;
			if(((wn>>i)&1)==1) counter-=300;
			if(((wb>>i)&1)==1) counter-=300;
			if(((wr>>i)&1)==1) counter-=450;
			if (((wq>>i)&1)==1){ counter-=1000;}
		
		}
		
		//System.out.println("wq="+wq+"   counter="+counter);
    	
    	return counter;
	}
	
	
	public static int ratesafB(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {
		int counter=0;
    	long bp,bn,bb,br,bq;
    	
    	bp=bP&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	bn=bN&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	bb=bB&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	br=bR&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	bq=bQ&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK);
    	
		for(int i=0;i<64;i++){
			if(((bp>>i)&1)==1) counter-=50;
			if(((bn>>i)&1)==1) counter-=300;
			if(((bb>>i)&1)==1) counter-=300;
			if(((br>>i)&1)==1) counter-=450;
			if(((bq>>i)&1)==1) counter-=1000;
			
		}
    	
    	return counter;
    	}
	

	private static int ratematW(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {int counter=0;
		
    	
    	for(int i=0;i<64;i++){
			if(((wP>>>i)&1)==1)counter+=100;
		
			if(((wN>>>i)&1)==1)counter+=300;
			
			if(((wB>>>i)&1)==1)counter+=300;
			
			if(((wR>>>i)&1)==1)counter+=450;
			
			if(((wQ>>>i)&1)==1)counter+=1000;
			
			
		}
    	
		return counter;
	}

	
	
	private static int ratematB(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {int counter=0;
		
    	
    	for(int i=0;i<64;i++){
			if(((bP>>>i)&1)==1)counter+=100;
			
			if(((bN>>>i)&1)==1)counter+=300;
			
			if(((bB>>>i)&1)==1)counter+=300;
			
			if(((bR>>>i)&1)==1)counter+=450;
			
			if(((bQ>>>i)&1)==1)counter+=1000;
	
    	}
		return counter;
	}
	
	
	
	
	
	
	
	
	private static int rateposW(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {int counter=0;
		for(int i=0;i<64;i++){
			if(((wP>>>i)&1)==1)counter+=pawnBoard[i/8][i%8];
			
			if(((wN>>>i)&1)==1)counter+=knightBoard[i/8][i%8];
			
			if(((wB>>>i)&1)==1)counter+=bishopBoard[i/8][i%8];
			
			if(((wR>>>i)&1)==1)counter+=rookBoard[i/8][i%8];
			
			if(((wQ>>>i)&1)==1)counter+=queenBoard[i/8][i%8];
			
			if(((wK>>>i)&1)==1)counter+=kingMidBoard[i/8][i%8];
		}
    	
		return counter;
	}
	
	private static int rateposB(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {int counter=0;
		for(int i=0;i<64;i++){
			
			if(((bP>>>i)&1)==1)counter+=blackPawnBoard[i/8][i%8];
			
			if(((bN>>>i)&1)==1)counter+=blackKnightBoard[i/8][i%8];
			
			if(((bB>>>i)&1)==1)counter+=blackBishopBoard[i/8][i%8];
			
			if(((bR>>>i)&1)==1)counter+=blackRookBoard[i/8][i%8];
			
			if(((bQ>>>i)&1)==1)counter+=blackQueenBoard[i/8][i%8];
			
			if(((bK>>>i)&1)==1)counter+=blackKingMidBoard[i/8][i%8];
			
					
		}
    	
		return counter;
	}
	
	
	
	
	
	
	
	
	

	static int pawnBoard[][]={//http://chessprogramming.wikispaces.com/Simplified+evaluation+function
        { 0,  0,  0,  0,  0,  0,  0,  0},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {10, 10, 20, 30, 30, 20, 10, 10},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 5, 10, 10,-20,-20, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
	
	
	static int knightBoard[][]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
	
	
	static int bishopBoard[][]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
	
	
	
	static int rookBoard[][]={
	 	{ 0,  0,  0,  5,  5,  0,  0,  0},
	    { 5, 10, 10, 10, 10, 10, 10,  5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
 	    {-5,  0,  0,  0,  0,  0,  0, -5},
	    {-5,  0,  0,  0,  0,  0,  0, -5},
	    {-5,  0,  0,  0,  0,  0,  0, -5},
	    {-5,  0,  0,  0,  0,  0,  0, -5},
	    { 0,  0,  0,  0,  0,  0,  0,  0}};
	
	
	static int queenBoard[][]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
	
	
	static int kingMidBoard[][]={
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        { 20, 30, 10,  0,  0, 10, 30, 20}};
	
	
 	static int blackPawnBoard[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 5, 10, 10,-20,-20, 10, 10,  5},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        {10, 10, 20, 30, 30, 20, 10, 10},
        {50, 50, 50, 50, 50, 50, 50, 50},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
 	

    static int blackKnightBoard[][]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
    
    
    static int blackBishopBoard[][]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
    
    
 	
 	static int blackRookBoard[][]={
        { 0,  0,  0,  5,  5,  0,  0,  0},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        { 5, 10, 10, 10, 10, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
    
    
    static int blackQueenBoard[][]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
        
    
    static int blackKingMidBoard[][]={
        { 20, 30, 10,  0,  0, 10, 30, 20},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30}};
    
    
    static int blackKingEndBoard[][]={
        {-50,-40,-30,-20,-20,-30,-40,-50},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-50,-30,-30,-30,-30,-30,-30,-50}};
    
    
    
    
    
    
    
  /*  private static int ratepos(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {int counter=0,counterb=0,counterw=0;
		for(int i=0;i<64;i++){
			if(((wP>>>i)&1)==1)counterw+=pawnBoard[i/8][i%8];
			
			if(((wN>>>i)&1)==1)counterw+=knightBoard[i/8][i%8];
			
			if(((wB>>>i)&1)==1)counterw+=bishopBoard[i/8][i%8];
			
			if(((wR>>>i)&1)==1)counterw+=rookBoard[i/8][i%8];
			
			if(((wQ>>>i)&1)==1)counterw+=queenBoard[i/8][i%8];
			
			if(((wK>>>i)&1)==1)counterw+=kingMidBoard[i/8][i%8];
			
			
			
			if(((bP>>>i)&1)==1)counterb+=blackPawnBoard[i/8][i%8];
			
			if(((bN>>>i)&1)==1)counterb+=blackKnightBoard[i/8][i%8];
			
			if(((bB>>>i)&1)==1)counterb+=blackBishopBoard[i/8][i%8];
			
			if(((bR>>>i)&1)==1)counterb+=blackRookBoard[i/8][i%8];
			
			if(((bQ>>>i)&1)==1)counterb+=blackQueenBoard[i/8][i%8];
			
			if(((bK>>>i)&1)==1)counterb+=blackKingMidBoard[i/8][i%8];
			
					
		}
    	
		if(Start.ratWhite)counter=counterw-counterb;
    	else counter=counterb-counterw;
    	
		return counter;
	}
    
    
    
    
    private static int ratemat(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {int counterw=0,counterb=0,counter=0,kW=0,kB=0;
		
    	
    	for(int i=0;i<64;i++){
			if(((wP>>>i)&1)==1)counterw+=10;
			
			if(((wN>>>i)&1)==1)counterw+=30;
			
			if(((wB>>>i)&1)==1)counterw+=30;
			
			if(((wR>>>i)&1)==1)counterw+=45;
			
			if(((wQ>>>i)&1)==1)counterw+=100;
			
			if(((wK>>>i)&1)==1)kW++;
			
			if(((bP>>>i)&1)==1)counterb+=10;
			
			if(((bN>>>i)&1)==1)counterb+=30;
			
			if(((bB>>>i)&1)==1)counterb+=30;
			
			if(((bR>>>i)&1)==1)counterb+=45;
			
			if(((bQ>>>i)&1)==1)counterb+=100;
			
			if(((bK>>>i)&1)==1)kB++;
			
		}
    	if(Start.ratWhite)counter=counterw-counterb;//-kB*5000+kW*5000;
    	else counter=counterb-counterw;//+kB*5000-kW*5000;
    //	System.out.println("counterw "+counterw+"counterb "+counterb);
    	if(Start.ratWhite&&kW==1&&kB==0){counter+=5000;}
    	if(!Start.ratWhite&&kW==0&&kB==1){counter+=5000;}
		return counter;
	}
    
    
    
    
    private static int ratesaf(long wP, long wN, long wB, long wR, long wQ,
			long wK, long bP, long bN, long bB, long bR, long bQ, long bK,
			long eP, boolean cWK, boolean cWQ, boolean cBK, boolean cBQ) {
		int counterw=0,counterb=0,counter = 0;
    	int tempb=1,tempw=1;
    
    	if (((wP&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterw-=5;
		}

		if ((wN&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0){
			counterw-=15;	
		}

		if (((wB&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterw-=15;
		}

		if (((wR&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterw-=25;
		}

		if (((wQ&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterw-=50;
		}

		//if (((wP&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))==0)){}

		
		if (((bP&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterb-=5;
		}

		if (((bN&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterb-=15;
		}

		if (((bB&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterb-=15;
		}

		if (((bR&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterb-=25;
		}

		if (((bQ&Moves.unsafeForBlack(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))!=0)){
			counterb-=50;
		}

		//if (((wP&Moves.unsafeForWhite(wP,wN,wB,wR,wQ,wK,bP,bN,bB,bR,bQ,bK))==0)){}



		if(Start.ratWhite)counter=counterw-counterb;//-kB*5000+kW*5000;
    	else counter=counterb-counterw;//+kB*5000-kW*5000;
		
    	return counter;
    }
    
    
    
    
    
    */
    

}