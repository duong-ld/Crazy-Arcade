package game.constant;

public class Constain {
	public static final int NUMBER_BLOCK_WIDTH = 15;
	public static final int NUMBER_BLOCK_HEIGHT = 13;
	public static final int SCALE_WIDTH = 50;
	public static final int SCALE_HIGHT = 60;
	
	public static final int SCREEN_WIDTH = NUMBER_BLOCK_WIDTH * SCALE_WIDTH;
	public static final int SCREEN_HEIGHT = NUMBER_BLOCK_HEIGHT * SCALE_HIGHT;
	
	public static final long TIME_BOMB = 2000; // time a bomb alive millisecond
	public static final long TIME_FIRE = 200;
	
	private Constain() {
		// none
	}
}
