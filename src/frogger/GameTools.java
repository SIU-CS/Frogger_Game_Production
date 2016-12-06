package frogger;

import java.util.ArrayList;

/*GameTools class contains all variable information for the game.*/

public class GameTools {
	public static int boardHeight = 600;
	//determined at run time in the background class
	//*************//
	public static int boardWidth = 0;
	public static int columnWidth = 0;
	public static int rowHeight = 0;
	public static int boardImageLength = 0;
	//*************//
	
	public static int numCols = 12;
	public static int numRows = 20;
	
	/*bottom to the top in rows
    *0 = null, 1 = car(left-right), 1 = car(right-left), 3 = log/alligator, 4 = LilyPads
    *boardPositions holds the information of the given map in integer form*/
	public static final int[] level1_boardPositions = {0,0,0,1,1,2,2,0,0,0,1,1,2,2,0,0,3,3,3,4};
	
	//water squares always start from the top doesn't include the top row
	public static final int numWaterSquares = 4;
	
	//Image paths of all the objects at use
	public final static String carLeftImagePath = "carLeft.png";
	public final static String carRightImagePath = "carRight.png";
	public final static String truckLeftImagePath = "truckLeft.png";
	public final static String truckRightImagePath = "truckRight.png";
	public final static String backgroundImagePath = "map.png";
	public final static String frogUpImagePath = "frogUp.png";
	public final static String frogDownImagePath = "frogDown.png";
	public final static String frogRightImagePath = "frogRight.png";
	public final static String frogLeftImagePath = "frogLeft.png";
	public final static String logImagePath = "log.png";
	public final static String lilyPadImagePath = "lilypad.png";
	
	private static String fileNames[] = {carLeftImagePath,carRightImagePath,truckLeftImagePath,truckRightImagePath,
			backgroundImagePath,frogUpImagePath,frogDownImagePath,frogRightImagePath,frogLeftImagePath,logImagePath,lilyPadImagePath};
	
	public static String[] getFileNames(){
		return fileNames;
	}
}
