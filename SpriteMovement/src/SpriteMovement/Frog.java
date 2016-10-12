package SpriteMovement;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Frog {
	 private int dx;
	    private int dy;
	    private int x;
	    private int y;
	    private Image image;

	    public Frog() {
	        
	        initCraft();
	    }
	    
	    private void initCraft() {
	        
	        ImageIcon ii = new ImageIcon("src/frog.png");
	        image = ii.getImage();
	        x = 40;
	        y = 530;        
	    }


	    public void move() {
	        x += dx;
	        y += dy;
	    }

	    public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }

	    public Image getImage() {
	        return image;
	    }

	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT) {
	            x -= 100;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            x += 100;
	        }

	        if (key == KeyEvent.VK_UP) {
	            y -= 100;
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            y += 100;
	        }
	    }

	    public void keyReleased(KeyEvent e) {
	        
	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT) {
	            dx = 0;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	            dx = 0;
	        }

	        if (key == KeyEvent.VK_UP) {
	            dy = 0;
	        }

	        if (key == KeyEvent.VK_DOWN) {
	            dy = 0;
	        }
	    }
	}

