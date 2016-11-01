/*Entity class extends all objects intended to be on the game board
*Entity will handle all attributes that will pertain to it position,
*graphics, size, visibility, and its bounds and boundaries to the map 
*and other objects around it.*/

package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Entity{
	protected Image sprite;     //Sprite of entity, its graphic
	protected boolean visible;  //indication of its visibility on the map
	protected int x;			//Its x coordinates on the map
	protected int y;	        //Its y coordinates on the map
	protected int width; 		//Its width of image
	protected int height;		//Its height of image
	
   
	public Entity() {
      this.setVisible(true);
	}
	
	//Sets object's visibility
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    
    //Gives object its boolean visibility
    public boolean getVisible(){
        return visible;
    }
    
    /*checks the boundaries of the map and considers if a move would put an
     *object out of the map boundaries, returns true if yes.
     *offset determines the leniency of the objects out of bounds.*/
    public boolean checkBoundaries(char axis, int movement, int offset){
    	switch(axis){
    	case 'x': //on X axis
    		if(movement > GameTools.boardWidth+offset || movement < 0-offset)
    			return true;
	   		else
	   			return false;
	   	case 'y': //on Y axis
	   		if(movement > GameTools.boardWidth || movement < 0)
	   			return true;
	   		else
	   			return false;
    	}
	    return false;
   }
   
   //Handles the objects drawing of its image
   public void drawSprite(Graphics g) {
	   Graphics2D g2d1 = (Graphics2D) g;
	   g2d1.drawImage(getSprite(), getX(), getY(), null); 
   }	

   	//Setting the Image for an object, along with width and height
	public void setSprite(String imageString){
		ImageIcon ii = new ImageIcon(imageString);
		this.sprite = ii.getImage();
		width=sprite.getWidth(null);
		height=sprite.getHeight(null);
	}
	
	//Gives the Image for an object
	public Image getSprite(){
		return sprite;
	}
	
	//Gives Image width
	public int getSpriteWidth(){
		return width;
	}
	
	//Gives Image Height
	public int getSpriteHeight(){
		return height;
	}
	
	//Gives a newly init'd Rectangle to for the objects sprite
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
  	}

	//sets objects x coordinates
	public void setX(int x){
		this.x = x;
	}
	
	//sets objects y coordinates
	public void setY(int y){
		this.y = y;
	}
	
	//Gives objects its x coordinates
	public int getX(){
		return x;
	}
	
	//Gives objects its x coordinates
	public int getY(){
		return y;
	}
	
}