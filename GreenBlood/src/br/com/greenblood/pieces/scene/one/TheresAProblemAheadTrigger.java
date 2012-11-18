package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.world.GameWorld;

public class TheresAProblemAheadTrigger extends Trigger {

	public TheresAProblemAheadTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		GameWorld.world().display("Oh no! There's a problem ahead touch to continue...");
	}

}
