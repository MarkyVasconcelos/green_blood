package br.com.greenblood.pieces;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;

public class StaticObject extends Entity {

	public StaticObject(Rect bounds) {
		super(bounds, bounds);
	}

	@Override
	public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
		if (image().current() == null)
			return;
		canvas.save();

		Rect currentBounds = currentBounds();
		currentBounds.offset((int) offset.x(), (int) offset.y());

		canvas.drawBitmap(image().current(), null, currentBounds, Paints.BLANK);

		canvas.restore();
	}

	@Override
	public void processLogics(long uptime) {
		// TODO Auto-generated method stub

	}

}
