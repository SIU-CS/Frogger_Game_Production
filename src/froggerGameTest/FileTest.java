package froggerGameTest;

import static org.junit.Assert.*;

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
		
		assertEquals(counter,fileList.length);
	}
}
