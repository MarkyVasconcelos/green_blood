package br.com.greenblood.history.mock;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.AnimatedSprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.util.ImageLoader;

public class SceneOneObjects {
	public static StaticObject hallow() {
		StaticObject hallow = new StaticObject(new Rect(0, 0, 144, 288));
		hallow.setSprite(new AnimatedSprite(new Bitmap[] { ImageLoader
				.image("hollow.PNG") }, 0l, null, 0, false));
		return hallow;
	}

	public static StaticObject fire() {
		StaticObject fire = new StaticObject(new Rect(0, 0, 53, 76));
		fire.setSprite(new AnimatedSprite(new Bitmap[] { ImageLoader
				.image("fire.PNG") }, 0l, null, 0, false));
		return fire;
	}
	
	public static StaticObject tree() {
		StaticObject fire = new StaticObject(new Rect(0, 0, 196, 288));
		fire.setSprite(new AnimatedSprite(new Bitmap[] { ImageLoader
				.image("tree.PNG") }, 0l, null, 0, false));
		return fire;
	}

}
