package br.com.greenblood.world;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Enemy;
import br.com.greenblood.pieces.MovableEntity;
import br.com.greenblood.world.map.HillTile;
import br.com.greenblood.world.map.Tile;

public class WorldMap {
    private final int MAP_WIDTH = 100, MAP_HEIGHT = 12;
    private final int MAP_VISIBLE_ROWS = 5;

    private Tile[][] map;

    private int screenHeight;
    private int screenWidth;

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
                
                if(tile == null)
                    continue;

                int xPos = GameCore.tilesToPixels(x) + offsetX;
                int yPos = GameCore.tilesToPixels(y) + offsetY;

                canvas.drawBitmap(tile.sprite(), null, new Rect(xPos, yPos, xPos + GameCore.tileSize(), yPos + GameCore.tileSize()), Paints.BLANK);
                canvas.drawText(x + ":" + y, xPos + GameCore.tileSize() / 2 , yPos + GameCore.tileSize() / 2, Paints.RED);
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
        if(col < 0 || row < 0)
            return null;
        
        if(col > MAP_WIDTH || row > MAP_HEIGHT)
            return null;
        return map[col][row];
    }
    
    public Tile tileAt(float x, float y) {
        int tileX = GameCore.pixelsToTiles(x);
        int tileY = GameCore.pixelsToTiles(y);
        
        if(tileX != otileX || tileY != otileY){
            Log.v("WorldMap.collids", tileX + ":" + tileY);
            otileX = tileX;
            otileY = tileY;
        }
        
        return tile(tileX, tileY);
    }


    public boolean isSolid(float x, float y) {
        return collids(x, y) > 0;
    }
    
    private static int otileX, otileY;
    public int collids(float x, float y) {
        int tileX = GameCore.pixelsToTiles(x);
        int tileY = GameCore.pixelsToTiles(y);
        
        if (tileX < 0 || tileX >= MAP_WIDTH)
            return 0;

        if (tileY < 0 || tileY >= MAP_HEIGHT)
            return 0;
        
        Tile tile = tile(tileX, tileY);
        if(tile == null || !tile.isSolid())
            return 0;
        
//        Rect bounds = ent.currentBoundingBounds();
//        bounds.offset(GameCore.tilesToPixels(tileX), GameCore.tilesToPixels(tileY));

        int pX = (int) (x - GameCore.tilesToPixels(tileX));
        int pY = (int) (y - GameCore.tilesToPixels(tileY));
        return tile.collids(pX, pY);
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

    public void createWorld() {
        map = new Tile[MAP_WIDTH][MAP_HEIGHT];

        for (int c = 0; c < MAP_WIDTH; c++)
            for (int r = 0; r < MAP_HEIGHT; r++)
                if(r == c || r == MAP_HEIGHT - 1){
                    map[c][r] = new Tile(true);
//                    if( c+ 1 < MAP_HEIGHT)
//                    map[c + 1][r] = new HillTile();
                }
        
        map[1][1] = null;
        map[0][0] = new Tile(true);
        map[0][1] = new Tile(true);
        map[0][2] = new Tile(true);
        map[1][2] = new Tile(true);
//        map[3][2] = new HillTile();
//        map[12][MAP_HEIGHT - 2] = new Tile(true);
        map[18][MAP_HEIGHT - 2] = new Tile(true);
        map[22][MAP_HEIGHT - 2] = new Tile(true);
        map[22][MAP_HEIGHT - 3] = new Tile(true);

        
//        int halfTile = GameCore.tileSize() / 2;
//        Enemy ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
//        ent.pos().setX(GameCore.tilesToPixels(3) + halfTile);
//        GameWorld.pieces().add(ent);
//        
//        ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
//        ent.pos().setX(GameCore.tilesToPixels(8) + halfTile);
//        GameWorld.pieces().add(ent);
//        
//        ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
//        ent.pos().setX(GameCore.tilesToPixels(12) + halfTile);
//        GameWorld.pieces().add(ent);
//        
//        ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
//        ent.pos().setX(GameCore.tilesToPixels(22) + halfTile);
//        GameWorld.pieces().add(ent);
//        
//        
//        ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
//        ent.pos().setX(GameCore.tilesToPixels(28) + halfTile);
//        GameWorld.pieces().add(ent);
//        
//        ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
//        ent.pos().setX(GameCore.tilesToPixels(30) + halfTile);
//        GameWorld.pieces().add(ent);
        
//        Random rdm = new Random();
//        for(int i = 0; i < 50; i++){
//            ent = new Enemy(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
//            ent.pos().setX(rdm.nextInt(8000));
//            GameWorld.pieces().add(ent);
//        }
    }

}
