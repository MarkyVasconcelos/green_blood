package br.com.greenblood.history.mock;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.Trigger;
import br.com.greenblood.pieces.scene.one.Chair;
import br.com.greenblood.pieces.scene.one.EmptyTrigger;
import br.com.greenblood.pieces.scene.one.FallingTree;
import br.com.greenblood.pieces.scene.one.GateKey;
import br.com.greenblood.pieces.scene.one.Log;
import br.com.greenblood.pieces.scene.one.Stairs;
import br.com.greenblood.pieces.scene.one.TheresAProblemAheadTrigger;
import br.com.greenblood.pieces.scene.one.TreeCutterTrigger;
import br.com.greenblood.util.ImageLoader;

public class SceneOneObjects {
	
	private static Entity fence;

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
	
	public static Entity cabin(){
		StaticObject cabin = new StaticObject(new Rect(0, 0, 110, 216));
		 cabin.setSprite(new Sprite(new Bitmap[] { ImageLoader.image("cabin.PNG")}, 0, false));
		return cabin;
	}
	
	public static Entity chair(){
		return new Chair(new Rect(0, 0, 33, 60));
	}
	
	public static Entity gateKey() {
		return new GateKey(new Rect(0,0,49,112));
	}
	
	public static Entity fence(){
		StaticObject fence = new StaticObject(new Rect(0, 0, 32, 140));
		fence.setCollidable(true);
		fence.setSprite(new Sprite(new Bitmap[] { ImageLoader.image("boss/fence.PNG")}, 0, false));
		setFenceInstance(fence);
		return fence;
	}
	
	public static Entity headquarters() {
		StaticObject bossHouse = new StaticObject(new Rect(0, 0, 608, 293));
		bossHouse.setSprite(new Sprite(new Bitmap[] { ImageLoader.image("boss/house.PNG")}, 0, false));
		return bossHouse;
	}
	
	public static Trigger problemTrigger() {
		return new EmptyTrigger(new Rect(0, 0, 40, 200));
//		return new TheresAProblemAheadTrigger(new Rect(0,0,40,200));
	}
	
	public static Trigger cabinTrigger() {
		return new EmptyTrigger(new Rect(0, 0, 40, 200));
//		return new TheresAProblemAheadTrigger(new Rect(0,0,40,200));
	}

	public static Trigger cutTrigger() {
//		return new EmptyTrigger(new Rect(0, 0, 40, 200));
		return new TreeCutterTrigger(new Rect(0,0,40,200));
	}

	public static void setFenceInstance(Entity fence) {
		SceneOneObjects.fence = fence;
	}
	
	public static Entity fenceInstance(){
		return SceneOneObjects.fence;
	}


}
