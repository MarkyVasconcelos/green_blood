package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

public class Log extends StaticObject {
	private static final int pixelsPerSecond = 70;
	
	private final Player player;
	private boolean walkingOverThis = false;
	
	public Log(Rect bounds) {
		super(bounds);

		setSprite(new Sprite(
				new Bitmap[] { ImageLoader.image("cutted_tree_obstacle.PNG") },
				0, false));
		
		setCollidable(true);
		player = GameWorld.player();
	}

	//TODO: Float backs to initial position
	@Override
	public void processLogics(long uptime) {
		if(Rect.intersects(player.currentBoundingBounds(), currentBounds())){
			player.walkingOnGround();
			walkingOverThis = true;
			long fall = pixelsPerSecond / uptime;
			pos().setY(pos().y() + fall);
			player.pos().setY(player.pos().y() + fall);
		}else if(walkingOverThis)
			player.walkingOnAir();
	}

}
