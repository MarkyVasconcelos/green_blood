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
        
        walking = new AnimatedSprite(new Bitmap[]{ImageLoader.image("stick.png"),ImageLoader.image("stick_1.png")}, 0, null, 100,true);
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
        return new AnimatedSprite(new Bitmap[]{ImageLoader.image("stick_punch.png")}, 400, new Listener<Void>() {
            @Override
            public void fire(Void t) {
                image = walking;
                punch();
            }
        },0,false);
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
        private final Bitmap[] imgs;
        private final Listener<Void> onAnimationEndListener;
        private long duration;
        private boolean fired;
            
        private int currentSprite;
        private int elapsedSprites;
        private long tick;
        private final int frameDelay;
        private final boolean loop;
        
        public AnimatedSprite(Bitmap[] imgs, long duration, Listener<Void> onAnimationEndListener, int frameDelay, boolean loop){
            this.imgs = imgs;
            this.duration = duration;
            this.onAnimationEndListener = onAnimationEndListener;
            this.frameDelay = frameDelay;
            this.loop = loop;
        }
        
        public Bitmap current(){
            return imgs[currentSprite];
        }
        
        public void update(long uptime){
            if(loop){
                tick += uptime;
                if(tick > frameDelay){
                    currentSprite = ++elapsedSprites % imgs.length;
                    System.out.println(currentSprite);
                    tick = 0;
                }
            }
            
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
