package br.com.greenblood.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageLoader {
    private static Context context;

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
	
	private static Bitmap houseFloor;
	private static Bitmap houseTop;
	private static Bitmap houseTree;
	private static Bitmap houseTreeFloor;
	private static Bitmap houseWall;
	private static Bitmap houseWallLeft;
	private static Bitmap houseWallRight;
	
	private static final Map<String, Bitmap> assets = new HashMap<String, Bitmap>();

    public static void init(Context context) {
        ImageLoader.context = context;
        
        mountain = image("scene/mountain.png");//image("tile_A.png");
        floor = image("scene/floor.png");//image("tile_B.png");
        mountainLeft = image("tile_C.png");
        mountainRight = image("tile_D.png");
        mountainCornerLeft = image("tile_E.png");
        mountainCornerRight = image("tile_F.png");
        mountainJoinCornerLeft = image("tile_G.png");
        mountainJoinCornerRight = image("tile_H.png");
        water = image("tile_water.png");
        waterFill = image("tile_water_fill.png");
        
        houseTree = image("house/tree.PNG");
        houseFloor = image("house/floor.PNG");
        houseTreeFloor = image("house/tree_floor.PNG");
        houseTop = image("house/top.PNG");
        houseWall = image("house/wall.PNG");
        houseWallLeft = image("house/wall_left.PNG");
        houseWallRight = image("house/wall_right.PNG");
    }
    
    public static Bitmap image(int resId) {
    	return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    
    //TODO: Cache things to speedup decode time
    public static Bitmap image(String asset) {
//    	if(assets.containsKey(asset) && !assets.get(asset).isRecycled())
//    		return assets.get(asset);
    	
        try {
//			return assets.put(asset, BitmapFactory.decodeStream(context.getAssets().open(asset)));
        	return BitmapFactory.decodeStream(context.getAssets().open(asset));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
	
	public static Bitmap houseTree() {
		return houseTree;
	}
	
	public static Bitmap houseFloor() {
		return houseFloor;
	}

	public static Bitmap houseTop() {
		return houseTop;
	}

	public static Bitmap houseWall() {
		return houseWall;
	}
	
	public static Bitmap houseWallLeft() {
		return houseWallLeft;
	}
	
	public static Bitmap houseWallRight() {
		return houseWallRight;
	}

	public static Bitmap houseTreeFloor() {
		return houseTreeFloor;
	}

	
}
