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
   
   //modification of the speed of translation.
   protected int modifier = 1;
   protected boolean moveRight;
   	
	public Dynamic(){
	
	}
	
    //sets a continuous spawn for the object created.
	//this spawn should determine what position should be set when the object meets the end of screen.
	//this should init once and on first creation of object, it sets x and y making one less step for object.
   public void setSpawn(int spawningY){	
      this.spawningY = spawningY;
      setY(spawningY);
   }
   
   //which direction object will move on x-axis of screen
   public void setMoveRight(boolean moveRight){
	   this.moveRight = moveRight;
	   if(moveRight){
		   spawningX = -100;
		   setX(spawningX);
	   }
	   else{
		   spawningX = BOARD_WIDTH + 100;
		   setX(spawningX);
	   }
   }
   
   //moves object
   public void move(){
	   int speed = translation*modifier;
		   if(moveRight)
			   if(checkBoundaries('x', (x+speed), pardon))
				   x = spawningX;
			   else
				   x += speed;
		   else
			   if(checkBoundaries('x', (x-speed), pardon))
				   x = spawningX;
			   else
				   x -= speed; 

   }
   
   

}
