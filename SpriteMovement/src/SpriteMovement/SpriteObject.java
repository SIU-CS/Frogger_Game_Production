package SpriteMovement;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class SpriteObject{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;
	protected Image image;
	
	public SpriteObject(int y){
		this.y=y;
		visible=true;
	}
	
	protected void loadImage(String imageName){
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	
	protected void getImageDimensions(){
		width=image.getWidth(null);
		height=image.getHeight(null);
	}
	public Image getImage(){
		return image;
	}
	public boolean isVisible(){
		return visible;
	}
	public void setVisible(Boolean v){
		visible=v;
	}
}
