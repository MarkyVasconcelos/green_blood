package br.com.greenblood.world.map;

import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.World.AnimableEntity;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;
import android.graphics.Bitmap;

public class WaterTile extends Tile implements AnimableEntity {
	private final Sprite waterSprite;
	
	public WaterTile() {
		super(false, null);
		waterSprite = new Sprite(new Bitmap[]{
				ImageLoader.water(1),
				ImageLoader.water(2),
				ImageLoader.water(3),
				ImageLoader.water(4)
		}, 560, true);
		
		GameWorld.addEntity(this);
	}
	
	@Override
	public Bitmap sprite() {
		return waterSprite.current();
	}

	@Override
	public void update(long uptime) {
		waterSprite.update(uptime);
	}

}
