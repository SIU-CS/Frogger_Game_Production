package frogger;

public class GameTools {
	public static int boardHeight = 600;
	//determined at run time in the background class
	//*************
	public static int boardWidth;
	public static int columnWidth;
	public static int rowHeight;
	public static int boardImageLength;
	//***********
	public static int numCols = 12;
	public static int numRows = 20;
	//bottom to the top in rows
    //0 = null, 1 = car, 2 = log/alligator, 3 = LilyPads
	public static int[] BOARD_POSITIONS = {0,0,0,1,1,1,1,0,0,0,1,1,1,1,0,0,2,2,2,3};
	//water squares always start from the top doesn't include the top row
	public static int numWaterSquares = 4;
	
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
}
