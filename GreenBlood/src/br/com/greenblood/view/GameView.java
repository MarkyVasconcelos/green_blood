package br.com.greenblood.view;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import br.com.greenblood.GameActivity;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.core.LoopSteps;
import br.com.greenblood.core.MainLoop;
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
        
        playerStats.draw(canvas);
        
        holder.unlockCanvasAndPost(canvas);        
    }

    @Override
    public void tearDown() {
        glooper.stop();
    }
    
	private void createHighUpDisplays(){
		Rect bounds = new Rect(GameCore.oneDp(), GameCore.oneDp(), GameCore.pixels(112), GameCore.pixels(48));
		playerStats = new PlayerStatsView(bounds);
//	    <br.com.greenblood.hud.PlayerStatsView
//	        android:id="@+id/player_stats"
//	        android:layout_width="113dp"
//	        android:layout_height="48dp"
//	        android:padding="1dp" />
	}
	
    private PlayerStatsView playerStats;
	public PlayerStatsView playerStats() {
		return playerStats;
	}
}
