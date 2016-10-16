package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Frog {
    private int x;
    private int y;
    private Image image;

    public Frog(int strtWidth, int strtHeight) {
        
        initCraft(strtWidth,strtHeight);
    }
    
    private void initCraft(int x, int y) {
        
        ImageIcon ii = new ImageIcon("src/frog.png");
        image = ii.getImage();
        
        this.x = x;
        this.y = y;        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }
    public void drawFrog(Graphics g) {
        
    	Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getImage(), getX(), getY(), null);        
    }
   //neggative is left
   //positive is right
   public void moveFrogHorizontal(int distance){
	   setX(getX()+distance);
   }
   //neggative is down
   //positive is up
   public void moveFrogVertical(int distance){
	   setY(getY()-distance);
   }
}

