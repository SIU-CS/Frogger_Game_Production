package froggerGame;

import javax.swing.ImageIcon;

public class Car extends Dynamic{
	// TODO Create the sprite(.png) for car and add to the designated path
	protected final String car = "";      //Path to image file of car sprite
	protected final int modifier = 2;	  //Constant speed of cars
	
	public Car(int spawningY, boolean moveRight) {
		setSpawn(spawningY);
		setMoveRight(moveRight);
		initPlayer();
	}
	public void initPlayer(){
		ImageIcon ii = new ImageIcon(car);
		setSprite(ii.getImage());
	}

}
