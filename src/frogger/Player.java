package frogger;

/*Player object : Dynamic : User driven
 *player object embodies the user controlled object, which is the frog.
 * */

public class Player extends Entity{
	
	protected final int translation = 100; //amount of translation on board on x and y dimension
	protected final int offset = 0;		   //amount of leniency in checkBoundaries, usually always 0
	
	//starting location of the player
	protected int startX; 
	protected int startY; 
	
	public Player(int startX, int startY) {
		this.startY = startY;
		this.startX = startX;
		initPlayer();
	}
	public void initPlayer(){
		setX(startX);
		setY(startY);
		setSprite(GameTools.frogUpImagePath);
		recenterImage();
	}
	public void reInit(){
		initPlayer();
	}
	
	//re-centers the image of the frog
	private void recenterImage(){
        x += (GameTools.columnWidth - getSpriteWidth())/2;
        y += (GameTools.rowHeight - getSpriteHeight())/2;
    }
	
	//re-centers the frog in the 64x64 square grid if not in.
	public void recenterOnLines() {
		if(y > (GameTools.numWaterSquares - 1) * GameTools.rowHeight)
			   moveBackToRow();
		//this is just in case of glitches out of the box
		if(y < -10 || y > GameTools.boardImageLength || x < -10 || x > GameTools.boardWidth)
			reInit();
		
	}
	//if frog isn't centered on grid, it will center it.
	private void moveBackToRow(){
		//centers the image on the square
		if(x % GameTools.columnWidth < 30)
			if(x % GameTools.columnWidth > GameTools.columnWidth/2)
				x += x % GameTools.columnWidth;
			else
				x -= x % GameTools.columnWidth;
		if(y % GameTools.rowHeight < 30)
			if(y % GameTools.rowHeight > GameTools.rowHeight/2)
				y += y % GameTools.rowHeight;
			else
				y -= y % GameTools.rowHeight;
		recenterImage();
	}
	//horizontal movement of the frog
	public void moveFrogHorizontal(boolean right){
		   if (right){
			   setSprite(GameTools.frogRightImagePath);
			   int tempRight = getX() + (GameTools.columnWidth);
			   if(tempRight < GameTools.boardWidth){
				   setX(tempRight);
			   }	
		   }
		   else{
			   setSprite(GameTools.frogLeftImagePath);
			   int tempLeft = getX() - (GameTools.columnWidth);
			   if(tempLeft > 0)
				   setX(tempLeft);
		   }	   
	   }
	//vertical movement of the frog   
	public void moveFrogVertical(boolean up){
		if(up){
			setSprite(GameTools.frogUpImagePath);
			int tempUp = getY() - (GameTools.rowHeight); 
			if(tempUp > -5)
				setY(tempUp);
		   	}
		   	else{
			   setSprite(GameTools.frogDownImagePath);
			   int tempDown = getY() + (GameTools.rowHeight); 
			   if(tempDown < GameTools.boardImageLength)
				   setY(tempDown);
		   }
	   }
}
