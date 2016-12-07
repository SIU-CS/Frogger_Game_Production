package frogger;

/*Car Object : Dynamic*/

public class Car extends Dynamic{
	
	public Car(int speed, int spawningX, int spawningY, int type, boolean moveRight) {
		setSpeed(speed);
		setMoveRight(moveRight);
		setSpawnX(spawningX);
		setSpawnY(spawningY);
		initCar(type);
	}
	public void initCar(int type){
		String typePath;
		
		//which vehicle to initialize
		switch(type){
			//car
			default:
			case 1:
				setOffset(64);
				if(getMoveRight())
					typePath= GameTools.carRightImagePath;
				else
					typePath= GameTools.carLeftImagePath;
				break;
			//truck
			case 2:
				if(getMoveRight())
					typePath= GameTools.truckRightImagePath;
				else
					typePath= GameTools.truckLeftImagePath;
				break;
		}
		setSprite(typePath);
	}
}
