package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

import commons.awt.Listener;

public class Chair extends StaticObject {
	private final Player player;
	private boolean grabbed = false;
	private boolean passingTroughThis;
	private int originalY;
	
	public Chair(Rect bounds) {
		super(bounds);

		setSprite(new Sprite(
				new Bitmap[] { ImageLoader.image("scene/chair.PNG") },
				0, false));
		
		setCollidable(true);
		player = GameWorld.player();
	}

	@Override
	public void processLogics(long uptime) {
		Rect playerBounds = player.currentBoundingBounds();
		
		if(grabbed){
			pos().set(playerBounds.centerX(), playerBounds.top);
			player.setOnNextActionListener(ungrabListener);
			return;
		}
			
		
		Rect thisBounds = currentBounds();
		if(Rect.intersects(playerBounds, thisBounds)){
			GameWorld.actions().displayGrab();
			player.setOnNextActionListener(grabListener);
			passingTroughThis = true;
		}else if(passingTroughThis){
			GameWorld.actions().displayPunch();
			player.setOnNextActionListener(null);
			passingTroughThis = false;
		}
	}
	
	
	private final Listener<Void> grabListener = new Listener<Void>() {
		@Override
		public void on(Void t) {
			grabbed = true;
			originalY = currentBounds().bottom;
			player.setOnNextActionListener(ungrabListener);
		}
	};
	
	private final Listener<Void> ungrabListener = new Listener<Void>() {
		@Override
		public void on(Void t) {
			grabbed = false;
			pos().setY(originalY - height() / 2);
		}
	};
	

}
