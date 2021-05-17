package game.map;

import game.constant.Constain;
import game.constant.MapDefine;
import game.controller.Controller;
import game.items.ItemBomb;
import game.items.ItemBombSize;
import objgame.character.Bomber;
import objgame.objmap.Box;
import objgame.objmap.Brick;
import objgame.objmap.Rock;
import objgame.objmap.Tree;

public class Map {
	public static int[][] matrix = { 
		    {-4, -4, -4, -4, 0, 0, 0, 2, 0, 0, 0, -4, -4, -4, -4}, 
		    {-4, 1, -4, 0, -4, -4, -4, 0, -4, -4, -4, 0, -4, 1, 0},
		    {2, -4, 0, -4, 0, 2, 0, -4, 0, 2, 0, -4, 0, -4, 0}, 
		    {3, 0, -4, 0, 3, 2, -4, 0, -4, 2, 3, 0, -4, 0, -4},
		    {2, 0, -4, 3, -4, 2, -4, 0, -4, 2, -4, 3, -4, 0, -4}, 
		    {-4, 0, -4, 0, -4, -4, 0, 0, 0, -4, -4, 0, -4, 0, 3},
		    {3, 0, -4, -4, 2, -4, 1, 1, 1, -4, 2, -4, -4, 0, 3}, 
		    {-4, 3, 0, -4, 0, -4, 3, 2, 3, -4, 0, -4, 0, -4, -4},
		    {-4, 3, -4, 0, 3, 2, -4, -4, -4, 2, 3, 0, 3, -4, 2}, 
		    {0, -4, -4, 3, 0, -4, 0, -4, 0, -4, 0, -4, 3, -4, 2},
		    {0, -4, 0, 3, -4, 0, -4, -4, -4, 0, -4, -4, -4, -4, -4}, 
		    {-4, 1, 4, -4, -4, -4, 0, 0, 0, -4, 3, -4, 4, 1, -4},
		    {-4, 0, 0, -4, -4, 3, 3, -4, 3, -4, -4, -4, 0, 0, -4}};
	
	
	public Map(Controller c) {
		for (int i = 0; i < Constain.NUMBER_BLOCK_HEIGHT; i++) {
			for (int j = 0; j < Constain.NUMBER_BLOCK_WIDTH; j++) {
				if (matrix[i][j] == 0) {
					continue;
				}
				
				switch (matrix[i][j]) {
				case MapDefine.PLAYER: {
					c.getPlayers().add(new Bomber(j, i));
					matrix[i][j] = 0;
					break;
				}
				case MapDefine.BRICK: {
					c.getMapItems().add(new Brick(j, i));
					break;
				}
				case MapDefine.ROCK: {
					c.getMapItems().add(new Rock(j, i));
					break;
				}
				case MapDefine.TREE: {
					c.getMapItems().add(new Tree(j, i));
					break;
				}
				case MapDefine.ITEM_BOMB: {
					c.getItems().add(new ItemBomb(j, i));
					break;
				}
				case MapDefine.ITEM_BOMBSIZE: {
					c.getItems().add(new ItemBombSize(j, i));
					break;
				}
				case MapDefine.BOX_EXPLODE: {
					c.getBoxes().add(new Box(j, i));
					break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + matrix[i][j]);
				}
			}
		}
	}
}
