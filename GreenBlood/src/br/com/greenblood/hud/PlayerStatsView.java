package br.com.greenblood.hud;

import br.com.digitalpages.commons.view.util.LayoutParamsFactory;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.view.LinearProgressBar;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayerStatsView extends LinearLayout {

	private LinearProgressBar bar;

	public PlayerStatsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		ImageView faceView = new ImageView(context);
		faceView.setImageBitmap(ImageLoader.image("player/player_face.png"));
		
		addView(faceView, LayoutParamsFactory.newLinear(39, 39));
		
		LinearLayout weast = new LinearLayout(context);
		weast.setGravity(Gravity.CENTER);
		weast.setOrientation(LinearLayout.VERTICAL);
		TextView score = new TextView(context);
		score.setText("12154800");
		weast.addView(score, LayoutParamsFactory.newMatchWrapLinear());
		bar = new LinearProgressBar(context);
		weast.addView(bar, LayoutParamsFactory.newMatchWrapLinear());
		
		addView(weast, LayoutParamsFactory.newWrapMatchLinear());
	}

	public void setMaximumHealth(int maxHelth) {
		bar.setTotal(maxHelth);
		bar.setValue(maxHelth);
	}
	
	public void setValue(final int helth) {
		post(new Runnable() {
			@Override
			public void run() {
				bar.setValue(helth);
			}
		});
	}

}
