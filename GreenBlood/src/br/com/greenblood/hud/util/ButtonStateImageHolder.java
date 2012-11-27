package br.com.greenblood.hud.util;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class ButtonStateImageHolder {
	private final Bitmap pressed, unpressed;
	private Rect size = new Rect(0, 0, 64, 64);

	public ButtonStateImageHolder(Bitmap unpressed, Bitmap pressed) {
		this.unpressed = unpressed;
		this.pressed = pressed;
	}
	
	public Bitmap pressed() {
		return pressed;
	}

	public Bitmap unpressed() {
		return unpressed;
	}

	public Rect size() {
		return size;
	}

}