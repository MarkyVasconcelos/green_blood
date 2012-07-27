package br.com.greenblood.pieces;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.math.Vector2D;

public class Player extends MovableEntity {
    private DirectionalControls controls;
    private Paint cyan;
    private ActionControls actions;

    public Player(Rect bounds) {
        super(bounds, 180);

        cyan = new Paint();
        cyan.setColor(Color.CYAN);
    }

    @Override
    public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
        Rect bounds = new Rect(bounds());
        bounds.offset((int) (offset.x() + pos().x()), (int) (offset.y() + pos().y()));
        canvas.drawRect(bounds, cyan);
    }

    @Override
    public void processLogics(long uptime) {
        if (!controls.isHoldingLeft() && !controls.isHoldingRight())
            dir().set(0, dir().y());

        if (controls.isHoldingLeft())
            dir().set(-1, 0);
        if (controls.isHoldingRight())
            dir().set(1, 0);

        if (actions.hasTouched())
            dir().set(0, -1);

        super.processLogics(uptime); // Move
    }

    public void setControls(DirectionalControls controls) {
        this.controls = controls;
    }

    public void setActionControls(ActionControls actions) {
        this.actions = actions;
    }

}
