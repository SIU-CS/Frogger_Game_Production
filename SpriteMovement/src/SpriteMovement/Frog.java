package SpriteMovement;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Frog extends SpriteObject{
    private int x=40;
    private int y=530;
    private Image image;
    
    public Frog(int y){
    	super(y);
    	initFrog();
    }
    
    private void initFrog() {
        
        ImageIcon ii = new ImageIcon("src/frog.png");
        image = ii.getImage();
        x = 40;
        y = 530;        
    }
    public void reInit(){
    	initFrog();
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
    public Rectangle getBounds(){
		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
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
    	
    }
}

