package frogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

import SpriteMovement.Log;


public class Board extends JPanel implements Runnable, ActionListener{

    /**
	 * auto generated
	 */
	private static final long serialVersionUID = 1L;
	protected final int[] LOG_POSITIONS={845,930,1050,1145};
	protected ArrayList<Log> logs;
    private Frog frog;
    private Timer timer;
    private final int DELAY=10;
    private Background background;

	TAdapter key;
    public Board() {
        initBoard();
        initObjects();
    }
    Random rand = new Random();
    private void initBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        //adds a key listener that send the presses to GameEngine
        key = new TAdapter();
        addKeyListener(key);
        timer = new Timer(DELAY, this);
        timer.start();
        
        background = new Background();
        background.setImage("map.png");
        frog = new Frog(64*3, background.getMaxHeight() -64);
        //this thread is running by itself in an endless loop
        // this is so the log updates on its own
        
        setPreferredSize(new Dimension(background.getMaxWidth(),background.getMaxHeight()) );
        //log = new Log(10, 40, 200 , true);
        
    }
    private void initObjects(){
    	logs = new ArrayList<>();
    	for(int i=0;i<LOG_POSITIONS.length;i++){
    		logs.add(new Log(LOG_POSITIONS[i]));
    	}
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.drawBackground(g);
        for(Log log : logs){
    		log.drawLog(g);
    	}
        frog.drawFrog(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    public Frog getFrog(){
    	return frog;
    }
    public Background getBackgroundGame(){
    	return background;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	for(Log log : logs){
    		log.move();
    	}
    	collision_Detection();
        repaint();
    }
    public void collision_Detection(){
    	Rectangle frog_rec = frog.getBounds();
    	for(Log log : logs){
    		Rectangle log_rec = log.getBounds();
    		if(frog_rec.intersects(log_rec)){
    			frog.setX(64*3);
    			frog.setY(background.getMaxHeight()-64);
    			try {
					GameEngine.bar.scroll(-background.getMaxHeight());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
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

}
