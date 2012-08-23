package br.com.greenblood.world;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.MovableEntity;
import br.com.greenblood.world.map.Tile;

public class WorldMap {
    private final int MAP_WIDTH = 100, MAP_HEIGHT = 12;
    private final int MAP_VISIBLE_ROWS = 5;

    private Tile[][] map;

    private int screenHeight;
    private int screenWidth;

    {
        map = new Tile[MAP_WIDTH][MAP_HEIGHT];

        for (int c = 0; c < MAP_WIDTH; c++)
            for (int r = 0; r < MAP_HEIGHT; r++)
                map[c][r] = new Tile(r == c || r == MAP_HEIGHT - 1);
        
        map[0][0] = new Tile(false);
        map[1][1] = new Tile(false);
        map[0][2] = new Tile(true);
        map[1][2] = new Tile(true);
        map[12][MAP_HEIGHT - 2] = new Tile(true);
        map[18][MAP_HEIGHT - 2] = new Tile(true);
        map[22][MAP_HEIGHT - 2] = new Tile(true);
        map[22][MAP_HEIGHT - 3] = new Tile(true);
    }

    public void surfaceCreated(Rect size) {
        screenWidth = size.width();
        screenHeight = size.height();

        int estimatedTileSize = screenHeight / (MAP_VISIBLE_ROWS + 1);
        GameCore.setAppliedScale(((float) estimatedTileSize) / ((float) GameCore.TILE_SIZE));
    }

    public Vector2D draw(Canvas canvas, Vector2D player) {
        canvas.save();

        int offsetX = screenWidth / 2 - Math.round(player.x());
        offsetX = Math.max(Math.min(offsetX, 0), screenWidth - width());

        int firstTileX = GameCore.pixelsToTiles(-offsetX);
        int lastTileX = GameCore.pixelsToTiles(GameCore.tilesToPixels(firstTileX) + screenWidth) + 1;

        int offsetY = screenHeight / 2 - Math.round(player.y());
        offsetY = Math.max(Math.min(offsetY, 0), screenHeight - height());

        int firstTileY = GameCore.pixelsToTiles(-offsetY);
        int lastTileY = GameCore.pixelsToTiles(GameCore.tilesToPixels(firstTileY) + screenHeight) + 1;

        for (int y = firstTileY; y < lastTileY; y++) {
            if( y >= MAP_HEIGHT)
                continue;
            for (int x = firstTileX; x <= lastTileX; x++) {
                if (x >= MAP_WIDTH)
                    continue;

                Tile tile = map[x][y];

                int xPos = GameCore.tilesToPixels(x) + offsetX;
                int yPos = GameCore.tilesToPixels(y) + offsetY;

                canvas.drawBitmap(tile.sprite(), null, new Rect(xPos, yPos, xPos + GameCore.tileSize(), yPos + GameCore.tileSize()), Paints.BLANK);
            }
        }

        return new Vector2D(offsetX, offsetY);
    }

    private int height() {
        return GameCore.tilesToPixels(MAP_HEIGHT);
    }

    int width() {
        return GameCore.tilesToPixels(MAP_WIDTH);
    }

    public Tile tile(int col, int row) {
        return map[col][row];
    }

    public boolean collids(MovableEntity ent, int tileX, int tileY) {
        if (tileX < 0 || tileX >= MAP_WIDTH)
            return false;

        if (tileY < 0 || tileY >= MAP_HEIGHT)
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
        if (tileX < 0 || tileX >= MAP_WIDTH)
            return false;

        if (tileY < 0 || tileY >= MAP_HEIGHT)
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
        
        if (tileX < 0 || tileX >= MAP_WIDTH)
            return false;

        if (tileY < 0 || tileY >= MAP_HEIGHT)
            return false;
        
        return tile(tileX, tileY).isSolid();
    }

    public int nextSolidDistance(float x, float y) {
        int tileX = GameCore.pixelsToTiles(x);
        int tileY = GameCore.pixelsToTiles(y);
        
        if (tileX < 0 || tileX >= MAP_WIDTH)
            return -1;

        if (tileY < 0 || tileY + 1 >= MAP_HEIGHT)
            return -1;
        
        if(tile(tileX, tileY + 1).isSolid())
            return (int) (y - GameCore.tilesToPixels(tileY + 1));
        return -1;
    }
}
