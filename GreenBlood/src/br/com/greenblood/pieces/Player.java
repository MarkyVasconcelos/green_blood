package br.com.greenblood.pieces;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.img.AnimatedSprite;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.util.Listener;
import br.com.greenblood.world.GameWorld;

public class Player extends MovableEntity {
    private DirectionalControls controls;
    private ActionControls actions;
    
    private final AnimatedSprite walking = new WalkingSprite();
    private final AnimatedSprite punching = new PunchSprite();

    
    public Player(Rect bounds, Rect boundingBox) {
        super(bounds, boundingBox, 260);
        
        setImage(walking);
    }

    @Override
    public void processLogics(long uptime) {
        // TODO: Stop if collids
        if (!controls.isHoldingLeft() && !controls.isHoldingRight())
            dir().set(0, dir().y());

        if (controls.isHoldingLeft())
            desaccelerate();

        if (controls.isHoldingRight())
            accelerate();

        if (actions.hasJumped())
            dir().setY(-1);
        
        if(actions.hasAction())
            setImage(punching);
        
        super.processLogics(uptime); // Move
    }
    
    public void setControls(DirectionalControls controls) {
        this.controls = controls;
    }

    public void setActionControls(ActionControls actions) {
        this.actions = actions;
    }
    
    private void punch() {
        float targetX = movingLeft() ? x() - width() / 2f : x() + width() / 2f;
        
        Entity target = GameWorld.pieces().entityAt((int) targetX, (int) (y()));
        
        if(target == null)
            return;
        
        target.kill();
    }
    

    private final class WalkingSprite extends AnimatedSprite {
        public WalkingSprite(){
            super(new Bitmap[] { ImageLoader.image("stick.png"), ImageLoader.image("stick_1.png") }, 0, null, 100, true);
        }
    }
    private final class PunchSprite extends AnimatedSprite{
        public PunchSprite() {
            super(new Bitmap[] { ImageLoader.image("stick_punch.png"), ImageLoader.image("stick_punch_1.png"), ImageLoader.image("stick_punch_2.png") },
                    400, new Listener<Void>() {
                        @Override
                        public void fire(Void t) {
                            setImage(walking);
                            punch();
                        }
                    }, 80, true);
        }
    }
}
