package br.com.greenblood;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText playerNameField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String playerName = savedName();
		
		if(playerName.length() > 0){
			enterLobby();
			return;
		}
		
		setContentView(R.layout.main_activity);
		
		playerNameField = (EditText) findViewById(R.id.player_name_field);
		Button confirmButton = (Button) findViewById(R.id.confirm);
		
		confirmButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(save())
					enterLobby();
			}
		});
	}
	
	private boolean save() {
		String playerName = playerNameField.getText().toString();
		
		if(playerName.length() == 0){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Você deve digitar um nome");
			builder.show();
			return false;
		}
		
		saveName(playerName);
		
		return true;
	}
	
	private void enterLobby() {
		startActivity(new Intent(this, LobbyActivity.class));
		finish();
	}
	
	private static final String PLAYER_NAME = "playerName";
	
	public String savedName(){
		return prefs().getString(PLAYER_NAME, "");
	}
	
	public void saveName(String playerName){
		prefs().edit().putString(PLAYER_NAME, playerName).commit();
	}

	private SharedPreferences prefs() {
		return getPreferences(MODE_PRIVATE);
	}
}
