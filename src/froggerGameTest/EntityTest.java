package froggerGameTest;

import static org.junit.Assert.*;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import frogger.Entity;

public class EntityTest {
	static Entity e = new Entity();
	static Image sprite;
	
	@Before
	public void setUpBefore() throws Exception {
		ImageIcon ii = new ImageIcon("src/frog.png");
		sprite = ii.getImage();
		e.setSprite("src/frog.png");
		e.setX(0);
		e.setY(0);
	}
	
	@Test
	public void testSetGetVisible() {
		e.setVisible(false);
		assertFalse(e.getVisible());
	}//passed
	
	@Test
	public void testSetGetXY() {
		e.setX(10);
		e.setY(15);
		assertEquals(10, e.getX());
		assertEquals(15, e.getY());
	}//passed

	@Test
	public void testGetBounds() {
		Rectangle r = e.getBounds();
		Rectangle r2 = new Rectangle(0,0, e.getSpriteWidth(), e.getSpriteHeight());
		assertEquals(r2,r);
	}//passed
	
	@Test
	public void testGetCheckBoundaries() {
		//test an inbounds object on x access
		assertFalse(e.checkBoundaries('x', 0, 0));
		//test an inbounds object on y access
		assertFalse(e.checkBoundaries('y', 0, 0));
		//test an out of bounds object on x axis
		assertTrue(e.checkBoundaries('x', 100, 0));
		//test an out of bounds object with a pardon
		//should be considered inbounds
		assertFalse(e.checkBoundaries('x', -100, 100));
		//test an out of bounds object on y axis
		assertTrue(e.checkBoundaries('y', -100, 0));			
	}//passed
	
	@Test
	public void testSetGetSprite(){
		String sprite2 = "src/frog.png";
		e.setSprite(sprite2);
		assertEquals(sprite, e.getSprite());	
	}//passed

}
