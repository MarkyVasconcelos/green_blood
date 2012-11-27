package br.com.greenblood.pieces.movable;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.util.ImageLoader;

public class EnemyBoss extends Enemy {
	private final Sprite walkingSprite = new WalkingSprite();
	private final Sprite punchingSprite = new PunchingSprite();
	
    public EnemyBoss(Rect bounds, Rect boundingBounds) {
        super(bounds, boundingBounds, 8, new WalkingSprite());
    }
    
    @Override
    public void kill() {
    	super.kill();
    	
    	//Start final cut-scene
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
            super(new Bitmap[] { ImageLoader.image("boss/stick.png"), ImageLoader.image("boss/stick_1.png") }, 200, true);
        }
    }
    
    private final class PunchingSprite extends Sprite{
        public PunchingSprite() {
            super(new Bitmap[] { ImageLoader.image("boss/stick_punch.png"), ImageLoader.image("boss/stick_punch_1.png"), ImageLoader.image("boss/stick_punch_2.png") },
                    593, false);
        }
    }
}
