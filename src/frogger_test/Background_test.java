package frogger_test;

import javax.swing.JFrame;
import javax.swing.ScrollPaneLayout;

import frogger.Background;

public class Background_test {
	private static JFrame frame = new JFrame();
	public static void main(String[] args)
    {
		Background.setBackground("long.png");
		Background.movePaneToBottom();
		//sets up the scrollPane for the frogger game ( no scroll bars ect)
		Background.setUpPane();
		frame.setContentPane(Background.getPane());
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(Background.getIconWidth() + 35, 600);
        frame.setVisible(true);
        //this is the only layout usable
        frame.getContentPane().setLayout(new ScrollPaneLayout());
        
      
    }
}