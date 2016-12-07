package frogger;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

//handles the controlling of the logs in a lane
public class LogController {
	Random rand = new Random();
	//max and min distance between to cars
	protected final int MAX_DIST_LOGS = 256;
	protected final int MINIMUM_DIST_LOGS = 192;
	
	//max and min speed that can be obtained
	protected final int MAX_SPEED = 5;
	protected final int MINIMUM_SPEED = 2;
	
	//amount of resets until speed for a lane will be changed
	protected final int CHANGE_SPEED_AFTER = rand.nextInt(4)+2;
	
	/*An ArrayList of ArrayLists of Logs,
	 *ArrayList<ArrayList<Log>> acts as the lane, many cars will be in it
	 *ArrayList<Log> is you list of cars in that lane*/
	protected ArrayList<ArrayList<Log>> laneArray = new ArrayList<ArrayList<Log>>();
	private long lastLogHitTimer;
	private boolean logMove = false;
	
	//adds a new lane(adds an ArrayList<Log> to main ArrayList)
	public void addLane(int typeObject, int spawningY){
		int lastX = 100;
		int speed = getNextSpeed();
		int countObject = rand.nextInt(3)+1;
		ArrayList<Log> logArray = new ArrayList<Log>();
		logMove = !logMove;
		
		if(typeObject == 3){
			
			while(countObject > 0){
				
				int distAhead = (MAX_DIST_LOGS - MINIMUM_DIST_LOGS) + MINIMUM_DIST_LOGS;
	        	Log newLog = addLog(speed, lastX + distAhead, spawningY, logMove);
	        	lastX = lastX+distAhead;
	        	logArray.add(newLog);
	        	countObject--;
	        }		
		}
		System.out.println("Lane: " + laneArray.size());
		laneArray.add(logArray);	
	}
	//adds a log and returns it
	private Log addLog(int speed, int spawningX, int spawningY, boolean moveRight){
		Log newLog = new Log(speed, spawningX, spawningY, moveRight);
		return newLog;
	}
	//draws the initialized logs
	public void drawLogs(Graphics g){
		for(ArrayList<Log> lane : laneArray){
			for(Log log : lane){
				log.drawSprite(g);
			}
		}
	}
	//moves the initialized logs
	public void moveLogs(){
		for(ArrayList<Log> lane : laneArray){
			for(Log log : lane){
					if(log.getNumResets() > CHANGE_SPEED_AFTER)
						log.setNumResets(getNextSpeed());
					log.move();	
			}
		}
	}
	//determines if given Rectangle is intersecting any logs
	//returns log as the log is handled differently for its collision
	public boolean collisionDetection(Rectangle bounds, Player frog){
		boolean collisionDetected = true;
		for(ArrayList<Log> lane : laneArray){
			for(Log log : lane){
				Rectangle logBound = log.getBounds();
				if(bounds.intersects(logBound)){
					collisionDetected = jumpOnLog(bounds, log, frog);
					return collisionDetected;
				}
			}
		}
		return collisionDetected;
    }
	
	//determines if frog has jumped on log and how that should be handled	
		public boolean jumpOnLog(Rectangle bounds, Log collidedLog, Player frog) {
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
	public boolean checkWaterHit(Player frog) {
		int frogY = frog.getY();
		if(frogY < GameTools.numWaterSquares * GameTools.rowHeight /*&& frogY > GameTools.rowHeight*/ && System.currentTimeMillis() - lastLogHitTimer > 100)
			return true;
		return false;
	}
	
	//gives a random speed in between max and min speed constants
	private int getNextSpeed() {
		return rand.nextInt(MAX_SPEED - MINIMUM_SPEED)+MINIMUM_SPEED;
	}

}
