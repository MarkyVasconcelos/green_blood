package br.com.greenblood.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import commons.awt.Listener;
import commons.view.LayoutParamsFactory;

public class DialogView extends FrameLayout {

	private final TextView view;

	public DialogView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setBackgroundColor(Color.parseColor("#aa2a2a2a"));
		
		view = new TextView(context);
		view.setGravity(Gravity.CENTER);
		view.setText("Moasmodmaodmasodmaso");
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
	
	public void display(final String txt, final Listener<Void> onTouchListener){
		post(new Runnable() {
			@Override
			public void run() {
				setVisibility(VISIBLE);
				view.setText(txt);	
				setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						onTouchListener.on(null);
						setOnClickListener(null);
					}
				});
			}
		});
	}

}
