package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import android.os.Handler;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.world.GameWorld;

import commons.awt.Listener;

public class TreeCutterTrigger extends Trigger {

	public TreeCutterTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		GameWorld.world().lockScreen();
		GameWorld.world().offsetDraw(90, 0); // TODO: smoothly do that
		coreographer.sendEmptyMessageDelayed(START_FALL, 3500);
	}
	
	private static final int START_FALL = 0;
	private static final int RESUME_GAME = 1;

	private Handler coreographer = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if(msg.what == START_FALL)
				FallingTree.instance.cut(new Listener<Void>() {
					@Override
					public void on(Void t) {
						sendEmptyMessage(RESUME_GAME);
					}
				});
			
			if(msg.what == RESUME_GAME){
				GameWorld.world().offsetDraw(0, 0); // TODO: smoothly do that
				GameWorld.world().unlockScreen();
			}
		};
	};

}
