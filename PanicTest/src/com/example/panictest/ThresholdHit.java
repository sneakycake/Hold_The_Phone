package com.example.panictest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ThresholdHit extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_threshold_hit);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_threshold_hit, menu);
		return true;
	}

}
