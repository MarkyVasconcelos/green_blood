package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.history.mock.SceneOneObjects;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.world.GameWorld;

public class CabinTrigger extends Trigger {

	public CabinTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		Entity cabin = SceneOneObjects.cabinInstance();
		Rect cabinBounds = cabin.currentBounds();
		
		Enemy enemy = SceneMaker.enemy();
		enemy.pos().set(cabinBounds.right, cabinBounds.bottom - enemy.height() / 2);
		GameWorld.world().addEntity(enemy);
	}

}
