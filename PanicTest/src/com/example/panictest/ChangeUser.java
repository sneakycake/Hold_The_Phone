package com.example.panictest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ChangeUser extends Activity {
	
	public static final String PREFS_NAME = "MyPrefsFile";
	private static final String PREF_USERNAME = "username";
	private static final String PREF_PASSWORD = "password";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_change_user, menu);
		return true;
	}
	
	//What happens after they Login
	public void login(View view)
	{
		EditText x = (EditText) findViewById(R.id.username);
    	String username = x.getText().toString(); 
    	
    	EditText y = (EditText) findViewById(R.id.userPassword);
    	String password = y.getText().toString(); 
    	//Keeps a local file on the android phone with username and password.  This was practice code just for me to learn how to do it.
		getSharedPreferences(PREFS_NAME,MODE_PRIVATE).edit().putString(PREF_USERNAME, username).putString(PREF_PASSWORD, password).commit();
		Intent intent = new Intent(this, HomePage.class);
		
		
		startActivity(intent);
		
	}
}
