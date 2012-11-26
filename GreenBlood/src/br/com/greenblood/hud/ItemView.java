package br.com.greenblood.hud;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import br.com.greenblood.history.mock.SceneOneObjects;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

import commons.view.LayoutParamsFactory;

public class ItemView extends LinearLayout {

	public ItemView(Context context, AttributeSet attrs) {
		super(context, attrs);

		ImageView itemView = new ImageView(context);
		itemView.setImageBitmap(ImageLoader.image("boss/gate_key.PNG"));

		setBackgroundColor(Color.GRAY);

		LayoutParams params = LayoutParamsFactory.newLinear(17, 39);
		params.leftMargin = 5;
		params.topMargin = 5;
		params.rightMargin = 5;
		params.bottomMargin = 5;

		addView(itemView, params);
	}

	public void show() {
		post(new Runnable() {
			@Override
			public void run() {
				setVisibility(View.VISIBLE);
				setOnClickListener(useKeyListener);
			}
		});
	}

	public void dismiss() {
		post(new Runnable() {
			@Override
			public void run() {
				setVisibility(View.INVISIBLE);
				setOnClickListener(null);
			}
		});
	}
	
	private final OnClickListener useKeyListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Player player = GameWorld.player();
			float targetX = player.isMovingLeft() ? player.x() - player.width() / 2f : player.x() + player.width() / 2f;

			Entity target = GameWorld.pieces().collidableObjectAt((int) targetX, (int) (player.y()));

			if (target == null)
				return;
			
			if(target == SceneOneObjects.fenceInstance()){
				target.kill();
				dismiss();
			}
		}
	};
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(getVisibility() == View.INVISIBLE)
			return false;
	
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if(getVisibility() == View.INVISIBLE)
			return false;
	
		return super.onTouchEvent(ev);
	}
}
