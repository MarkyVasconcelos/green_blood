package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import br.com.digitalpages.commons.awt.Listener;
import br.com.greenblood.pieces.TextObject;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.world.GameWorld;

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
				GameWorld.world().display("Oh no! There's a problem ahead touch to continue...");
				return true;
			}
		});
		GameWorld.world().addEntity(chainsawDisplay);
	}

}
