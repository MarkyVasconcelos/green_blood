package br.com.greenblood.pieces;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.AnimatedSprite;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.util.Listener;
import br.com.greenblood.world.GameWorld;

public class Enemy extends MovableEntity {
    private static final Random rdm = new Random();
    
    private final AnimatedSprite walking = new WalkingSprite();
    private final AnimatedSprite punching = new PunchSprite();

    public Enemy(Rect bounds, Rect boundingBounds) {
        super(bounds, boundingBounds, 130 + rdm.nextInt(130));
        setSprite(walking);
        setCollidable(true);
    }

    @Override
    public void processLogics(long uptime) {
        Player player = GameWorld.player();

        int dist = (int) (player.x() - x());
        if (dist > 0 && dist < 200)
            accelerate();
        else if (dist < 0 && dist > -200)
            desaccelerate();
        
        if(Math.abs(dist) < 100 && image() != punching)
            setSprite(punching);
        
        super.processLogics(uptime); // Move
    }

    private void punch() {
        float targetX = movingLeft() ? x() - width() / 2f : x() + width() / 2f;

        Entity target = GameWorld.pieces().entityAt((int) targetX, (int) (y()));

        if (target == null || target instanceof Enemy || target instanceof StaticObject)
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
                            setSprite(walking);
                            punch();
                        }
                    }, 80, true);
        }
    }
}
