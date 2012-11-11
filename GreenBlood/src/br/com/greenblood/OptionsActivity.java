package br.com.greenblood;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class OptionsActivity extends Activity {
	private ImageView toggleImage;
	private TextView description;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.options_activity);
		
		toggleImage = (ImageView) findViewById(R.id.sound_toggle_button);
		description = (TextView) findViewById(R.id.sound_description);
		toggleImage.setOnClickListener(toggleListener);
		description.setOnClickListener(toggleListener);
	}

	private OnClickListener toggleListener = new OnClickListener() {
		boolean on;

		@Override
		public void onClick(View v) {
			on = !on;

			if (on) {
				toggleImage.setImageResource(R.drawable.speaker_activated);
				description.setText("Ativado");
				description.setTextColor(Color.GREEN);
			} else {
				toggleImage.setImageResource(R.drawable.speaker_deactivated);
				description.setText("Desativado");
				description.setTextColor(Color.RED);
			}
		}
	};

}
