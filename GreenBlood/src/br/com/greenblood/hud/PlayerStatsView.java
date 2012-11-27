package br.com.greenblood.hud;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.view.HighUpDisplay;
import br.com.greenblood.view.LinearProgressBar;

public class PlayerStatsView extends HighUpDisplay {
	
	private Bitmap playerFace;
	private LinearProgressBar bar;
	private Rect playerFaceBounds;

	public PlayerStatsView(Rect bounds) {
		super(bounds);
		playerFace = ImageLoader.image("player/player_face.png");
		playerFaceBounds = new Rect(0, 0, 39, 39);
		playerFaceBounds.offset(bounds.left, bounds.top);
		playerFaceBounds.bottom = GameCore.pixels(39);
		playerFaceBounds.right = GameCore.pixels(39);
		
		Rect healthBarBounds = new Rect(playerFaceBounds.right + GameCore.oneDp(),
										bounds.bottom - GameCore.pixels(20),
										bounds.right - GameCore.oneDp(),
										bounds.bottom - GameCore.oneDp());
		bar = new LinearProgressBar(healthBarBounds);
		
		//TODO: Add score
//		TextView score = new TextView(context);
//		score.setText("12154800");
//		weast.addView(score, LayoutParamsFactory.newMatchWrapLinear());
//		bar = new LinearProgressBar(context);
//		weast.addView(bar, LayoutParamsFactory.newMatchWrapLinear());
//		addView(weast, LayoutParamsFactory.newWrapMatchLinear());
	}

	public void setMaximumHealth(int maxHelth) {
		bar.setTotal(maxHelth);
		bar.setValue(maxHelth);
	}
	
	public void setValue(final int health) {
		bar.setValue(health);
	}

	@Override
	public void draw(Canvas canvas, Rect surfaceView) {
		canvas.save();
		
		canvas.drawRect(surfaceView, Paints.BLACK);
		canvas.drawBitmap(playerFace, null, playerFaceBounds, Paints.BLANK);
		bar.draw(canvas);
		
		canvas.restore();
	}

}
