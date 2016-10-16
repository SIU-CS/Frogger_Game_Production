package froggerGame;

import java.awt.Color;
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

public class Board extends JPanel implements ActionListener, Constants {

    /**
	 * auto generated
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	protected ArrayList<Log> logs;
	protected final int[] LOG_POSITIONS={50,200,325,450};
	Player frog;
	Log log;
	Log log2;
	Log log3;

    public Board() {
        initBoard();
        initObjects();
    }
    
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        frog = new Player();
        timer = new Timer(DELAY, this);
        timer.start();    
    }
    
    private void initObjects(){
    	logs = new ArrayList<>();
    	for(int i=0;i<LOG_POSITIONS.length;i++){
    		logs.add(new Log(LOG_POSITIONS[i], true));
    	}
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawObjects(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void drawObjects(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(frog.getSprite(), frog.getX(), frog.getY(), this);
    	for(Log log : logs){
    		g2d.drawImage(log.getSprite(), log.getX(), log.getY(), this);
    	}
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
    			frog.reInit();
    			
    		}
    	}
    }
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            frog.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            frog.keyPressed(e);
        }
    }
}