package br.com.greenblood.pieces;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.math.Vector2D;

public abstract class Entity {
    private final Vector2D pos;
    private Rect bounds;

    public Entity(Rect bounds) {
        this.bounds = bounds;
        
        pos = new Vector2D();
    }

    public Vector2D pos() {
        return pos;
    }

    public Rect bounds() {
        return bounds;
    }

    public void setBounds(Rect rect) {
        this.bounds = rect;
    }

    public abstract void draw(Canvas canvas, Rect surfaceView, Vector2D offset);

    public abstract void processLogics(long uptime);
    
    public int tileX() {
        return GameCore.pixelsToTiles(pos().x());
    }
    
    public int tileY() {
        return GameCore.pixelsToTiles(pos().y());
    }
    
    public float x(){
        return pos().x();
    }
    
    public float y(){
        return pos().y();
    }
    
    public int width(){
        return bounds().width();
    }
    
    public int height(){
        return bounds().height();
    }
}
