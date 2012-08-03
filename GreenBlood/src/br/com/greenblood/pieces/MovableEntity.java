package br.com.greenblood.pieces;

import br.com.greenblood.core.GameCore;
import br.com.greenblood.math.Gravity;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.world.GameWorld;
import br.com.greenblood.world.WorldMap;
import android.graphics.Rect;

public abstract class MovableEntity extends Entity {
    private final Vector2D direction = new Vector2D();
    private final Vector2D acceleration = new Vector2D(0.08f, 0);
    private final float speed;
    private Walking walking;

    public MovableEntity(Rect bounds, float speed) {
        super(bounds);
        this.speed = speed * GameCore.scale();
    }

    @Override
    public void processLogics(long uptime) {
        if (uptime == 0)
            return;

        Vector2D step = direction.multiply(speed / (float) uptime);

        WorldMap map = GameWorld.map();

        Walking dir = step.y() == 0 ? Walking.Ground : (step.y() > 0 ? Walking.Falling : Walking.Jumping);

        if (dir == Walking.Falling) {
            int nextSolidY = map.nextSolidDistance(pos().x(), bottom());
            if (nextSolidY != -1 && -nextSolidY <= step.y()) {
                step.setY(-nextSolidY);
                direction.setY(0);
            } else if(map.isSolid(pos().x(), bottom())){
                direction.setY(0);
                step.setY(0);
            }else
                Gravity.apply(this, uptime);
        }

        if (dir == Walking.Jumping)
            Gravity.apply(this, uptime);

        if (dir == Walking.Ground)
            if (!map.isSolid(pos().x(), bottom()))
                Gravity.apply(this, uptime);
            else
                direction.setY(0);

        pos().plusMe(step);
    }

    public Vector2D dir() {
        return direction;
    }

    public void accelerate() {
        if (direction.x() < 0)
            direction.setX(0);

        direction.plusMe(acceleration);

        if (direction.x() > 1)
            direction.setX(1);
    }

    public void desaccelerate() {
        if (direction.x() > 0)
            direction.setX(0);

        direction.minusMe(acceleration);
        if (direction.x() < -1)
            direction.setX(-1);
    }

    public int bottomTile() {
        return GameCore.pixelsToTiles(bottom());
    }

    public int bottom() {
        return (int) (pos().y() + height() / 2);
    }

    private enum Walking {
        Ground, Jumping, Falling;
    }
}
