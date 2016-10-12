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
    private Frog frog;
    private Log log;

    private final int DELAY = 10;

    public Board() {

        initBoard();
    }
    Random rand = new Random();
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        frog = new Frog();
        log = new Log(5);
        timer = new Timer(DELAY, this);
        timer.start();        
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFrog(g);
        drawLog(g);
        Toolkit.getDefaultToolkit().sync();
    }
    private void drawLog(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(log.getImage(), log.getX(), log.getY(),this);
    	
    }
    private void drawFrog(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(frog.getImage(), frog.getX(), frog.getY(), this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	updateLog();
        repaint();  
    }
    private void updateLog(){
    	if(log.getX()<800)
    		log.move();
    	else log.setX(0);
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