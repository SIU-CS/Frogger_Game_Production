package frogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import org.junit.Test;

import java.util.Random;

public class Log implements Runnable{
	private String map = "map.png";
	private int imageWidth = 0;
	private int imageHeight = 0;
	private final int waitTimeDefault = 8;
    private int x;
    private int y;
    private Image image;
    private ImageIcon ii;
    private int ranBottomWait;
    private int ranTopWait;
    private int waitTime;
    private boolean srtRight;
    private boolean randWaitTimeBool = false;
    //get from a separate function
    //impossible to get from this function
    private int boardWidth = GameTools.boardWidth;
    

    public Log(){
    	initCraft();
    }


	public void initCraft() {
		ImageIcon ii = new ImageIcon(map);
		imageWidth = ii.getIconWidth();
		image = ii.getImage();
		y = ii.getIconHeight();
		imageHeight = y;
		constantSpeedInitiate(waitTimeDefault, 0);
		
	} 
	public void constantSpeedInitiate(int waitTime, int up) {
        if (waitTime > 0)
    		this.waitTime = waitTime;
    	else{
    		this.waitTime = waitTimeDefault;
    	}
        
        
        this.y -= up;
        if (srtRight){
        	x = boardWidth;
        }
        else{
        	x = -imageWidth;
        }
	}
	public void randomSpeedInitiate(int ranBottomWait, int ranTopWait, int up, boolean srtRight) {
		this.srtRight = srtRight;
    	if (ranBottomWait > 0 && ranTopWait > ranBottomWait )
    	{
    		this.ranBottomWait = ranBottomWait;
    		this.ranTopWait = ranTopWait;
        	waitTime = -1;
    	}
    	else if (ranTopWait == ranBottomWait && ranBottomWait > 0)
    		waitTime = ranTopWait;
    	else
    		waitTime = waitTimeDefault;

        this.y -= up;
        if (srtRight){
        	x = GameTools.boardWidth;
        }
        else{
        	x = -ii.getIconWidth();
        }
	}

	public void autoLog() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		if (randWaitTimeBool){
			waitTime = randomNum();
			System.out.println(waitTime);
		}
		while(true)
		{
			if(srtRight)
				while(x > -imageWidth)
				{
					x -= 1;
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			else
				while(x < GameTools.boardWidth)
				{
					x += 1;
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			//System.out.println("Here");
			if (srtRight)
				x= boardWidth;
			else
				x = (-imageWidth);
			if(randWaitTimeBool)
				waitTime = randomNum();
			System.out.println(waitTime);
		}
	}
	
	public int randomNum(){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		return rand.nextInt((ranTopWait - ranBottomWait) + 1) + ranBottomWait;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		autoLog();
	} 
   
    	
}
