package br.com.greenblood.pieces;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.math.Vector2D;

public abstract class Entity {
    private final Vector2D pos;
    private final Rect bounds;
    private boolean dead;
    private Sprite image;
    private boolean collidable;

    public Entity(Rect bounds) {
        pos = new Vector2D();
        
        this.bounds = bounds;
        
        this.bounds.left *= GameCore.scale();
        this.bounds.top *= GameCore.scale();
        this.bounds.right *= GameCore.scale();
        this.bounds.bottom *= GameCore.scale();
    }
    
    public void setSprite(Sprite image){
        this.image = image;
        this.image.reset();
    }
    protected Sprite image(){
        return image;
    }

    public Vector2D pos() {
        return pos;
    }

    public Rect bounds() {
        return bounds;
    }

    public abstract void draw(Canvas canvas, Rect surfaceView, Vector2D offset);

    public abstract void processLogics(long uptime);
    
    public abstract void processAnimationLogics(long uptime);
    
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
    
    public Rect currentBounds() {
        int centerX = width() / 2;
        int centerY = height() / 2;
        return new Rect((int) x() - centerX, (int) y() - centerY, (int) x() + centerX, (int) y() + centerY);
    }
    
    public void kill() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

}
