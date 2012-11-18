package br.com.greenblood.pieces;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.digitalpages.commons.awt.Listener;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;

public class TextObject extends Entity {
	
	private final String text;
	private long duration;
	private Listener<Void> onEndListener;

	public TextObject(Rect bounds, String text) {
		super(bounds);
		this.text = text;
		duration = 2000;
	}

	@Override
	public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
		canvas.save();

		Rect currentBounds = currentBounds();
		currentBounds.offset((int) offset.x(), (int) offset.y());

		canvas.drawText(text, currentBounds.left, currentBounds.top, Paints.RED);

		canvas.restore();
	}

	@Override
	public void processLogics(long uptime) {
	}

	@Override
	public void processAnimationLogics(long uptime) {
		duration -= uptime;
		if(duration <= 0){
			if(onEndListener != null){
				onEndListener.on(null);
				onEndListener = null;
			}
			kill();
		}
	}

	public void onEnd(Listener<Void> onEndListener) {
		this.onEndListener = onEndListener;
	}

}
