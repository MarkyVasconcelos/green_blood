package br.com.greenblood.img;

import android.graphics.Bitmap;
import br.com.greenblood.util.Listener;

public class AnimatedSprite {
        private final Bitmap[] imgs;
        private final Listener<Void> onAnimationEndListener;
        private long duration;
        private boolean fired;
            
        private int currentSprite;
        private int elapsedSprites;
        private long tick;
        private final int frameDelay;
        private final boolean loop;
        private final long originalDuration;
        
        public AnimatedSprite(Bitmap[] imgs, long duration, Listener<Void> onAnimationEndListener, int frameDelay, boolean loop){
            this.imgs = imgs;
            originalDuration = duration;
            this.duration = duration;
            this.onAnimationEndListener = onAnimationEndListener;
            this.frameDelay = frameDelay;
            this.loop = loop;
        }
        
        public Bitmap current(){
            return imgs[currentSprite];
        }
        
        public void reset(){
            fired = false;
            duration = originalDuration;
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