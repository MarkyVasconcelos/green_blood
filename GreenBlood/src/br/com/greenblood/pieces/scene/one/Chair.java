package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.digitalpages.commons.awt.Listener;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

public class Chair extends StaticObject {
	private final Player player;
	private boolean grabbed = false;
	private boolean passingTroughThis;
	private int originalY;
	
	public Chair(Rect bounds) {
		super(bounds);

		setSprite(new Sprite(
				new Bitmap[] { ImageLoader.image("boss/chair.PNG") },
				0, false));
		
		setCollidable(true);
		player = GameWorld.player();
	}

	@Override
	public void processLogics(long uptime) {
		Rect playerBounds = player.currentBoundingBounds();
		
		if(grabbed){
			pos().set(playerBounds.centerX(), playerBounds.top);
			return;
		}
			
		
		Rect thisBounds = currentBounds();
		if(Rect.intersects(playerBounds, thisBounds) && !passingTroughThis){
			player.setOnNextActionListener(grabListener);
			passingTroughThis = true;
		}else if(passingTroughThis){
			passingTroughThis = false;
			player.setOnNextActionListener(null);
		}
	}
	
	
	private final Listener<Void> grabListener = new Listener<Void>() {
		@Override
		public boolean on(Void t) {
			grabbed = true;
			originalY = currentBounds().bottom;
			player.setOnNextActionListener(ungrabListener);
			return false;
		}
	};
	
	private final Listener<Void> ungrabListener = new Listener<Void>() {
		@Override
		public boolean on(Void t) {
			grabbed = false;
			pos().setY(originalY - height() / 2);
			return false;
		}
	};
	

}
