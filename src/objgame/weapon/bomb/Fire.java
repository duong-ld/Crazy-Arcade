package objgame.weapon.bomb;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.constant.Constain;
import game.constant.Direction;
import game.constant.EffectDefine;
import game.constant.FireDefine;
import game.constant.MapDefine;
import game.map.Map;
import game.position.Position;
import objgame.ObjGame;

public class Fire extends ObjGame {
	// 9 state of fire: define in FireDefine.class
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private long start; // time start create
	private int maxLength; // max length of fire
	private int[] lengths = {-1, -1, -1, -1}; // length of each direction
	
	public Fire(Position pos, int maxLength) {
		super(pos);
		this.maxLength = maxLength;
		start = System.currentTimeMillis();
		initImage();
		setLengths();
	}
	
	public Fire(int x, int y, int maxLength) {
		super(x, y);
		this.maxLength = maxLength;
		start = System.currentTimeMillis();
		initImage();
		setLengths();
	}

	@Override
	public void initImage() {
		try {
			BufferedImage tmp = null;
            for (int i = 0; i < EffectDefine.NUMBER_STATE_FIRE; i++) {
            	tmp = ImageIO.read(new File("src/images/weapon/boom/fire/" + i + ".png"));
            	images.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public BufferedImage getImage() {
		return images.get(FireDefine.CENTER);
	}
	
	// find lengths for 4 direction
	private void setLengths() {
		for (int i = 1; i <= maxLength; i++) {
			int x = pos.getX();
			int y = pos.getY();
			
			
			if (lengths[Direction.LEFT] == -1) {
				if (x - i < 0 || Map.matrix[y][x - i] > 0) {
					lengths[Direction.LEFT] = i - 1;
				} else if (Map.matrix[y][x - i] == MapDefine.BOX_EXPLODE) {
					lengths[Direction.LEFT] = i;
				}
			}
			
			if (lengths[Direction.RIGHT] == -1) {
				if (x + i >= Constain.NUMBER_BLOCK_WIDTH || Map.matrix[y][x + i] > 0) {
					lengths[Direction.RIGHT] = i - 1;
				} else if (Map.matrix[y][x + i] == MapDefine.BOX_EXPLODE) {
					lengths[Direction.RIGHT] = i;
				}
			}
			
			if (lengths[Direction.UP] == -1) {
				if (y - i < 0 || Map.matrix[y - i][x] > 0) {
					lengths[Direction.UP] = i - 1;
				} else if (Map.matrix[y - i][x] == MapDefine.BOX_EXPLODE) {
					lengths[Direction.UP] = i;
				}
			}
			
			if (lengths[Direction.DOWN] == -1) {
				if (y + i >= Constain.NUMBER_BLOCK_HEIGHT || Map.matrix[y + i][x] > 0) {
					lengths[Direction.DOWN] = i - 1;
				} else if (Map.matrix[y + i][x] == MapDefine.BOX_EXPLODE) {
					lengths[Direction.DOWN] = i;
				}
			}
		}
		// 4 direction
		for (int i = 0; i < 4; i++) {
			if (lengths[i] == -1) {
				lengths[i] = maxLength;
			}
		}
	}

	public ArrayList<BufferedImage> getImages() {
		return images;
	}

	public int[] getLengths() {
		return lengths;
	}
	
	public long getStart() {
		return start;
	}

}
