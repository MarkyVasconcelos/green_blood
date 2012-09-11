package br.com.greenblood.pieces;

import br.com.greenblood.core.GameCore;
import br.com.greenblood.math.Gravity;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.world.GameWorld;
import br.com.greenblood.world.WorldMap;
import br.com.greenblood.world.map.Tile;
import android.graphics.Movie;
import android.graphics.Rect;

public abstract class MovableEntity extends Entity {
    private final Vector2D direction = new Vector2D();
    private final Vector2D acceleration = new Vector2D(0.08f, 0);
    private final float speed;
    private MoveDirection moving;
    private Walking walk;

    public MovableEntity(Rect bounds, Rect boundingBox, float speed) {
        super(bounds, boundingBox);
        this.speed = speed * GameCore.scale();
        walk = Walking.Ground;
    }

    @Override
    public void processLogics(long uptime) {
        if (uptime == 0)
            return;

        Vector2D step = direction.multiply(speed / (float) uptime);

        if(step.y() != 0)
            walk = step.y() > 0 ? Walking.Falling : Walking.Jumping;
        else
            walk = Walking.Ground;
            
        walk.proccessCollisions(this, step, uptime);
        
        float targetX = movingLeft() ? x() - width() / 2f : x() + width() / 2f;
        Entity target = GameWorld.pieces().entityAt((int) targetX, (int) (y()));
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
        Ground {
            @Override
            public void proccessCollisions(MovableEntity movableEntity, Vector2D step, long uptime) {
                WorldMap map = GameWorld.map();

                float y = movableEntity.boundingBottom();
                float x = movableEntity.pos().x();

                boolean solidHill = false;
                Tile t = map.tileAt(x, y);
                if(t != null){
                    int solid = t.y(step.x());
                    solidHill = true;
                    if (solid != 0) {
                        step.setY(solid);
                        movableEntity.direction.setY(0);
                    }
                }else if(!map.isSolid(x, y))
                    Gravity.apply(movableEntity, uptime);
                
                if (step.x() < 0)
                    movableEntity.moving = MoveDirection.Left;
                else if (step.x() > 0)
                    movableEntity.moving = MoveDirection.Right;
                
                if (step.x() != 0 && !solidHill)
                    if ((movableEntity.movingLeft() && map.isSolid(movableEntity.boundingLeft(), y - 1))
                            || (movableEntity.movingRight() && map.isSolid(movableEntity.boundingRight(), y - 1))) {
                        movableEntity.direction.setX(0);
                        step.setX(0);
                    }
                
            }
        }, Jumping {
            @Override
            public void proccessCollisions(MovableEntity movableEntity, Vector2D step, long uptime) {
                Gravity.apply(movableEntity, uptime);
            }
        }, Falling {
            @Override
            public void proccessCollisions(MovableEntity movableEntity, Vector2D step, long uptime) {
                WorldMap map = GameWorld.map();

                float futureY = movableEntity.boundingBottom() + step.y();
                if(!map.isSolid(movableEntity.pos().x(), futureY + 1))
                    Gravity.apply(movableEntity, uptime);
                
                int solid = map.collids(movableEntity.pos().x(), futureY);
                if (solid > 0) {
                    step.setY(-solid + step.y());
                    movableEntity.direction.setY(0);
                }
            }
        };

        public abstract void proccessCollisions(MovableEntity movableEntity, Vector2D step, long uptime);
    }
    
    private enum MoveDirection {
        Left, Right, None;
    }
}
