package br.com.greenblood.pieces.movable;

import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Gravity;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.world.GameWorld;
import br.com.greenblood.world.WorldMap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public abstract class MovableEntity extends Entity {
    private final Vector2D direction = new Vector2D();
    private final Vector2D acceleration = new Vector2D(0.08f, 0);
    private final float speed;
    private Walking walking;
    private MoveDirection moving;
	private Rect boundingBox;
	private boolean lockDirection;
	
    public MovableEntity(Rect bounds, Rect boundingBox, float speed) {
        super(bounds);
        this.speed = speed * GameCore.scale();
        
        if(boundingBox == null)
            boundingBox = new Rect(bounds);
        
        
        this.boundingBox = boundingBox;
        this.boundingBox.left *= GameCore.scale();
        this.boundingBox.top *= GameCore.scale();
        this.boundingBox.right *= GameCore.scale();
        this.boundingBox.bottom *= GameCore.scale();
    }
    
    @Override
    public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
    	if(image().current() == null)
    		return;
        canvas.save();
        
        Rect currentBounds = currentBounds();
        currentBounds.offset((int)offset.x(), (int)offset.y());
        
        float scale = (float) currentBounds.width() / (float) image().current().getWidth();
        
        boolean leftMovement = movingLeft();
        
        Matrix matrix = new Matrix();
        matrix.setScale(leftMovement ? -scale : scale, scale);
        matrix.postTranslate(leftMovement ? currentBounds.right : currentBounds.left, currentBounds.top);

        canvas.drawBitmap(image().current(), matrix, Paints.BLANK);
        
        canvas.restore();
    }


    @Override
    public void processLogics(long uptime) {
        if (uptime == 0)
            return;

        Vector2D step = direction.multiply(speed / (float) uptime);

        WorldMap map = GameWorld.map();
        
        if(walking != Walking.Controlled){
	        if(!map.isSolid(pos().x(), boundingBottom()) && walking != Walking.Ground)
	            Gravity.apply(this, uptime);
	        
	        if(step.y() > 0)
	            if (map.isSolid(pos().x(), boundingBottom())) {
	                int tileY = GameCore.tilesToPixels(GameCore.pixelsToTiles(boundingBottom()));
	                step.setY(tileY - boundingBottom());
	                direction.setY(0);
	            }
	        
	        MoveDirection currentDirection = null;
	
	        if (step.x() < 0)
	        	currentDirection = MoveDirection.Left;
	        else if (step.x() > 0)
	        	currentDirection = MoveDirection.Right;
	        
	        if(!lockDirection && currentDirection != null)
	        	moving = currentDirection;
	        
	        if (step.x() != 0){
	            if(MoveDirection.isLeft(currentDirection) && map.isSolid(boundingLeft(), boundingBottom() - 1)){
	                direction.setX(0);
	                step.setX(0);
	            }else if (MoveDirection.isRight(currentDirection) && map.isSolid(boundingRight(), boundingBottom() - 1)){
	                direction.setX(0);
	                step.setX(0);
	            }
	        }
        
	        float targetX = movingLeft() ? x() - width() / 2f : x() + width() / 2f;
	        Entity target = GameWorld.pieces().punchCollidableEntityAt((int) targetX, (int) (y()));
	        if(target != null && target.isCollidable()){
	            direction.setX(0);
	            step.setX(0);
	        }else{
				Entity obj = GameWorld.pieces().collidableObjectAt((int) targetX, (int) y());
				if (obj != null) {
					direction.setX(0);
					step.setX(0);
				}
	        }
	        
	        float targetY = y() + height() / 2f + step.y();
	        Entity obj = GameWorld.pieces().collidableObjectAt((int) x(), (int) targetY);
	        if(obj != null){
	            direction.setY(0);
	            step.setY(obj.currentBounds().top - currentBoundingBounds().bottom + 1);
	        }
        }
        
        pos().plusMe(step);
    }
    

	@Override
    public void processAnimationLogics(long uptime) {
    	image().update(uptime);
    }
    
	
	protected void lockDirection() {
		lockDirection = true;
	}

	protected void unlockDirection() {
		lockDirection = false;
	}
    
    public boolean movingLeft(){
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
    
    public Rect boundingBox() {
        return boundingBox;
    }
    
    public int boundingHeight() {
        return boundingBox().height();
    }
    
    public int boundingWidth() {
        return boundingBox().width();
    }
    
    public Rect currentBoundingBounds() {
        int centerX = boundingWidth() / 2;
        int centerY = boundingHeight() / 2;
        return new Rect((int) x() - centerX, (int) y() - centerY, (int) x() + centerX, (int) y() + centerY);
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
    
	protected boolean isControlledWalking() {
		return walking == Walking.Controlled;
	}
    
    public void walkingOnGround(){
    	walking = Walking.Ground;
    }
    
    public void controlledWalking(){
    	walking = Walking.Controlled;
    }

    public void walkingOnAir(){
    	walking = Walking.Jumping;
    }

    private enum Walking {
        Ground, Jumping, Falling, Controlled;
    }
    
    private enum MoveDirection {
        Left, Right;
        
        public static boolean isLeft(MoveDirection dir){
        	return dir == Left;
        }
        
        public static boolean isRight(MoveDirection dir){
        	return dir == Right;
        }
    }
}
