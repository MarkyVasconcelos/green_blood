package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import br.com.greenblood.pieces.TextObject;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.world.GameWorld;

import commons.awt.Listener;

public class TheresAProblemAheadTrigger extends Trigger {

	public TheresAProblemAheadTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		GameWorld.world().lockScreen();
		
		TextObject chainsawDisplay = new TextObject(new Rect(0,0,80,100), "Que barulho é esse?", 2000);
		chainsawDisplay.pos().set(x(), y());
		chainsawDisplay.onEnd(new Listener<Void>() {
			@Override
			public void on(Void t) {
				GameWorld.world().unlockScreen();
			}
		});
		GameWorld.world().addEntity(chainsawDisplay);
	}

}
