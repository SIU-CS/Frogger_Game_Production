package frogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;


public class Board extends JPanel implements Runnable{

    /**
	 * auto generated
	 */
	private static final long serialVersionUID = 1L;
    private Frog frog;
    private Background background;
    private Log log;
	TAdapter key;
    public Board() {

        initBoard();
    }
    Random rand = new Random();
    private void initBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        //adds a key listener that send the presses to GameEngine
        key = new TAdapter();
        addKeyListener(key);
        
        
        background = new Background();
        background.setImage("map.png");
        frog = new Frog(background.getMaxWidth()/2, background.getMaxHeight() -100);
        
        log = new Log();
        log.initCraft(3, 7, 200 , true);
        //this thread is running by itself in an endless loop
        // this is so the log updates on its own
        Thread t = new Thread(log);
        t.start();
        
        setPreferredSize(new Dimension(background.getMaxWidth(),background.getMaxHeight()) );
        //log = new Log(10, 40, 200 , true);
        
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.drawBackground(g);
        log.drawLog(g);
        frog.drawFrog(g);
                
        Toolkit.getDefaultToolkit().sync();
    }
    public Frog getFrog(){
    	return frog;
    }
    public Background getBackgroundGame(){
    	return background;
    }
    public void update(){
    	repaint();
    }

    private class TAdapter extends KeyAdapter {
	    @Override
	    public void keyPressed(KeyEvent e) {
	        GameEngine.keyPressed(e);
	        
	    }
	    public void keyReleased(KeyEvent e) {
	        GameEngine.keyReleased(e);
	    }
  	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
			update();
	}
}
