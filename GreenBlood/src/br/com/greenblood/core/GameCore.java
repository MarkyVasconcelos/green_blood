package br.com.greenblood.core;

public class GameCore {
    public static final int TILE_SIZE = 50;
    private static float scale;

    public static int tilesToPixels(int tile) {
        return (int) (tile * TILE_SIZE * scale);
    }

    public static int tilesToPixels(float tile) {
        return tilesToPixels((int) tile);
    }

    public static int pixelsToTiles(int pixel) {
        return (int) (pixel / scale / TILE_SIZE) ;
    }

    public static int pixelsToTiles(float pixel) {
        return pixelsToTiles((int) pixel);
    }

    public static void setAppliedScale(float scale) {
        GameCore.scale = scale;
    }

    public static int tileSize() {
        return (int) (TILE_SIZE * scale);
    }
}
