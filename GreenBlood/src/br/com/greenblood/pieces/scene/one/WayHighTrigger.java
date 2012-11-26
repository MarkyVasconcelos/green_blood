package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
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
		GameWorld.world().display("Essas escadas estão altas!", new Listener<Void>() {
			@Override
			public void on(Void t) {
				GameWorld.world().offsetDraw(0, 0);				
			}
		});
	}
}
