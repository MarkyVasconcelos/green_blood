package br.com.greenblood.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public abstract class HighUpDisplay {
	private final Rect thisBounds;
	private int w, h;

	public HighUpDisplay(Rect bounds) {
		thisBounds = bounds;
		w = bounds.right - bounds.left;
		h = bounds.bottom - bounds.top;
		
		visible = true;
	}
	
	public void draw(Canvas canvas) {
		if(firstDraw){
			onFirstDraw();
			firstDraw = false;
		}
		
		if(visible)
			draw(canvas, thisBounds);
	}
	
	private boolean firstDraw = true;
	private boolean visible;
	public void onFirstDraw(){ }
	
	public abstract void draw(Canvas canvas, Rect thisSize);
	
	public int left(){
		return thisBounds.left;
	}
	
	public int top(){
		return thisBounds.top;
	}
	
	public int right(){
		return thisBounds.right;
	}
	
	public int bottom(){
		return thisBounds.bottom;
	}
	
	public int width(){
		return w;
	}
	
	public int height(){
		return h;
	}

	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}
	

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public boolean isInvisible() {
		return !isVisible();
	}

	public Rect bounds() {
		return thisBounds;
	}
}