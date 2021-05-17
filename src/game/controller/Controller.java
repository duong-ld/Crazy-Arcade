package game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

import game.constant.Constain;
import game.constant.Direction;
import game.constant.MapDefine;
import game.gui.DrawObject;
import game.items.Item;
import game.items.ItemBomb;
import game.items.ItemBombSize;
import game.map.Map;
import objgame.ObjGame;
import objgame.character.*;
import objgame.objmap.Box;
import objgame.objmap.ObjMap;
import objgame.weapon.bomb.Bomb;
import objgame.weapon.bomb.Fire;

public class Controller extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Bomber> players = new ArrayList<Bomber>();
	private ArrayList<ObjMap> mapItems = new ArrayList<ObjMap>();
	private ArrayList<Bomb> bombs = new ArrayList<Bomb>();
	private ArrayList<Fire> fires = new ArrayList<Fire>();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	// box is a special object map, it can explode. When it explode -> drop random item
	// -> need control box special
	private ArrayList<Box> boxes = new ArrayList<Box>();
	private Map map;
	
	private Controller controllerThis = this;
	
	public Controller() {
		map = new Map(this);
		add(new DrawObject(this));
		this.addKeyListener(new Handler());
		init();
	}
	
	public void init() {
	    setTitle("Bomb Game");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // size of window = screen_height + 25: 25 is size of exit button
	    setSize(Constain.SCREEN_WIDTH, Constain.SCREEN_HEIGHT + 25);
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
	public ArrayList<Bomber> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Bomber> players) {
		this.players = players;
	}

	public ArrayList<ObjMap> getMapItems() {
		return mapItems;
	}

	public void setMapItems(ArrayList<ObjMap> mapItems) {
		this.mapItems = mapItems;
	}

	public ArrayList<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(ArrayList<Bomb> bombs) {
		this.bombs = bombs;
	}
	
	public ArrayList<Fire> getFires() {
		return fires;
	}
	
	public void setFires(ArrayList<Fire> fires) {
		this.fires = fires;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public ArrayList<Box> getBoxes() {
		return boxes;
	}
	
	public void setBoxes(ArrayList<Box> boxes) {
		this.boxes = boxes;
	}
	
	public Map getMap() {
		return map;
	}
	
	public void checkBombs() {
		Iterator<Bomb> itor = bombs.iterator();
		
		while (itor.hasNext()) {
			Bomb tmp = (Bomb) itor.next();
			
			if (tmp.getStart() + Constain.TIME_BOMB <= System.currentTimeMillis()
					|| checkDamageFire(tmp)) {
				tmp.explode(this);
				itor.remove();
			}
		}
	}
	
	public void checkFires() {
		Iterator<Fire> itor = fires.iterator();
		
		while (itor.hasNext()) {
			Fire tmp = (Fire) itor.next();
			
			if (tmp.getStart() + Constain.TIME_FIRE <= System.currentTimeMillis()) {
				itor.remove();
			}
		}
	}
	
	public void checkPlayer() {
		Iterator<Bomber> itor = players.iterator();
		
		while(itor.hasNext()) {
			Bomber tmp = (Bomber) itor.next();
			if (tmp.getEffect() == null 
					&& (tmp.getMaxBomb() + tmp.getLength() > 4)) {
				
				tmp.showPower();
			}
			if (checkDamageFire(tmp)) {
				tmp.dead(this);
			}
		}
	}
	
	// box can explode and drop item
	public void checkBox() {
		Iterator<Box> itor = boxes.iterator();
		
		int posX = 0; // now position of box
		int posY = 0;
		
		while(itor.hasNext()) {
			Box tmp = (Box) itor.next();
			
			if (checkDamageFire(tmp)) {
				posX = tmp.getPos().getX();
				posY = tmp.getPos().getY();
				
				int randomResult = randomItem();
				Map.matrix[posY][posX] = randomResult;
				
				switch (randomResult) {
				case MapDefine.ITEM_BOMB: {
					items.add(new ItemBomb(posX, posY));
					break;
				}
				case MapDefine.ITEM_BOMBSIZE: {
					items.add(new ItemBombSize(posX, posY));
					break;
				}
				default:
					break;
				}
				
				itor.remove();
			}
		}
	}
	
	public int randomItem() {
		// make rate of random -> item
		int ranNum = ThreadLocalRandom.current().nextInt(-9, -1);
		
		if (ranNum == MapDefine.ITEM_BOMB) {
			return MapDefine.ITEM_BOMB;
		}
		if (ranNum == MapDefine.ITEM_BOMBSIZE) {
			return MapDefine.ITEM_BOMBSIZE;
		}
		
		return 0;
	}
	
	public void checkItems() {
		Iterator<Item> itor = items.iterator();
		
		int posX = 0; // now position of item
		int posY = 0;
		
		while(itor.hasNext()) {
			Item tmp = (Item) itor.next();
			posY = tmp.getPos().getY();
			posX = tmp.getPos().getX();
			
			if (Map.matrix[posY][posX] == 0) {
				itor.remove();
			} else if (tmp.getStart() + Constain.TIME_BOMB > System.currentTimeMillis()){
				// Prevents items from exploding as soon as they are created by a box
				continue;
			} else if (checkDamageFire(tmp)) {
				itor.remove();
			}
		}
	}
	
	// check Damage from fire to ObjGame
	public boolean checkDamageFire(ObjGame o) {
		for (Fire f: fires) {
			if (o.getPos().getX() == f.getPos().getX()) {
				// (1) |Max length|   (2)    |center|     (3)     |Max length| (4)
				// position 2, 3 takeDamage
				// position 1, 4 not
				if (f.getPos().getY() <= o.getPos().getY()
						&& f.getPos().getY() + f.getLengths()[Direction.DOWN] >= o.getPos().getY()) {
	
					return true;
				}
				else if (f.getPos().getY() >= o.getPos().getY()
						&& f.getPos().getY() - f.getLengths()[Direction.UP] <= o.getPos().getY()) {

					return true;
				}
					
			} else if (o.getPos().getY() == f.getPos().getY()) {
				if (f.getPos().getX() <= o.getPos().getX() 
						&& f.getPos().getX() + f.getLengths()[Direction.RIGHT] >= o.getPos().getX()) {
					
					return true;
				} 
				else if (f.getPos().getX() >= o.getPos().getX() 
						&& f.getPos().getX() - f.getLengths()[Direction.LEFT] <= o.getPos().getX()) {
					
					return true;
				}
			}	
		}
		return false;
	}


	public class Handler implements KeyListener {
	    public void keyTyped(KeyEvent e) {
	    	// none
	    }

	    public void keyPressed(KeyEvent e) {
	      switch (e.getKeyCode()) {
	        case 37: {
	        	if (players.get(0).isAlive())
	        		players.get(0).move(Direction.LEFT);
	        	break;
	        }
	        case 38: {
	        	if (players.get(0).isAlive())
	        		players.get(0).move(Direction.UP);
	        	break;
	        }
	        case 39: {
	        	if (players.get(0).isAlive())
	        		players.get(0).move(Direction.RIGHT);
	        	break;
	        }
	        case 40: {
	        	if (players.get(0).isAlive())
	        		players.get(0).move(Direction.DOWN);
	        	break;
	        }
	        case 16: { // shift
	        	if (players.get(0).isAlive())
	        		players.get(0).putBomb(controllerThis);
	        	break;
	        }

	        case 65: {
	        	if (players.get(1).isAlive())
	        		players.get(1).move(Direction.LEFT);
	        	break;
	        }
	        case 87: {
	        	if (players.get(1).isAlive())
	        		players.get(1).move(Direction.UP);
	        	break;
	        }
	        case 68: {
	        	if (players.get(1).isAlive())
	        		players.get(1).move(Direction.RIGHT);
	        	break;
	        }
	        case 83: {
	        	if (players.get(1).isAlive())
	        		players.get(1).move(Direction.DOWN);
	        	break;
	        }
	        case 32: {
	        	if (players.get(1).isAlive())
	        		players.get(1).putBomb(controllerThis);
	        	break;
	        }
	      }
	    }

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
