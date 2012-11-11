package br.com.greenblood.world.map;

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
		return new Tile(Tile.Type.WATER);
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
}
