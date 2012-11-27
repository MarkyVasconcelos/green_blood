package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.history.mock.SceneOneObjects;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

import commons.awt.Listener;

public class FallingTree extends StaticObject {
	// TODO: Remove static reference
	public static FallingTree instance;

	public FallingTree(Rect bounds) {
		super(bounds);
		
		instance = this;

		setSprite(new Sprite(new Bitmap[] {
				ImageLoader.image("falling_tree/tree.png")}, 0, false));
	}
	
	public void cut(final Listener<Void> listener){
		Sprite sprite = new Sprite(new Bitmap[] {
				ImageLoader.image("falling_tree/tree.png"),
				ImageLoader.image("falling_tree/tree2.PNG"),
				ImageLoader.image("falling_tree/tree3.PNG"),
				ImageLoader.image("falling_tree/tree4.PNG") }, 800,
				false);
		sprite.addOnAnimationEndListener(new Listener<Void>() {
			@Override
			public void on(Void t) {
				//TODO: Correct position of enemy
				GameWorld.player().walkRightUntilBlock(new Listener<Void>() {
					@Override
					public void on(Void t) {
						GameWorld.player().punch(new Listener<Void>() {
							@Override
							public void on(Void t) {
								SceneOneObjects.chainsawEnemy().kill();
								listener.on(t);
							}
						});
					}
				});
			}
		});
		setSprite(sprite);
	}

	public void startCutting() {
		setSprite(new Sprite(new Bitmap[] {
				ImageLoader.image("falling_tree/tree.png"),
				}, 0, false));		
		//Add enemy cutting it
	}

}
