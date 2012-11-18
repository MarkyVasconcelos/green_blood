package br.com.greenblood.pieces.scene.one;

import android.graphics.Rect;
import android.os.Handler;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.history.mock.SceneOneObjects;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.util.Listener;
import br.com.greenblood.world.GameWorld;

public class TreeCutterTrigger extends Trigger {

	public TreeCutterTrigger(Rect bounds) {
		super(bounds);
	}

	@Override
	public void onFired() {
		GameWorld.world().lockScreen();
		GameWorld.world().offsetDraw(90); // TODO: smoothly do that
		coreographer.sendEmptyMessageDelayed(START_FALL, 2000);
	}
	
	private static final int START_FALL = 0;
	private static final int RESUME_GAME = 1;

	private Handler coreographer = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if(msg.what == START_FALL)
				FallingTree.instance.cut(new Listener<Void>() {
					@Override
					public void fire(Void t) {
						sendEmptyMessage(RESUME_GAME);
					}
				});
			
			if(msg.what == RESUME_GAME){
				GameWorld.world().offsetDraw(0); // TODO: smoothly do that
				GameWorld.world().unlockScreen();
			}
		};
	};

}
