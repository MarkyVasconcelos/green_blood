package br.com.greenblood.view;

import br.com.digitalpages.commons.awt.Listener;
import br.com.digitalpages.commons.view.util.LayoutParamsFactory;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

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

	public void hide() {
		setVisibility(View.INVISIBLE);
	}
	
	public void show() {
		setVisibility(View.VISIBLE);
	}
	
	/**
	 * Since setVisibility will be called trough game thread, this will post the
	 * action into UI-Thread
	 */
	public void setVisibility(final int visibility){
		post(new Runnable() {
			@Override
			public void run() {
				DialogView.super.setVisibility(visibility);
			}
		});
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(getVisibility() == View.INVISIBLE)
			return false;
	
		return super.dispatchTouchEvent(ev);
	}
	
	public void display(final String txt, final Listener<Void> onTouchListener){
		show();
		
		post(new Runnable() {
			@Override
			public void run() {
				view.setText(txt);				
			}
		});
		
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				onTouchListener.on(null);
				view.setOnClickListener(null);
			}
		});
	}

}
