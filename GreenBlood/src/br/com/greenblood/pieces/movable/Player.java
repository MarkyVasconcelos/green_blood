package br.com.greenblood.pieces.movable;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.hud.PlayerStatsView;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.util.ImageLoader;

import commons.awt.Listener;

public class Player extends Character {
    private DirectionalControls controls;
    private ActionControls actions;
    
	private final Sprite walkingSprite = new WalkingSprite();
	private final Sprite punchingSprite = new PunchingSprite();
	private final Sprite jumpingSprite = new JumpingSprite();
	private final Sprite fallingSprite = new FallingSprite();
	private final Sprite backwardDash = new BackwardDashSprite();
	
	private static final int JUMPING = 3;
	private static final int FALLING = 4;
	private static final int DASHING = 5;
	private PlayerStatsView statsView;
	
	private int maxHelth = 10;
	private int currentHelth = maxHelth;
	private Listener<Integer> onTouchingActionListener;
	private Listener<Integer> onTouchingJumpListener;
	private Listener<Void> nextActionListener;
	
    public Player(Rect bounds, Rect boundingBox) {
        super(bounds, boundingBox, 180, new StandingSprite());
        setCollidable(true);
    }

    @Override
    public void processSelfLogics(long uptime) {
        if (!controls.isHoldingLeft() && !controls.isHoldingRight())
            dir().set(0, dir().y());

        if (controls.isHoldingLeft())
            desaccelerate();

        if (controls.isHoldingRight())
            accelerate();

        if(isControlledWalking()){
        	if(actions.isTouchingAction())
        		onTouchingActionListener.on((int) uptime);
        	
        	if(actions.isTouchingJump())
        		onTouchingJumpListener.on((int) uptime);
        }else{
	        if (actions.hasJumped()){
	        	walkingOnAir();
	            dir().setY(-1.2f);
	        }
	        
	        if(actions.hasAction())
	         	executeAction();
        }
    }
    
	private void executeAction() {
		if(nextActionListener != null)
			nextActionListener.on(null);
		else punch();
		
		nextActionListener = null;
	}

	protected void processMoveLogicsOnAnimation(Vector2D dir){
		if(blockInput)
			return;
		
		if(Math.abs(dir.y()) == 0){
			super.processMoveLogicsOnAnimation(dir);
			return;
		}
		
		if(dir.y() > 0)
			execute(fallingSprite.id(FALLING), false);
		else
			execute(jumpingSprite.id(JUMPING), false);
	}
    
    public void setControls(DirectionalControls controls) {
        this.controls = controls;
        this.controls.setOnDoubleTapListener(new Listener<Void>() {
			@Override
			public void on(Void t) {
				doBackwardDash();
			}
		});
    }
    
	@Override
    public void processAnimationLogics(long uptime) {
		if(walkingRightUntilBlock){
			accelerate();
			super.processLogics(uptime);
			if(dir().x() == 0){
				walkingRightUntilBlock = false;
				endListener.on(null);
			}
		}
    	image().update(uptime);
    }

    public void setActionControls(ActionControls actions) {
        this.actions = actions;
    }

    private static final class StandingSprite extends Sprite {
        public StandingSprite(){
            super(new Bitmap[] { ImageLoader.image("player/player_stand.png") }
            			,0, true);
        }
    }
    
    private final class FallingSprite extends Sprite {
        public FallingSprite(){
            super(new Bitmap[] { ImageLoader.image("player/player_falling.png"),
            			}, 0, true);
        }
    }
    
    private final class JumpingSprite extends Sprite {
        public JumpingSprite(){
            super(new Bitmap[] { ImageLoader.image("player/player_jumping.png"),
            			}, 0, true);
        }
    }
    
    private final class BackwardDashSprite extends Sprite {
        public BackwardDashSprite(){
            super(new Bitmap[] { ImageLoader.image("player/backward_jump/back_jump_1.png"),
            					 ImageLoader.image("player/backward_jump/back_jump_2.png"),
            					 ImageLoader.image("player/backward_jump/back_jump_3.png"),
            			}, 700, false);
        }
    }
    
    private final class WalkingSprite extends Sprite {
        public WalkingSprite(){
            super(new Bitmap[] { ImageLoader.image("player/player_walk_01.png"),
            					 ImageLoader.image("player/player_walk_02.png"),
            					 ImageLoader.image("player/player_walk_03.png"),
            					 ImageLoader.image("player/player_walk_04.png"),
            					 ImageLoader.image("player/player_walk_05.png"),
            					 ImageLoader.image("player/player_walk_06.png"),
            					 ImageLoader.image("player/player_walk_07.png") 
            			}, 700, true);
        }
    }
    private final class PunchingSprite extends Sprite{
        public PunchingSprite() {
            super(new Bitmap[] { ImageLoader.image("stick_punch.png"),
            					 ImageLoader.image("stick_punch_1.png"), 
            					 ImageLoader.image("stick_punch_2.png") 
            		    }, 565, false);
        }
        
        @Override
        public void update(long uptime) {
        	super.update(uptime);
        }
    }
	@Override
	public void hit() {
		currentHelth--;
		statsView.setValue(currentHelth);
		//TODO: Die
	}
	
	public void doBackwardDash() {
		lockDirection();
		backwardDash.addOnAnimationEndListener(new Listener<Void>() {
			@Override
			public void on(Void t) {
				unlockDirection();
				stop();
			}
		});
		dir().set(new Vector2D(isMovingLeft() ? 0.75f : -0.75f, -0.6f));
		execute(backwardDash.id(DASHING), true);
	}

	@Override
	protected Sprite walkingSprite() {
		return walkingSprite;
	}

	@Override
	protected Sprite punchingSprite() {
		return punchingSprite;
	}

	public void setStatsView(PlayerStatsView statsView) {
		this.statsView = statsView;
		statsView.setMaximumHealth(maxHelth);
	}
	
	public void stop() {
		execute(standingSprite(), false);
	}

	public void onTouchAction(Listener<Integer> onTouchingActionListener) {
		this.onTouchingActionListener = onTouchingActionListener;
	}

	public void onTouchJump(Listener<Integer> onTouchingJumpListener) {
		this.onTouchingJumpListener = onTouchingJumpListener;
	}

	public void setOnNextActionListener(Listener<Void> nextActionListener) {
		this.nextActionListener = nextActionListener;
	}
	
	//Please do it better
	private boolean walkingRightUntilBlock;
	private Listener<Void> endListener;
	public void walkRightUntilBlock(Listener<Void> endListener) {
		blockInput = true;
		this.endListener = endListener;
		walkingRightUntilBlock = true;
		setSprite(walkingSprite);
	}
	
}
