
package br.com.greenblood.world.map;

import br.com.greenblood.math.Vector2D;
import br.com.greenblood.util.ImageLoader;
import android.graphics.Bitmap;

//TODO: Make it works for left down hill
public class HillTile extends Tile {
    private Bitmap img;
    private final Vector2D start, end;
    
    public HillTile(){
        super(true);
        
        img = ImageLoader.tileHillRight();
        start =new Vector2D();
        end = new Vector2D(32,32);
    }

    public Bitmap sprite() {
        return img;
    }
    
    @Override
    public int collids(int x, int y) {
        return y - x;
//        int far = y - x;
//        return far == 0 ? 1 : far;
//        if(isLeft(start, end, new Vector2D(x, y))) {
//            
//        }
        
//        return 0;
    }
    
    public boolean isLeft(Vector2D a, Vector2D b, Vector2D c){
        return ((b.x() - a.x()) * (c.y() - a.y()) - (b.y() - a.y()) * (c.x() - a.x())) > 0;
   }
    
    @Override
    public int y(float x) {
        return (int) x;
    }
    
}
