package com.example.panictest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class JoinRoomFailed extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_room_failed);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_join_room_failed, menu);
		return true;
	}

}
