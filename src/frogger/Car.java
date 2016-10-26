package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;

public class Car{
	protected static final int MAX_SPEED = 4;
	protected static final int MINIMUM_SPEED = 1;
    private Random rand= new Random();
	protected final int CHANGE_SPEED_AFTER = rand.nextInt(4)+2;
	private ArrayList<Rectangle> CARS_BOUNDS = new ArrayList<Rectangle>();
	private ArrayList<Integer> X_VALUES = new ArrayList<Integer>();
    private int y;
    protected Image image;
    private ImageIcon ii;
    private boolean moveRight;
    private int numCarResets;
    private int speed = rand.nextInt(MAX_SPEED - MINIMUM_SPEED)+MINIMUM_SPEED;
    

    public Car(int y, boolean moveRight) {
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
    	numCarResets = 0;
    	rand.setSeed(System.nanoTime());
    	if(rand.nextBoolean())
    	{
    		if(moveRight)
    			ii = new ImageIcon(GameTools.carRightImagePath);
    		else
    			ii = new ImageIcon(GameTools.carLeftImagePath);
    	}
    	else
    	{
    		
    		if(moveRight)
    			ii = new ImageIcon(GameTools.truckRightImagePath);
    		else
    			ii = new ImageIcon(GameTools.truckLeftImagePath);
    	}
    	 image = ii.getImage();
         X_VALUES.add(0);
         CARS_BOUNDS.add(new Rectangle(X_VALUES.get(0),y,image.getWidth(null),image.getHeight(null)));
       
    }
    public void move() {
    	if(numCarResets > CHANGE_SPEED_AFTER){
    		getNextSpeed();
    		numCarResets = 0;
    	}
    	int index = 0;
    	while(index < X_VALUES.size()){
    		X_VALUES.set(index, moveThisCar(X_VALUES.get(index)));
    		CARS_BOUNDS.get(index).x = X_VALUES.get(index);
    		index++;
    	}
 
    }
    private int moveThisCar(int x){
    	if(moveRight)
	        if(x<GameTools.boardWidth)
	        	x += speed;
	        else 
	        {
	        	x=-(ii.getIconWidth());
	        	numCarResets++;
	        }
    	else{
    		if(x>-ii.getIconWidth())
            	x -= speed;
            else 
            {
            	x=GameTools.boardWidth;
            	numCarResets++;
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
	public void drawCar(Graphics g) {
		for(int x : X_VALUES){
    	Graphics2D g2d1 = (Graphics2D) g;
        g2d1.drawImage(getImage(), x, getY(), null); 
		}
    }
	public ArrayList<Rectangle> getBounds(){
		return CARS_BOUNDS;
	}
	//try add new Car to the same row
	public void addNewCar(int distAhead){
		int lastXValue = X_VALUES.get(X_VALUES.size()-1);
		Rectangle newCarRec = new Rectangle(lastXValue + distAhead,
				y,image.getWidth(null),image.getHeight(null));
		CARS_BOUNDS.add(newCarRec);
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
