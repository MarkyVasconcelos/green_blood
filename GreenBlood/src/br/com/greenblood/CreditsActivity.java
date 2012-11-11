package br.com.greenblood;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

public class CreditsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView view = new TextView(this);
		view.setText("Desenvolvido pela equipe FIVEMONKEYS!");
		view.setGravity(Gravity.CENTER);
		setContentView(view);
	}
}
