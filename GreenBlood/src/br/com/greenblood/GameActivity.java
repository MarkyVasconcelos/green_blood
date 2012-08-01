package br.com.greenblood;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.util.MultiTouchActivity;
import br.com.greenblood.view.GameView;

public class GameActivity extends MultiTouchActivity {
    private GameView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ImageLoader.init(this);
        
        LayoutParams params;
        FrameLayout screen = new FrameLayout(this);

        DirectionalControls leftRight = new DirectionalControls(this);
        ActionControls actions = new ActionControls(this);
        
        
        
        gameView = new GameView(this, leftRight, actions);
        
        params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        screen.addView(gameView, params);

        params = new LayoutParams(160, 80);
        params.gravity = Gravity.BOTTOM;
        screen.addView(leftRight, params);
        leftRight.setOnTouchListener(this);
        
        params = new LayoutParams(80, 160);
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        screen.addView(actions, params);
        actions.setOnTouchListener(this);

        setContentView(screen);
    }
}