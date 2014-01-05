package com.example.panictest;



import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class RoomCreateSuccess extends Activity {
	
	//Page that tells you that you created the room successfully.
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_create_success);
		
		//Displays success image for 1.5 seconds and then returns to the homepage
		
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(RoomCreateSuccess.this, HomePage.class);
                RoomCreateSuccess.this.startActivity(mainIntent);
                RoomCreateSuccess.this.finish();
            }
        }, 1500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_room_create_success, menu);
		return true;
	}

}
