package frogger;

import static org.junit.Assert.*;

import org.junit.Test;

public class WindowSize {
	public void setUp(){
		Background game = new Background();
	}
	@Test
	public void test() {
		assertEquals(GameTools.boardHeight,600);
		assertEquals(GameTools.boardWidth,0);
		assertEquals(GameTools.columnWidth,0);
		assertEquals(GameTools.rowHeight,0);
	}
	
	
}
