package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import br.com.greenblood.pieces.TextObject;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.world.GameWorld;

import commons.awt.Listener;

public class WayHighTrigger extends Trigger {

	public WayHighTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		GameWorld.world().lockScreen();
		GameWorld.world().offsetDraw(0, -90); // TODO: smoothly do that
		
		TextObject spoke = new TextObject(new Rect(0,0,80,100), "não consigo alcançar essas escadas ...", 2500);
		Rect playerBounds = GameWorld.player().currentBounds();
		spoke.pos().set(playerBounds.left, playerBounds.top - 10);
		spoke.onEnd(new Listener<Void>() {
			@Override
			public void on(Void t) {
				GameWorld.world().offsetDraw(0, 0);
				GameWorld.world().unlockScreen();
			}
		});
		GameWorld.world().addEntity(spoke);
	}
}
