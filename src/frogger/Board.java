package frogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements Runnable, ActionListener{

   
	private static final long serialVersionUID = 1L;
	
	protected static final int MAX_DIST_LOGS = 400;
	protected static final int MINIMUM_DIST_LOGS = 250;
	
	protected static final int MAX_DIST_CARS = 350;
	protected static final int MINIMUM_DIST_CARS = 150;
	
	private ArrayList<Integer> CAR_POSITIONS = new ArrayList<Integer>();
	private ArrayList<Integer> LOG_POSITIONS = new ArrayList<Integer>();
	
	protected ArrayList<Car> cars;
	protected ArrayList<Log> logs;
	protected ArrayList<LilyPad> lilypads;
    private Frog frog;
    private Timer timer;
    private final int DELAY=20;
    private Background background;
    private Random rand;
	TAdapter key;
    public Board() {
        initBoard();
        initObjects();
    }
    
    private void initBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        //always place background first
        //adds important information to GameTools
        background = new Background();
        background.setImage(GameTools.backgroundImagePath);
        //adds a key listener that send the presses to GameEngine
        key = new TAdapter();
        addKeyListener(key);
        timer = new Timer(DELAY, this);
        timer.start();

        setUpMovables();

        setPreferredSize(new Dimension(background.getMaxWidth(),background.getMaxHeight()) );
        
    }

	public void setUpMovables() {
		frog = new Frog((GameTools.columnWidth)*GameTools.numCols/2,
        		GameTools.boardImageLength -GameTools.rowHeight);
		
		int distRows = GameTools.rowHeight;
        int count = GameTools.BOARD_POSITIONS.length -1;
        int positionUp = 0;
        while(count >= 0){
        	int type = GameTools.BOARD_POSITIONS[count];
        	if (type == 1){
        		CAR_POSITIONS.add(positionUp);
        	}
        	else if (type == 2){
        		LOG_POSITIONS.add(positionUp);
        	}
        	else if (type == 3){
        		setUpLilyPads(positionUp);
        	}
        	positionUp += distRows;
        	count--;
        }
        //sets up the lily pads at the end
	}

    private void initObjects(){
    	//sets all cars on the screen and adds them to the array cars
    	//random number determines if they go right or left
    	logs = new ArrayList<>();
		rand = new Random();
    	boolean temp = rand.nextBoolean();
    	for(int i=0;i<LOG_POSITIONS.size();i++){
    		logs.add(new Log(LOG_POSITIONS.get(i),temp));
    		int count = rand.nextInt(1)+1;
    		while(count > 0){
    			logs.get(i).addNewLog(rand.nextInt(MAX_DIST_LOGS - MINIMUM_DIST_LOGS)+MINIMUM_DIST_LOGS);
    			count--;
    		}
    		temp = !temp;
    	}
    	
    	cars = new ArrayList<>();
    	for(int i=0;i<CAR_POSITIONS.size();i++){
    		cars.add(new Car(CAR_POSITIONS.get(i),temp));
    		int count = rand.nextInt(3)+1;
    		while(count > 0){
    			cars.get(i).addNewCar(rand.nextInt(MAX_DIST_CARS - MINIMUM_DIST_CARS)+MINIMUM_DIST_CARS);
    			count--;
    		}
    		temp = !temp;
    	}
    }

	public void setUpLilyPads(int positionUp) {
		//sets up the lily pads
		lilypads = new ArrayList<>();
		int numCols = GameTools.numCols;
		//this is if we have even rows
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
		//this is if we have odd rows
		else{
			numCols--;
			while(numCols > 0){
				lilypads.add(new LilyPad((numCols - 1) * GameTools.columnWidth, positionUp));
				numCols -= 2;
			}
		}
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.drawBackground(g);
        for(Log log : logs){
    		log.drawLog(g);
    	}
        for(LilyPad lilypad : lilypads){
        	lilypad.drawLilyPad(g);
        }
        frog.drawFrog(g);
        for(Car car : cars){
    		car.drawCar(g);
    	}
        Toolkit.getDefaultToolkit().sync();
    }
    public void paintComponentEnd(Graphics g){
    	super.paintComponent(g);
    	
    }
    public Frog getFrog(){
    	return frog;
    }
    public Background getBackgroundGame(){
    	return background;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	for(Car car : cars){
    		car.move();
    		collision_Detection(car);
    		repaint();
    	}
    	for(Log log : logs){
    		log.move();
    		collision_Detection(log);
    		repaint();
    	}
    	for(LilyPad lilypad : lilypads){
    		collision_Detection(lilypad);
    		repaint();
    	}
    	frog.checkWaterHit();
    	frog.recenterOnLines();
    }
    //for car
    public void collision_Detection(Car car){
    	Rectangle frog_rec = frog.getBounds();
		//what happens when you run into a log
		//if you run off the screen you lose
		ArrayList<Rectangle> rectangles;
		rectangles = car.getBounds();
		for(Rectangle rect : rectangles)
    		if(frog_rec.intersects(rect))
	    			GameEngine.gameLoseSequence();

    }
	//for log
    public void collision_Detection(Log log){
    	Rectangle frog_rec = frog.getBounds();
    		//what happens when you run into a log
    		//if you run off the screen you lose
    		ArrayList<Rectangle> rectangles;
    		rectangles = log.getBounds();
    		for(Rectangle rect : rectangles){
	    		if(frog_rec.intersects(rect)){
		    		if(!frog.jumpOnLog(rect))
		    			GameEngine.gameLoseSequence();
			    	}
    		}
    }
    //for lilypad
    public void collision_Detection(LilyPad lilypad){
    	Rectangle frog_rec = frog.getBounds();
    	Rectangle lilypad_rec =  lilypad.getBounds();
    	if(frog_rec.intersects(lilypad_rec)){
    		//make you win sign, then reset
    		GameEngine.gameWinSequence();
    	}
    }
    private class TAdapter extends KeyAdapter {
	    @Override
	    public void keyPressed(KeyEvent e) {
	        GameEngine.keyPressed(e);
	        
	    }
	    public void keyReleased(KeyEvent e) {
	        GameEngine.keyReleased(e);
	    }
  	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
