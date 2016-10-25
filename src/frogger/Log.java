package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Rectangle;

public class Log{
    private int x1=0;
    private int x2=GameTools.boardWidth/3;
    private int x3 = x2*2;
    private int y;
    protected Image image;
    private ImageIcon ii;
    private boolean moveRight;
    
    Random rand=new Random();
    private int speed = rand.nextInt(3)+1;
    

    public Log(int y, boolean moveRight) {
    	this.moveRight = moveRight;
    	this.y=y;
        initCraft();
        recenterImage();
    }
    private void recenterImage(){
    	//centers the image on the square
        y += (GameTools.rowHeight - ii.getIconHeight())/2;
    }
    private void initCraft() {
        ii = new ImageIcon(GameTools.logImagePath);
        
        image = ii.getImage();
        
    }
    public void move() {
    	x1 = moveThisLog(x1);
    	x2 = moveThisLog(x2);
    	x3 = moveThisLog(x3);
 
    }
    private int moveThisLog(int x){
    	if(moveRight)
	        if(x<GameTools.boardWidth)
	        	x += speed;
	        else 
	        	x=-(ii.getIconWidth());
    	else{
    		if(x>-ii.getIconWidth())
            	x -= speed;
            else 
            	x=GameTools.boardWidth;
    	}
    	return x;
    }
    public void setX(int x){
    	this.x1=x;
    }
    public int[] getX(){
    	int[] X_VALUES = {x1,x2,x3};
		return X_VALUES;
    }

    public int getY(){
    	return y;
    }
    public Image getImage(){
    	return image;
    }
	public void drawLog(Graphics g) {
    	Graphics2D g2d1 = (Graphics2D) g;
        g2d1.drawImage(getImage(), x1, getY(), null); 
        Graphics2D g2d2 = (Graphics2D) g;
        g2d2.drawImage(getImage(), x2, getY(), null);
        Graphics2D g2d3 = (Graphics2D) g;
        g2d3.drawImage(getImage(), x3, getY(), null);
    }
    public Rectangle getBoundsLog1(){
  		return new Rectangle(x1,y,image.getWidth(null),image.getHeight(null));
  	}
    public Rectangle getBoundsLog2(){
  		return new Rectangle(x2,y,image.getWidth(null),image.getHeight(null));
  	}
    public Rectangle getBoundsLog3(){
  		return new Rectangle(x3,y,image.getWidth(null),image.getHeight(null));
  	}
	public ImageIcon getImageIcon() {
		return ii;
	}
}
