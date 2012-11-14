package br.com.greenblood.hud;

import br.com.greenblood.dev.Paints;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DirectionalControls extends View {
    private volatile boolean holdingLeft;
    private volatile boolean holdingRight;

    public DirectionalControls(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isUp = event.getAction() == MotionEvent.ACTION_UP;
        if (isUp) {
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

        // canvas.drawRect(0, 0, getWidth()-1, getHeight(), Paints.BLACK);
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

}
