package br.com.greenblood.world;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.greenblood.GameActivity;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.dev.Paints;
import br.com.greenblood.history.ObjectCreator;
import br.com.greenblood.history.mock.SceneMaker;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.ItemView;
import br.com.greenblood.math.Vector2D;
import br.com.greenblood.pieces.Entity;
import br.com.greenblood.pieces.World;
import br.com.greenblood.pieces.World.AnimableEntity;
import br.com.greenblood.pieces.movable.Enemy;
import br.com.greenblood.pieces.movable.Player;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.view.GameView;

import commons.awt.Listener;

public class GameWorld {
    private static GameWorld world;

	public static void init(GameActivity gameActivity) {
		world = new GameWorld();
		world.itemView = gameActivity.itemView();
		world.gameActivity = gameActivity;
	}

	private GameActivity gameActivity;
	private ItemView itemView;
    private WorldMap worldMap;
    private World worldScene;
    private Player player;
	private Scene scene;
	private boolean blockMove;
	private float offsetX;
	private float offsetY;
	private final Bitmap sky;
	private final Rect skyBounds;
	
    private GameWorld() {
        scene = SceneMaker.sceneOne();
		worldMap = new WorldMap(scene);
		
		sky = ImageLoader.image("scene/sky.png");
		skyBounds = new Rect(0, 0, sky.getWidth(), sky.getHeight());
    }

    public void proccessAI(long uptime) {
    	worldScene.onProcessPhaseStarted();
    	
    	if(translater != null){
    		translater.update(uptime);
    		if(!translater.running)
    			translater = null;
    	}
    	
    	if(!blockMove)
    		worldScene.processLogics(uptime);
        worldScene.processAnimationLogics(uptime);
    }

    public void draw(Canvas canvas, Rect surfaceSize) {
        canvas.save();

    	canvas.drawBitmap(sky, skyBounds, surfaceSize, Paints.BLANK);
    	
        Vector2D offset = worldMap.draw(canvas, player.pos(), (int)offsetX, (int)offsetY);
        worldScene.draw(canvas, surfaceSize, offset);
        
        canvas.restore();
    }

    public void surfaceCreated(Rect size) {
        worldMap.surfaceCreated(size);

        player = new Player(new Rect(0, 0, 64, 128), new Rect(0, 0, 30, 128));
        player.setControls(view().controls());
        player.setActionControls(view().actions());
        
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
        
        worldScene.set(animables);
        
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
		view().enemyStats().display(enemy);
	}
	
	public void display(String txt, final Listener<Void> listener){
		lockScreen();
		gameActivity.display(txt, new Listener<Void>() {
			@Override
			public void on(Void t) {
				unlockMove();
				view().dialog().hide();
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
	
	private static final List<AnimableEntity> animables = new ArrayList<World.AnimableEntity>();
	private Translater translater;
	public static void addEntity(AnimableEntity ent) {
		animables.add(ent);
	}
	
	public void offsetDraw(int offsetX, int offsetY, Listener<Void> onOffsetEnd){
		translater = new Translater((int) (offsetX - this.offsetX), (int) (offsetY - this.offsetY), onOffsetEnd);
	}

	public static ActionControls actions() {
		return view().actions();
	}
	
	public static GameView view() {
		return world().gameActivity.view();
	}
	
	private class Translater {
		float speedX, speedY;
		final long duration = 1200;
		private boolean running;
		long elapsed;
		private final Listener<Void> onOffsetEnd;
		
		public Translater(int offsetX, int offsetY, Listener<Void> onOffsetEnd) {
			this.onOffsetEnd = onOffsetEnd;
			
			if(Math.abs(offsetX) > 8)
				speedX = offsetX > 0 ? 4 : -4;
			if (Math.abs(offsetY) > 8)
				speedY = offsetY > 0 ? 4 : -4;
			
			running = true;
		}
		
		public void update(long uptime){
			if(!running)
				return;
			
			elapsed += uptime;
			if(elapsed > duration){
				onOffsetEnd.on(null);
				running = false;
				return;
			}
			
			GameWorld.world.offsetX += speedX;
			GameWorld.world.offsetY += speedY;
		
		}
	}

	public static void showFinalView() {
		world().gameActivity.slide().displayFinalCut();
	}
}
