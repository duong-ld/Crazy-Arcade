package objgame.objmap;

import game.position.Position;
import objgame.ObjGame;

//one object in the map like: brick, rock, ...
public abstract class ObjMap extends ObjGame {
	
	public ObjMap(Position pos) {
		super(pos);
	}
	
	public ObjMap(int x, int y) {
		super(x, y);
	}
}
