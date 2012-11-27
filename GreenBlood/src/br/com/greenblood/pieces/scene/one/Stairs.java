package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

import commons.awt.Listener;

public class Stairs extends StaticObject {
	private static final int pixelsPerSecond = 70;
	
	private final Player player;
	private boolean walkingOverThis = false;
	private Rect imageBounds, imageTargetBounds;
	private int stairs;
	
	public Stairs(Rect bounds) {
		super(bounds);

		img = ImageLoader.image("house/stairs.png");
		imageBounds = new Rect(0,0,img.getWidth(), img.getHeight());
		
		imageTargetBounds = new Rect(imageBounds);
		imageTargetBounds.right *= GameCore.scale();
		imageTargetBounds.bottom *= GameCore.scale();
		
		stairs = bounds().height() / imageBounds.height();
		stairs++;
		
		setCollidable(false);
		player = GameWorld.player();
	}
	
	@Override
	public void processAnimationLogics(long uptime) {
	}
	
	@Override
	public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
		canvas.save();
		
		Rect bounds = currentBounds();
		Rect imgRect = new Rect(imageTargetBounds);
		imgRect.offset(bounds.left, bounds.top);
		imgRect.offset((int)offset.x,(int) offset.y);
		
		for(int i = 0; i <= stairs; i++){
			canvas.drawBitmap(img, imageBounds, imgRect, Paints.BLANK);
			imgRect.offset(0, imageBounds.height() - 20);
		}
		
		canvas.restore();
	}

	@Override
	public void processLogics(long uptime) {
		if(Rect.intersects(player.currentBoundingBounds(), currentBounds())){
			player.controlledWalking();
			GameWorld.actions().displayDirectionals();
			walkingOverThis = true;
			player.dir().setY(0);
			player.onTouchAction(onTouchingActionListener);
			player.onTouchJump(onTouchingJumpListener);
		}else if(walkingOverThis){
			GameWorld.actions().displayBasicControls();
			player.walkingOnAir();
		}
	}
	
	private final Listener<Integer> onTouchingActionListener = new Listener<Integer>() {
		@Override
		public void on(Integer t) {
			long fall = pixelsPerSecond / t;
			player.pos().setY(player.pos().y() - fall);
		}
	};
	
	private final Listener<Integer> onTouchingJumpListener = new Listener<Integer>() {
		@Override
		public void on(Integer t) {
			long fall = pixelsPerSecond / t;
			player.pos().setY(player.pos().y() + fall);
		}
	};

	private Bitmap img;

}

