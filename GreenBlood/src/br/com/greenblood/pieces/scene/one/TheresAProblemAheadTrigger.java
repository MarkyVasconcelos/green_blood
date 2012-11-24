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
		
		TextObject chainsawDisplay = new TextObject(new Rect(0,0,80,100), "Vrum!! Vrum!!");
		chainsawDisplay.pos().set(currentBounds().left + 90, currentBounds().top + 180);
		chainsawDisplay.onEnd(new Listener<Void>() {
			@Override
			public boolean on(Void t) {
				GameWorld.world().display("Que som � esse?!?! Tem alguma coisa errada a frente!");
				return true;
			}
		});
		GameWorld.world().addEntity(chainsawDisplay);
	}

}
