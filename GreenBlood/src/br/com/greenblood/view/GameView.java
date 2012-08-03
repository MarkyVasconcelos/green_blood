package br.com.greenblood.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import br.com.greenblood.core.LoopSteps;
import br.com.greenblood.core.MainLoop;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.world.GameWorld;

public class GameView extends SurfaceView implements LoopSteps {
    private final Thread looper;
    private final MainLoop glooper;
    
    private GameWorld gameWorld;
    private SurfaceHolder holder;
    private Rect thisSize;

    public GameView(Context context, DirectionalControls controls, ActionControls actions) {
        super(context);
        
        GameWorld.init(controls, actions);
        gameWorld = GameWorld.world();

        looper = new Thread(glooper = new MainLoop(this, 40, 0,16));
        looper.setDaemon(true);

        holder = getHolder();
        holder.addCallback(new SurfaceCallback());
    }

    private class SurfaceCallback implements SurfaceHolder.Callback {
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
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
        holder.unlockCanvasAndPost(canvas);        
    }

    @Override
    public void tearDown() {
    }

}
