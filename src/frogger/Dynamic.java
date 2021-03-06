package frogger;

import java.util.Random;

/*Dynamic Class handles all attributes to dynamic entities that aren't recognized as the player
 *It obtains the spawning coordinates, the direction it moves, the speed in which it moves.
 *It also obtains the move method in which will handle all movement pertaining to subclasses.*/


public class Dynamic extends Entity{
    Random rand = new Random();
   
   //The coordinates in which the hostile entity will spawn at once it is initiated.
   protected int spawnX;
   protected int spawnY;
   protected int offset = 0;
   
   protected boolean moveRight; //whether the object is moving from left of screen or right
   private int speed;           //speed of the car
   protected int numResets = 0; //amount of resets an object has done
  
   //sets objects spawn in X coordinates
   public void setSpawnX(int spawningX){
	   if(getMoveRight()){
		   System.out.println("spawnX: " + (-1 *(spawningX)));
		   this.spawnX = -1 * (spawningX);
	   }
	   else{
		   System.out.println("spawnX: " + spawningX);
		   this.spawnX = GameTools.boardWidth + spawningX;
	   }
	   
	   setX(this.spawnX);  
   }
   
   //sets objects spawn in Y coordinates
   public void setSpawnY(int spawningY){	
	   System.out.println("spawnY: " + spawningY);
	   this.spawnY = spawningY;
	   setY(spawningY);
   }
   
 //gives objects spawn in X coordinates
   public int getSpawnX(){
	   if(spawnX < 0)
		   return -(spawnX);
	   return spawnX;
   }
   
   //gives objects spawn in Y coordinates
   public int getSpawnY(){
	   return spawnY;
   }
	
   public void setOffset(int offset){
	this.offset = offset;	   
   }
   
   //sets objects direction its moving
   public void setMoveRight(boolean moveRight){
	   this.moveRight = moveRight;
   }
   
   //gives boolean of objects direction it is moving
   public boolean getMoveRight(){
	   return moveRight;
   }
   
   //sets the number of resets the object has went through
   public void setNumResets(int numResets){
	   this.numResets = numResets;
   }
   
   //gives the number of resets the object has went through
   public int getNumResets(){
	   return numResets;
   }
   
   //sets objects speed
   public void setSpeed(int speed){
	   this.speed = speed;
   }
   
   //gives objects current speed
   public int getSpeed(){
	   return speed;
   }
   
   //moving of the object
   public int move(){
	   if(moveRight){
		   if(getX() < GameTools.boardWidth + offset)
			   setX(getX() + speed);
   		   else{
   			   x=-(getSpriteWidth());
   			   numResets++;
   		   }
	   }
   	   else{
   		   if(getX() > -(getSpriteWidth() + offset))
   			   setX(getX() - speed);
           else{
        	   x=GameTools.boardWidth;
        	   numResets++;
           }
   	   }
   	   return x;
   }  

}
