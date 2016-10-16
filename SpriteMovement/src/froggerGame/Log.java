package froggerGame;

import javax.swing.ImageIcon;

public class Log extends Dynamic{
    protected final String log = ("src/log.png");
    protected final int modifier = 1;
    
    public Log(int spawningY, boolean moveRight) {
    	setSpawn(spawningY);
    	setMoveRight(moveRight);
        initCraft();
    }
    private void initCraft() {
    	ImageIcon ii = new ImageIcon(log);
		setSprite(ii.getImage());
    }
}

