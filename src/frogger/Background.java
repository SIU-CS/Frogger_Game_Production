package frogger;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;

import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;


public class Background extends JLabel implements AdjustmentListener {

	private static final long serialVersionUID = 1L;
	private static JScrollPane pane = new JScrollPane();
	private static ImageIcon Icon = new ImageIcon();

	
	private Background(String file){
		Icon = new ImageIcon(file);
		setIcon(Icon);
	}
	public static int getIconWidth(){
		return Background.Icon.getIconWidth();
	}
	
	public static int getIconHeight(){
		return Background.Icon.getIconHeight();
	}
	
	public static void setBackground(String file){
		pane.setViewportView(new Background(file));
		pane.getVerticalScrollBar().setMaximum(Icon.getIconHeight());
		
	}
	
	public static void movePaneToBottom(){
		//moves you to the bottom and makes it to where you cant move up
        Adjustable sb = pane.getVerticalScrollBar();
        boolean atBottom = atBottom(sb);

        if (!atBottom) {
                      JScrollBar bar = pane.getVerticalScrollBar();
                      bar.setValue(bar.getMaximum());
                  }
	}
	private static boolean atBottom(Adjustable sb) {
		int val = sb.getValue();
        int lowest = val + sb.getVisibleAmount();
        int maxVal = sb.getMaximum();
        boolean atBottom = maxVal == lowest;
		return atBottom;
	}
	
	public static void setUpPane(){
		 //disallows you to be able to scroll with the mouse wheel
        pane.setWheelScrollingEnabled(false);
        //doesn't show the scroll bars
        pane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
        //freezes the use of the arrow keys to scroll
        disableArrowKeys(pane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
	}
	
	private static void disableArrowKeys(InputMap im) {
    	String[] keystrokeNames = {"UP","DOWN","LEFT","RIGHT"};
    	for(int i=0; i<keystrokeNames.length; ++i)
    	im.put(KeyStroke.getKeyStroke(keystrokeNames[i]), "none");
    	}
	
	public static void scrollUp(int far) throws Exception{
		JScrollBar bar = pane.getVerticalScrollBar();
		if (bar.getValue() - far < bar.getMinimum()){
        	throw new Exception("further than top");
        }
		else
			bar.setValue(bar.getValue()-far);
	}
	public static void scrollUpSmooth(int far, int sleep) throws Exception{
		JScrollBar bar = pane.getVerticalScrollBar();
		if (bar.getValue() - far < bar.getMinimum()){
        	throw new Exception("further than top");
        }
		else
		{
			int count = 0;
			while(count<far)
			{
			bar.setValue(bar.getValue()-1);
			Thread.sleep(sleep);
			count++;
			}
		}
	}
	
	public static void scrollDown(int far) throws Exception{
		JScrollBar bar = pane.getVerticalScrollBar();
		if (bar.getValue() + far > bar.getMaximum()){
        	throw new Exception("further than bottom");
        }
		else
			bar.setValue(bar.getValue()+ far);
	}
	
	public static void scrollDownSmooth(int far, int sleep) throws Exception{
		JScrollBar bar = pane.getVerticalScrollBar();
		if (bar.getValue() + far > bar.getMaximum()){
        	throw new Exception("further than bottom");
        }
		else
		{
			int count = 0;
			while(count<far)
			{
			bar.setValue(bar.getValue()+1);
			Thread.sleep(sleep);
			count++;
			}
		}
	}
	

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public static JScrollPane getPane() {
		return pane;
	}


	public static void setPane(JScrollPane pane) {
		Background.pane = pane;
	}


}
