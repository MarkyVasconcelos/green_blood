package br.com.greenblood;

import android.os.Bundle;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.hud.EnemyStatsView;
import br.com.greenblood.hud.PlayerStatsView;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.util.MultiTouchActivity;
import br.com.greenblood.view.GameView;
import br.com.greenblood.world.GameWorld;

public class GameActivity extends MultiTouchActivity {
    private GameView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ImageLoader.init(this);
        
        setContentView(R.layout.game_activity);
        
        gameView = (GameView) findViewById(R.id.game_view);

        DirectionalControls leftRight = (DirectionalControls) findViewById(R.id.direction_control);
        ActionControls actions = (ActionControls) findViewById(R.id.action_control);
        gameView.set(leftRight, actions);
        leftRight.setOnTouchListener(this);
        actions.setOnTouchListener(this);

        GameWorld.setEnemyStatsView((EnemyStatsView) findViewById(R.id.enemy_stats));
        GameWorld.setPlayerStatsView((PlayerStatsView) findViewById(R.id.player_stats));
    }
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
//    	startActivity(new Intent(this, PauseActivity.class));
//    	startActivity(new Intent(this, GameProgressActivity.class));
    }
    
    @Override
    public void finish() {
        super.finish();
        
        gameView.tearDown();
    }
}
