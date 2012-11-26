package br.com.greenblood.pieces.scene.one;

import android.graphics.Bitmap;
import android.graphics.Rect;
import br.com.greenblood.img.Sprite;
import br.com.greenblood.pieces.StaticObject;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

public class Log extends StaticObject {
	private static final int pixelsPerSecond = 70;
	
	private final Player player;
	private boolean walkingOverThis = false;
	private boolean playerTouched = false;
	private int originalY;
	
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
		Rect currentBounds = currentBounds();
		if(Rect.intersects(player.currentBoundingBounds(), currentBounds)){
			if(!playerTouched){
				originalY = currentBounds.bottom;
				playerTouched = true;
			}
			player.walkingOnGround();
			walkingOverThis = true;
			long fall = pixelsPerSecond / uptime;
			pos().setY(pos().y() + fall);
			player.pos().setY(player.pos().y() + fall);
		}else if(walkingOverThis){
			player.walkingOnAir();
			walkingOverThis = false;
		}else if(playerTouched && originalY != currentBounds.bottom)
			//TODO: Don't let it floats away on air
			pos().setY(pos().y() - pixelsPerSecond / uptime);
	}

}
