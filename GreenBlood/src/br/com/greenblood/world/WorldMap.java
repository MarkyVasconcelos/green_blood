package br.com.greenblood.world;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.movable.MovableEntity;
import br.com.greenblood.world.map.Tile;

public class WorldMap {
	private final int mapWidth, mapHeight;
    private final int MAP_VISIBLE_ROWS = 5;

    private Tile[][] map;

    private int screenHeight;
    private int screenWidth;

    public WorldMap(Scene scene){
    	map = scene.tiles();
    	mapWidth = scene.colMapSize();
    	mapHeight = scene.rowMapSize();
    }

    public void surfaceCreated(Rect size) {
        screenWidth = size.width();
        screenHeight = size.height();

        int estimatedTileSize = screenHeight / (MAP_VISIBLE_ROWS + 1);
        GameCore.setAppliedScale(((float) estimatedTileSize) / ((float) GameCore.TILE_SIZE));
    }

    public Vector2D draw(Canvas canvas, Vector2D player, int offset) {
        canvas.save();

        int offsetX = screenWidth / 2 - Math.round(player.x() + offset);
        offsetX = Math.max(Math.min(offsetX, 0), screenWidth - width());

        int firstTileX = GameCore.pixelsToTiles(-offsetX);
        int lastTileX = GameCore.pixelsToTiles(GameCore.tilesToPixels(firstTileX) + screenWidth) + 1;

        int offsetY = screenHeight / 2 - Math.round(player.y());
        offsetY = Math.max(Math.min(offsetY, 0), screenHeight - height());

        int firstTileY = GameCore.pixelsToTiles(-offsetY);
        int lastTileY = GameCore.pixelsToTiles(GameCore.tilesToPixels(firstTileY) + screenHeight) + 1;

        for (int y = firstTileY; y < lastTileY; y++) {
            if( y >= mapHeight)
                continue;
            for (int x = firstTileX; x <= lastTileX; x++) {
                if (x >= mapWidth)
                    continue;

                Tile tile = map[x][y];
                
                if(tile == null)
                	continue;

                int xPos = GameCore.tilesToPixels(x) + offsetX;
                int yPos = GameCore.tilesToPixels(y) + offsetY;

                canvas.drawBitmap(tile.sprite(), null, new Rect(xPos, yPos, xPos + GameCore.tileSize(), yPos + GameCore.tileSize()), Paints.BLANK);
//                canvas.drawRect(new Rect(xPos, yPos, xPos + GameCore.tileSize(), yPos + GameCore.tileSize()), Paints.BLACK_STROKE);
//                canvas.drawText("(" + x + "," + y + ")", xPos + 8, yPos + 20, Paints.BLACK);
            }
        }

        return new Vector2D(offsetX, offsetY);
    }

    private int height() {
        return GameCore.tilesToPixels(mapHeight);
    }

    int width() {
        return GameCore.tilesToPixels(mapWidth);
    }

    public Tile tile(int col, int row) {
        return map[col][row];
    }

    public boolean collids(MovableEntity ent, int tileX, int tileY) {
        if (tileX < 0 || tileX >= mapWidth)
            return false;

        if (tileY < 0 || tileY >= mapHeight)
            return false;

        Tile t = tile(tileX, tileY);
        if (!t.isSolid())
            return false;

        int tileXPos = GameCore.tilesToPixels(tileX);
        int tileYPos = GameCore.tilesToPixels(tileY);
        Rect tileSqr = new Rect(tileXPos, tileYPos, tileXPos + GameCore.tileSize(), tileYPos + GameCore.tileSize());
        return Rect.intersects(tileSqr, ent.currentBounds());
    }

    public boolean willCollids(MovableEntity ent, int tileX, int tileY) {
        if (tileX < 0 || tileX >= mapWidth)
            return false;

        if (tileY < 0 || tileY >= mapHeight)
            return false;

        Tile t = tile(tileX, tileY);
        if (!t.isSolid())
            return false;

        int tileXPos = GameCore.tilesToPixels(tileX);
        int tileYPos = GameCore.tilesToPixels(tileY);
        Rect tileSqr = new Rect(tileXPos, tileYPos, tileXPos + GameCore.tileSize(), tileYPos + GameCore.tileSize());
        return Rect.intersects(tileSqr, ent.currentBounds());
    }

    public boolean isSolid(float x, float y) {
        int tileX = GameCore.pixelsToTiles(x);
        int tileY = GameCore.pixelsToTiles(y);
        
        if (tileX < 0 || tileX >= mapWidth)
            return false;

        if (tileY < 0 || tileY >= mapHeight)
            return false;
        
        if (tile(tileX, tileY) == null)
        	return false;
        
        return tile(tileX, tileY).isSolid();
    }

    public int nextSolidDistance(float x, float y) {
        int tileX = GameCore.pixelsToTiles(x);
        int tileY = GameCore.pixelsToTiles(y);
        
        if (tileX < 0 || tileX >= mapWidth)
            return -1;

        if (tileY < 0 || tileY + 1 >= mapHeight)
            return -1;
        
        if(tile(tileX, tileY + 1).isSolid())
            return (int) (y - GameCore.tilesToPixels(tileY + 1));
        return -1;
    }
}
