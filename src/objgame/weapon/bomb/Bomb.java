package objgame.weapon.bomb;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.constant.EffectDefine;
import game.controller.Controller;
import game.map.Map;
import game.position.Position;
import objgame.ObjGame;
import objgame.character.Bomber;

public class Bomb extends ObjGame {
	private int length; // length of fire
	private long start; // time this bomb have been created
	private Bomber onwer;
	
	// 8 states of bomb: Make bomb more pretty
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private int stateBomb = 0; // state now of bomb
	// -> getImage() -> images[stateBomb]: each time has bomb image different
	
	@Deprecated
	public Bomb(Position pos, int length, Bomber p) {
		super(pos);
		this.length = length;
		this.start = System.currentTimeMillis();
		this.onwer = p;
		initImage();
	}
	
	public Bomb(int x, int y, int length, Bomber p) {
		super(x, y);
		this.length = length;
		this.start = System.currentTimeMillis();
		this.onwer = p;
		initImage();
	}

	@Override
	public void initImage() {
		try {
			BufferedImage tmp = null;
            for (int i = 0; i < EffectDefine.NUMBER_STATE_BOMB; i++) {
				tmp = ImageIO.read(new File("src/images/weapon/boom/boom" + (i + 1) + ".png"));
				images.add(tmp);
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return images.get(stateBomb++ % EffectDefine.NUMBER_STATE_BOMB);
	}

	public int getLength() {
		return length;
	}

	public long getStart() {
		return start;
	}

	public void explode(Controller c) {
		Map.matrix[pos.getY()][pos.getX()] = 0;
		onwer.setCountBomb(onwer.getCountBomb() - 1);
		c.getFires().add(new Fire(pos, length));
		
	}
}
