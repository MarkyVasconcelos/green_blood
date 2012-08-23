package br.com.greenblood.pieces;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.world.GameWorld;

public class Enemy extends MovableEntity {
    private static final Random rdm = new Random();
    private Bitmap resource;

    public Enemy(Rect bounds, Rect boundingBounds) {
        super(bounds, boundingBounds, 130 + rdm.nextInt(130));
        
        resource = ImageLoader.image("red_stick.png");
    }

    @Override
    public void draw(Canvas canvas, Rect surfaceSize, Vector2D offset) {
        Rect currentBounds = currentBounds();
        currentBounds.offset((int)offset.x(), (int)offset.y());
        canvas.drawBitmap(resource, null, currentBounds, Paints.BLANK);
    }

    @Override
    public void processLogics(long uptime) {
        Player player = GameWorld.player();
        
//        if(player.x() < x())
//            desaccelerate();
//        else
//            accelerate();
        
        
        super.processLogics(uptime); // Move
    }

}
