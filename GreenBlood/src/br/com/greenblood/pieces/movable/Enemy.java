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

    public Enemy(Rect bounds, Rect boundingBounds) {
        super(bounds, boundingBounds, 130 + rdm.nextInt(130), new WalkingSprite());
        setCollidable(true);
        currentHealth = 3;
    }

    @Override
    public void processSelfLogics(long uptime) {
        Player player = GameWorld.player();

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
                    400, false);
        }
    }
	@Override
	public void hit() {
		currentHealth--;
		
		GameWorld.world().showEnemyStats(this);
		
		if(currentHealth == 0)
			kill();
	}

	private final Sprite walkingSprite = new WalkingSprite();
	@Override
	protected Sprite walkingSprite() {
		return walkingSprite;
	}

	private final Sprite punchingSprite = new PunchingSprite();
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
