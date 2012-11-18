package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.util.Listener;
import br.com.greenblood.world.GameWorld;

public class FallingTree extends StaticObject {
	// TODO: Remove static reference
	public static FallingTree instance;

	public FallingTree(Rect bounds) {
		super(bounds);
		
		instance = this;

		setSprite(new Sprite(new Bitmap[] {
				ImageLoader.image("falling_tree/cutting_tree1.PNG"),
				ImageLoader.image("falling_tree/cutting_tree2.PNG"),
				ImageLoader.image("falling_tree/cutting_tree3.PNG"),
				ImageLoader.image("falling_tree/cutting_tree4.PNG") }, 600,
				true));
	}
	
	public void cut(Listener<Void> listener){
		Sprite sprite = new Sprite(new Bitmap[] {
				ImageLoader.image("falling_tree/tree.PNG"),
				ImageLoader.image("falling_tree/tree2.PNG"),
				ImageLoader.image("falling_tree/tree3.PNG"),
				ImageLoader.image("falling_tree/tree4.PNG") }, 800,
				false);
		sprite.addOnAnimationEndListener(new Listener<Void>() {
			@Override
			public void fire(Void t) {
				//TODO: Correct position of enemy
				Enemy enemy = SceneMaker.enemy();
				int bottom = currentBounds().bottom - enemy.height();
				int left = currentBounds().left + 100;
				enemy.pos().set(left, bottom);
				GameWorld.world().addEntity(enemy);
			}
		});
		sprite.addOnAnimationEndListener(listener);
		setSprite(sprite);
	}

}
