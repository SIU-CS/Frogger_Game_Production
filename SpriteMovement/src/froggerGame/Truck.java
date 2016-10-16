package froggerGame;

import javax.swing.ImageIcon;

public class Truck extends Dynamic{
	// TODO Create the sprite(.png) for car and add to the designated path
	protected final String truck = "";	  //path to image file of truck sprite
	protected final int truckSpeed = 1;   //Constant speed of trucks
	
	public Truck(int spawningY, boolean moveRight) {
		setSpawn(spawningY);
		setMoveRight(moveRight);
		initPlayer();
	}
	public void initPlayer(){
		ImageIcon ii = new ImageIcon(truck);
		setSprite(ii.getImage());
	}
}
