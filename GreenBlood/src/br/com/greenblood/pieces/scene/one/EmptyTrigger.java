package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import br.com.greenblood.pieces.Trigger;

public class EmptyTrigger extends Trigger {

	public EmptyTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		//Does nothing on purpose
	}

}
