package frogger;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Background extends JPanel{

	private static final long serialVersionUID = 1L;
	
    private Image image;
    
 public Background() {
        
        initCraft();
        
    }
    
    private void initCraft() {
        
        ImageIcon ii = new ImageIcon();

        image = ii.getImage(); 
        
    }
    
    public void setImage(String image){
    	ImageIcon ii = new ImageIcon(image);

        this.image = ii.getImage();
        GameTools.boardWidth = ii.getIconWidth();
        GameTools.boardImageLegth = ii.getIconHeight();
    }
    
	public Image getImage(){
		return image;
	}
	
	public int getMaxHeight(){
		return image.getHeight(this);
	}
	
	public int getMaxWidth(){
		return image.getWidth(this);
	}

    public void drawBackground(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(getImage(), 0, 0, this);
    }
}
