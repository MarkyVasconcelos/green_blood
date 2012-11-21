package br.com.greenblood.history.mock;

import static br.com.greenblood.world.map.Tiles.*;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.history.ObjectCreator;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.pieces.movable.MiniBossEnemy;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.Scene;
import br.com.greenblood.world.map.Tile;

public class SceneMaker {
	public static Scene sceneOne() {
		int MAP_WIDTH = 150; // cols
		int MAP_HEIGHT = 20; // rows
		Scene scene = new Scene(MAP_HEIGHT, MAP_WIDTH);
		scene.setName("Prologue");

		Tile[][] tiles = scene.tiles();

		// waterfall
		tiles[0][8] = water();

		for (int row = 9; row <= 19; row++)
			tiles[0][row] = waterFill();

		tiles[1][8] = mountainCornerRight();

		for (int row = 9; row <= 11; row++)
			tiles[1][row] = mountainRight();

		// Floor (hallow, fire )
		floorGround(tiles, 1, MAP_WIDTH, 11, MAP_HEIGHT);
		tiles[1][11] = mountainJoinCornerRight();
		
		scene.setPlayerInitialTile(new Vector2D(4, 9));
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Trigger salut = SceneOneObjects.welcomeTrigger();
				int bottom = GameCore.tilesToPixels(11);
				salut.pos().set(GameCore.tilesToPixels(5), bottom - salut.height() / 2);
				return salut;
			}
		});

		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Entity hallow = SceneOneObjects.hallow();
				int bottom = GameCore.tilesToPixels(11);
				hallow.pos().set(GameCore.tilesToPixels(3), bottom - hallow.height() / 2);
				return hallow;
			}
		});

		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Entity fire = SceneOneObjects.fire();
				int bottom = GameCore.tilesToPixels(11);
				fire.pos().set(GameCore.tilesToPixels(6), bottom - fire.height() / 2);
				return fire;
			}
		});

		// Remove floor to build up fall
		for (int col = 18; col < 42; col++) {
			for (int row = 11; row < 17; row++)
				tiles[col][row] = empty();
			tiles[col][16] = floor();
		}
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Trigger problem = SceneOneObjects.problemTrigger();
				int bottom = GameCore.tilesToPixels(11);
				problem.pos().set(GameCore.tilesToPixels(17), bottom - problem.height() / 2);
				return problem;
			}
		});
		
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

		// first enemy (tree)
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Trigger problem = SceneOneObjects.cutTrigger();
				int bottom = GameCore.tilesToPixels(16);
				problem.pos().set(GameCore.tilesToPixels(30), bottom - problem.height() / 2);
				return problem;
			}
		});
		

		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Entity tree = SceneOneObjects.beingCuttedTree();
				int bottom = GameCore.tilesToPixels(16);
				tree.pos().set(GameCore.tilesToPixels(34),
						bottom - tree.height() / 2);
				return tree;
			}
		});
		
		// Rise
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
		
		//Water and jump obstacles

		//down a level
		remove(tiles, 67, 78, 15, MAP_HEIGHT);
		
		// River borders
		for (int row = 15; row < MAP_HEIGHT; row++) {
			tiles[66][row] = mountainRight();
			tiles[79][row] = mountainLeft();
		}
		
		tiles[66][15] = mountainCornerRight();
		tiles[79][15] = mountainCornerLeft();
		
		//Water
		for (int row = 67; row < 79; row++) {
			tiles[row][15] = water();

			for (int i = 16; i < MAP_HEIGHT; i++)
				tiles[row][i] = waterFill();
		}
		
		addLog(scene, 69, 15);
		addLog(scene, 73, 15);
		addLog(scene, 77, 15);
		
		
		//Great house tree + stairs
		for(int row = 7; row <= 14; row++){
			tiles[82][row] = houseTree();
			tiles[83][row] = houseTree();
		}
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Entity stairs = SceneOneObjects.stairs();
				int bottom = GameCore.tilesToPixels(10);
				stairs.pos().set(GameCore.tilesToPixels(83),
						bottom - stairs.height() / 2);
				return stairs;
			}
		});
		
		
		//House (TODO: Add window)
		for(int col = 78; col <= 88; col++){
			tiles[col][8] = houseFloor();
			for (int row = 5; row <= 7; row++)
				tiles[col][row] = houseWall();
			tiles[col][4] = houseTop();
		}
		
		for (int row = 4; row <= 7; row++){
			tiles[77][row] = houseWallRight();
			tiles[89][row] = houseWallLeft();
		}
		
		tiles[82][8] = houseTree();
		tiles[83][8] = houseTree();
		tiles[82][8] = houseTreeFloor();
		tiles[83][8] = houseTreeFloor();
		
		
		//TODO: Add enemy trough trigger
		//Miniboss
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Enemy ent =  new MiniBossEnemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
				int bottom = GameCore.tilesToPixels(7);
				ent.pos().set(GameCore.tilesToPixels(85), bottom - ent.height() / 2);
				return ent;
			}
		});
		
		
		//Enemy and chair
		//TODO: Animate
		addEnemy(scene, 93, 15);
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Entity chair = SceneOneObjects.chair();
				chair.setCollidable(true);
				int bottom = GameCore.tilesToPixels(15);
				chair.pos().set(GameCore.tilesToPixels(94),
						bottom - chair.height() / 2);
				return chair;
			}
		});
		
		//TODO: Fence and boss house
		
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Entity fence = SceneOneObjects.fence();
				fence.setCollidable(true);
				int bottom = GameCore.tilesToPixels(15);
				fence.pos().set(GameCore.tilesToPixels(95),
						bottom - fence.height() / 2);
				return fence;
			}
		});
		
		//TODO: Make this the boos
		addEnemy(scene, 99, 15);
		
		// Scenario block wall
		for (int row = 0; row < MAP_HEIGHT; row++)
			tiles[105][row] = mountainLeft();

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
	
	public static void addLog(Scene scene, final int x, final int y){
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Entity ent = SceneOneObjects.log();
				int bottom = GameCore.tilesToPixels(y);
				ent.pos().set(GameCore.tilesToPixels(x), bottom);
				return ent;
			}
		});
	}
	
	public static void addEnemy(Scene scene, final int x, final int y){
		scene.addObjectCreator(new ObjectCreator() {
			@Override
			public Entity create() {
				Enemy ent = enemy();
				int bottom = GameCore.tilesToPixels(y);
				ent.pos().set(GameCore.tilesToPixels(x),
						bottom - ent.height() / 2);
				return ent;
			}
		});
	}

	public static Enemy enemy() {
		return new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
	}

}