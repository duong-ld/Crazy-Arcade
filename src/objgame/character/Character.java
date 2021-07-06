package objgame.character;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.controller.Controller;
import game.position.Position;
import objgame.ObjGame;
import objgame.effect.DeadEffect;
import objgame.effect.Effect;
import objgame.effect.PowerEffect;

public abstract class Character extends ObjGame {
	
	protected BufferedImage[] images = {null, null, null, null}; // 4 state of character
	protected boolean isLiving; // if still alive: isLiving = true
	protected int direction;
	protected Effect effect = null;


	public Character(Position pos) {
		super(pos);
		isLiving = true;
	}
	
	public Character(int x, int y) {
		super(x, y);
		isLiving = true;
	}
	
	public void dead(Controller c) {
		isLiving = false;
		try {
            BufferedImage tmp = ImageIO.read(new File("src/images/bomber/grave.png"));
            setImage(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
		effect = new DeadEffect(pos);
	}
	
	public void showPower() {
		effect = new PowerEffect(pos);
	}
	
	public boolean isAlive() {
		return isLiving;
	}
	
	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	
	public abstract void move(int direction);
	
	public abstract void putBomb(Controller c);
	
}
