package game.gui;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.constant.Constain;
import game.constant.Direction;
import game.controller.Controller;
import game.items.Item;
import objgame.ObjGame;
import objgame.character.Bomber;
import objgame.character.Monster;
import objgame.effect.Effect;
import objgame.objmap.Box;
import objgame.objmap.ObjMap;
import objgame.weapon.bomb.Bomb;
import objgame.weapon.bomb.Fire;

public class DrawObject extends JPanel implements Runnable {
	AlphaComposite acomp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller c;
	private Thread thread;
	
	public DrawObject(Controller c) {
		thread = new Thread(this);
		thread.start();
		this.c = c;
	}
	
	@Override
	public void paint(Graphics g) {
		if (!c.isLose() && !c.isWin()) {
			try {
				BufferedImage tmp = ImageIO.read(new File("src/images/map/background.png"));
				g.drawImage(tmp, 0, 0, Constain.SCREEN_WIDTH, Constain.SCREEN_HEIGHT, this);
			} catch (IOException e) {
				g.fillRect(0, 0, Constain.SCREEN_WIDTH, Constain.SCREEN_HEIGHT);
			}
			draw(g);
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// blur photo
        	((Graphics2D)g).setComposite(acomp);
			if (c.isLose()) {
				try {
					BufferedImage tmp = ImageIO.read(new File("src/images/background/game-over.png"));
					g.drawImage(tmp, 0, 0, Constain.SCREEN_WIDTH, Constain.SCREEN_HEIGHT, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (c.isWin()) {
				try {
					BufferedImage tmp = ImageIO.read(new File("src/images/background/win.png"));
					g.drawImage(tmp, 0, 0, Constain.SCREEN_WIDTH, Constain.SCREEN_HEIGHT, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		for (ObjMap m: c.getMapItems()) {
			drawObj(g, m);
		}
		for (Box b: c.getBoxes()) {
			drawObj(g, b);
		}
		
		for (Item i: c.getItems()) {
			drawObj(g, i);
		}
		for (Bomber p: c.getPlayers()) {
			drawObj(g, p);
			if (p.getEffect() != null) {
				drawObj(g, p.getEffect());
			}
		}
		for (Monster p: c.getMonsters()) {
			drawObj(g, p);
			if (p.getEffect() != null) {
				drawObj(g, p.getEffect());
			}
		}
		for (Bomb b: c.getBombs()) {
			drawObj(g, b);
		}
		for (Fire f: c.getFires()) {
			drawObj(g, f);
		}
	}
	
	public void drawObj(Graphics g, ObjGame o) {
		int xPos = o.getPos().getX() * Constain.SCALE_WIDTH;
		int yPos = o.getPos().getY() * Constain.SCALE_HIGHT;
		int height = Constain.SCALE_HIGHT;
		int width = Constain.SCALE_WIDTH;
		
		if (o instanceof Bomber || o instanceof Effect || o instanceof Monster) {
			if (yPos > height) {
				yPos = yPos - height / 4;
			}
		}
		if (o instanceof Fire) {
			Fire f = (Fire) o;
			drawFire(g, f);
		}
		if (o instanceof Item) {
			xPos = xPos + width / 8;
			yPos = yPos + height / 10;
			height = height * 4 / 6;
			width = width * 4 / 5;
		}
		
		g.drawImage(o.getImage(), xPos, yPos, width, height, this);
	}
	
	public void drawFire(Graphics g, Fire f) {
		int x = 0;
		int y = 0;
		// fire up
		for (int i = 1; i < f.getLengths()[Direction.UP]; i++) {
			x = f.getPos().getX() * Constain.SCALE_WIDTH;
			y = (f.getPos().getY() - i) * Constain.SCALE_HIGHT;
			g.drawImage(f.getImages().get(Fire.UP_BODY),
					x, y, Constain.SCALE_WIDTH, Constain.SCALE_HIGHT, this);
		}
		if (f.getLengths()[Direction.UP] > 0) {
			x = f.getPos().getX() * Constain.SCALE_WIDTH;
			y = (f.getPos().getY() - f.getLengths()[Direction.UP]) * Constain.SCALE_HIGHT;
			g.drawImage(f.getImages().get(Fire.UP_END),
					x, y, Constain.SCALE_WIDTH, Constain.SCALE_HIGHT, this);
		}
		// fire down
		for (int i = 1; i < f.getLengths()[Direction.DOWN]; i++) {
			x = f.getPos().getX() * Constain.SCALE_WIDTH;
			y = (f.getPos().getY() + i) * Constain.SCALE_HIGHT;
			g.drawImage(f.getImages().get(Fire.DOWN_BODY),
					x, y, Constain.SCALE_WIDTH, Constain.SCALE_HIGHT, this);
		}
		if (f.getLengths()[Direction.DOWN] > 0) {
			x = f.getPos().getX() * Constain.SCALE_WIDTH;
			y = (f.getPos().getY() + f.getLengths()[Direction.DOWN]) * Constain.SCALE_HIGHT;
			g.drawImage(f.getImages().get(Fire.DOWN_END),
					x, y, Constain.SCALE_WIDTH, Constain.SCALE_HIGHT, this);
		}
		// fire left
		for (int i = 1; i < f.getLengths()[Direction.LEFT]; i++) {
			x = (f.getPos().getX() - i) * Constain.SCALE_WIDTH;
			y = f.getPos().getY() * Constain.SCALE_HIGHT;
			g.drawImage(f.getImages().get(Fire.LEFT_BODY),
					x, y, Constain.SCALE_WIDTH, Constain.SCALE_HIGHT, this);
		}
		if (f.getLengths()[Direction.LEFT] > 0) {
			x = (f.getPos().getX() - f.getLengths()[Direction.LEFT]) * Constain.SCALE_WIDTH;
			y = f.getPos().getY() * Constain.SCALE_HIGHT;
			g.drawImage(f.getImages().get(Fire.LEFT_END),
					x, y, Constain.SCALE_WIDTH, Constain.SCALE_HIGHT, this);
		}
		// fire right
		for (int i = 1; i < f.getLengths()[Direction.RIGHT]; i++) {
			x = (f.getPos().getX() + i) * Constain.SCALE_WIDTH;
			y = f.getPos().getY() * Constain.SCALE_HIGHT;
			g.drawImage(f.getImages().get(Fire.RIGHT_BODY),
					x, y, Constain.SCALE_WIDTH, Constain.SCALE_HIGHT, this);
		}
		if (f.getLengths()[Direction.RIGHT] > 0) {
			x = (f.getPos().getX() + f.getLengths()[Direction.RIGHT]) * Constain.SCALE_WIDTH;
			y = f.getPos().getY() * Constain.SCALE_HIGHT;
			g.drawImage(f.getImages().get(Fire.RIGHT_END),
					x, y, Constain.SCALE_WIDTH, Constain.SCALE_HIGHT, this);
		}
	}
	
	@Override
	public void run() {
		while (true) {
			c.checkBombs();
			c.checkFires();
			c.checkPlayer();
			c.checkMonster();
			c.checkBox();
			c.checkItems();
			try {
				Thread.sleep(75);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
}
