package br.com.greenblood.world;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.core.PiecesManager;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.history.ObjectCreator;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.hud.EnemyStatsView;
import br.com.greenblood.hud.PlayerStatsView;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Enemy;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.Player;

public class GameWorld {
    private static GameWorld world;
    
    public static void init(DirectionalControls controls, ActionControls actions){
        world = new GameWorld(controls, actions);
    }

    private PlayerStatsView playerStatsView;
    private EnemyStatsView enemyStatsView;
    private WorldMap worldMap;
    private PiecesManager pieces;
    private Player player;
    private final DirectionalControls controls;
    private final ActionControls actions;
	private Scene scene;
    
    private GameWorld(DirectionalControls controls, ActionControls actions) {
        this.controls = controls;
        this.actions = actions;
        
        scene = SceneMaker.sceneOne();
		worldMap = new WorldMap(scene);
    }

    public void proccessAI(long uptime) {
        pieces.proccessAI(uptime);
    }

    public void draw(Canvas canvas, Rect surfaceSize) {
        canvas.save();

        canvas.drawRect(surfaceSize, Paints.BLUE);

        Vector2D offset = worldMap.draw(canvas, player.pos());
        pieces.draw(canvas, surfaceSize, offset);
        
        canvas.restore();
    }

    public void surfaceCreated(Rect size) {
        worldMap.surfaceCreated(size);

        player = new Player(new Rect(0, 0, 64, 128), new Rect(0, 0, 30, 128));
        player.setControls(controls);
        player.setActionControls(actions);
        player.setStatsView(playerStatsView);
        
        Vector2D initialTile = scene.playerInitialTile();
		player.pos().set(GameCore.tilesToPixel(initialTile));

        pieces = new PiecesManager(player);
        
        for(ObjectCreator ent : scene.objects())
        	pieces.add(ent.create());
    }

    public static GameWorld world(){
        return world;
    }
    
    public static WorldMap map() {
        return world().worldMap;
    }
    
    public static PiecesManager pieces() {
        return world().pieces;
    }
    
    public static Player player() {
        return world().player;
    }

	public static void setEnemyStatsView(EnemyStatsView enemyStatsView) {
		world().enemyStatsView = enemyStatsView;
		enemyStatsView.dismiss();
	}
	
	public static void setPlayerStatsView(PlayerStatsView playerStatsView){
		world().playerStatsView = playerStatsView;
	}

	public void showEnemyStats(Enemy enemy) {
		enemyStatsView.display(enemy);
	}
}
