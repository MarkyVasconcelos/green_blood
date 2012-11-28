package br.com.greenblood;

import android.os.Bundle;
import android.view.View;
import br.com.greenblood.core.GameCore;
import br.com.greenblood.hud.ItemView;
import br.com.greenblood.util.ImageLoader;
import br.com.greenblood.util.MultiTouchActivity;
import br.com.greenblood.view.GameView;
import br.com.greenblood.view.SlideView;

import commons.awt.Listener;

public class GameActivity extends MultiTouchActivity {
    private GameView gameView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        GameCore.setDensity(getResources().getDisplayMetrics().density);
        
        ImageLoader.init(this);
        
        setContentView(R.layout.game_activity);
        
        gameView = view();
        gameView.set(this);
    }
    
	public GameView view() {
		return (GameView) findViewById(R.id.game_view);
	}
	
	public SlideView slide() {
		return (SlideView) findViewById(R.id.slide_view);
	}
    
	public ItemView itemView() {
		return (ItemView) findViewById(R.id.item_view);
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
		view().display(txt, listener);
	}
	
	public void showSlide(Listener<Void> onEndListener) {
		slide().display(onEndListener);
	}

	public void hideControllers() {
		setControllersVisibility(View.INVISIBLE);
	}
	
	public void showControllers() {
		setControllersVisibility(View.VISIBLE);
	}
	
	private void setControllersVisibility(final int visibility){
				view().controls().setVisible(visibility == View.VISIBLE);
				view().actions().setVisible(visibility == View.VISIBLE);
	}

}
