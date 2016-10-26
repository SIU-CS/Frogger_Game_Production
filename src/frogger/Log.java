package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

public class Log{
	protected static final int MAX_SPEED = 5;
	protected static final int MINIMUM_SPEED = 2;
    private Random rand= new Random();
	protected final int CHANGE_SPEED_AFTER = rand.nextInt(4)+2;
	private ArrayList<Rectangle> LOGS_BOUNDS = new ArrayList<Rectangle>();
	private ArrayList<Integer> X_VALUES = new ArrayList<Integer>();
    private int y;
    protected Image image;
    private ImageIcon ii;
    private boolean moveRight;
    private int numLogResets;
    private int speed = rand.nextInt(MAX_SPEED - MINIMUM_SPEED)+MINIMUM_SPEED;
    

    public Log(int y, boolean moveRight) {
    	this.moveRight = moveRight;
    	this.y=y;
        initCraft();
        recenterImage();
    }
    
    private void recenterImage(){
    	//centers the image on the square
        y += (GameTools.rowHeight - ii.getIconHeight())/2;
    }
    private void initCraft() {
    	numLogResets = 0;
        ii = new ImageIcon(GameTools.logImagePath);
        image = ii.getImage();
        X_VALUES.add(0);
        LOGS_BOUNDS.add(new Rectangle(X_VALUES.get(0),y,image.getWidth(null),image.getHeight(null)));
    }
    public void move() {

    	if(numLogResets > CHANGE_SPEED_AFTER){
    		getNextSpeed();
    		numLogResets = 0;
    	}
    	int index = 0;
    	while(index < X_VALUES.size()){
    		X_VALUES.set(index, moveThisLog(X_VALUES.get(index)));
    		LOGS_BOUNDS.set(index, new Rectangle(X_VALUES.get(index),y,image.getWidth(null),image.getHeight(null)));
    		index++;
    	}
 
    }
    private int moveThisLog(int x){
    	if(moveRight)
	        if(x<GameTools.boardWidth)
	        	x += speed;
	        else 
	        {
	        	x=-(ii.getIconWidth());
	        	numLogResets++;
	        }
    	else{
    		if(x>-ii.getIconWidth())
            	x -= speed;
            else 
            {
            	x=GameTools.boardWidth;
            	numLogResets++;
            }
    	}
    	return x;
    }
    public void setX(int index, int x){
    	 X_VALUES.set(index, x);
    }
    public ArrayList<Integer> getX(){
		return X_VALUES;
    }

    public int getY(){
    	return y;
    }
    public Image getImage(){
    	return image;
    }
	public void drawLog(Graphics g) {
		for(int x : X_VALUES){
    	Graphics2D g2d1 = (Graphics2D) g;
        g2d1.drawImage(getImage(), x, getY(), null); 
		}
    }
	public ArrayList<Rectangle> getBounds(){
		return LOGS_BOUNDS;
	}
	//try add new log to the same row
	//if theres no more room it will return with false
	public void addNewLog(int distAhead){
		int lastXValue = X_VALUES.get(X_VALUES.size()-1);
		Rectangle newLogRec = new Rectangle(lastXValue + distAhead,
				y,image.getWidth(null),image.getHeight(null));
			LOGS_BOUNDS.add(newLogRec);
			X_VALUES.add(lastXValue + distAhead);
	}
	public ImageIcon getImageIcon() {
		return ii;
	}
	private void getNextSpeed(){
		rand.setSeed(System.nanoTime());
		if(rand.nextBoolean())
			if(speed < MAX_SPEED)
				speed++;
		else
			if(speed > MINIMUM_SPEED)
				speed--;
	}
}
