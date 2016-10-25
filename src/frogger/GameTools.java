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
	public static int numCols = 8;
	public static int numRows = 20;
    //0 = null, 1 = car, 2 = log/alligator, 3 = LilyPads
	public static int[] BOARD_POSITIONS = {0,0,0,1,1,1,1,0,0,0,1,1,1,1,0,0,2,2,2,3};
	//water squares always start from the top
	public static int numWaterSquares = 4;
	
	public final static String carImagePath = "car.png";
	public final static String backgroundImagePath = "map.png";
	public final static String frogImagePath = "frog.png";
	public final static String logImagePath = "log.png";
	public final static String lilyPadImagePath = "lilypad.png";
}
