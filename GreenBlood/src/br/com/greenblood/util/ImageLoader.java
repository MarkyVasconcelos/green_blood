package br.com.greenblood.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageLoader {
    private static final Random random = new Random();
    
    private static List<Bitmap> tiles;
    
    private static Context context;

    private static Bitmap emptyTile;
    
    private static Bitmap floor;
	private static Bitmap mountain;
	private static Bitmap mountainRight;
	private static Bitmap mountainLeft;
	private static Bitmap mountainCornerLeft;
	private static Bitmap mountainCornerRight;
	private static Bitmap mountainJoinCornerLeft;
	private static Bitmap mountainJoinCornerRight;
	private static Bitmap water;
	private static Bitmap waterFill;

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
        
        mountain = image("tile_A.png");
        floor = image("tile_B.png");
        mountainLeft = image("tile_C.png");
        mountainRight = image("tile_D.png");
        mountainCornerLeft = image("tile_E.png");
        mountainCornerRight = image("tile_F.png");
        mountainJoinCornerLeft = image("tile_G.png");
        mountainJoinCornerRight = image("tile_H.png");
        water = image("tile_water.png");
        waterFill = image("tile_water_fill.png");
        
        try {
            emptyTile = BitmapFactory.decodeStream(context.getAssets().open("tile_0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Bitmap image(int resId) {
    	return BitmapFactory.decodeResource(context.getResources(), resId);
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

	public static Bitmap floor() {
		return floor;
	}
	
	public static Bitmap water() {
		return water;
	}

	public static Bitmap mountain() {
		return mountain;
	}
	
	public static Bitmap mountainRight() {
		return mountainRight;
	}

	public static Bitmap mountainLeft() {
		return mountainLeft;
	}

	public static Bitmap mountainCornerLeft() {
		return mountainCornerLeft;
	}

	public static Bitmap mountainCornerRight() {
		return mountainCornerRight;
	}

	public static Bitmap mountainJoinCornerLeft() {
		return mountainJoinCornerLeft;
	}

	public static Bitmap mountainJoinCornerRight() {
		return mountainJoinCornerRight;
	}

	public static Bitmap waterFill() {
		return waterFill;
	}
	
}
