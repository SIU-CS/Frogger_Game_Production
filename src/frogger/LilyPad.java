package frogger;

/*LilyPad Object : Static*/

public class LilyPad extends Entity{
	
	public LilyPad(int x, int y) {
    	setX(x);
    	setY(y);
        initCraft();
        centerImage();
    }
	private void initCraft() {
		setSprite(GameTools.lilyPadImagePath); 
	}
	
	private void centerImage(){
		//centers the image on the square
		y += (GameTools.rowHeight - getSpriteHeight())/2;
	}
}
