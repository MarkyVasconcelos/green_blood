package br.com.greenblood.history.mock;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.pieces.scene.one.FallingTree;
import br.com.greenblood.pieces.scene.one.Log;
import br.com.greenblood.pieces.scene.one.Stairs;
import br.com.greenblood.pieces.scene.one.TheresAProblemAheadTrigger;
import br.com.greenblood.pieces.scene.one.TreeCutterTrigger;
import br.com.greenblood.pieces.scene.one.WhatABeutifullDayTrigger;
import br.com.greenblood.util.ImageLoader;

public class SceneOneObjects {
	
	public static Entity hallow() {
		StaticObject hallow = new StaticObject(new Rect(0, 0, 144, 288));
		hallow.setSprite(new Sprite(new Bitmap[] { ImageLoader.image("hollow.PNG") }, 0l, false));
		return hallow;
	}

	public static Entity fire() {
		StaticObject fire = new StaticObject(new Rect(0, 0, 53, 76));
		fire.setSprite(new Sprite(new Bitmap[] { ImageLoader.image("fire/fire.PNG"),
				ImageLoader.image("fire/fire2.PNG")}, 300, true));
		return fire;
	}
	
	public static Entity beingCuttedTree() {
		return new FallingTree(new Rect(0, 0, 383, 288));
	}
	
	public static Entity log(){
		return new Log(new Rect(0, 0, 121, 42));
	}
	
	public static Entity stairs(){
		return new Stairs(new Rect(0, 0, 64, 128));
	}
	
	public static Entity fence(){
		StaticObject fence = new StaticObject(new Rect(0, 0, 32, 140));
		fence.setSprite(new Sprite(new Bitmap[] { ImageLoader.image("boss/fence.PNG")}, 0, false));
		return fence;
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
