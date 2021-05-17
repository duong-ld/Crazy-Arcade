package game.items;

import game.position.Position;
import objgame.ObjGame;

public abstract class Item extends ObjGame {
	private final long start = System.currentTimeMillis();
	
	public Item(Position pos) {
		super(pos);
	}
	public Item(int x, int y) {
		super(x, y);
	}
	
	public long getStart() {
		return start;
	}
}
