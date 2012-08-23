package br.com.greenblood.pieces;

import br.com.greenblood.core.GameCore;
import br.com.greenblood.core.PiecesManager;
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
    private MoveDirection moving;

    public MovableEntity(Rect bounds, Rect boundingBox, float speed) {
        super(bounds, boundingBox);
        this.speed = speed * GameCore.scale();
    }

    @Override
    public void processLogics(long uptime) {
        if (uptime == 0)
            return;

        Vector2D step = direction.multiply(speed / (float) uptime);

        WorldMap map = GameWorld.map();

        if(!map.isSolid(pos().x(), boundingBottom()))
            Gravity.apply(this, uptime);
        
        if(step.y() > 0)
            if (map.isSolid(pos().x(), boundingBottom())) {
                int tileY = GameCore.tilesToPixels(GameCore.pixelsToTiles(boundingBottom()));
                step.setY(tileY - boundingBottom());
                direction.setY(0);
            }

        if (step.x() < 0)
            moving = MoveDirection.Left;
        else if (step.x() > 0)
            moving = MoveDirection.Right;
        
        if (step.x() != 0){
            if(movingLeft() && map.isSolid(boundingLeft(), boundingBottom() - 1)){
                direction.setX(0);
                step.setX(0);
            }else if (movingRight() && map.isSolid(boundingRight(), boundingBottom() - 1)){
                direction.setX(0);
                step.setX(0);
            }
        }
        
        float targetX = movingLeft() ? x() - width() / 2f : x() + width() / 2f;
        Entity target = GameWorld.world().pieces().entityAt((int) targetX, (int) (y()));
        if(target != null){
            direction.setX(0);
            step.setX(0);
        }
        
        pos().plusMe(step);
    }
    
    protected boolean movingLeft(){
        return moving == MoveDirection.Left;
    }
    
    protected boolean movingRight(){
        return moving == MoveDirection.Right;
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
    
    
    private float boundingBottom() {
        return (int) (pos().y() + boundingHeight() / 2);
    }
    
    private float boundingLeft() {
        return (int) (pos().x() - boundingWidth() / 2);
    }
    
    private float boundingRight() {
        return (int) (pos().x() + boundingWidth() / 2);
    }

    private enum Walking {
        Ground, Jumping, Falling;
    }
    
    private enum MoveDirection {
        Left, Right;
    }
}
