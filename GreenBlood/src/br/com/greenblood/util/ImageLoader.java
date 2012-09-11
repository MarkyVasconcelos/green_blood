package br.com.greenblood.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageLoader {
    public static final String TILE_HILL_RIGHT = "tile_hill_right";
    
    private static final Random random = new Random();
    
    private static List<Bitmap> tiles;
    
    private static Context context;

    private static Bitmap emptyTile;
    
    private final static Map<String, Bitmap> namedTiles = new HashMap<String, Bitmap>();

    public static void init(Context context) {
        ImageLoader.context = context;
        
        tiles = new ArrayList<Bitmap>();
        tiles.add(image("tile_A.png"));
        tiles.add(image("tile_B.png"));
        tiles.add(image("tile_C.png"));
        tiles.add(image("tile_D.png"));
        tiles.add(image("tile_E.png"));
        tiles.add(image("tile_F.png"));
        tiles.add(image("tile_G.png"));
        tiles.add(image("tile_H.png"));
        
        emptyTile = image("tile_0.png");
        
        namedTiles.put(TILE_HILL_RIGHT, image("tile_hill_right.png"));
    }

    public static Bitmap image(String asset) {
        try {
            return BitmapFactory.decodeStream(context.getAssets().open(asset));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Bitmap randomTile(){
        return tiles.get(random.nextInt(tiles.size()));
    }

    public static Bitmap emptyTile() {
        return emptyTile;
    }
    
    public static Bitmap tileHillRight() {
        return namedTile(TILE_HILL_RIGHT);
    }

    public static Bitmap namedTile(String key) {
        return namedTiles.get(key);
    }
    
}
