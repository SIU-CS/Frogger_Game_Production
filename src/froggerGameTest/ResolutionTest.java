package froggerGameTest;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.junit.Test;

import com.sun.media.sound.Toolkit;

public class ResolutionTest {

	@Test
	public void test() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		boolean screenIsBigEnough = (width>=800 && height >=600);
		assertEquals(screenIsBigEnough,true);
	}
	
	

}
