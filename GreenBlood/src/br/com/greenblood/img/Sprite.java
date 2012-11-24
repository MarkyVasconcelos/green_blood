package br.com.greenblood.img;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

import commons.awt.Listener;

public class Sprite {
        private final Bitmap[] imgs;
        private List<Listener<Void>> onAnimationEndListeners;
        private long duration;
        private boolean fired;
            
        private int currentSprite;
        private int elapsedSprites;
        private long tick;
        private final int frameDelay;
        private final boolean loop;
        private final long originalDuration;
		private int id;
        
        public Sprite(Bitmap[] imgs, long duration, boolean loop){
            this.imgs = imgs;
            originalDuration = duration;
            this.duration = duration;
            this.frameDelay = (int) (duration / imgs.length);
            this.loop = loop;
            
            onAnimationEndListeners = new  ArrayList<Listener<Void>>();
        }
        
        public void addOnAnimationEndListener(Listener<Void> onAnimationEndListener){
			this.onAnimationEndListeners.add(onAnimationEndListener);
        }

		public Bitmap current(){
            return imgs[currentSprite];
        }
        
        public void reset(){
            fired = false;
            duration = originalDuration;
            currentSprite = 0;
        }
        
        public void update(long uptime){
        	tick += uptime;
            if(tick > frameDelay){
            	if(!loop)
            		currentSprite = ++currentSprite == imgs.length ? imgs.length - 1 : currentSprite;
            	else
            		currentSprite = ++elapsedSprites % imgs.length;
                tick = 0;
            }
            
            if(loop || fired)
                return;
            
            duration -= uptime;
            if(duration <= 0){
            	for(Listener<Void> onAnimationEndListener : onAnimationEndListeners)
            		onAnimationEndListener.on(null);
            	
            	onAnimationEndListeners = new ArrayList<Listener<Void>>();
                fired = true;
            }
        }

		public Sprite id(int id) {
 			this.id = id;
			return this;
		}
		
		public int id(){
			return id;
		}
    }