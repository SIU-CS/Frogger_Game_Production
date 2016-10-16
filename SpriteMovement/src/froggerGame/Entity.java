/*
Entity is the parent to all objects that will play a part on the game, such as the frog or player,
hostile entities, and possible static environment such as path blockers.
This class will hold the visibility, xCoords at the time, and its yCoords at the time along with the entities Image.
*/

package froggerGame;

import java.awt.Image;
import java.awt.Rectangle;

public class Entity implements Constants{
	protected Image sprite;  //Sprite of entity, its graphic
	protected boolean visible; //indication of its visibility on the map
	protected int x;	//Its x coordinates on the map
	protected int y;	//Its y coordinates on the map
	protected int width;
	protected int height;
	
   
	public Entity() {
      setVisible(true);
	}
	
	public Rectangle getBounds(){
  		return new Rectangle(x,y,width,height);
  	}
	
   public void setVisible(boolean visible){
      this.visible = visible;
   }
   
   public boolean getVisible(){
      return visible;
   }
   
   public boolean checkBoundaries(char axis, int movement, int pardon){
	   switch(axis){
	   		case 'x':
	   			if(movement > BOARD_WIDTH+pardon || movement < 0-pardon)
	   		   		return true;
	   		    else
	   		   		return false;
	   		case 'y':
	   			if(movement > BOARD_HEIGHT || movement < 0)
	   				return true;
	   			else
	   				return false;
	   }
	   return false;
   }
   
	//setting the sprite of the entity
	public void setSprite(Image sprite){
		this.sprite = sprite;
		width=sprite.getWidth(null);
		height=sprite.getHeight(null);
	}
	
	//getting the sprite of the entity
	public Image getSprite(){
		return sprite;
	}

	//setting its x coords
	public void setX(int x){
		this.x = x;
	}
	
	//setting its y coords
	public void setY(int y){
		this.y = y;
	}
	
	//getting its x coords
	public int getX(){
		return x;
	}
	
	//getting its y coords
	public int getY(){
		return y;
	}
	
}