package objgame.effect;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.constant.EffectDefine;
import game.position.Position;

public class PowerEffect extends Effect {

	@Deprecated
	public PowerEffect(int x, int y) {
		super(x, y);
		initImage();
		// TODO Auto-generated constructor stub
	}
	
	public PowerEffect(Position pos) {
		super(pos);
		initImage();
	}
	
	@Override
	public void initImage() {
		try {
			BufferedImage tmp = null;
            for (int i = 0; i < EffectDefine.NUMBER_STATE_POWER; i++) {
				tmp = ImageIO.read(new File("src/images/effect/power/" + (i + 1) + ".png"));
				images.add(tmp);
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public BufferedImage getImage() {
		return images.get(stateEffect++ % EffectDefine.NUMBER_STATE_POWER);
	}
	
}