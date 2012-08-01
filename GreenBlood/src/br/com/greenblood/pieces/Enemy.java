package br.com.greenblood.pieces;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.util.ImageLoader;

public class Enemy extends MovableEntity {
    private Bitmap resource;

    public Enemy(Rect bounds) {
        super(bounds, 260);
        
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
        //TODO: AI
        super.processLogics(uptime); // Move
    }

}
