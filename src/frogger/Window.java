package frogger;

import javax.swing.JFrame;

public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	private ScrollBoard scrlBar;
	private Board board;
	public Window() 
	{
	        initUI();
	    }
    private void initUI() {
    	scrlBar = new ScrollBoard();
        add(scrlBar);
        board = scrlBar.getBoard();
        setSize(GameTools.boardWidth, GameTools.boardHeight);
        setResizable(false);
        setTitle(" sprite");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public int getWindowWidth(){
    	return getWidth();
    }
    public int getWindowHeight(){
    	return getHeight();
    }
    public ScrollBoard getScrollBar(){
    	return scrlBar;
    }
    public Board getBoard(){
    	return board;
    }
    
}
