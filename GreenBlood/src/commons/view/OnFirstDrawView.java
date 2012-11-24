package commons.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public abstract class OnFirstDrawView extends View {

    private boolean alreadyDrawed;

    public OnFirstDrawView(Context context) {
        super(context);
    }

    public OnFirstDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void onFirstDraw();

    @Override
    public void onDraw(Canvas canvas) {
        if (!alreadyDrawed) {
            alreadyDrawed = true;
            onFirstDraw();
        }
    }

}
