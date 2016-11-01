package frogger;
import javax.swing.JFrame;

/*Window class initializes the main window, the ScrollBoard and the gameBoard.
 *handles all interactions with the boards and window.*/

public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	private ScrollBoard gameScroll;
	private Board gameBoard;
	
	public Window(){
		initUI();
	}
    private void initUI(){
    	gameBoard = new Board();
    	gameScroll = new ScrollBoard(gameBoard);
    	gameBoard.setKeyboard(gameScroll);
        add(gameScroll);
        setSize(GameTools.boardWidth, GameTools.boardHeight);
        setResizable(false);
        setTitle("Frogger T4");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
    	Window ex = new Window();
    	ex.setVisible(true);
    }
}
