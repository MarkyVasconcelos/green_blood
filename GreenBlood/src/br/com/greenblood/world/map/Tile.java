
package br.com.greenblood.world.map;

import br.com.greenblood.util.ImageLoader;
import android.graphics.Bitmap;

public class Tile {
    private boolean solid;
    private Bitmap img;
    
    public Tile(boolean solid){
        this.solid = solid;
        img = solid ? ImageLoader.randomTile() : ImageLoader.emptyTile();
    }

    public boolean isSolid() {
        return solid;
    }

    public Bitmap sprite() {
        return img;
    }
    
    
}
