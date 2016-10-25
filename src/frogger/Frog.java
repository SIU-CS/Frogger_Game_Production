package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Frog {
	private int startX;
	private int startY;
    private int x;
    private int y;
    private Image image;
    private ImageIcon ii;
    //used in the detection of you hitting the water
    private long lastLogHitTimer;

    public Frog(int strtWidth, int strtHeight) {
    	startX = strtWidth;
    	startY = strtHeight;
        initCraft(strtWidth,strtHeight);
    }
    
    private void initCraft(int x, int y) {
        
        ii = new ImageIcon(GameTools.frogImagePath);
        setImage(ii);
        
        this.x = x;
        this.y = y;
        recenterImage();
    }
    private void recenterImage(){
        x += (GameTools.columnWidth - ii.getIconWidth())/2;
        y += (GameTools.rowHeight - ii.getIconHeight())/2;
    }
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }
    
    private void setImage(ImageIcon ii){
    	image = ii.getImage();
    }
    public void drawFrog(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getImage(), getX(), getY(), null);        
    }
    public Rectangle getBounds(){
		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
    }
   
   public void moveFrogHorizontal(boolean right){
	   if (right){
		   int tempRight = getX() + (GameTools.columnWidth);
		   if(tempRight < GameTools.boardWidth){
			   setX(tempRight);
		   }	
	   }
	   else{
		   int tempLeft = getX() - (GameTools.columnWidth);
		   if(tempLeft > 0)
			   setX(tempLeft);
	   }
		   
   }
   
   public void moveFrogVertical(boolean up){
	   if(up){
		   int tempUp = getY() - (GameTools.rowHeight); 
		   if(tempUp > -5)
			   setY(tempUp);
	   }
	   else{
		   int tempDown = getY() + (GameTools.rowHeight); 
		   if(tempDown < GameTools.boardImageLength)
			   setY(tempDown);
	   }
   }
   public void resetToStart(){
	   setY(startY);
	   setX(startX);
	   recenterImage();
   }
   //what happens when this frog jumps on a log
	public boolean jumpOnLog(Rectangle log) {
		int logX = log.x;
		int logY = log.y;
		int width = (int) log.getWidth();
		y = logY;
		if(x < logX + width/2)
			x = logX;
		else
			x =logX + width - this.ii.getIconWidth();
		//this is for timer since last log hit, to check if you need to reset
		lastLogHitTimer = System.currentTimeMillis();
		//retruns false if you run off the the screen
		if(x + this.ii.getIconWidth() < 0 || x > GameTools.boardWidth){
			return false;
		}
		return true;
	}
	
	public void checkWaterHit() {
		 if(y < GameTools.numWaterSquares * GameTools.rowHeight && y > GameTools.rowHeight && System.currentTimeMillis() - lastLogHitTimer > 100)
			   GameEngine.gameLoseSequence();
		
	}

	public void recenterOnLines() {
		if(y > (GameTools.numWaterSquares - 1) * GameTools.rowHeight)
			   moveBackToRow();
		//this is just in case of glitches out of the box
		if(y < -10 || y > GameTools.boardImageLength || x < -10 || x > GameTools.boardWidth)
			GameEngine.gameLoseSequence();
		
	}
}

