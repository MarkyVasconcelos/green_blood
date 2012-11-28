package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.history.mock.SceneOneObjects;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.world.GameWorld;

public class EnemyOnTreeTrigger extends Trigger {

	public EnemyOnTreeTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		Entity tree = SceneOneObjects.tree;
		Rect treeBounds = tree.currentBounds();
		
		Enemy enemy = SceneMaker.enemy();
		enemy.pos().set(treeBounds.right - GameCore.pixels(120), treeBounds.top);
		GameWorld.world().addEntity(enemy);
	}

}
