package frogger;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

//handles the controlling of the cars in a lane
public class CarController {
	Random rand = new Random();
	//max and min distance between to cars
	protected final int MAX_DIST_CARS = 256;			
	protected final int MINIMUM_DIST_CARS = 192;
	
	//max and min speed that can be obtained
	protected final int MAX_SPEED = 6;
	protected final int MINIMUM_SPEED = 2;
	
	//amount of resets until speed for a lane will be changed
	protected final int CHANGE_SPEED_AFTER = rand.nextInt(4)+2;
	
	/*An ArrayList of ArrayLists of Cars,
	 *ArrayList<ArrayList<Car>> acts as the lane, many cars will be in it
	 *ArrayList<Car> is you list of cars in that lane*/
	protected ArrayList<ArrayList<Car>> laneArray = new ArrayList<ArrayList<Car>>();
	//adds a new lane(adds an ArrayList<Car> to main ArrayList)
	public void addLane(int typeObject, int spawningY){
		int lastX = 100;
		int speed = getNextSpeed();
		int countObject = rand.nextInt(3)+1;
		ArrayList<Car> carArray = new ArrayList<Car>();
		if(typeObject == 1){
			while(countObject > 0){
				int distAhead = (MAX_DIST_CARS - MINIMUM_DIST_CARS) + MINIMUM_DIST_CARS;
	        	int typeVehicle = rand.nextInt(2)+1;
	        	Car newCar = addCar(speed, lastX + distAhead, spawningY, typeVehicle, true);
	        	lastX = lastX+distAhead;
	        	carArray.add(newCar);
	        	countObject--;
	        }
		}		
		if(typeObject == 2){
			while(countObject > 0){
				int distAhead = (MAX_DIST_CARS - MINIMUM_DIST_CARS) + MINIMUM_DIST_CARS;
	        	int typeVehicle = rand.nextInt(2)+1;
	        	Car newCar = addCar(speed, lastX + distAhead, spawningY, typeVehicle, false);
	        	lastX = lastX+distAhead;
	        	carArray.add(newCar);
	        	countObject--;
	        }
		}
		System.out.println("Lane: " + laneArray.size());
		laneArray.add(carArray);	
	}
	//adds a car and returns it
	private Car addCar(int speed, int spawningX, int spawningY, int typeVehicle, boolean moveRight){
		Car newCar = new Car(speed, spawningX, spawningY, typeVehicle, moveRight);
		return newCar;
	}
	//draws the initialized cars
	public void drawCars(Graphics g){
		for(ArrayList<Car> lane : laneArray){
			for(Car car : lane){
				car.drawSprite(g);
			}
		}
	}
	//moves the initialized cars
	public void moveCars(){
		for(ArrayList<Car> lane : laneArray){
			for(Car car : lane){
					if(car.getNumResets() > CHANGE_SPEED_AFTER)
						car.setNumResets(getNextSpeed());
					car.move();	
			}
		}
	}
	//determines if given Rectangle is intersecting any cars
	public boolean collisionDetection(Rectangle bounds){
		for(ArrayList<Car> lane : laneArray){
			for(Car car : lane){
				Rectangle carBound = car.getBounds();
				if(bounds.intersects(carBound))
					return true;
			}
		}
		return false;
    }
	
	//gives a random speed in between max and min speed constants
	private int getNextSpeed() {
		return rand.nextInt(MAX_SPEED - MINIMUM_SPEED)+MINIMUM_SPEED;
	}

}
