package br.com.greenblood.pieces.movable;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.history.mock.SceneOneObjects;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

public class MiniBossEnemy extends Enemy {
	private final Sprite walkingSprite = new WalkingSprite();
	private final Sprite punchingSprite = new PunchingSprite();
	
    public MiniBossEnemy(Rect bounds, Rect boundingBounds) {
        super(bounds, boundingBounds, 1, new WalkingSprite());
    }
    
    @Override
    public void kill() {
    	super.kill();
    	
    	Entity key = SceneOneObjects.gateKey();
		key.pos().set(pos().x(), GameCore.tilesToPixels(8) - key.height() / 2);
		GameWorld.world().addEntity(key);
    }
    
    
	@Override
	protected Sprite walkingSprite() {
		return walkingSprite;
	}

	@Override
	protected Sprite punchingSprite() {
		return punchingSprite;
	}
	
    private static final class WalkingSprite extends Sprite {
        public WalkingSprite(){
            super(new Bitmap[] { ImageLoader.image("house/stick.png"), ImageLoader.image("house/stick_1.png") }, 200, true);
        }
    }
    
    private final class PunchingSprite extends Sprite{
        public PunchingSprite() {
            super(new Bitmap[] { ImageLoader.image("house/stick_punch.png"), ImageLoader.image("house/stick_punch_1.png"), ImageLoader.image("house/stick_punch_2.png") },
                    593, false);
        }
    }
}
