package game.items;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.position.Position;

public class ItemBomb extends Item {

	public ItemBomb(int x, int y) {
		super(x, y);
		initImage();
		// TODO Auto-generated constructor stub
	}
	@Deprecated
	public ItemBomb(Position pos) {
		super(pos);
		initImage();
	}

	@Override
	public void initImage() {
		try {
            BufferedImage tmp = ImageIO.read(new File("src/images/items/item_bomb.png"));
            setImage(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
