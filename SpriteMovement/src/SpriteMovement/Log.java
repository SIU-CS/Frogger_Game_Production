package SpriteMovement;

import java.awt.Rectangle;
import java.util.Random;

public class Log extends SpriteObject{
    private int x=-100;
    Random rand=new Random();
    private int speed = rand.nextInt(5)+3;
    

    public Log(int y) {
    	super(y);
        initCraft();
    }
    private void initCraft() {
        loadImage("src/log.png");
        getImageDimensions();
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
    public Rectangle getBounds(){
  		return new Rectangle(x,y,image.getWidth(null),image.getHeight(null));
  	}
}

