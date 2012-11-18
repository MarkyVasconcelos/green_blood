package br.com.greenblood.world;

import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.digitalpages.commons.awt.Listener;
import br.com.greenblood.GameActivity;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.history.ObjectCreator;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.hud.EnemyStatsView;
import br.com.greenblood.hud.PlayerStatsView;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Enemy;
import br.com.greenblood.pieces.Player;
import br.com.greenblood.pieces.World;

public class GameWorld {
    private static GameWorld world;

	public static void init(GameActivity gameActivity) {
		world = new GameWorld(gameActivity.controls(), gameActivity.actions());
		world.playerStatsView = gameActivity.playerStats();
		world.enemyStatsView = gameActivity.enemyStats();
		world.gameActivity = gameActivity;
	}

	private GameActivity gameActivity;
    private PlayerStatsView playerStatsView;
    private EnemyStatsView enemyStatsView;
    private WorldMap worldMap;
    private World worldScene;
    private Player player;
    private final DirectionalControls controls;
    private final ActionControls actions;
	private Scene scene;
	private boolean blockInput;
    
    private GameWorld(DirectionalControls controls, ActionControls actions) {
        this.controls = controls;
        this.actions = actions;
        
        scene = SceneMaker.sceneOne();
		worldMap = new WorldMap(scene);
    }

    public void proccessAI(long uptime) {
    	if(!blockInput)
    		worldScene.processLogics(uptime);
        worldScene.processAnimationLogics(uptime);
    }

    public void draw(Canvas canvas, Rect surfaceSize) {
        canvas.save();

        canvas.drawRect(surfaceSize, Paints.BLUE);

        Vector2D offset = worldMap.draw(canvas, player.pos());
        worldScene.draw(canvas, surfaceSize, offset);
        
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

        worldScene = new World(player);
        
        for(ObjectCreator ent : scene.objects())
        	worldScene.add(ent.create());
    }

    public static GameWorld world(){
        return world;
    }
    
    public static WorldMap map() {
        return world().worldMap;
    }
    
    public static World pieces() {
        return world().worldScene;
    }
    
    public static Player player() {
        return world().player;
    }

	public void showEnemyStats(Enemy enemy) {
		enemyStatsView.display(enemy);
	}

	public void display(String txt){
		lockInput();
		gameActivity.hideControllers();
		gameActivity.display(txt, new Listener<Void>() {
			@Override
			public boolean on(Void t) {
				unlockInput();
				gameActivity.dialog().hide();
				gameActivity.showControllers();
				return false;
			}
		});
	}

	public void lockInput() {
		blockInput = true;
	}
	
	public void unlockInput() {
		blockInput = false;
	}

}
