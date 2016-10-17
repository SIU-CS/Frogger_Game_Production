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
    private int ranBottomWait;
    private int ranTopWait;
    private int waitTime = waitTimeDefault;
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
		 
	      map = null;
	      image = null ;
		  randWaitTimeBool = false;
		  ranTopWait = -10;
		  ranBottomWait = 3;
		  srtRight = false;
		  imageWidth = -10;
		  waitTime = 0;
		  //handled by an outside source
		  boardWidth = 300;
		  
		  imageHeight = 400;
		  y = imageHeight;
		  
		  //the number of times autoLog is run
		  //this is an endless function
		  //put here for testing only
		  times = 1;
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
		}
		int count = 0;
		while(count < times)
		{
			if(srtRight)
				while(x > -imageWidth)
				{
					x -= 1;
					if (waitTime > 0)
					{
						try {
							Thread.sleep(waitTime);
						} catch (InterruptedException e) {
							System.out.println("Class Log, log cant wait, Error in"
									+ "Thred.sleep(waitTime)");
						}
					}
				}
			else
				while(x < boardWidth)
				{
					x += 1;
					if (waitTime > 0)
					{
						try {
							Thread.sleep(waitTime);
						} catch (InterruptedException e) {
							System.out.println("Class Log, log cant wait, Error in"
									+ "Thred.sleep(waitTime)");
						}
					}
					
				}
			moveStartRight(srtRight);
			
			if(randWaitTimeBool)
				waitTime = randomNum();
			
			//this is for testing
			count++;
		}
		assertTrue("The Value of 'x' is " + x + " And the wait time is " + waitTime
				,x >= -(imageWidth) && x <= boardWidth);
	}


	//added test on second fail phase
	@Test
	public void randomNumTest(){
		int count = 0;
		int test = 0;
		while(count < 10)
		{
		ranBottomWait = 3;
		ranTopWait = -10;
		//processing the go through so I can test the value
		if(ranBottomWait <= 0)
			test = waitTimeDefault;
		else if (ranTopWait < ranBottomWait){
			int temp = ranTopWait;
			ranTopWait = ranBottomWait;
			ranBottomWait = temp;
		}
		test = randomNum();
		
		assertTrue("test value failed, the value is " + test +
				" should be between " + ranBottomWait + " and " +
				ranTopWait,(test >= ranBottomWait && test < ranTopWait) || test == waitTimeDefault);
		count++;
		}
	}
	//for use inside the AutoLog function
	public int randomNum(){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		if(ranBottomWait <= 0)
			return waitTimeDefault;
		else if (ranTopWait < ranBottomWait){
			int temp = ranTopWait;
			ranTopWait = ranBottomWait;
			ranBottomWait = temp;
		}
		return rand.nextInt((ranTopWait - ranBottomWait) + 1) + ranBottomWait;
	}
	
	 //********************************************
	
	//for testing purposes only
	@Test
	public void setUp_constantSpeedInitateTest()
	{
		int waitTime = 30;
		int up = -2000;
		constantSpeedInitiateTest(waitTime, up);
	}
	
	public void constantSpeedInitiateTest(int waitTime, int up) {
	        if (waitTime > 0)
	    		this.waitTime = waitTime;
	    	else{
	    		this.waitTime = waitTimeDefault;
	    	}
	        moveUp(up);
	        
	        moveStartRight(srtRight);
	        
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

	// has already been tested in randomSpeedInitate
	// and constantSpeedInitiate
	public void moveUp(int up){
		if (up < 0)
        	up *= -1;
		if( this.y + up > imageHeight)
			this.y = imageHeight;
		else if(this.y - up<0)
			this.y = 0;
		else
			this.y -= up;
	}
	
	public void moveStartRight(boolean srtRight){
		 if (srtRight){
	        	x = boardWidth;
	        }
	        else{
	        	x = -imageWidth;
	        }
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

	    moveUp(up);
	        
	    moveStartRight(srtRight);
	    
        assertTrue(x <= boardWidth && x >= -imageWidth && y >= 0 && y <= imageHeight &&
        		((this.ranTopWait > (this.ranBottomWait) && this.ranBottomWait > 0) || waitTime > 0));
    }  

	
	//various getters and setters
	//unimportant methods
	//well tested methods, such as the graphical draw
}
