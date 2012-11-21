package br.com.greenblood;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import br.com.digitalpages.commons.awt.Listener;
import br.com.greenblood.hud.ActionControls;
import br.com.greenblood.hud.DirectionalControls;
import br.com.greenblood.hud.EnemyStatsView;
import br.com.greenblood.hud.ItemView;
import br.com.greenblood.hud.PlayerStatsView;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.util.MultiTouchActivity;
import br.com.greenblood.view.DialogView;
import br.com.greenblood.view.GameView;

public class GameActivity extends MultiTouchActivity {
    private GameView gameView;
    
    private final Handler uiHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ImageLoader.init(this);
        
        setContentView(R.layout.game_activity);
        
        gameView = (GameView) findViewById(R.id.game_view);

        gameView.set(this);
        controls().setOnTouchListener(this);
        actions().setOnTouchListener(this);
    }

	public PlayerStatsView playerStats() {
		return (PlayerStatsView) findViewById(R.id.player_stats);
	}

	public EnemyStatsView enemyStats() {
		return (EnemyStatsView) findViewById(R.id.enemy_stats);
	}

	public DialogView dialog() {
		return (DialogView) findViewById(R.id.dialog_view);
	}
    
    public ActionControls actions() {
		return (ActionControls) findViewById(R.id.action_control);
	}
    
	public ItemView itemView() {
		return (ItemView) findViewById(R.id.item_view);
	}

    public DirectionalControls controls() {
		return (DirectionalControls) findViewById(R.id.direction_control);
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

	public void display(String txt, Listener<Void> listener) {
		dialog().display(txt, listener);
	}

	public void hideControllers() {
		setControllersVisibility(View.INVISIBLE);
	}
	
	public void showControllers() {
		setControllersVisibility(View.VISIBLE);
	}
	
	private void setControllersVisibility(final int visibility){
		uiHandler.post(new Runnable() {
			@Override
			public void run() {
				controls().setVisibility(visibility);
				actions().setVisibility(visibility);
			}
		});
	}
}
