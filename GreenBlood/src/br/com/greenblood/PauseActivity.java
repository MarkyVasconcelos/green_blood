package br.com.greenblood;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class PauseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pause_dialog);
		
		findViewById(R.id.resume_game_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		findViewById(R.id.exit_game_button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//TODO setResult
			}
		});
		
	}
}
