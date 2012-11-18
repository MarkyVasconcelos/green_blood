package br.com.greenblood.pieces;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.world.GameWorld;

public abstract class Trigger extends Entity {
	private boolean fired = false;
	
	public Trigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void draw(Canvas canvas, Rect surfaceView, Vector2D offset) {
		Rect currentBounds = currentBounds();
		currentBounds.offset((int) offset.x(), (int) offset.y());
		canvas.drawRect(currentBounds, Paints.BLACK_STROKE);
	}
	
	@Override
	public void processAnimationLogics(long uptime) {
		//Triggers doesn't have animations
	}

	@Override
	public void processLogics(long uptime) {
		if(fired)
			return;
		
		if (Rect.intersects(GameWorld.player().currentBounds(), currentBounds()))
			fireTrigger();
	}
	
	private void fireTrigger() {
		fired = true;
		onFired();
	}

	public abstract void onFired();

}
