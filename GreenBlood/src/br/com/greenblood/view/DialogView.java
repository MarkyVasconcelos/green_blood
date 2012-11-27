package br.com.greenblood.view;

import br.com.greenblood.dev.Paints;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import commons.awt.Listener;
import commons.view.LayoutParamsFactory;

public class DialogView extends HighUpDisplay {

	private Paint bgPaint;
	private String txt;
	private Listener<Void> onTouchListener;

	public DialogView(Rect bounds) {
		super(bounds);
		
		bgPaint = new Paint(Color.parseColor("#aa2a2a2a"));
		setVisible(false);
	}

	/**
	 * Since hide() will be called trough game thread, this will post the
	 * action into UI-Thread
	 */
	public void hide() {
		setVisible(false);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if(super.isInvisible())
			return false;
	
//		setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				onTouchListener.on(null);
//				setOnClickListener(null);
//			}
//		});
		return super.onTouchEvent(ev);
	}
	
	public void display(final String txt, final Listener<Void> onTouchListener){
		this.txt = txt;
		this.onTouchListener = onTouchListener;
		setVisible(true);
	}

	@Override
	public void draw(Canvas canvas, Rect thisSize) {
		canvas.save();
		
		canvas.drawRect(thisSize, bgPaint);
		canvas.drawText(txt, thisSize.left,  thisSize.top, Paints.RED_WRITE);
		
		canvas.restore();
	}

}
