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
	private Board gameBoard;
	
	public ScrollBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
		setUpPane();
    }
	
	//moves you to the bottom and makes it to where you cant move up
	public void movePaneToBottom(){	
        Adjustable sb = getVerticalScrollBar();
        boolean atBottom = atBottom(sb);
        
	    if (!atBottom){
	    	bar.setValue(bar.getMaximum());
	        bar.setValue(bar.getMaximum());
	    }
    }
	private boolean atBottom(Adjustable sb) {
		int val = sb.getValue();
		int lowest = val + sb.getVisibleAmount();
	    int maxVal = sb.getMaximum();
	    boolean atBottom = maxVal == lowest;
		return atBottom;
	}
	
	public void setUpPane(){
		setVisible(true);
		setWheelScrollingEnabled(false); //disallows you to be able to scroll with the mouse wheel
		//doesn't show the scroll bars
	    setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
	    setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
	    disableArrowKeys(getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)); //freezes the use of the arrow keys to scroll
	    
	    //these must remain in the order. IMPORTANT!!!!!
        add(gameBoard);
        setViewportView(gameBoard);
	    movePaneToBottom();        
	}
	
	/*Scrolls the board
	 *Negative is scrolling down
	 *Positive is scrolling up*/
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
	/*Scrolls the board smoothly
	 *Negative is scrolling down
	 *Positive is scrolling up*/
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
		scroll(far, speed);
	}
	/*Scrolls the board
	 *used by scrollSmooth and scrollTo to do the scrolling*/
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

	/*Disables the use of arrow keys for scrolling the board.*/
	private void disableArrowKeys(InputMap im) {
    	String[] keystrokeNames = {"UP","DOWN","LEFT","RIGHT"};
    	for(int i=0; i<keystrokeNames.length; ++i)
	   	im.put(KeyStroke.getKeyStroke(keystrokeNames[i]), "none");
	}
	/*Scrolls to a specific position.*/
	public void scrollTo(int spot) throws Exception{		
		if (bar.getValue() - spot < bar.getMinimum()){
			bar.setValue(bar.getMinimum());
        	throw new Exception("bar at the top");
        }
		if((bar.getValue() - spot > bar.getMaximum())){
			bar.setValue(bar.getMaximum());
			throw new Exception("bar at the bottom");
		}
		else{
			bar.setValue(spot);
		}
	}
	
	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
	}
		
}

