package frogger;

import javax.swing.JFrame;
import javax.swing.ScrollPaneLayout;

public class World {
	private static JFrame frame = new JFrame();
	
	public static void setBackground(String file)
    {
		Background.setBackground(file);
		Background.movePaneToBottom();
		//sets up the scrollPane for the frogger game ( no scroll bars ect)
		Background.setUpPane();
		frame.setContentPane(Background.getPane());
        frame.setSize(Background.getIconWidth() + 35, 600);
        //this is the only layout usable
        frame.getContentPane().setLayout(new ScrollPaneLayout());
    }
	public static void setUpFrame()
	{
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible(true);
	}
	public static void setFrameHeight(int height) {
		frame.setSize(frame.getWidth(), height);
	}
	public static void setPaddingOnImage(int pad) throws Exception{
		if (pad >= 0)
			frame.setSize(Background.getIconWidth() + pad, frame.getHeight());
		else
			throw new Exception("padding cant be less than zero");
	}
}
