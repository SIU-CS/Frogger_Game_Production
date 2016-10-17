package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.Rectangle;

public class Car{
    private int x=100;
    protected int y;
    protected Image image;
    
    Random rand=new Random();
    private int speed = rand.nextInt(5)+3;
    

    public Car(int y) {
    	this.y=y;
        initCraft();
    }
    private void initCraft() {
        ImageIcon ii = new ImageIcon(GameTools.carImagePath);
        
        image = ii.getImage();
        
    }
    public void move() {
        if(x<800)
        	x += speed;
        else x=-200;
    }
    public void setX(int x){
    	this.x=x;
    }
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public Image getImage(){
    	return image;
    }
	public void drawLog(Graphics g) {
        
    	Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getImage(), getX(), getY(), null);        
    }
    public Rectangle getBounds(){
  		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
  	}
}
