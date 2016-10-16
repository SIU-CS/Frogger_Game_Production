package frogger;

import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class ScrollBoard extends JScrollPane implements AdjustmentListener{

	private static final long serialVersionUID = 1L;
	private JScrollBar bar = getVerticalScrollBar();
	private Board board = new Board();
	public ScrollBoard() {
        setUpPane();
    }
	
		public void movePaneToBottom(){
			//moves you to the bottom and makes it to where you cant move up
	        Adjustable sb = getVerticalScrollBar();
	        boolean atBottom = atBottom(sb);

	        if (!atBottom) {
	               
	               bar.setValue(bar.getMaximum());
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
		
		public void setUpPane(){
			setVisible(true);
			 //disallows you to be able to scroll with the mouse wheel
	        setWheelScrollingEnabled(false);
	        //doesn't show the scroll bars
	        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
	        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
	        //freezes the use of the arrow keys to scroll
	        disableArrowKeys(getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
	       //these must remain in the order. IMPORTANT!!!!!
	        add(board);
	        setViewportView(board);
	        movePaneToBottom();
	        
		}
		
		private void disableArrowKeys(InputMap im) {
	    	String[] keystrokeNames = {"UP","DOWN","LEFT","RIGHT"};
	    	for(int i=0; i<keystrokeNames.length; ++i)
	    	im.put(KeyStroke.getKeyStroke(keystrokeNames[i]), "none");
	    	}
		//neggative is down
		//positive is up
		public void scroll(int far) throws Exception{
			
			if (bar.getValue() - far < bar.getMinimum()){
				bar.setValue(bar.getMinimum());
	        	throw new Exception("bar at the top");
	        }
			if((bar.getValue() - far > bar.getMaximum())){
				bar.setValue(bar.getMaximum());
				throw new Exception("bar at the bottom");
			}
			else{
				bar.setValue(bar.getValue()-far);
			}
		}
		//neggative is down
		//positive is up
		public void scrollSmooth(int far, int speed) throws Exception{
			
			if (bar.getValue() - far < bar.getMinimum()){
				scroll(bar.getValue()-bar.getMinimum(), speed);
	        	throw new Exception("bar at the top");
	        }
			else if (bar.getValue() + far > bar.getMaximum()){
				scroll(bar.getMaximum()- bar.getValue(), speed);
	        	throw new Exception("bar at the bottom");
			}
			else
			{
				scroll(far, speed);
			}
		}
		private void scroll(int far, int speed){
			int count = 0;
			int add = speed;
			if (far < 0){
				far *= -1;
				add *= -1;
			}
			while(count+speed<far)
			{
			bar.setValue(bar.getValue()-add);
			count += speed;
			}
			//corrections
			if(add < 0)
				bar.setValue(bar.getValue()+(far - count));
			else
				bar.setValue(bar.getValue()-(far - count));
		}
		
		public Board getBoard(){
			return board;
		}
		

		@Override
		public void adjustmentValueChanged(AdjustmentEvent arg0) {
			// TODO Auto-generated method stub
			
	}
		
}

