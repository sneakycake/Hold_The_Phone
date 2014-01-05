package com.example.panictest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
public class HomePage extends Activity {
	
	
	public static final String PREFS_NAME = "MyPrefsFile";
	private static final String PREF_USERNAME = "username";
	private static final String PREF_PASSWORD = "password";
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		
		
		
		Parse.initialize(this, "63Yf8EYNRDGDn9UH2wsOpIHqV2XbP65bXx0jtI5l", "YLj93NTJHyoDEO36k5s6rJyt3aiWSQNeOeDm7Uxr"); 
		
		
		SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);  
		//getSharedPreferences(PREFS_NAME,MODE_PRIVATE).edit().putString(PREF_USERNAME, "Kevin").putString(PREF_PASSWORD, null).commit();
		String username = pref.getString(PREF_USERNAME, null);
		String password = pref.getString(PREF_PASSWORD, null);
		
		//setting Login/Change User Text
		Button login = (Button) findViewById(R.id.changeUserButton);
		Log.w("myApp", "hit preff");
		if(username==null)
		{
			
			login.setText("Login");
			Log.w("myApp", "hit login button");
		}
		else
		{
			login.setText("Change User");
			
		}
		//Setting Username Text
		final TextView user = (TextView) findViewById(R.id.user);
		user.setText(username);
		Log.w("myApp", "set user");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_page, menu);
		return true;
	}
	
	//What happens after you click the panic button
	public void panic(View view)
	{
		
		//Trying to Send pushes through phone. Not working as of now.
		ParseInstallation installation = ParseInstallation.getCurrentInstallation();
		installation.increment("Counter");
		installation.put("class",true);
		installation.saveInBackground();
		ParseQuery pushQuery = ParseInstallation.getQuery();
		pushQuery.whereEqualTo("class", true);
		// Send push notification to query
		ParsePush push = new ParsePush();
		push.setQuery(pushQuery); // Set our Installation query
		push.setMessage("it works!");
		push.sendInBackground();
		
		
		//Goes to TestPage.class after button is clicked "Panic" button is clicked
		Intent intent = new Intent(this, TestPage.class);
		startActivity(intent);
	}
	//Goes to CreateRoom
	public void createRoom(View view)
	{
		Intent intent = new Intent(this, CreateRoom.class);
		startActivity(intent);
	}
	//Goes to Change User
	public void changeUser(View view)
	{
		Intent intent = new Intent(this, ChangeUser.class);
		startActivity(intent);
	}
	
}
