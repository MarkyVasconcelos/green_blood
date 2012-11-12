package br.com.greenblood.world;

import java.util.ArrayList;
import java.util.List;

import br.com.greenblood.history.ObjectCreator;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.world.map.Tile;

public class Scene {
	private String name;
	private String background;
	private String foreground;
	private final int rowMapSize;
	private final int colMapSize;
	private Vector2D playerInitialTile;
	
	private Tile[][] tiles;
	private List<ObjectCreator> objects;
	
	public Scene(int rowMapSize, int colMapSize){
		this.rowMapSize = rowMapSize;
		this.colMapSize = colMapSize;
		
		tiles = new Tile[rowMapSize][colMapSize];
		
		objects = new ArrayList<ObjectCreator>();
	}
	
	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String background() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String foreground() {
		return foreground;
	}

	public void setForeground(String foreground) {
		this.foreground = foreground;
	}

	public int rowMapSize() {
		return rowMapSize;
	}

	public int colMapSize() {
		return colMapSize;
	}

	public Tile[][] tiles() {
		return tiles;
	}
	
	public List<ObjectCreator> objects(){
		return objects;
	}

	public Vector2D playerInitialTile() {
		return playerInitialTile;
	}

	public void setPlayerInitialTile(Vector2D playerInitialPosition) {
		this.playerInitialTile = playerInitialPosition;
	}

	public void addObjectCreator(ObjectCreator objectCreator) {
		objects().add(objectCreator);
	}
}

