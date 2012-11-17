package br.com.greenblood.pieces;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.hud.PlayerStatsView;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.util.ImageLoader;

public class Player extends Character {
    private DirectionalControls controls;
    private ActionControls actions;
    
	private final Sprite walkingSprite = new WalkingSprite();
	private final Sprite punchingSprite = new PunchingSprite();
	private final Sprite jumpingSprite = new JumpingSprite();
	private final Sprite fallingSprite = new FallingSprite();
	
	private static final int JUMPING = 3;
	private static final int FALLING = 4;
	private PlayerStatsView statsView;
	
	private int maxHelth = 10;
	private int currentHelth = maxHelth;
	
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

        if (actions.hasJumped())
            dir().setY(-1.2f);
        
        if(actions.hasAction())
         	punch();
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
    }

    public void setActionControls(ActionControls actions) {
        this.actions = actions;
    }

    private static final class StandingSprite extends Sprite {
        public StandingSprite(){
            super(new Bitmap[] { ImageLoader.image("player/player_stand.png") }
            			,0, 0, true);
        }
    }
    
    private final class FallingSprite extends Sprite {
        public FallingSprite(){
            super(new Bitmap[] { ImageLoader.image("player/player_falling.png"),
            			}, 0, 100, true);
        }
    }
    
    private final class JumpingSprite extends Sprite {
        public JumpingSprite(){
            super(new Bitmap[] { ImageLoader.image("player/player_jumping.png"),
            			}, 0, 100, true);
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
            			}, 0, 100, true);
        }
    }
    private final class PunchingSprite extends Sprite{
        public PunchingSprite() {
            super(new Bitmap[] { ImageLoader.image("stick_punch.png"),
            					 ImageLoader.image("stick_punch_1.png"), 
            					 ImageLoader.image("stick_punch_2.png") 
            		    }, 400, 80, false);
        }
    }
	@Override
	public void hit() {
		currentHelth--;
		statsView.setValue(currentHelth);
		//TODO: Die
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
}
