package objgame.objmap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.position.Position;

public class Brick extends ObjMap {

	public Brick(int x, int y) {
		super(x, y);
		initImage();
	}
	
	public Brick(Position pos) {
		super(pos);
		initImage();
	}

	@Override
	public void initImage() {
		try {
            BufferedImage tmp = ImageIO.read(new File("src/images/map/brick.png"));
            setImage(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
