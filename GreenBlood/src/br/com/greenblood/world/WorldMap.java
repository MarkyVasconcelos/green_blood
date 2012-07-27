package br.com.greenblood.world;

import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Player;
import br.com.greenblood.world.map.Tile;

import android.graphics.Canvas;
import android.graphics.Rect;

public class WorldMap {
    private final int MAP_WIDTH = 100, MAP_HEIGHT = 20;
    private final int MAP_VISIBLE_ROWS = 8;

    private Tile[][] map;

    private int screenHeight;
    private int screenWidth;

    {
        map = new Tile[MAP_WIDTH][MAP_HEIGHT];

        for (int c = 0; c < MAP_WIDTH; c++)
            for (int r = 0; r < MAP_HEIGHT; r++)
                map[c][r] = new Tile(r == c);
    }

    public WorldMap() {
    }

    // TODO:scale
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
        int lastTileX =
                GameCore.pixelsToTiles(GameCore.tilesToPixels(firstTileX) + screenWidth) + 1;

        int offsetY = screenHeight / 2 - Math.round(player.y());
        offsetY = Math.max(Math.min(offsetY, 0), screenHeight - height());

        int firstTileY = GameCore.pixelsToTiles(-offsetY);
        int lastTileY =
                GameCore.pixelsToTiles(GameCore.tilesToPixels(firstTileY) + screenHeight) + 1;

        for (int y = firstTileY; y < lastTileY; y++) {
            for (int x = firstTileX; x <= lastTileX; x++) {
                if (x >= MAP_WIDTH)
                    continue;

                Tile tile = map[x][y];

                int xPos = GameCore.tilesToPixels(x) + offsetX;
                int yPos = GameCore.tilesToPixels(y) + offsetY;
                canvas.drawRect(xPos, yPos, xPos + GameCore.tileSize(), yPos + GameCore.tileSize(),
                        tile.isSolid() ? Paints.WHITE : Paints.STROKE_BLUE);
//                canvas.drawText("(" + x + "," + y + ")", xPos + 5, yPos + 20, Paints.RED);
            }
        }

        return new Vector2D(offsetX, offsetY);
    }

    private int height() {
        return GameCore.tilesToPixels(MAP_HEIGHT);
    }

    private int width() {
        return GameCore.tilesToPixels(MAP_WIDTH);
    }

    public Tile tile(int col, int row) {
        return map[col][row];
    }

    public boolean collids(Player player, int tileX, int tileY) {
        if (tileX < 0 || tileX >= MAP_WIDTH)
            return false;
        
        if (tileY < 0 || tileY >= MAP_HEIGHT)
            return false;

        Tile t = tile(tileX, tileY);
        if (!t.isSolid())
            return false;

        int tileXPos = GameCore.tilesToPixels(tileX);
        int tileYPos = GameCore.tilesToPixels(tileY);
        Rect tileSqr =
                new Rect(tileXPos, tileYPos, tileXPos + GameCore.tileSize(), tileYPos
                        + GameCore.tileSize());
        Rect playerSqr =
                new Rect((int) player.x(), (int) player.y(), (int) player.x() + player.width(),
                        (int) player.y() + player.height());

//        System.out.println(tileSqr);
//        System.out.println(playerSqr);
//        System.out.println(Rect.intersects(tileSqr, playerSqr));

        return Rect.intersects(tileSqr, playerSqr);
    }
}
