package froggerGameTest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import froggerGame.Dynamic;

public class DynamicTest {
	Dynamic d = new Dynamic();
	
	@Before
	public void setUpBefore() throws Exception {
	}

	@Test
	public void testSetModifierGetSpeed() {
		d.setModifier(2);
		assertEquals(20, d.getSpeed());
	} //passed
	
	@Test
	public void testSpawnMoveRightGetXYMoveRight() {
		d.setSpawn(100);
		d.setMoveRight(true);
		assertEquals(-100, d.getSpawningX());
		assertEquals(100, d.getSpawningY());
		assertEquals(true, d.getMoveRight());
	} //passed
	
	@Test
	public void testMove() {
		//assuming pardon is 100, translation is 10, modifier is 1!
		//a right moving object
		d.setMoveRight(true);
		
		//testing move to right
		d.move();
		assertEquals(-90, d.getX());
		
		//testing move to right when reaching out of bounds
		d.setX(900); //assuming board width is 800
		d.move();
		assertEquals(d.getSpawningX(), d.getX());
		
		//a left moving object
		d.setMoveRight(false);
		
		//testing move to left
		d.move();
		assertEquals(890, d.getX()); //assuming board width is 800
		
		//testing move to left when reaching out of bounds
		d.setX(-100); //assuming board width is 800
		d.move();
		assertEquals(d.getSpawningX(), d.getX());
		
	} //passed
}
