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

public class FileCheck {
	private ArrayList<String> fileList = GameTools.getFileNames();
	int counter = 0;
	@Test
	public void test() {
		
		for(int i=0;i<fileList.size();i++){
			ImageIcon img = new ImageIcon(fileList.get(i));
			System.out.println(fileList.get(i));
			
			if (img.getIconHeight()>0)
				System.out.println(counter);
				counter++;
		}
		
		assertEquals(counter,fileList.size()-1);
	}
}
