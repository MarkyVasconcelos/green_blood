package br.com.greenblood.pieces.movable;

import br.com.greenblood.img.Sprite;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.util.Listener;
import br.com.greenblood.world.GameWorld;
import android.graphics.Rect;

//statefull animations
public abstract class Character extends MovableEntity {

	protected boolean blockInput;
	
	private static final int STANDING = 0;
	private static final int WALKING = 1;
	private static final int PUNCHING = 2;

	private final Sprite standingSprite;

	public Character(Rect bounds, Rect boundingBox, float speed, Sprite standingSprite) {
		super(bounds, boundingBox, speed);
		this.standingSprite = standingSprite;
		
		setSprite(standingSprite.id(STANDING));
	}
	
	@Override
	public final void processLogics(long uptime) {
		if(!blockInput)
			processSelfLogics(uptime);
		
		super.processLogics(uptime);
		
		processMoveLogicsOnAnimation(dir());
	}
	
	protected void processMoveLogicsOnAnimation(Vector2D dir){
		if(blockInput)
			return;
		
		if(Math.abs(dir.x()) > 0)
			execute(walkingSprite().id(WALKING), false);
		else
			execute(standingSprite.id(STANDING), false);
	}
	
	protected abstract void processSelfLogics(long uptime);

	protected void execute(Sprite sprite, boolean blockInput) {
		if(sprite.id() != image().id()){
			this.blockInput = blockInput;
			setSprite(sprite);
		}
	}
	
	protected void punch(){
		Sprite punchingSprite = punchingSprite();
		punchingSprite.addOnAnimationEndListener(new Listener<Void>() {
            @Override
            public void fire(Void t) {
            	executePunch();
                execute(standingSprite.id(STANDING), false);
            }
        });
		execute(punchingSprite.id(PUNCHING), true);
	}

	private void executePunch(){
		float targetX = movingLeft() ? x() - width() / 2f : x() + width() / 2f;

		Character target = GameWorld.pieces().punchCollidableEntityAt((int) targetX, (int) (y()));

		if (target == null)
			return;

		target.hit();
	}
	
	protected Sprite standingSprite(){
		return standingSprite;
	}
	
	public abstract void hit();
	protected abstract Sprite walkingSprite();
	protected abstract Sprite punchingSprite();
	
	
}