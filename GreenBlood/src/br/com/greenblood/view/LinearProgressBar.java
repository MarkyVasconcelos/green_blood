package br.com.greenblood.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import commons.view.OnFirstDrawView;

public class LinearProgressBar extends HighUpDisplay {
    private float progress;

    private ProgressCalculator calc;

    private Paint foreground;
    private Paint background;
    private Paint upground;

    private RectF back;
    private RectF fore;
    private RectF up; 

    public LinearProgressBar(Rect bounds) {
    	super(bounds);
    	
        progress = 0;

        foreground = new Paint();
        foreground.setColor(Color.parseColor("#EE2222"));
        upground = new Paint();
        upground.setColor(Color.parseColor("#FF1111"));
        upground.setAlpha(180);

        background = new Paint();
        background.setColor(Color.parseColor("#000000"));

    }

    @Override
    public void onFirstDraw() {
        back = new RectF(left(), top(), right(), bottom());
        
        fore = new RectF(left(), top(), right(), bottom());
        up = new RectF(left(), top() + 3, right(), height());
    }

    public void setValue(int value) {
        progress = calc.getPercent(value);

        float prog = ((float)width()) / 100 * progress;
        fore =  new RectF(left(), top(), left() + prog, bottom());
        up = new RectF(left(), top() + 3, left() + prog, height());
    }

    public void setTotal(int totalPages) {
        calc = new ProgressCalculator(totalPages);
    }

    private class ProgressCalculator {
        private float total;

        public ProgressCalculator(float total) {
            this.total = total;
        }

        public float getPercent(float currentProccess) {
            return (currentProccess * 100f) / total;
        }
    }

	@Override
	public void draw(Canvas canvas, Rect surfaceView) {
		   if (calc == null)
	            return;

	        canvas.save();
	        canvas.drawRoundRect(back, 5, 5, background);
	        canvas.drawRoundRect(fore, 5, 5, foreground);
	        canvas.drawRoundRect(up, 5, 5, upground);
	        canvas.restore();		
	}
}