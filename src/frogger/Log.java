package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Rectangle;

public class Log{
    private int x=100;
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
 
    }
    public void setX(int x){
    	this.x=x;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public Image getImage(){
    	return image;
    }
	public void drawLog(Graphics g) {
        
    	Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getImage(), getX(), getY(), null);        
    }
    public Rectangle getBounds(){
  		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
  	}
	public ImageIcon getImageIcon() {
		return ii;
	}
}
