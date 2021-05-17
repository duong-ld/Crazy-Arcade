package objgame.character;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.constant.Constain;
import game.constant.Direction;
import game.constant.MapDefine;
import game.controller.Controller;
import game.map.Map;
import game.position.Position;
import objgame.weapon.bomb.Bomb;

public class Bomber extends Character {
	private int length = 1; // length of bomb
	private int maxBomb = 1; // max of bomb can set
	private int countBomb = 0; // count number of bomb
	private static int flagColor = 0; // use for initialize color player
	
	public Bomber(Position pos) {
		super(pos);
		initImage();
	}
	public Bomber(int x, int y) {
		super(x, y);
		initImage();
	}
	
	public int getCountBomb() {
		return countBomb;
	}
	
	public void setCountBomb(int countBomb) {
		this.countBomb = countBomb;
	}
	
	public int getMaxBomb() {
		return maxBomb;
	}
	
	public int getLength() {
		return length;
	}
	
	@Override
	public void initImage() {
		try {
			if (flagColor == 0){
				images[Direction.UP] = ImageIO.read(new File("src/images/bomber/red/up.png"));
		        images[Direction.DOWN] = ImageIO.read(new File("src/images/bomber/red/down.png"));
		        images[Direction.LEFT] = ImageIO.read(new File("src/images/bomber/red/left.png"));
		        images[Direction.RIGHT] = ImageIO.read(new File("src/images/bomber/red/right.png"));
		        flagColor ++;
			} else {
				images[Direction.UP] = ImageIO.read(new File("src/images/bomber/pink/up.png"));
		        images[Direction.DOWN] = ImageIO.read(new File("src/images/bomber/pink/down.png"));
		        images[Direction.LEFT] = ImageIO.read(new File("src/images/bomber/pink/left.png"));
		        images[Direction.RIGHT] = ImageIO.read(new File("src/images/bomber/pink/right.png"));
		        flagColor ++;
			}
		} catch (IOException e) {
	          e.printStackTrace();
		}
		setImage(images[Direction.DOWN]);
	}
	private boolean touchObjGame(int x, int y) {
		if (x < 0 || x >= Constain.NUMBER_BLOCK_WIDTH) {
			return true;
		}
		if (y < 0 || y >= Constain.NUMBER_BLOCK_HEIGHT) {
			return true;
		}
		if (Map.matrix[y][x] > 0) {
			return true;
		}
		if (Map.matrix[y][x] == MapDefine.BOMB) {
			return true;
		}
		if (Map.matrix[y][x] == MapDefine.BOX_EXPLODE) {
			return true;
		}
		if (Map.matrix[y][x] == MapDefine.ITEM_BOMB) {
			maxBomb++;
			Map.matrix[y][x] = 0; // bomber eat item and it disappear
			return false;
		}
		if (Map.matrix[y][x] == MapDefine.ITEM_BOMBSIZE) {
			length++;
			Map.matrix[y][x] = 0;
			return false;
		}
		
		return false;
	}
	
	@Override
	public void move(int direction) {
		this.direction = direction;
		int newX = pos.getX();
		int newY = pos.getY();
		
		switch (direction) {
		case Direction.UP: {
			newY = newY - 1;
			break;
		}
		case Direction.DOWN: {
			newY = newY + 1;
			break;
		}
		case Direction.LEFT: {
			newX = newX - 1;
			break;
		}
		case Direction.RIGHT: {
			newX = newX + 1;
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
		
		if (!touchObjGame(newX, newY)) {
			this.pos.setXY(newX, newY);
		}
		
		setImage(images[direction]);
	}
	
	@Override
	public void putBomb(Controller c) {
		if (Map.matrix[pos.getY()][pos.getX()] == 0
				&& isLiving
				&& countBomb < maxBomb) {
			
			c.getBombs().add(new Bomb(pos.getX(), pos.getY(), length, this));
			countBomb ++;
			
			Map.matrix[pos.getY()][pos.getX()] = MapDefine.BOMB;
		}
	}
}
