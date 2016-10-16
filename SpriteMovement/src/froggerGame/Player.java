package froggerGame;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player extends Entity{
	
	// TODO Create sprite(.png) frog and add in path
	protected final String frog = "src/frog.png"; //path to image file for frog sprite
	
   // TODO determine the starting position for frog and the constant x and y translating
	protected final int startX = 40; //starting location x
	protected final int startY = 530; //starting location y
	protected final int translation = 100; //amount of translation on board on x and y dimension
	protected final int pardon = 0;
	
	public Player() {
		initPlayer();
	}
	public void initPlayer(){
		ImageIcon ii = new ImageIcon(frog);
		setSprite(ii.getImage());
		setX(startX);
		setY(startY);
	}
	public void reInit(){
		initPlayer();
	}

	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT && !checkBoundaries('x', (x-translation), pardon)) 
            x -= translation;

        if (key == KeyEvent.VK_RIGHT && !checkBoundaries('x', (x+translation), pardon))
            x += translation;

        if (key == KeyEvent.VK_UP && !checkBoundaries('y', (y-translation), pardon))
            y -= translation;

        if (key == KeyEvent.VK_DOWN && !checkBoundaries('y', (y+translation), pardon))
            y += translation;
    }
	
	public void keyReleased(KeyEvent e) {
    	
    }
	
}
