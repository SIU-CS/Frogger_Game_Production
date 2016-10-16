package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import java.util.Random;

public class Log implements Runnable{
	private int speedDefault = 5;
    private int x;
    private int y = GameTools.boardImageLegth;
    private Image image;
    private ImageIcon ii;
    private int ranBottomWait;
    private int ranTopWait;
    private int speed;
    private boolean srtRight;

    public Log(){
    	initCraft();
    }

	private void initCraft() {
        ii = new ImageIcon("src/log.png");
        image = ii.getImage();
    }  
	
	public void initCraft(int speed, int up, boolean srtRight) {
        ii = new ImageIcon("src/log.png");
        image = ii.getImage();
        if (speed > 0)
    		this.speed = speed;
    	else{
    		this.speed = speedDefault;
    	}
        if(up<0)
        	up *= -1;
        this.y -= up;
        if (srtRight){
        	x = GameTools.boardWidth;
        }
        else{
        	x = -ii.getIconWidth();
        }
        
    }  
	
	public void initCraft(int ranBottomWait, int ranTopWait, int up, boolean srtRight) {
        ii = new ImageIcon("src/log.png");
        image = ii.getImage();
        
        this.srtRight = srtRight;
    	if (ranBottomWait > 0 && ranTopWait > ranBottomWait )
    	{
    		this.ranBottomWait = ranBottomWait;
    		this.ranTopWait = ranTopWait;
        	speed = -1;
    	}
    	else if (ranTopWait == ranBottomWait && ranBottomWait > 0)
    		speed = ranTopWait;
    	else
    		speed = speedDefault;

        if(up<0)
        	up *= -1;
        this.y -= up;
        if (srtRight){
        	x = GameTools.boardWidth;
        }
        else{
        	x = -ii.getIconWidth();
        }
        
    }  
	
    private void autoLog() {
    	boolean randomBool = false;
		Random rand = new Random();
		if (speed <= 0){
			randomBool = true;
			speed = rand.nextInt((ranTopWait - ranBottomWait) + 1) + ranBottomWait;
		}
		while(true)
		{
			if(srtRight)
				while(getX() > 0-ii.getIconWidth())
				{
					move(-1);
					try {
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			else
				while(getX() < GameTools.boardWidth)
				{
					move(1);
					try {
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			//System.out.println("Here");
			if (srtRight)
				setX(GameTools.boardWidth);
			else
				setX(-ii.getIconWidth());
			if(randomBool)
				speed = rand.nextInt((ranTopWait - ranBottomWait) + 1) + ranBottomWait;
		}
	}
    public void move(int speed) {
        if(x<GameTools.boardWidth || x > 0)
        	x += speed;
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
    
	public void drawLog(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(getImage(), getX(), getY(),null);
    	
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		autoLog();
	}
    	
}
