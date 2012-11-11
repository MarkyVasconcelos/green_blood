package br.com.greenblood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LobbyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lobby_activity);
		
		findViewById(R.id.start_game_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(LobbyActivity.this, GameActivity.class));
			}
		});
		
		findViewById(R.id.options_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LobbyActivity.this, OptionsActivity.class));
			}
		});
		
		findViewById(R.id.ranking_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LobbyActivity.this, RankingActivity.class));
			}
		});
		
		findViewById(R.id.credits_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LobbyActivity.this, CreditsActivity.class));
			}
		});
	}
}
