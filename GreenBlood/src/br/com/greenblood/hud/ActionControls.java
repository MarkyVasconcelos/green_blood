package br.com.greenblood.hud;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.hud.util.ButtonStateImageHolder;
import br.com.greenblood.util.ImageLoader;

public class ActionControls extends View {
	//TODO Create directionals
	private ButtonStateImageHolder atk, jump, grab;
	
	private boolean touchedAction, stillTouchingAction;
    private boolean touchedJump, stillTouchingJump;

    public ActionControls(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        atk = new ButtonStateImageHolder(ImageLoader.image("huds/atk_unpressed.png"), ImageLoader.image("huds/atk_pressed.png"));
        jump = new ButtonStateImageHolder(ImageLoader.image("huds/jump_unpressed.png"), ImageLoader.image("huds/jump_pressed.png"));
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

        Rect half = new Rect(0,0,getWidth(), getHeight() / 2);
        canvas.drawBitmap(atk.unpressed(), atk.size(), half, Paints.BLANK);
        half.offset(0, half.height());
        canvas.drawBitmap(jump.unpressed(), jump.size(), half, Paints.BLANK);

        canvas.restore();
    }
}
