package froggerGame;


import java.awt.EventQueue;
import javax.swing.JFrame;

public class Window extends JFrame implements Constants{
	public Window() {
	        initUI();
	    }
    private void initUI() {
        add(new Board());
        
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setResizable(false);
        setTitle("Frogger");
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Window ex = new Window();
                ex.setVisible(true);
            }
        });
    }
}
