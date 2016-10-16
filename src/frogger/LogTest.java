package frogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogTest{
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
    private int boardWidth;
    //testing only
    private int times;
	

	//just impliments initCraft
   public LogTest(){

    }
   
	 
	 @Before
	  public void setUp() { 
		 
	      map = "map.png";
	      image = null;
		  randWaitTimeBool = true;
		  ranTopWait = 2;
		  ranBottomWait = 1;
		  srtRight = true;
		  imageWidth = 30;
		  waitTime = 5;
		  boardWidth = 300;
		  imageHeight = 400;
		  y = imageHeight;
		  
		  //the number of times autoLog is run
		  //this is an endless function
		  //put here for testing only
		  times = 10;
	   } 
	 
	 @After
	 public void tearDown() { 
	        
   }

	 
   
   //**************************************************************
   
	//initlizes the Log with an image that will be used
	@Test
	public void initCraftTest() {
		ImageIcon ii = new ImageIcon(map);
		imageWidth = ii.getIconWidth();
		image = ii.getImage();
		y = ii.getIconHeight();
		imageHeight = y;
		assertEquals("failure - images are not equal", image, new ImageIcon(map).getImage());
    } 

	
	  
	  //*********************************************************
	  //this is for testing, since it is an endless loop this is the number of times it will run
	  // before the test
	   

	 
	 //moves the log automatically
	 //at either a variable speed or a constant speed based on the boolean randomBool
	//resets when off the screen
	//use a endless loop and a new thread to update
	@Test
    public void autoLogTest() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		if (randWaitTimeBool){
			waitTime = randomNum();
			System.out.println(waitTime);
		}
		int count = 0;
		while(count < times)
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
			
			//this is for testing
			count++;
		}
		assertTrue("The Value of 'x' is " + x + " And the wait time is " + waitTime
				,x >= -(imageWidth) && x <= boardWidth && waitTime > 0);
	}
	
	public int randomNum(){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		return rand.nextInt((ranTopWait - ranBottomWait) + 1) + ranBottomWait;
		
	}
	
	 //********************************************
	
	//for testing purposes only
	@Test
	public void setUp_constantSpeedInitateTest()
	{
		int waitTime = 30;
		int up = 100;
		constantSpeedInitiateTest(waitTime, up);
	}
	
	public void constantSpeedInitiateTest(int waitTime, int up) {
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
	        assertTrue(x <= boardWidth && x >= -imageWidth && y >= 0 && y <= imageHeight);
    }
	 
	 //*********************************************************
	
	//for testing purposes only
	@Test
	public void setUp_randomSpeedInitateTest()
	{
		int ranBottomWait = 3;
		int ranTopWait = 10;
		boolean srtRight = true;
		int up = 100;
		randomSpeedInitiateTest(ranBottomWait, ranTopWait, up, srtRight);
	}
	
	public void randomSpeedInitiateTest(int ranBottomWait, int ranTopWait, int up, boolean srtRight) {
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
        assertTrue(x <= boardWidth && x >= -imageWidth && y >= 0 && y <= imageHeight &&
        		((this.ranTopWait > (this.ranBottomWait) && this.ranBottomWait > 0) || waitTime > 0));
    }  

	
	//various getters and setters
	//unimportant methods
	//well tested methods, such as the graphical draw
}
