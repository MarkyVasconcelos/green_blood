package br.com.greenblood.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import br.com.digitalpages.commons.view.OnFirstDrawView;

public class LinearProgressBar extends OnFirstDrawView {
    private float progress;

    private ProgressCalculator calc;

    private Paint foreground;
    private Paint background;
    private Paint upground;

    private RectF back;
    private RectF fore;
    private RectF up; 

    public LinearProgressBar(Context context) {
        super(context);
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
        back = new RectF(0, 0, getWidth(), 10);

        float prog = ((float)getWidth()) / 100 * progress;
        fore = new RectF(0, 0, prog, 10);
        up = new RectF(0, 3, prog, 10);
    }

    public void onDraw(Canvas canvas) {
        if (calc == null)
            return;

        super.onDraw(canvas);

        canvas.save();
        canvas.drawRoundRect(back, 5, 5, background);
        canvas.drawRoundRect(fore, 5, 5, foreground);
        canvas.drawRoundRect(up, 5, 5, upground);
        canvas.restore();
    }

    public void setValue(int value) {
        progress = calc.getPercent(value);

        float prog = ((float)getWidth()) / 100 * progress;
        fore = new RectF(0, 0, prog, 10);
        up = new RectF(0, 3, prog, 10);
        
        invalidate();
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
}