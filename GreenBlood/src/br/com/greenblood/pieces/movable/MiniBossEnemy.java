package br.com.greenblood.pieces.movable;

import android.graphics.Rect;
import br.com.greenblood.history.mock.SceneOneObjects;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.world.GameWorld;

public class MiniBossEnemy extends Enemy {
    public MiniBossEnemy(Rect bounds, Rect boundingBounds) {
        super(bounds, boundingBounds);
    }
    
    @Override
    public void kill() {
    	super.kill();
    	
    	Entity key = SceneOneObjects.gateKey();
		key.pos().set(pos().x(), pos().y() - key.height() / 2);
		GameWorld.world().addEntity(key);
    }
}
