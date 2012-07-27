package br.com.greenblood.world;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.math.Gravity;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Player;

public class GameWorld {
    private Player player;
    private WorldMap worldMap;

    public GameWorld(DirectionalControls controls, ActionControls actions) {
        player = new Player(new Rect(0, 0, 10, 10));
        player.setControls(controls);
        player.setActionControls(actions);

        worldMap = new WorldMap();
    }

    public void proccessAI(long uptime) {
        player.processLogics(uptime);

        int tileX = player.tileX();
        int tileY = player.tileY();
        boolean collids = worldMap.collids(player, tileX, tileY);
        
//        System.out.println(tileY + ":" + tileX + ":" + collids);
        if (!collids)
            Gravity.apply(player, uptime);
    }

    public void draw(Canvas canvas, Rect surfaceSize) {
        canvas.save();

        canvas.drawRect(surfaceSize, Paints.BLACK);

        Vector2D offset = worldMap.draw(canvas, player.pos());
        player.draw(canvas, surfaceSize, offset);

        canvas.restore();
    }

    public void surfaceCreated(Rect size) {
        worldMap.surfaceCreated(size);
    }

}
