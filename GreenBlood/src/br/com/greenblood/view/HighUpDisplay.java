package br.com.greenblood.view;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class HighUpDisplay {
	private final Rect thisBounds;
	private int w, h;

	public HighUpDisplay(Rect bounds) {
		thisBounds = bounds;
		w = bounds.right - bounds.left;
		h = bounds.bottom - bounds.top;
	}
	
	public void draw(Canvas canvas) {
		if(firstDraw){
			onFirstDraw();
			firstDraw = false;
		}
		draw(canvas, thisBounds);
	}
	
	private boolean firstDraw = true;
	public void onFirstDraw(){ }
	
	public abstract void draw(Canvas canvas, Rect surfaceView);
	
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
}