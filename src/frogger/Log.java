package frogger;

/*Log Object : Dynamic*/

public class Log extends Dynamic{
    
    public Log(int speed, int spawningX, int spawningY, boolean moveRight) {
    	setSpeed(speed);
    	setMoveRight(moveRight);
    	setSpawnX(spawningX);
    	setSpawnY(spawningY);
        initLog();
    }
    private void initLog() {
		setSprite(GameTools.logImagePath);
    }
}

