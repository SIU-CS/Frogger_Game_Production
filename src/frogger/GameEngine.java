package frogger;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;

public class GameEngine{
	
	private static Window ex;
	public static ScrollBoard bar;
	private static Board gameBoard;
	private static long timeLastKeyPress;
	// in milliseconds
	private static int waitNextKeyPress = 150;

	public static void main(String[] args) {
                //these are the items so we can access our generated ScrollContainer
                //and out Board container
                
                EventQueue.invokeLater(new Runnable(){
                	@Override
                	public void run(){
                		ex = new Window();
                        ex.setVisible(true);
                        bar = ex.getScrollBar();
                        gameBoard = ex.getBoard();
                	}
                });
            }

	public static void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(System.currentTimeMillis() - timeLastKeyPress >  waitNextKeyPress)
		{
        if (key == KeyEvent.VK_LEFT) {
            gameBoard.getFrog().moveFrogHorizontal(false);
            
            System.out.println("LEFT");
        }

        else if (key == KeyEvent.VK_RIGHT) {
        	gameBoard.getFrog().moveFrogHorizontal(true);
        	
        	System.out.println("RIGHT");
        }

        else if (key == KeyEvent.VK_UP) {
        	gameBoard.getFrog().moveFrogVertical(true);

        	try {
        		bar.scroll(GameTools.rowHeight);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	System.out.println("UP");
        }

        else if (key == KeyEvent.VK_DOWN) {
        	gameBoard.getFrog().moveFrogVertical(false);
        	try {
        		bar.scroll(-GameTools.rowHeight);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	System.out.println("DOWN");
        }
        timeLastKeyPress = System.currentTimeMillis();
		}
	}
	public static void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
        	
        }
        else if (key == KeyEvent.VK_DOWN) {
        	
        }
	}
	
	public static void gameLoseSequence(){
		//what happens when you run into a the water or a car
		gameResetSequence();
		System.out.println("YOU LOSE");
	}
	public static void gameWinSequence(){
		//what happens when you run into a lilypad
		gameResetSequence();
		System.out.println("YOU WIN");
	}
	public static void gameResetSequence(){
		gameBoard.getFrog().resetToStart();
		try {
			bar.movePaneToBottom();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


