package br.com.greenblood.history.mock;

import static br.com.greenblood.world.map.Tiles.empty;
import static br.com.greenblood.world.map.Tiles.floor;
import static br.com.greenblood.world.map.Tiles.mountain;
import static br.com.greenblood.world.map.Tiles.mountainCornerRight;
import static br.com.greenblood.world.map.Tiles.mountainJoinCornerRight;
import static br.com.greenblood.world.map.Tiles.mountainLeft;
import static br.com.greenblood.world.map.Tiles.mountainRight;
import static br.com.greenblood.world.map.Tiles.water;
import static br.com.greenblood.world.map.Tiles.waterFill;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.history.ObjectCreator;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Enemy;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.world.Scene;
import br.com.greenblood.world.map.Tile;

public class SceneMaker {
	public static Scene sceneOne() {
		int MAP_WIDTH = 150; //cols
		int MAP_HEIGHT = 20; //rows
		Scene scene = new Scene(MAP_WIDTH, MAP_HEIGHT);
		scene.setName("Prologue");

		scene.setPlayerInitialTile(new Vector2D(4, 9));
		
		Tile[][] tiles = scene.tiles();

		//Floor all map
		for (int col = 0; col < MAP_WIDTH; col++){
			tiles[col][11] = floor();
			
			for(int i = 12; i < MAP_HEIGHT; i++)
				tiles[col][i] = mountain();
		}
		
		//waterfall
		tiles[0][8] = water();
		
		for(int row = 9; row <= 19; row++)
			tiles[0][row] = waterFill();
		
		tiles[1][8] = mountainCornerRight();
		
		for(int row = 9; row <= 11; row++)
			tiles[1][row] = mountainRight();
		
		tiles[1][11] = mountainJoinCornerRight();
		//end
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				StaticObject hallow = SceneOneObjects.hallow();
				int bottom = GameCore.tilesToPixels(11);
				hallow.pos().set(GameCore.tilesToPixels(3), bottom - hallow.height() / 2);
				return hallow;
			}
		});
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				StaticObject fire = SceneOneObjects.fire();
				int bottom = GameCore.tilesToPixels(11);
				fire.pos().set(GameCore.tilesToPixels(6), bottom - fire.height() / 2);
				return fire;
			}
		});
		
		//Remove floor to build up fall
		for(int col = 18; col < 37; col++){
			for(int row = 11; row < 17; row++)
				tiles[col][row] = empty();
			tiles[col][16] = floor();
		}
		
		//Fall
		tiles[18][11] = mountainCornerRight();
		tiles[18][12] = mountainJoinCornerRight();
		tiles[19][12] = mountainCornerRight();
		tiles[19][13] = mountainJoinCornerRight();
		tiles[20][13] = mountainCornerRight();
		tiles[20][14] = mountainJoinCornerRight();
		tiles[21][14] = mountainCornerRight();
		tiles[21][15] = mountainJoinCornerRight();
		tiles[22][15] = mountainCornerRight();
		tiles[22][16] = mountainJoinCornerRight();
		
		tiles[18][13] = mountain();
		tiles[18][14] = mountain();
		tiles[18][15] = mountain();
		tiles[18][16] = mountain();
		tiles[19][14] = mountain();
		tiles[19][15] = mountain();
		tiles[19][16] = mountain();
		tiles[20][15] = mountain();
		tiles[20][16] = mountain();
		
		//TODO: first enemy (tree)
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				StaticObject tree = SceneOneObjects.tree();
				int bottom = GameCore.tilesToPixels(16);
				tree.pos().set(GameCore.tilesToPixels(26), bottom - tree.height() / 2);
				return tree;
			}
		});
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Enemy ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
				int bottom = GameCore.tilesToPixels(16);
				ent.pos().set(GameCore.tilesToPixels(27), bottom - ent.height() / 2);
				return ent;
			}
		});
		
		for(int row = 0; row < MAP_HEIGHT; row++)
			tiles[37][row] = mountainLeft();
		
		//Up
//		tiles[16][11] = mountainJoinCornerLeft();
//		tiles[16][10] = mountainCornerLeft();
//		tiles[17][10] = mountainJoinCornerLeft();
//		tiles[17][9] = mountainCornerLeft();
//		tiles[18][9] = mountainJoinCornerLeft();
//		tiles[18][8] = mountainCornerLeft();
		
		
		//Fill floor again in another level
//		for (int col = 19; col < MAP_WIDTH; col++){
//			tiles[col][8] = floor();
//			tiles[col][9] = mountain();
//		}
		
		//Water
//		tiles[29][8] = mountainCornerRight();
//		tiles[50][8] = mountainCornerLeft();
		
//		for (int row = 9; row < MAP_HEIGHT; row++){
//			tiles[29][row] = mountainRight();
//			tiles[50][row] = mountainLeft();
//		}
//		
		
//		for (int c = 30; c < 50; c++){
//			tiles[c][8] = water();
//		
//			for(int i = 9; i < MAP_HEIGHT; i++)
//				tiles[c][i] = waterFill();
//		}
		
		return scene;
	}


}

