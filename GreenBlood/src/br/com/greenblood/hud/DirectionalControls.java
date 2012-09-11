package br.com.greenblood.hud;

import br.com.greenblood.dev.Paints;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class DirectionalControls extends View {
    private volatile boolean holdingLeft;
    private volatile boolean holdingRight;

    public DirectionalControls(Context context) {
        super(context);
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
        canvas.drawRect(0, 0, getWidth() - 1, getHeight(), Paints.STROKE_RED);
        canvas.drawLine(middle, 0, middle - 1, getHeight(), Paints.RED);

        canvas.restore();
    }

    public boolean isHoldingLeft() {
        return holdingLeft;
    }

    public boolean isHoldingRight() {
        return holdingRight;
    }

}
