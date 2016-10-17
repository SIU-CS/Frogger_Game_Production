package frogger;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;

public class GameEngine{
	
	private static Window ex;
	public static ScrollBoard bar;
	private static Board gameBoard;

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

        if (key == KeyEvent.VK_LEFT) {
            gameBoard.getFrog().moveFrogHorizontal(-64);
            
            System.out.println("LEFT");
        }

        else if (key == KeyEvent.VK_RIGHT) {
        	gameBoard.getFrog().moveFrogHorizontal(64);
        	
        	System.out.println("RIGHT");
        }

        else if (key == KeyEvent.VK_UP) {
        	gameBoard.getFrog().moveFrogVertical(64);
        	try {
				bar.scroll(50);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	System.out.println("UP");
        }

        else if (key == KeyEvent.VK_DOWN) {
        	gameBoard.getFrog().moveFrogVertical(-64);
        	try {
        		bar.scroll(-50);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	System.out.println("DOWN");
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
}


