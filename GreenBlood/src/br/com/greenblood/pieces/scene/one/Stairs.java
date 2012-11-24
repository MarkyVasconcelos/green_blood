package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

import commons.awt.Listener;

public class Stairs extends StaticObject {
	private static final int pixelsPerSecond = 70;
	
	private final Player player;
	private boolean walkingOverThis = false;
	
	public Stairs(Rect bounds) {
		super(bounds);

		setSprite(new Sprite(
				new Bitmap[] { ImageLoader.image("house/stairs.PNG") },
				0, false));
		
		setCollidable(false);
		player = GameWorld.player();
	}

	@Override
	public void processLogics(long uptime) {
		if(Rect.intersects(player.currentBoundingBounds(), currentBounds())){
			player.controlledWalking();
			walkingOverThis = true;
			player.dir().setY(0);
			player.onTouchAction(onTouchingActionListener);
			player.onTouchJump(onTouchingJumpListener);
		}else if(walkingOverThis)
			player.walkingOnAir();
	}
	
	private final Listener<Integer> onTouchingActionListener = new Listener<Integer>() {
		@Override
		public boolean on(Integer t) {
			long fall = pixelsPerSecond / t;
			player.pos().setY(player.pos().y() - fall);
			return false;
		}
	};
	
	private final Listener<Integer> onTouchingJumpListener = new Listener<Integer>() {
		@Override
		public boolean on(Integer t) {
			long fall = pixelsPerSecond / t;
			player.pos().setY(player.pos().y() + fall);
			return false;
		}
	};

}
