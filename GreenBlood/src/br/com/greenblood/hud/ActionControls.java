package br.com.greenblood.hud;

import br.com.greenblood.dev.Paints;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class ActionControls extends View {
    private boolean hasTouched;

    public ActionControls(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            hasTouched = true;
        return true;
    }

    public boolean hasTouched() {
        if (!hasTouched)
            return false;

        hasTouched = false;
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

//        canvas.drawRect(0, 0, getWidth()-1, getHeight(), Paints.BLACK);
        canvas.drawRect(0, 0, getWidth()-1, getHeight(), Paints.STROKE_BLUE);

        canvas.restore();
    }

}
