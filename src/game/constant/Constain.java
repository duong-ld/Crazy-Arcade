package game.constant;

public class Constain {
	public static final int NUMBER_BLOCK_WIDTH = 15;
	public static final int NUMBER_BLOCK_HEIGHT = 13;
	public static final int SCALE_WIDTH = 45;
	public static final int SCALE_HIGHT = 54;
	
	public static final int SCREEN_WIDTH = NUMBER_BLOCK_WIDTH * SCALE_WIDTH;
	public static final int SCREEN_HEIGHT = NUMBER_BLOCK_HEIGHT * SCALE_HIGHT;
	
	public static final long TIME_BOMB = 5000; // time a bomb alive millisecond
	public static final long TIME_FIRE = 5000;
	public static final long TIME_MOVE_MONSTER = 500;

	
	private Constain() {
		// none
	}
}
