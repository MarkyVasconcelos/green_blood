package br.com.greenblood.pieces.movable;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

public class Enemy extends Character {
	private static final Random rdm = new Random();

	private final int maxHealth = 3;
	private int currentHealth;

	private final Sprite walkingSprite = new WalkingSprite();
	private final Sprite punchingSprite = new PunchingSprite();

	private boolean active = true;

	public Enemy(Rect bounds, Rect boundingBounds) {
		this(bounds, boundingBounds, 2, new StandingSprite());
	}

	protected Enemy(Rect bounds, Rect boundingBounds, int maxHealth,
			Sprite standingSprite) {
		super(bounds, boundingBounds, 130 + rdm.nextInt(130), standingSprite);
		setCollidable(true);
		currentHealth = maxHealth;
	}

	@Override
	public void processSelfLogics(long uptime) {
		if (!active)
			return;

		Player player = GameWorld.player();

		int hundred = GameCore.pixels(150);
		if (Math.abs(player.y() - y()) > hundred)
			return;

		int dist = (int) (player.x() - x());
		if (dist > 0 && dist < hundred)
			accelerate();
		else if (dist < 0 && dist > -hundred)
			desaccelerate();

		if (Math.abs(dist) < GameCore.pixels(60))
			punch();
	}

	private static final class StandingSprite extends Sprite {
		public StandingSprite() {
			super(new Bitmap[] { ImageLoader.image("enemy/walk_04.png") }, 200,
					true);
		}
	}

	private static final class WalkingSprite extends Sprite {
		public WalkingSprite() {
			super(new Bitmap[] { ImageLoader.image("enemy/walk_01.png"),
					ImageLoader.image("enemy/walk_02.png"),
					ImageLoader.image("enemy/walk_03.png"),
					ImageLoader.image("enemy/walk_04.png") }, 200, true);
		}
	}

	private final class PunchingSprite extends Sprite {
		public PunchingSprite() {
			super(new Bitmap[] { ImageLoader.image("enemy/punch_1.png"),
					ImageLoader.image("enemy/punch_2.png"),
					ImageLoader.image("enemy/punch_3.png"),
					ImageLoader.image("enemy/punch_3.png") }, 593, false);
		}
	}

	@Override
	public void hit() {
		currentHealth--;

		GameWorld.world().showEnemyStats(this);

		if (currentHealth == 0)
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

	public void setActive(boolean active) {
		this.active = active;
	}
}
