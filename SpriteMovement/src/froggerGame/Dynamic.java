package froggerGame;
/*
Dynamic will handle all non-user controlled objects that are dynamic in the game.
*/

public class Dynamic extends Entity {
    // TODO add constant for direction
	protected final int translation = 10; //amount of translation on board
	protected final int pardon = 100;
	
   
   //The coordinates in which the hostile entity will spawn at once it is initiated.
   protected int spawningX; 
   protected int spawningY;
 
   protected int modifier = 1; //modification of the speed of translation.
   protected boolean moveRight; //whether the object is moving from left of screen or right

	public void setModifier(int modifier){
		this.modifier = modifier;
	}
	
	public int getSpeed(){
		return translation*modifier;
	}
	
    //sets a continuous spawn for the object created.
	//this spawn should determine what position should be set when the object meets the end of screen.
	//this should initialize once and on first creation of object, it sets y making one less step for the object.
   public void setSpawn(int spawningY){	
      this.spawningY = spawningY;
      setY(spawningY);
   }
   
   //which direction object will move on x-axis of screen
   //as the direction an dynamic object is moving, determines where
   //it needs to spawn, this method handles the spawning position in x-axis making one less step for the object.
   public void setMoveRight(boolean moveRight){
	   this.moveRight = moveRight;
	   if(moveRight){ //from left to right
		   spawningX = (-pardon);
		   setX(spawningX);
	   }
	   else{ //from right to left
		   spawningX = BOARD_WIDTH + pardon;
		   setX(spawningX);
	   }
   }
   
   public int getSpawningX(){
	   return spawningX;
   }
   
   public int getSpawningY(){
	   return spawningY;
   }
   
   public boolean getMoveRight(){
	   return moveRight;
   }
   
   //moves object
   public void move(){
	   int speed = getSpeed();
		   if(moveRight)
			   if(checkBoundaries('x', (x+speed), pardon))
				   setX(spawningX);
			   else
				   setX((getX()+speed));
		   else
			   if(checkBoundaries('x', (x-speed), pardon))
				   setX(spawningX);
			   else
				   setX((getX()-speed));

   }
   
   

}
