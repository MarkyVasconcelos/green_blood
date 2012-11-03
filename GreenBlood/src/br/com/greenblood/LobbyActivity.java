package br.com.greenblood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LobbyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lobby_activity);
		
		View startGame = findViewById(R.id.start_game_button);
		
		startGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(LobbyActivity.this, GameActivity.class));
			}
		});
		
		View ranking = findViewById(R.id.ranking_button);
		
		ranking.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LobbyActivity.this, RankingActivity.class));
			}
		});
	}
}
