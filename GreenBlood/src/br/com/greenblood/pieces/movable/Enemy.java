package br.com.greenblood.pieces.movable;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

public class Enemy extends Character {
    private static final Random rdm = new Random();

    private final int maxHealth = 3;
    private int currentHealth;

	private final Sprite walkingSprite = new WalkingSprite();
	private final Sprite punchingSprite = new PunchingSprite();
	
    public Enemy(Rect bounds, Rect boundingBounds) {
    	this(bounds, boundingBounds, 3, new WalkingSprite());
    }
    
	protected Enemy(Rect bounds, Rect boundingBounds, int maxHealth, Sprite standingSprite) {
        super(bounds, boundingBounds, 130 + rdm.nextInt(130), standingSprite);
        setCollidable(true);
        currentHealth = maxHealth;
    }

    @Override
    public void processSelfLogics(long uptime) {
        Player player = GameWorld.player();

        if(Math.abs(player.y() - y()) > 100)
        	return;
        
        int dist = (int) (player.x() - x());
        if (dist > 0 && dist < 100)
            accelerate();
        else if (dist < 0 && dist > -100)
            desaccelerate();
        
        if(Math.abs(dist) < 40)
        	punch();
    }

    private static final class WalkingSprite extends Sprite {
        public WalkingSprite(){
            super(new Bitmap[] { ImageLoader.image("stick.png"), ImageLoader.image("stick_1.png") }, 200, true);
        }
    }
    private final class PunchingSprite extends Sprite{
        public PunchingSprite() {
            super(new Bitmap[] { ImageLoader.image("stick_punch.png"), ImageLoader.image("stick_punch_1.png"), ImageLoader.image("stick_punch_2.png") },
                    593, false);
        }
    }
    
	@Override
	public void hit() {
		currentHealth--;
		
		GameWorld.world().showEnemyStats(this);
		
		if(currentHealth == 0)
			kill();
	}

	@Override
	protected Sprite walkingSprite() {
		return walkingSprite;
	}

	@Override
	protected Sprite punchingSprite() {
		return punchingSprite;
	}

	public int maxHealth() {
		return maxHealth;
	}
	
	public int currentHealth() {
		return currentHealth;
	}
}
