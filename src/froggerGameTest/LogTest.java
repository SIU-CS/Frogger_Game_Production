package froggerGameTest;

import static org.junit.Assert.*;
import java.awt.Image;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test; 

import frogger.GameTools;

import frogger.Log;

public class LogTest {
	private Log log;
	@Before
	public void setUpBefore() throws Exception {
	}

	@Test
	public void testSetUpRightLog() {
		//sets up the testie
		log = new Log(1, 0, 0, true);
		//tests if it is moving the correct direction
		assertEquals(true,log.getMoveRight());
	}
	@Test
	public void testLogImage(){
		log = new Log(1, 0, 0, true);
		
		ImageIcon ii = new ImageIcon(GameTools.logImagePath);
		Image logImage = ii.getImage();
		//tests if the images are correct
		assertEquals(logImage,log.getSprite());
	}
	@Test
	public void testSetUpLeftLog() {
		//sets up the testie
		log = new Log(1, 0, 0, false);
		//tests if it is moving the correct direction
		assertEquals(false,log.getMoveRight());
	}
	@Test
	public void testLogSpeed(){
		//normal functionality
		log = new Log(10, 0, 0, true);
		assertEquals(10,log.getSpeed());
		//checks if the speed is zero
		log = new Log(0, 0, 0, true);
		assertEquals(0,log.getSpeed());
		//checks if speed is less than zero
		log = new Log(-1, 0, 0, true);
		assertFalse(-1==log.getSpeed());
	}

}
