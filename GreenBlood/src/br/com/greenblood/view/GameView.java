package br.com.greenblood.view;

import java.util.ArrayList;
import java.util.List;

import commons.awt.Listener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import br.com.greenblood.GameActivity;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.core.LoopSteps;
import br.com.greenblood.core.MainLoop;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.hud.EnemyStatsView;
import br.com.greenblood.hud.PlayerStatsView;
import br.com.greenblood.world.GameWorld;

public class GameView extends SurfaceView implements LoopSteps {
    private final Thread looper;
    private final MainLoop glooper;
    
    private GameWorld gameWorld;
    private SurfaceHolder holder;
    private Rect thisSize;
    
    public GameView(Context context, AttributeSet attrs){
    	super(context, attrs);
    	
        looper = new Thread(glooper = new MainLoop(this, 40, 0,16));
        looper.setDaemon(true);

        holder = getHolder();
        holder.addCallback(new SurfaceCallback());
    }
    
	public void set(GameActivity gameActivity) {
		GameWorld.init(gameActivity);
		gameWorld = GameWorld.world();
	}
	
    private class SurfaceCallback implements SurfaceHolder.Callback {
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
        	createHighUpDisplays();
            thisSize = new Rect(0, 0, getWidth(), getHeight());
            gameWorld.surfaceCreated(thisSize);
            looper.setDaemon(true);
            looper.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            glooper.stop();
        }
    }

    @Override
    public void setup() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void processLogics(long uptime) {
        gameWorld.proccessAI(uptime);
    }

    @Override
    public void paintScreen() {
        Canvas canvas = holder.lockCanvas();
        gameWorld.draw(canvas, thisSize);
        
        for(HighUpDisplay hud : huds)
        	hud.draw(canvas);
        
        holder.unlockCanvasAndPost(canvas);        
    }

    @Override
    public void tearDown() {
        glooper.stop();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	for(HighUpDisplay hud : huds)
    		if(hud.bounds().contains((int)event.getX(), (int)event.getY())){
    			MotionEvent evt = MotionEvent.obtain(event);
    			evt.setLocation(event.getX() - hud.left(), event.getY() - hud.top());
    			return hud.onTouchEvent(evt);
    		}
    	
    	return false;
    }
    
	private void createHighUpDisplays(){
		Rect bounds = new Rect(GameCore.oneDp(), GameCore.oneDp(), GameCore.pixels(112), GameCore.pixels(48));
		playerStats = new PlayerStatsView(bounds);
		huds.add(playerStats);

		int w = GameCore.pixels(113);
		int middle = getWidth() / 2;
		bounds = new Rect(middle - w / 2, GameCore.oneDp(), middle + w / 2, GameCore.pixels(40));
		enemyStats = new EnemyStatsView(bounds);
		huds.add(enemyStats);

		bounds = new Rect(GameCore.oneDp(), getHeight() - GameCore.pixels(97), GameCore.pixels(193), getHeight() - GameCore.oneDp());
		controls = new DirectionalControls(bounds);
		huds.add(controls);
		
		bounds = new Rect(getWidth() - GameCore.pixels(97), getHeight() - GameCore.pixels(191), getWidth() - GameCore.oneDp(), getHeight() - GameCore.oneDp());
		actions = new ActionControls(bounds);
		huds.add(actions);

		bounds = new Rect(getWidth() + GameCore.pixels(8), getHeight() - GameCore.pixels(260), getWidth() - GameCore.pixels(8), getHeight() - GameCore.oneDp());
		dialog = new DialogView(bounds);
		huds.add(dialog);
	}
	
    private EnemyStatsView enemyStats;
	public EnemyStatsView enemyStats() {
		return enemyStats;
	}
	
    private PlayerStatsView playerStats;
	public PlayerStatsView playerStats() {
		return playerStats;
	}

	private ActionControls actions;
	public ActionControls actions() {
		return actions;
	}
	
	private DialogView dialog;
	public DialogView dialog() {
		return dialog;
	}

	public void display(String txt, Listener<Void> listener) {
		dialog().display(txt, listener);		
	}

	private	DirectionalControls controls;
	public DirectionalControls controls() {
		return controls;
	}
	
	private final List<HighUpDisplay> huds = new ArrayList<HighUpDisplay>();
}
