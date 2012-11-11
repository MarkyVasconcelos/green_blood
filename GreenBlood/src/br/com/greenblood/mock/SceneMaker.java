package br.com.greenblood.mock;

import br.com.greenblood.math.Vector2D;
import br.com.greenblood.world.Scene;
import br.com.greenblood.world.map.Tile;
import br.com.greenblood.world.map.Tiles;

import static br.com.greenblood.world.map.Tiles.*;

public class SceneMaker {
	public static Scene sceneOne() {
		int MAP_WIDTH = 150; //cols
		int MAP_HEIGHT = 20; //rows
		Scene scene = new Scene(MAP_WIDTH, MAP_HEIGHT);
		scene.setName("Prologue");

		scene.setPlayerInitialTile(new Vector2D(4, 7));
		
		Tile[][] tiles = scene.tiles();

		//Floor all map
		for (int col = 0; col < MAP_WIDTH; col++){
			tiles[col][9] = floor();
			
			for(int i = 10; i < MAP_HEIGHT; i++)
				tiles[col][i] = mountain();
		}
		
		//Left block screen
		tiles[0][9] = mountainJoinCornerRight();
		
		for(int col = 4; col <= 8; col++)
			tiles[0][col] = Tiles.mountainRight();
		
		tiles[0][4] = mountainCornerRight();
		//end
		
		
		//Fall
		tiles[10][9] = mountainCornerRight();
		tiles[10][10] = mountainJoinCornerRight();
//		tiles[11][10] = mountainCornerRight();
//		tiles[11][11] = mountainJoinCornerRight();
		
		//TODO: Tree and first enemy
		for(int col = 11; col <= 16; col++){
			for(int row = 9; row < 11; row++)
				tiles[col][row] = empty();
			tiles[col][11] = floor();
		}
		
		//Up
		tiles[16][11] = mountainJoinCornerLeft();
		tiles[16][10] = mountainCornerLeft();
		tiles[17][10] = mountainJoinCornerLeft();
		tiles[17][9] = mountainCornerLeft();
		tiles[18][9] = mountainJoinCornerLeft();
		tiles[18][8] = mountainCornerLeft();
		
		
		//Fill floor again in another level
		for (int col = 19; col < MAP_WIDTH; col++){
			tiles[col][8] = floor();
			tiles[col][9] = mountain();
		}
		
		//Water
		tiles[29][8] = mountainCornerRight();
		tiles[50][8] = mountainCornerLeft();
		
		for (int row = 9; row < MAP_HEIGHT; row++){
			tiles[29][row] = mountainRight();
			tiles[50][row] = mountainLeft();
		}
		
		
		for (int c = 30; c < 50; c++){
			tiles[c][8] = water();
		
			for(int i = 9; i < MAP_HEIGHT; i++)
				tiles[c][i] = waterFill();
		}
		
		return scene;
	}


}
