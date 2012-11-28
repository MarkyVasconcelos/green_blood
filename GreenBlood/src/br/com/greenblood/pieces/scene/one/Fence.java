package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.util.ImageLoader;

public class Fence extends StaticObject {
	public Fence(Rect bounds) {
		super(bounds);

		setSprite(new Sprite(new Bitmap[] { ImageLoader.image("scene/fence_closed.png")}, 0, false));
		setCollidable(true);
	}
	
	@Override
	public void kill() {
		setSprite(new Sprite(new Bitmap[] { ImageLoader.image("scene/fence_open.png")}, 0, false));
		setCollidable(false);
	}

	@Override
	public void processLogics(long uptime) {
	}
}
