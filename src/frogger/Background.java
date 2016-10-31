package frogger;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*Background object handles all information for that background*/

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
    /*Sets the Image for the background
     *sends to GameTools all the information needed from the background*/
    public void setImage(String image){
    	ImageIcon ii = new ImageIcon(image);
        this.image = ii.getImage();
        GameTools.boardWidth = ii.getIconWidth();
        GameTools.boardImageLength = ii.getIconHeight();
        GameTools.columnWidth = GameTools.boardWidth/GameTools.numCols;
        GameTools.rowHeight = GameTools.boardImageLength/GameTools.numRows;
    }
    //gives the background image
	public Image getImage(){
		return image;
	}
	//gives the height of the background
	public int getMaxHeight(){
		return image.getHeight(this);
	}
	//gives the width of the background
	public int getMaxWidth(){
		return image.getWidth(this);
	}
	//draws the background
    public void drawBackground(Graphics g){
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(getImage(), 0, 0, this);
    }
}
