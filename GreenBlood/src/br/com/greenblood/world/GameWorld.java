package br.com.greenblood.world;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.GameActivity;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.history.ObjectCreator;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.hud.EnemyStatsView;
import br.com.greenblood.hud.ItemView;
import br.com.greenblood.hud.PlayerStatsView;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.World;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;

import commons.awt.Listener;

public class GameWorld {
    private static GameWorld world;

	public static void init(GameActivity gameActivity) {
		world = new GameWorld(gameActivity.controls(), gameActivity.actions());
		world.playerStatsView = gameActivity.playerStats();
		world.enemyStatsView = gameActivity.enemyStats();
		world.itemView = gameActivity.itemView();
		world.gameActivity = gameActivity;
	}

	private GameActivity gameActivity;
	private ItemView itemView;
    private PlayerStatsView playerStatsView;
    private EnemyStatsView enemyStatsView;
    private WorldMap worldMap;
    private World worldScene;
    private Player player;
    private final DirectionalControls controls;
    private final ActionControls actions;
	private Scene scene;
	private boolean blockMove;
	private int offsetX;
	private int offsetY;
	private final Bitmap sky;
	private final Rect skyBounds;
	
    private GameWorld(DirectionalControls controls, ActionControls actions) {
        this.controls = controls;
        this.actions = actions;
        
        scene = SceneMaker.sceneOne();
		worldMap = new WorldMap(scene);
		
		sky = ImageLoader.image("scene/sky.png");
		skyBounds = new Rect(0, 0, sky.getWidth(), sky.getHeight());
    }

    public void proccessAI(long uptime) {
    	worldScene.onProcessPhaseStarted();
    	if(!blockMove)
    		worldScene.processLogics(uptime);
        worldScene.processAnimationLogics(uptime);
    }

    public void draw(Canvas canvas, Rect surfaceSize) {
        canvas.save();

    	canvas.drawBitmap(sky, skyBounds, surfaceSize, Paints.BLANK);
    	
        Vector2D offset = worldMap.draw(canvas, player.pos(), offsetX, offsetY);
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
		
		player.setOnNextActionListener(new Listener<Void>() {
			@Override
			public void on(Void t) {
				//TODO: Activate slidess
//				player.pos().setX(GameCore.tilesToPixels(70));
//				lockScreen();
//				gameActivity.showSlide(new Listener<Void>() {
//					@Override
//					public void on(Void t) {
//						unlockScreen();
//					}
//				});
			}
		});

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
	
	public void display(String txt, final Listener<Void> listener){
		lockScreen();
		gameActivity.display(txt, new Listener<Void>() {
			@Override
			public void on(Void t) {
				unlockMove();
				gameActivity.dialog().hide();
				gameActivity.showControllers();
				
				if(listener != null)
					listener.on(null);
			}
		});
	}
	
	public void displayItemView(){
		itemView.show();
	}

	public void lockScreen() {
		player.stop();
		lockMove();
		gameActivity.hideControllers();
	}
	
	public void unlockScreen() {
		unlockMove();
		gameActivity.showControllers();
	}

	public void lockMove() {
		blockMove = true;
	}
	
	public void unlockMove() {
		blockMove = false;
	}

	public void addEntity(Entity ent) {
		worldScene.postAdd(ent);
	}
	
	public void offsetDraw(int offsetX, int offsetY){
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}

}
