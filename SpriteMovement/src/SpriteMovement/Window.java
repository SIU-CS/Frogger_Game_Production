package SpriteMovement;


import java.awt.EventQueue;
import javax.swing.JFrame;

public class Window extends JFrame{
	public Window() {
	        
	        initUI();
	    }
    private void initUI() {
        add(new Board());
        
        setSize(800, 600);
        setResizable(false);
        setTitle("Moving sprite");
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
