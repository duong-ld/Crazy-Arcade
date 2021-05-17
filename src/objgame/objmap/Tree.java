package objgame.objmap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.position.Position;

public class Tree extends ObjMap {

	public Tree(int x, int y) {
		super(x, y);
		initImage();
	}
	public Tree(Position pos) {
		super(pos);
		initImage();
	}

	@Override
	public void initImage() {
		try {
            BufferedImage tmp = ImageIO.read(new File("src/images/map/tree.png"));
            setImage(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
