package objgame.effect;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.position.Position;
import objgame.ObjGame;

public abstract class Effect extends ObjGame {
	
	protected ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	protected int stateEffect = 0;

	@Deprecated
	public Effect(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public Effect(Position pos) {
		super(pos);
	}

}
