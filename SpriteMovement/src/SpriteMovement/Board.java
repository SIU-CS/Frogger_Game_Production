package SpriteMovement;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    /**
	 * auto generated
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	Frog frog;
	Log log;
	Log log2;
	Log log3;

    private final int DELAY = 10;

    public Board() {
        initBoard();
    }
    Random rand = new Random();
    void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        frog = new Frog();
        log = new Log(5,350);
        log2 = new Log(7,200);
        log3 = new Log(2,50);
        timer = new Timer(DELAY, this);
        timer.start();        
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLog(g);
        Toolkit.getDefaultToolkit().sync();
    }
    private void drawLog(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(frog.getImage(), frog.getX(), frog.getY(), this);
    	g2d.drawImage(log.getImage(), log.getX(), log.getY(),this);
    	g2d.drawImage(log2.getImage(), log2.getX(), log2.getY(), this);
    	g2d.drawImage(log3.getImage(), log3.getX(), log3.getY(), this);
    	
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
    	log.move();
    	log2.move();
    	log3.move();
        repaint();
        collision_Detection();
    }
  
    private void collision_Detection(){
    	if( (( Math.abs(frog.getX()-log.getX()+40) ) < 20)  && (Math.abs( frog.getY()-log.getY() ) < 50 ) ){
    		log.setX(-200);
    		frog.reInit();
    		setBackground(Color.BLACK);
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