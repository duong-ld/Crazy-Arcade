package game.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import game.constant.Constain;
import game.controller.Controller;
import game.items.ItemBomb;
import game.items.ItemBombSize;
import objgame.character.Bomber;
import objgame.character.Monster;
import objgame.objmap.Box;
import objgame.objmap.Brick;
import objgame.objmap.Rock;
import objgame.objmap.Tree;

public class Map {
	public static int[][] matrix = new int[20][20];
	public static final int BRICK = 1;
	public static final int ROCK = 2;
	public static final int TREE = 3;
	public static final int PLAYER = 4;
	public static final int MONSTER = 5;
	public static final int BOMB = -1;
	public static final int ITEM_BOMB = -2;
	public static final int ITEM_BOMBSIZE = -3;
	public static final int BOX_EXPLODE = -4;
	
	public Map() {
		readMap();
	}
	public Map(Controller c) {
		readMap();
		for (int i = 0; i < Constain.NUMBER_BLOCK_HEIGHT; i++) {
			for (int j = 0; j < Constain.NUMBER_BLOCK_WIDTH; j++) {
				if (matrix[i][j] == 0) {
					continue;
				}
				
				switch (matrix[i][j]) {
				case Map.PLAYER: {
					c.getPlayers().add(new Bomber(j, i));
					matrix[i][j] = 0;
					break;
				}
				case Map.MONSTER: {
					c.getMonsters().add(new Monster(j, i));
					matrix[i][j] = 0;
					break;
				}
				case Map.BRICK: {
					c.getMapItems().add(new Brick(j, i));
					break;
				}
				case Map.ROCK: {
					c.getMapItems().add(new Rock(j, i));
					break;
				}
				case Map.TREE: {
					c.getMapItems().add(new Tree(j, i));
					break;
				}
				case Map.ITEM_BOMB: {
					c.getItems().add(new ItemBomb(j, i));
					break;
				}
				case Map.ITEM_BOMBSIZE: {
					c.getItems().add(new ItemBombSize(j, i));
					break;
				}
				case Map.BOX_EXPLODE: {
					c.getBoxes().add(new Box(j, i));
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + matrix[i][j]);
				}
			}
		}
	}

	public void readMap() {
		Random generator = new Random();
		int n = generator.nextInt(5) + 1;
		// System.out.println(n);
		System.out.println(n);
		try {
			Scanner sc = new Scanner(new File("src/MAP_TEXT/map"+n+".txt"));
			for (int i = 0; i < Constain.NUMBER_BLOCK_HEIGHT; i++) {
				for (int j = 0; j < Constain.NUMBER_BLOCK_WIDTH; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
