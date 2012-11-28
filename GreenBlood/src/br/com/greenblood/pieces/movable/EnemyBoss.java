package br.com.greenblood.pieces.movable;

import commons.awt.Listener;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.TextObject;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

public class EnemyBoss extends Enemy {
	private final Sprite walkingSprite = new WalkingSprite();
	private final Sprite punchingSprite = new PunchingSprite();
	
    public EnemyBoss(Rect bounds, Rect boundingBounds) {
        super(bounds, boundingBounds, 4, new WalkingSprite());
    }
    
    @Override
    public void kill() {
    	super.kill();
    	
    	GameWorld.world().lockScreen();
    	TextObject spoke = new TextObject(new Rect(0,0,80,100), "nunca será!", 2000);
    	spoke.pos().set(x(), y() - bounds().left);
		spoke.onEnd(new Listener<Void>() {
			@Override
			public void on(Void t) {
				GameWorld.world().offsetDraw(0, 0, new Listener<Void>() {
					@Override
					public void on(Void t) {
						GameWorld.showFinalView();							
					}
				});
				
			}
		});
		
		GameWorld.world().addEntity(spoke);
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
	
    private final static class WalkingSprite extends Sprite{
        public WalkingSprite() {
            super(new Bitmap[] { ImageLoader.image("boss/boss_1.png"), ImageLoader.image("boss/boss_3.png"), ImageLoader.image("boss/boss_4.png") },
                    593, true);
        }
    }
    
    private final static class PunchingSprite extends Sprite{
        public PunchingSprite() {
            super(new Bitmap[] { ImageLoader.image("boss/boss_1.png"), ImageLoader.image("boss/boss_3.png"), ImageLoader.image("boss/boss_4.png") },
                    593, false);
        }
    }
}
