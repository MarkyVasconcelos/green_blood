package br.com.greenblood;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.view.GameView;

public class GameActivity extends Activity {
    private GameView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        
        params = new LayoutParams(90, 90);
        params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        screen.addView(actions, params);

        setContentView(screen);
    }
}