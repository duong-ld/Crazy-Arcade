package objgame.effect;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.position.Position;

public class DeadEffect extends Effect {

	public static final int NUMBER_STATE_DEAD = 16;

	@Deprecated
	public DeadEffect(int x, int y) {
		super(x, y);
		initImage();
		// TODO Auto-generated constructor stub
	}
	
	public DeadEffect(Position pos) {
		super(pos);
		initImage();
	}
	
	@Override
	public void initImage() {
		try {
			BufferedImage tmp = null;
            for (int i = 0; i < DeadEffect.NUMBER_STATE_DEAD; i++) {
				tmp = ImageIO.read(new File("src/images/effect/dead/" + (i + 1) + ".png"));
				images.add(tmp);
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public BufferedImage getImage() {
		return images.get(stateEffect++ % DeadEffect.NUMBER_STATE_DEAD);
	}
	
}
