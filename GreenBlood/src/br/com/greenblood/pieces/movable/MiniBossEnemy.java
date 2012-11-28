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
        super(bounds, boundingBounds, 3, new StandingSprite());
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
	
	private static final class StandingSprite extends Sprite {
		public StandingSprite() {
			super(new Bitmap[] { ImageLoader.image("minibossenemy/walk_04.png") }, 200,
					true);
		}
	}
	
	private static final class WalkingSprite extends Sprite {
		public WalkingSprite() {
			super(new Bitmap[] { ImageLoader.image("minibossenemy/walk_01.png"),
					ImageLoader.image("minibossenemy/walk_02.png"),
					ImageLoader.image("minibossenemy/walk_03.png"),
					ImageLoader.image("minibossenemy/walk_04.png") }, 200, true);
		}
	}

	private final class PunchingSprite extends Sprite {
		public PunchingSprite() {
			super(new Bitmap[] { ImageLoader.image("minibossenemy/punch_1.png"),
					ImageLoader.image("minibossenemy/punch_2.png"),
					ImageLoader.image("minibossenemy/punch_3.png"),
					ImageLoader.image("minibossenemy/punch_3.png") }, 493, false);
		}
	}
}
