package br.com.greenblood.pieces;

import br.com.greenblood.math.Vector2D;
import android.graphics.Rect;

public abstract class MovableEntity extends Entity {
    private final Vector2D direction;
    private float speed;

    public MovableEntity(Rect bounds, float speed) {
        super(bounds);

        direction = new Vector2D();
        this.speed = speed;
    }

    @Override
    public void processLogics(long uptime) {
        if(uptime == 0)
            return;
        
        float stepSpeed = speed / (float) uptime;
        Vector2D step = direction.multiply(stepSpeed);
        pos().plusMe(step);
    }

    protected Vector2D dir() {
        return direction;
    }
}
