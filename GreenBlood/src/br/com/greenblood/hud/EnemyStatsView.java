package br.com.greenblood.hud;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.view.HighUpDisplay;
import br.com.greenblood.view.LinearProgressBar;

public class EnemyStatsView extends HighUpDisplay {
	private LinearProgressBar bar;
	private Bitmap face;
	private Rect faceBounds;
	
	public EnemyStatsView(Rect bounds) {
		super(bounds);
		
		face = ImageLoader.image("enemy/enemy_face.png");
		faceBounds = new Rect(0, 0, 39, 39);
		faceBounds.offset(bounds.left, bounds.top);
		faceBounds.bottom = bounds.top + GameCore.pixels(39);
		faceBounds.right = bounds.left + GameCore.pixels(39);
		
		Rect healthBarBounds = new Rect(faceBounds.right + GameCore.oneDp(),
										bounds.bottom - GameCore.pixels(20),
										bounds.right - GameCore.oneDp(),
										bounds.bottom - GameCore.oneDp());
		bar = new LinearProgressBar(healthBarBounds);
		
		setVisible(false);
		bar.setTotal(10);
	}

	
	public void display(final Enemy enemy) {
		setVisible(true);
		bar.setTotal(enemy.maxHealth());
		bar.setValue(enemy.currentHealth());

		hider.postDelayed(new Runnable() {
			@Override
			public void run() {
				setVisible(false);
			}
		}, 2000);
	}
	
	@Override
	public void draw(Canvas canvas, Rect surfaceView) {
		canvas.save();
		
		canvas.drawRect(surfaceView, Paints.BLACK);
		canvas.drawBitmap(face, null, faceBounds, Paints.BLANK);
		bar.draw(canvas);
		
		canvas.restore();
	}
	
	private Handler hider = new Handler();
}
