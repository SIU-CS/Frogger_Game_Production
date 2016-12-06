package froggerGameTest;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.junit.Before;
import frogger.Car;

import frogger.GameTools;

public class CarTest {
	//sets up the car and truck before the class
	private Car car;
	private Car truck;
	@Before
	public void setUpBefore() throws Exception {
		
	}

	@Test
	public void testSetUpRightCar() {
		//sets up the testie
		car = new Car(1, 0, 0, 1, true);
		//sets up the tester
		ImageIcon ii = new ImageIcon(GameTools.carRightImagePath);
		Image carRightImage = ii.getImage();
		//tests if the images are correct
		assertEquals(carRightImage,car.getSprite());
		//tests if it is moving the correct direction
		assertEquals(true,car.getMoveRight());
	}
	@Test
	public void testSetUpLeftCar() {
		//sets up the testie
		car = new Car(1, 0, 0, 1, false);
		//sets up the tester
		ImageIcon ii = new ImageIcon(GameTools.carLeftImagePath);
		Image carLeftImage = ii.getImage();
		//tests if the images are correct
		assertEquals(carLeftImage,car.getSprite());
		//tests if it is moving the correct direction
		assertEquals(false,car.getMoveRight());
	}
	@Test
	public void testSetUpRightTruck() {
		//sets up the testie
		truck = new Car(1, 0, 0, 2, true);
		//sets up the tester
		ImageIcon ii = new ImageIcon(GameTools.truckRightImagePath);
		Image truckRightImage = ii.getImage();
		//tests if the images are correct
		assertEquals(truckRightImage,truck.getSprite());
		//tests if it is moving the correct direction
		assertEquals(true,truck.getMoveRight());
	}
	@Test
	public void testSetUpLeftTruck() {
		//sets up the testie
		truck = new Car(1, 0, 0, 2, false);
		//sets up the tester
		ImageIcon ii = new ImageIcon(GameTools.truckLeftImagePath);
		Image truckLeftImage = ii.getImage();
		//tests if the images are correct
		assertEquals(truckLeftImage,truck.getSprite());
		//tests if it is moving the correct direction
		assertEquals(false,truck.getMoveRight());
	}
		
}
