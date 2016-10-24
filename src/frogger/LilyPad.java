package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class LilyPad {
	
	private ImageIcon ii;
	private Image image;
	private int y;
	private int x;
	
	
	public LilyPad(int x, int y) {
    	this.y=y;
    	this.x = x;
        initCraft();
        centerImage();
    }
	 private void initCraft() {
	        ii = new ImageIcon(GameTools.lilyPadImagePath);
	        
	        image = ii.getImage();
	        
	    }
	
	 private void centerImage(){
	    	//centers the image on the square
	        y += (GameTools.rowHeight - ii.getIconHeight())/2;
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
		public void drawLilyPad(Graphics g) {
	        
	    	Graphics2D g2d = (Graphics2D) g;
	        g2d.drawImage(getImage(), getX(), getY(), null);        
	    }
	    public Rectangle getBounds(){
	  		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
	  	}
}
