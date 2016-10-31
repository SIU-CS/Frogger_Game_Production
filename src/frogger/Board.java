package frogger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/*Board Class handles all things related to the board, (what is displayed on the screen).*/

public class Board extends JPanel implements Runnable, ActionListener{

   
	private static final long serialVersionUID = 1L;
	CarController carControls = new CarController(); //controller for the cars
	LogController logControls = new LogController(); //controller for the logs
	KeyboardController keyboardControls;			 //controller for the key inputs
	protected ArrayList<LilyPad> lilypads;			 //array of object lily pads
	
    private Player frog;
    private Timer timer;
    private final int DELAY=20;
    private long lastLogHitTimer;
    private Background background;
	TAdapter key;
	
    public Board() {
        initBoard();
        initObjects();        
    }
    
    /*Initializes the board
     *Creates the background 
     *Adds a key listener that sends input to keyboardController
     *Starts a timer which enacts moves after certain time*/
    private void initBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        
        //always place background first
        //adds important information to GameTools
        background = new Background();
        background.setImage(GameTools.backgroundImagePath);
        
        key = new TAdapter();
        addKeyListener(key);
        timer = new Timer(DELAY, this);
        timer.start();
        setPreferredSize(new Dimension(background.getMaxWidth(),background.getMaxHeight()) );
    }
    
    /*Initializes the Objects
     * Creates a player or frog 
     * Considers the lanes given and determines the dynamic object to create
     * */
    private void initObjects(){
    	//X and Y in which frog will be starting at all times
    	int startX = (GameTools.columnWidth)*GameTools.numCols/2;
    	int startY = GameTools.boardImageLength-GameTools.rowHeight;
    	frog = new Player(startX, startY);
    			
    	int distRows = GameTools.rowHeight;					//height given to each row :: 64
    	int count = GameTools.level1_boardPositions.length -1;	//getting the length of board positions for objects
    	int positionUp = 0;									//counter for each row up
 	
    	//creation of the dynamic entities dependent on the given board positions
    	while(count >= 0){    	        	
    		int typeObject = GameTools.level1_boardPositions[count]; //type of entity to spawn in that row
    		//board_position for cars
    		if (typeObject == 1 || typeObject == 2)
    			carControls.addLane(typeObject, positionUp);
    		//board_position for logs
    		if (typeObject == 3)
    			logControls.addLane(typeObject, positionUp);
    		//board_position for lily pads
    	   	else if (typeObject == 4){
    	   		setUpLilyPads(positionUp);
    	   	}
    	   	positionUp += distRows;
    	   	count--;
    	}	
    }
    
    /*sets up a new thread that runs the KeyboardController*/
    public void setKeyboard(ScrollBoard gameScroll){
    	//Initializing KeyboardController
        EventQueue.invokeLater(new Runnable(){
        	@Override
        	public void run(){
        		keyboardControls = new KeyboardController(gameScroll, frog);
        	}
        });
    }
	
	/*Paints all the graphics that reside on the board
	 *Car controller and Log controller multiples of their object and draw them in mass
	 * */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.drawBackground(g);
        carControls.drawCars(g);	//draws all initialized Cars
        logControls.drawLogs(g);    //draws all initialized Logs
        
        for(LilyPad lilypad : lilypads){
        	lilypad.drawSprite(g);
        }  
        frog.drawSprite(g);
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public Player getFrog(){
    	return frog;
    }
    public Background getBackgroundGame(){
    	return background;
    }
    
    /*Controls all of the actions performed
     *Moves dynamic entities, determines collision and initiates win and loss sequence.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
    		Rectangle frogBounds = frog.getBounds();
        	carControls.moveCars();
        	if(carControls.collisionDetection(frogBounds))
        		keyboardControls.gameLoseSequence();

        	logControls.moveLogs();
        	Log collidedLog = logControls.collisionDetection(frogBounds);
        	if(collidedLog != null)
        		if(!jumpOnLog(frogBounds, collidedLog))
        			keyboardControls.gameLoseSequence();
        	
        	for(LilyPad lilypad : lilypads){
        		collision_Detection(lilypad);
        	}
		    
    	checkWaterHit();
    	frog.recenterOnLines();
    }
    
    //TODO might be advantageous to create a staticController in the future if we create more types of static entities
    //sets up lily pads
	public void setUpLilyPads(int positionUp) {
		//sets up the lily pads
		lilypads = new ArrayList<>();
		int numCols = GameTools.numCols;
		//this is if we have even columns
		if(numCols % 2 == 0)
		{
			numCols = GameTools.numCols/2;
			while(numCols > 0){
				lilypads.add(new LilyPad((numCols - 1) * GameTools.columnWidth, positionUp));
				numCols -= 2;
			}
			numCols = GameTools.numCols/2;
			while(numCols < GameTools.numCols){
				lilypads.add(new LilyPad((numCols) * GameTools.columnWidth, positionUp));
				numCols += 2;
			}
		}
		//this is if we have odd columns
		else{
			numCols--;
			while(numCols > 0){
				lilypads.add(new LilyPad((numCols - 1) * GameTools.columnWidth, positionUp));
				numCols -= 2;
			}
		}
	}
	
    //lily pad collision
    public void collision_Detection(LilyPad lilypad){
    	Rectangle frogBounds = frog.getBounds();
    	Rectangle lilypad_rec =  lilypad.getBounds();
    	if(frogBounds.intersects(lilypad_rec)){
    		//make you win sign, then reset
    		keyboardControls.gameWinSequence();
    	}
    }
    
    //determines if frog has jumped on log and how that should be handled	
	public boolean jumpOnLog(Rectangle frogBounds, Log collidedLog) {
		int frogX = frog.getX();
		int frogWidth = frog.getSpriteWidth();
		int logX = collidedLog.getX();
		int logY = collidedLog.getY();
		int logWidth = collidedLog.getSpriteWidth();
			
		frog.setY(logY);
		if(frogX < logX + logWidth/2)
			frog.setX(logX);
		else
			frog.setX(logX + logWidth - frogWidth);
			
		//this is for timer since last log hit, to check if you need to reset
		lastLogHitTimer = System.currentTimeMillis();
			
		//returns false if you run off the the screen
		if(frogX + frogWidth < 0 || frogX > GameTools.boardWidth){
			return false;
		}
			
		return true;
	}
	//checks if frog is in water and not on the log   
	public void checkWaterHit() {
		int frogY = frog.getY();
		if(frogY < GameTools.numWaterSquares * GameTools.rowHeight && frogY > GameTools.rowHeight && System.currentTimeMillis() - lastLogHitTimer > 100)
			keyboardControls.gameLoseSequence();	
		}
	
    private class TAdapter extends KeyAdapter {
	    @Override
	    public void keyPressed(KeyEvent e) {
	        keyboardControls.keyPressed(e);
	        
	    }
	    public void keyReleased(KeyEvent e) {
	        keyboardControls.keyReleased(e);
	    }
  	}
    
	@Override
	public void run() {
	}
}