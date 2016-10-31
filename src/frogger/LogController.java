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
	protected final int MAX_SPEED = 6;
	protected final int MINIMUM_SPEED = 2;
	
	//amount of resets until speed for a lane will be changed
	protected final int CHANGE_SPEED_AFTER = rand.nextInt(4)+2;
	
	/*An ArrayList of ArrayLists of Logs,
	 *ArrayList<ArrayList<Log>> acts as the lane, many cars will be in it
	 *ArrayList<Log> is you list of cars in that lane*/
	protected ArrayList<ArrayList<Log>> laneArray = new ArrayList<ArrayList<Log>>();
	//adds a new lane(adds an ArrayList<Log> to main ArrayList)
	public void addLane(int typeObject, int spawningY){
		int lastX = 100;
		int speed = getNextSpeed();
		int countObject = rand.nextInt(3)+1;
		ArrayList<Log> logArray = new ArrayList<Log>();
		if(typeObject == 3){
			while(countObject > 0){
				boolean logMove = true;
				int distAhead = (MAX_DIST_LOGS - MINIMUM_DIST_LOGS) + MINIMUM_DIST_LOGS;
	        	Log newLog = addLog(speed, lastX + distAhead, spawningY, logMove);
	        	lastX = lastX+distAhead;
	        	logArray.add(newLog);
	        	logMove = !logMove;
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
	public Log collisionDetection(Rectangle bounds){
		for(ArrayList<Log> lane : laneArray){
			for(Log log : lane){
				Rectangle logBound = log.getBounds();
				if(bounds.intersects(logBound))
					return log;
			}
		}
		return null;
    }
	
	//gives a random speed in between max and min speed constants
	private int getNextSpeed() {
		return rand.nextInt(MAX_SPEED - MINIMUM_SPEED)+MINIMUM_SPEED;
	}

}