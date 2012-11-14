package br.com.greenblood;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.digitalpages.commons.device.ConnectivityInfo;
import br.com.digitalpages.commons.util.AlertUtil;

public class RankingActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ranking_activity);
		
		ListView rankingView = (ListView) findViewById(R.id.ranking_list_view);
		ArrayAdapter<HighscoreDTO> arrayAdapter = new ArrayAdapter<HighscoreDTO>(
				this, android.R.layout.simple_list_item_1);
		
		ConnectivityInfo.init(this);
		if(ConnectivityInfo.isConnected()){
		Random rdm = new Random();
		for (int i = 0; i < 100; i++)
			arrayAdapter.add(new HighscoreDTO(winners[rdm.nextInt(winners.length)], 22400 + rdm.nextInt(20) * 100));
		
		rankingView.setAdapter(arrayAdapter);
		
		} else
			AlertUtil.showSimpleDialog(this, "Voce não está conectado na internet!");
	}

	private static final String[] winners = { 
		"Marky.Vasconcelos", "Dogaum13", "AlMagnadoth", "J.Ricardo", "Albuquerque" 
	};

	private static class HighscoreDTO {
		private final String name;
		private final int value;

		public HighscoreDTO(String name, int value) {
			this.name = name;
			this.value = value;
		}

		@Override
		public String toString() {
			return name + ": " + value;
		}
	}

}
