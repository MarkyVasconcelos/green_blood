package br.com.greenblood.history.mock;

import static br.com.greenblood.world.map.Tiles.*;

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
		int MAP_WIDTH = 150; // cols
		int MAP_HEIGHT = 20; // rows
		Scene scene = new Scene(MAP_HEIGHT, MAP_WIDTH);
		scene.setName("Prologue");

		scene.setPlayerInitialTile(new Vector2D(4, 9));

		Tile[][] tiles = scene.tiles();

		// waterfall
		tiles[0][8] = water();

		for (int row = 9; row <= 19; row++)
			tiles[0][row] = waterFill();

		tiles[1][8] = mountainCornerRight();

		for (int row = 9; row <= 11; row++)
			tiles[1][row] = mountainRight();

		// Floor (hallow, fire ) TODO: trigger
		floorGround(tiles, 1, MAP_WIDTH, 11, MAP_HEIGHT);
		tiles[1][11] = mountainJoinCornerRight();

		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				StaticObject hallow = SceneOneObjects.hallow();
				int bottom = GameCore.tilesToPixels(11);
				hallow.pos().set(GameCore.tilesToPixels(3),
						bottom - hallow.height() / 2);
				return hallow;
			}
		});

		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				StaticObject fire = SceneOneObjects.fire();
				int bottom = GameCore.tilesToPixels(11);
				fire.pos().set(GameCore.tilesToPixels(6),
						bottom - fire.height() / 2);
				return fire;
			}
		});

		// Remove floor to build up fall
		for (int col = 18; col < 42; col++) {
			for (int row = 11; row < 17; row++)
				tiles[col][row] = empty();
			tiles[col][16] = floor();
		}

		// Fall
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
		tiles[21][16] = mountain();

		// TODO: first enemy (tree)

		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				StaticObject tree = SceneOneObjects.tree();
				int bottom = GameCore.tilesToPixels(16);
				tree.pos().set(GameCore.tilesToPixels(26),
						bottom - tree.height() / 2);
				return tree;
			}
		});
		
		addEnemy(scene, 27, 16);
		
		// Up (XXX: see anti-word of fall)
		tiles[37][16] = mountainJoinCornerLeft();
		tiles[37][15] = mountainCornerLeft();
		tiles[38][15] = mountainJoinCornerLeft();
		tiles[38][14] = mountainCornerLeft();
		tiles[39][14] = mountainJoinCornerLeft();
		tiles[39][13] = mountainCornerLeft();
		
		tiles[38][16] = mountain();
		tiles[39][16] = mountain();
		tiles[39][15] = mountain();

		// Floor on another level
		remove(tiles, 40, MAP_WIDTH, 11, 14);
		floorGround(tiles, 40, MAP_WIDTH, 13, MAP_HEIGHT);
		
		//TODO: House and enemy + trigger
		
		addEnemy(scene, 53, 13);
		
		//down a level
		remove(tiles, 54, MAP_WIDTH, 13, 14);
		tiles[54][13] = mountainCornerRight();
		tiles[54][14] = mountainJoinCornerRight();
		floorGround(tiles, 55, MAP_WIDTH, 14, MAP_HEIGHT);
		
		addEnemy(scene, 58, 14);
		
		//down a level
		remove(tiles, 60, MAP_WIDTH, 14, 15);
		tiles[60][14] = mountainCornerRight();
		tiles[60][15] = mountainJoinCornerRight();
		floorGround(tiles, 61, MAP_WIDTH, 15, MAP_HEIGHT);
		
		//TODO: Water and jump obstacles

		//down a level
		remove(tiles, 67, 77, 15, MAP_HEIGHT);
		
		// Water
		
		for (int row = 15; row < MAP_HEIGHT; row++) {
			tiles[66][row] = mountainRight();
			tiles[78][row] = mountainLeft();
		}
		
		tiles[66][15] = mountainCornerRight();
		tiles[78][15] = mountainCornerLeft();
	
		for (int row = 67; row < 78; row++) {
			tiles[row][15] = water();

			for (int i = 16; i < MAP_HEIGHT; i++)
				tiles[row][i] = waterFill();
		}
		
		// Scenario block wall
		for (int row = 0; row < MAP_HEIGHT; row++)
			tiles[100][row] = mountainLeft();

		return scene;
	}

	public static void remove(Tile[][] tiles, int x1, int x2, int y1, int y2) {
		for (int col = x1; col < x2; col++)
			for (int row = y1; row < y2; row++)
				tiles[col][row] = empty();

	}

	public static void floorGround(Tile[][] tiles, int x1, int x2, int y1, int y2) {
		for (int col = x1; col < x2; col++) {
			tiles[col][y1] = floor();

			for (int i = y1 + 1; i < y2; i++)
				tiles[col][i] = mountain();
		}
	}
	
	public static void addEnemy(Scene scene, final int x, final int y){
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Enemy ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0,
						30, 128));
				int bottom = GameCore.tilesToPixels(y);
				ent.pos().set(GameCore.tilesToPixels(x),
						bottom - ent.height() / 2);
				return ent;
			}
		});
	}

}
