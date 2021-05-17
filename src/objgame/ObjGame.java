package objgame;

import java.awt.image.BufferedImage;

import game.position.Position;

public abstract class ObjGame {
	protected Position pos;
	protected BufferedImage image;
	
	public ObjGame(Position pos) {
		this.pos = pos;
	}
	
	public ObjGame(int x, int y) {
		this.pos = new Position(x, y);
	}
	
	public abstract void initImage();
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
}
