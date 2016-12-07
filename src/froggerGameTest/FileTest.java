package frogger;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Test;

import frogger.GameTools;

public class FileTest {
	private String fileList[] = GameTools.getFileNames();
	int counter = 0;
	@Test
	public void test() {
		
		for(int i=0;i<fileList.length;i++){
			ImageIcon img = new ImageIcon(fileList[i]);
			System.out.println(fileList[i]);
			
			if (img.getIconHeight()>0)
				System.out.println(counter);
				counter++;
		}
		
		assertEquals(counter,fileList.length-1);
	}
}
