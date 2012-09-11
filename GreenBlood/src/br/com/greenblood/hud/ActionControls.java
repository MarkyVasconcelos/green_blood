package br.com.greenblood.hud;

import br.com.greenblood.dev.Paints;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class ActionControls extends View {
    private boolean touchedJump;
    private boolean touchedAction;

    public ActionControls(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (event.getY() < getHeight() / 2)
                touchedAction = true;
            else
                touchedJump = true;
        }
        
        
        return true;
    }

    public boolean hasAction() {
        if (!touchedAction)
            return false;

        touchedAction = false;
        return true;
    }
    
    public boolean hasJumped() {
        if (!touchedJump)
            return false;

        touchedJump = false;
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        int middle = getHeight() / 2;
        canvas.drawRect(0, 0, getWidth() - 1, getHeight(), Paints.STROKE_RED);
        canvas.drawLine(0, middle, getWidth(), middle - 1, Paints.RED);

        canvas.restore();
    }

}
