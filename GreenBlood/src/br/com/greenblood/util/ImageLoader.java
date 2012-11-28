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
	private static Bitmap water1, water2, water3, water4;
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
        mountainLeft = mountain; //image("tile_C.png");
        mountainRight = mountain; //image("tile_D.png");
        mountainCornerLeft = image("scene/corner_left.png");//image("tile_E.png");
        mountainCornerRight = image("scene/corner_right.png");//image("tile_F.png");
        mountainJoinCornerLeft = image("scene/join_left.png");
        mountainJoinCornerRight = image("scene/join_right.png");
        water1 = image("water/water_1.png");
        water2 = image("water/water_2.png");
        water3 = image("water/water_3.png");
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
        try {
        	return BitmapFactory.decodeStream(context.getAssets().open(asset));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
	public static Bitmap floor() {
		return floor;
	}
	
	public static Bitmap water(int idx) {
		return idx == 1 ? water1 : idx == 2 ? water2 : idx == 3 ? water3 : water4;
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
