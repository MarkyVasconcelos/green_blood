package br.com.greenblood.world.map;

import br.com.greenblood.util.ImageLoader;

public class Tiles {
	public static Tile empty(){
		return null;//TODO: Document it
	}
	
	public static Tile mountainRight() {
		return new Tile(Tile.Type.MOUNTAIN_RIGHT);
	}

	public static Tile waterFill() {
		return new Tile(Tile.Type.WATER_FILL);
	}

	public static Tile water() {
		return new WaterTile();
	}

	public static Tile mountain() {
		return new Tile(Tile.Type.MOUNTAIN);
	}

	public static Tile mountainJoinCornerRight() {
		return new Tile(Tile.Type.MOUNTAIN_JOIN_CORNER_RIGHT);
	}

	public static Tile floor() {
		return new Tile(Tile.Type.FLOOR);
	}

	public static Tile mountainJoinCornerLeft() {
		return new Tile(Tile.Type.MOUNTAIN_JOIN_CORNER_LEFT);
	}

	public static Tile mountainCornerLeft() {
		return new Tile(Tile.Type.MOUNTAIN_CORNER_LEFT);
	}

	public static Tile mountainCornerRight() {
		return new Tile(Tile.Type.MOUNTAIN_CORNER_RIGHT);
	}

	public static Tile mountainLeft() {
		return new Tile(Tile.Type.MOUNTAIN_LEFT);
	}
	
	public static Tile houseTree() {
		return new Tile(false, ImageLoader.houseTree());
	}
	
	public static Tile houseTreeFloor() {
		return new Tile(true, ImageLoader.houseTreeFloor());
	}
	
	public static Tile houseWallLeft() {
		return new Tile(true, ImageLoader.houseWallLeft());
	}
	
	public static Tile houseWallRight() {
		return new Tile(true, ImageLoader.houseWallRight());
	}
	
	public static Tile houseFloor() {
		return new Tile(true, ImageLoader.houseFloor());
	}
	
	public static Tile houseWall() {
		return new Tile(false, ImageLoader.houseWall());
	}
	
	public static Tile houseTop() {
		return new Tile(false, ImageLoader.houseTop());
	}
}
