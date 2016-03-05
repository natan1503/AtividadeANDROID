package br.edu.ifpb.atividadeandroid.activity;

import br.edu.ifpb.atividadeandroid.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class SplashActivity extends Activity implements Runnable {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Handler SplashScren = new Handler();
		SplashScren.postDelayed(SplashActivity.this, 3000);
	}
	
	public void run(){
		startActivity(new Intent(SplashActivity.this, BuscarNomeActivity.class));
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}