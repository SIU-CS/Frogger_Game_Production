package froggerGame;

import javax.swing.ImageIcon;

public class RaceCar extends Dynamic{
	// TODO Create the sprite(.png) for race car and add to the designated path
	protected final String racecar = "";  //Path to image file of race car sprite
	protected final int modifier = 3; //Constant speed of race cars
	
	public RaceCar(int spawningY, boolean moveRight) {
		setSpawn(spawningY);
		setMoveRight(moveRight);
		initPlayer();
	}
	public void initPlayer(){
		ImageIcon ii = new ImageIcon(racecar);
		setSprite(ii.getImage());
	}

}
