package SpriteMovement;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Log {
    private int x;
    private final int y=300;
    private Image image;
    private int speed;
    

    public Log(int speed) {
        this.speed=speed;
        initCraft();
    }
    private void initCraft() {
        
        ImageIcon ii = new ImageIcon("src/log.png");
        image = ii.getImage();
        x = 0; 
    }
    public void move() {
        if(x<800)
        	x += speed;
        else x=0;
    }
    public void setX(int x){
    	this.x=x;
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
}

