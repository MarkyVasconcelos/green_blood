package br.com.greenblood.hud;

import br.com.greenblood.dev.Paints;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ActionControls extends View {
	private boolean touchedAction, stillTouchingAction;
    private boolean touchedJump, stillTouchingJump;

    public ActionControls(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            if (event.getY() < getHeight() / 2)
                touchAction();
			else
				touchJump();
        }
        
        if (event.getAction() == MotionEvent.ACTION_UP) {
            clearFlags();
            return true;
        }
        
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            if (event.getY() < getHeight() / 2)
                touchingAction();
            else
                touchingJump();
        }
        
        return true;
    }

	private void clearFlags() {
		touchedJump = false;
		stillTouchingJump = false;
		touchedAction = false;
		stillTouchingAction = false;
	}


    private void touchAction() {
    	clearFlags();
    	touchedAction = true;
    	touchingAction();
	}

	private void touchJump() {
		clearFlags();
		touchedJump = true;
		touchingJump();
	}
	
	private void touchingAction() {
		stillTouchingAction = true;
		stillTouchingJump = false;
	}

	private void touchingJump() {
		stillTouchingJump = true;
		stillTouchingAction = false;
	}
	
	public boolean isTouchingAction(){
		return stillTouchingAction;
	}
	
	public boolean isTouchingJump(){
		return stillTouchingJump;
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
        canvas.drawRect(0, 0, getWidth() - 1, getHeight(), Paints.TRANS_BLACK);
        canvas.drawRect(0, 0, getWidth() - 1, getHeight(), Paints.STROKE_BLUE);
        canvas.drawLine(0, middle, getWidth(), middle - 1, Paints.BLUE);


        canvas.restore();
    }

}
