package br.com.greenblood.history.mock;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.pieces.scene.one.FallingTree;
import br.com.greenblood.pieces.scene.one.TheresAProblemAheadTrigger;
import br.com.greenblood.pieces.scene.one.TreeCutterTrigger;
import br.com.greenblood.pieces.scene.one.WhatABeutifullDayTrigger;
import br.com.greenblood.util.ImageLoader;

public class SceneOneObjects {
	public static StaticObject hallow() {
		StaticObject hallow = new StaticObject(new Rect(0, 0, 144, 288));
		hallow.setSprite(new Sprite(new Bitmap[] { ImageLoader.image("hollow.PNG") }, 0l, false));
		return hallow;
	}

	public static StaticObject fire() {
		StaticObject fire = new StaticObject(new Rect(0, 0, 53, 76));
		fire.setSprite(new Sprite(new Bitmap[] { ImageLoader.image("fire/fire.PNG"),
				ImageLoader.image("fire/fire2.PNG")}, 300, true));
		return fire;
	}
	
	public static StaticObject beingCuttedTree() {
		return new FallingTree(new Rect(0, 0, 383, 288));
	}

	public static Trigger welcomeTrigger() {
		return new WhatABeutifullDayTrigger(new Rect(0,0,40,80));
	}
	
	public static Trigger problemTrigger() {
		return new TheresAProblemAheadTrigger(new Rect(0,0,40,80));
	public static Trigger cutTrigger() {
		return new TreeCutterTrigger(new Rect(0,0,40,200));
	}

}
