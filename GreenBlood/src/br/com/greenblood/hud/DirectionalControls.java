package br.com.greenblood.hud;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import br.com.greenblood.dev.Paints;

import commons.awt.Listener;

public class DirectionalControls extends View {
    private volatile boolean holdingLeft;
    private volatile boolean holdingRight;
    private DoubleTapListener doubleTapListener = new DoubleTapListener();
    
    private Listener<Void> onDoubleTap; 

    public DirectionalControls(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	doubleTapListener.onTouchEvent(event);
    	
        if (event.getAction() == MotionEvent.ACTION_UP) {
            holdingLeft = holdingRight = false;
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN)
            holdingLeft = holdingRight = false;

        if (event.getX() < getWidth() / 2){
            holdingLeft = true;
            holdingRight = false;
        }else{
            holdingLeft = false;
            holdingRight = true;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        int middle = getWidth() / 2;

        canvas.drawRect(0, 0, getWidth()-1, getHeight(), Paints.TRANS_BLACK);
        canvas.drawRect(0, 0, getWidth() - 1, getHeight(), Paints.STROKE_BLUE);
        canvas.drawLine(middle, 0, middle - 1, getHeight(), Paints.BLUE);

        canvas.restore();
    }

    public boolean isHoldingLeft() {
        return holdingLeft;
    }

    public boolean isHoldingRight() {
        return holdingRight;
    }
	
	public Listener<Void> onDoubleTapListener() {
		return onDoubleTap;
	}

	public void setOnDoubleTapListener(Listener<Void> onDoubleTapListener) {
		this.onDoubleTap = onDoubleTapListener;
	}

	private class DoubleTapListener {
		private int tapCount;
		private long tapTick;
		private long downTick;
		
		private void onTouchEvent(MotionEvent evt) {
			long now = System.currentTimeMillis();
			if(evt.getAction() == MotionEvent.ACTION_DOWN){
				if(tapCount == 1)
					if(now - tapTick > 280)
						tapCount = 0;
				
				downTick = now;
			}
			
			if(evt.getAction() == MotionEvent.ACTION_UP){
				if(tapCount == 0){
					if(now - downTick < 280){
						tapCount++;
						tapTick = now;
					}
				} else if (tapCount == 1)
					if (!((now - tapTick) > 280))
						fire();
			}
			
		}

		private void fire() {
			onDoubleTap.on(null);
			tapCount = 0;
		}
	}
}
