package br.com.greenblood.pieces;

import br.com.greenblood.math.Vector2D;
import android.graphics.Rect;

public abstract class MovableEntity extends Entity {
    private final Vector2D direction;
    private float speed;
    private Vector2D acceleration;

    public MovableEntity(Rect bounds, float speed) {
        super(bounds);
        
        acceleration = new Vector2D(0.05f, 0);

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

    public Vector2D dir() {
        return direction;
    }
    
    public void accelerate(){
        direction.plusMe(acceleration);
        
        if(direction.x() > 1)
            direction.setX(1);
    }
    
    public void desaccelerate(){
        direction.minusMe(acceleration);
        if(direction.x() < -1)
            direction.setX(-1);
    }

    public int bottom() {
        return (int) (pos().y() + height() / 2);
    }

}
