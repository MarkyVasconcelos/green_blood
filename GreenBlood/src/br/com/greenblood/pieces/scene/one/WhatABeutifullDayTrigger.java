package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.world.GameWorld;

public class WhatABeutifullDayTrigger extends Trigger {

	public WhatABeutifullDayTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		GameWorld.world().display("Que belo dia!");
	}

}
