package frogger;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Test;

public class FileCheck {
	String[] fileList = {"car.png","carLeft.png","carRight.png","frogDown.png","frogLeft.png","frogRight.png","frogUp.png","lilypad.png","log.png","lose.png","Map_Grid.png","map.png","truckLeft.png","truckRight.png","win.png"};
	int counter = 0;
	@Test
	public void test() {
		
		for(int i=0;i<15;i++){
			ImageIcon img = new ImageIcon(fileList[i]);
			System.out.println(fileList[i]);
			
			if (img.getIconHeight()>0)
				System.out.println(counter);
				counter++;
		}
		
		assertEquals(counter,15);
	}
}
