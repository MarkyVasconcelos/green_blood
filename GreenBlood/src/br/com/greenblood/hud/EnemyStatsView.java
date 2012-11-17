package br.com.greenblood.hud;

import br.com.digitalpages.commons.view.util.LayoutParamsFactory;
import br.com.greenblood.pieces.Enemy;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.view.LinearProgressBar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EnemyStatsView extends LinearLayout {

	private LinearProgressBar bar;

	public EnemyStatsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		ImageView faceView = new ImageView(context);
		faceView.setImageBitmap(ImageLoader.image("enemy/enemy_face.png"));
		
		addView(faceView, LayoutParamsFactory.newLinear(40, 40));
		
		bar = new LinearProgressBar(context);
		
		LayoutParams params = LayoutParamsFactory.newWrapMatchLinear();
		params.gravity = Gravity.CENTER_VERTICAL;
		addView(bar, params);
	}
	
	public void display(final Enemy enemy) {
		post(new Runnable() {
			@Override
			public void run() {
				show();
				bar.setTotal(enemy.maxHealth());
				bar.setValue(enemy.currentHealth());
				
				postDelayed(new Runnable() {
					@Override
					public void run() {
						dismiss();
					}
				}, 2000);
			}
		});
	}
	
	private void show() {
		setVisibility(View.VISIBLE);
	}
	
	public void dismiss() {
		setVisibility(View.INVISIBLE);
	}
}
