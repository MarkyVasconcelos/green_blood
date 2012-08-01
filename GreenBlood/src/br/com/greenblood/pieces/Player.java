package br.com.greenblood.pieces;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.util.Listener;
import br.com.greenblood.world.GameWorld;

public class Player extends MovableEntity {
    private DirectionalControls controls;
    private ActionControls actions;
    private AnimatedSprite image;
    private final AnimatedSprite walking;
    
    public Player(Rect bounds) {
        super(bounds, 260);
        
        walking = new AnimatedSprite(ImageLoader.image("stick.png"), 0, null);
        image = walking;
    }

    @Override
    public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
        Rect currentBounds = currentBounds();
        currentBounds.offset((int)offset.x(), (int)offset.y());
        canvas.drawBitmap(image.current(), null, currentBounds, Paints.BLANK);
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

        if (actions.hasJumped()) {
            if (dir().x() == 0)
                dir().setY(-1);
            else
                dir().rotateMe(dir().x() < 0 ? 45 : -45);
        }
        
        if(actions.hasAction()){
            image = punching();
        }
        
        image.update(uptime);

        super.processLogics(uptime); // Move
    }
    
    private AnimatedSprite punching(){
        return new AnimatedSprite(ImageLoader.image("stick_punch.png"), 400, new Listener<Void>() {
            @Override
            public void fire(Void t) {
                image = walking;
                punch();
            }
        });
    }

    public void setControls(DirectionalControls controls) {
        this.controls = controls;
    }

    public void setActionControls(ActionControls actions) {
        this.actions = actions;
    }
    
    private void punch() {
        Entity target = GameWorld.world().pieces().entityAt((int) (x() + width() / 2 ), (int) (y()));
        
        if(target == null)
            return;
        
        target.kill();
    }
    
    private class AnimatedSprite {
        private final Bitmap img;
        private final Listener<Void> onAnimationEndListener;
        private long duration;
        private boolean fired;
        
        public AnimatedSprite(Bitmap img, long duration, Listener<Void> onAnimationEndListener){
            this.img = img;
            this.duration = duration;
            this.onAnimationEndListener = onAnimationEndListener;
        }
        
        public Bitmap current(){
            return img;
        }
        
        public void update(long uptime){
            if(fired || duration == 0)
                return;
            duration -= uptime;
            if(duration <= 0){
                onAnimationEndListener.fire(null);
                fired = true;
            }
        }
    }
}
