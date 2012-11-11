
package br.com.greenblood.world.map;

import br.com.greenblood.util.ImageLoader;
import android.graphics.Bitmap;

public class Tile {
    private boolean solid;
    private Bitmap img;
    
    public Tile(Type type){
        this.solid = type.solid();
        img = type.img();
    }

    public boolean isSolid() {
        return solid;
    }

    public Bitmap sprite() {
        return img;
    }
    
    public enum Type {
    	FLOOR(true) {
			@Override
			Bitmap img() {
				return ImageLoader.floor();
			}
		}, MOUNTAIN(false){
			@Override
			Bitmap img() {
				return ImageLoader.mountain();
			}
		}, MOUNTAIN_LEFT(true){
			@Override
			Bitmap img() {
				return ImageLoader.mountainLeft();
			}
		}, MOUNTAIN_RIGHT(true){
			@Override
			Bitmap img() {
				return ImageLoader.mountainRight();
			}
		}, MOUNTAIN_CORNER_LEFT(true){
			@Override
			Bitmap img() {
				return ImageLoader.mountainCornerLeft();
			}
		}, MOUNTAIN_CORNER_RIGHT(true){
			@Override
			Bitmap img() {
				return ImageLoader.mountainCornerRight();
			}
		}, MOUNTAIN_JOIN_CORNER_LEFT(true){
			@Override
			Bitmap img() {
				return ImageLoader.mountainJoinCornerLeft();
			}
		}, MOUNTAIN_JOIN_CORNER_RIGHT(true){
			@Override
			Bitmap img() {
				return ImageLoader.mountainJoinCornerRight();
			}
		}, WATER(false) {
			@Override
			Bitmap img() {
				return ImageLoader.water();
			}
		}, WATER_FILL(false) {
			@Override
			Bitmap img() {
				return ImageLoader.waterFill();
			}
		};
    	
    	private final boolean solid;
		Type(boolean solid){
			this.solid = solid;
    	}
    	boolean solid(){
    		return solid;
    	}
    	abstract Bitmap img();
    }
    
    
}
