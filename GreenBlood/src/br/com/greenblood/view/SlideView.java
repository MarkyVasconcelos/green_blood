package br.com.greenblood.view;

import br.com.greenblood.util.ImageLoader;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import commons.awt.Listener;
import commons.view.LayoutParamsFactory;

public class SlideView extends FrameLayout {

	private final ImageView view;

	public SlideView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setBackgroundColor(Color.alpha(255));
		
		view = new ImageView(context);
		view.setScaleType(ScaleType.CENTER);
		view.setImageBitmap(ImageLoader.image("slides/fire_sprite.PNG"));
		addView(view, LayoutParamsFactory.newMatchFrame());
	}

	/**
	 * Since hide() will be called trough game thread, this will post the
	 * action into UI-Thread
	 */
	public void hide() {
		post(new Runnable() {
			@Override
			public void run() {
				setVisibility(View.INVISIBLE);
			}
		});
	}
	
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
	
	public void displayFinalCut(){
		post(new Runnable() {
			@Override
			public void run() {
				view.setImageBitmap(ImageLoader.image("slides/theend.PNG"));
				view.setScaleType(ScaleType.FIT_XY);
				setVisibility(VISIBLE);
			}
		});
	}
	
	//This will display only once
	public void display(final Listener<Void> onTouchListener){
		post(new Runnable() {
			@Override
			public void run() {
				setVisibility(VISIBLE);
				setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						view.setImageBitmap(ImageLoader.image("slides/huds.PNG"));
						view.setScaleType(ScaleType.FIT_START);
						setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								hide();
								onTouchListener.on(null);
								setOnClickListener(null);
							}
						});
						
					}
				});
			}
		});
	}
	

}
