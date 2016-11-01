package frogger;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

/*KeyboardController class handles all inputs from the user.
 *inputs will affect the frog, scroll board, menu(in the future)*/

public class KeyboardController{
	
	Player frog;
	ScrollBoard gameScroll;
	
	private static long timeLastKeyPress;
	private static int waitNextKeyPress = 150; //in milliseconds
	
	KeyboardController(ScrollBoard gameScroll, Player frog){
		this.gameScroll = gameScroll;
		this.frog = frog;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(System.currentTimeMillis() - timeLastKeyPress >  waitNextKeyPress){ //gates input between times
		//Pressing left on the keyboard
        if (key == KeyEvent.VK_LEFT) {
            frog.moveFrogHorizontal(false);
            
            System.out.println("LEFT");
        }
        //Pressing right on the keyboard
        else if (key == KeyEvent.VK_RIGHT) {
        	frog.moveFrogHorizontal(true);
        	
        	System.out.println("RIGHT");
        }
        
        //Pressing up on the keyboard
        else if (key == KeyEvent.VK_UP) {
        	frog.moveFrogVertical(true);
        	
        	//percent is to scroll when the frog in the middle of the screen
        	double percent = (double)frog.getY()/(double)GameTools.boardImageLength;
        	if(percent < 0.85)
        		try {
	        		gameScroll.scrollSmooth(GameTools.rowHeight, 10);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        	System.out.println("UP");
        }
      //Pressing down on the keyboard
        else if (key == KeyEvent.VK_DOWN) {
        	frog.moveFrogVertical(false);
        	
        	//percent is to scroll when frog is in the middle of the screen
        	double percent = (double)frog.getY()/(double)GameTools.boardImageLength;
        	if(percent > 0.15)
	        	try {
	        		gameScroll.scrollSmooth(-GameTools.rowHeight, 10);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
        	System.out.println("DOWN");
        }
        timeLastKeyPress = System.currentTimeMillis();
		}
	}
	
	//releasing of the keys
	public void keyReleased(KeyEvent e) {
	}
	
	//TODO game sequences in future will be handled by menu
	public void gameLoseSequence(){
		//what happens when you run into a the water or a car
		 JOptionPane.showMessageDialog(gameScroll,
			        "YOU LOSE!","Sorry, Try Again",
			        JOptionPane.PLAIN_MESSAGE);
		gameResetSequence();
		System.out.println("YOU LOSE");
	}
	public void gameWinSequence(){
		//what happens when you run into a lilypad
		 JOptionPane.showMessageDialog(gameScroll,
			        "YOU WIN!!!!","Good Job!",
			        JOptionPane.PLAIN_MESSAGE);
		gameResetSequence();
		System.out.println("YOU WIN");
	}
	public void gameResetSequence(){
		frog.reInit();
		try {
			gameScroll.movePaneToBottom();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


