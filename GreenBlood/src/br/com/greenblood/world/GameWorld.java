package br.com.greenblood.world;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.core.PiecesManager;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Player;

public class GameWorld {
    private static GameWorld world;
    
    public static GameWorld world(){
        return world;
    }
    
    public static WorldMap map() {
        return world().worldMap;
    }
    
    public static Player player() {
        return world().player;
    }
    
    public static PiecesManager pieces() {
        return world().pieces;
    }
    
    public static void init(DirectionalControls controls, ActionControls actions){
        world = new GameWorld(controls, actions);
    }

    private WorldMap worldMap;
    private PiecesManager pieces;
    private Player player;
    private final DirectionalControls controls;
    private final ActionControls actions;
    
    private GameWorld(DirectionalControls controls, ActionControls actions) {
        this.controls = controls;
        this.actions = actions;
        
        worldMap = new WorldMap();
    }

    public void proccessAI(long uptime) {
        pieces.proccessAI(uptime);
    }

    public void draw(Canvas canvas, Rect surfaceSize) {
//        long now = System.currentTimeMillis();
        canvas.save();

        canvas.drawRect(surfaceSize, Paints.BLUE);

        Vector2D offset = worldMap.draw(canvas, player.pos());
        pieces.draw(canvas, surfaceSize, offset);
        
        canvas.restore();
        
//        long after = System.currentTimeMillis();
//        System.out.println("draw finished in:" + (after - now ) +"ms");
    }

    public void surfaceCreated(Rect size) {
        worldMap.surfaceCreated(size);
        
        player = new Player(new Rect(0, 0, 42, 128), new Rect(0, 0, 30, 128));
        player.pos().setX(GameCore.tilesToPixels(1));
        player.setControls(controls);
        player.setActionControls(actions);
        
        pieces = new PiecesManager(player);
        
        worldMap.createWorld();
    }

}
