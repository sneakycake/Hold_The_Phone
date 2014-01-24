package com.example.panictest;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PanicPage extends Activity {
	String roomName = JoinRoom.roomName();
	
	static double counter;
	static double threshold;
	static double numStudents;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panic_page);
		
		final TextView room = (TextView) findViewById(R.id.RoomNameText);
		room.setText(roomName);
		Parse.initialize(this, "63Yf8EYNRDGDn9UH2wsOpIHqV2XbP65bXx0jtI5l", "YLj93NTJHyoDEO36k5s6rJyt3aiWSQNeOeDm7Uxr"); 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_panic_page, menu);
		return true;
	}

	
	
	public void panic(View view)
	{
		
		
		/*
		JSONObject object = new JSONObject();
		  try {
		    object.put("action", "com.example.UPDATE_STATUS");
		    object.put("nickname", "Hacker");
		  } catch (JSONException e) {
		    e.printStackTrace();
		  }
		
		  
		// Send push notification to query
		ParseQuery<ParseInstallation> pushQuery = ParseInstallation.getQuery();
		pushQuery.whereEqualTo("class", true);
		
		ParsePush push = new ParsePush();
		push.setQuery(pushQuery); // Set our Installation query
		push.setMessage("it works!");
		push.setData(object);
		push.sendInBackground();
		
		*/
		
		
		
		//the following code is random and doesnt make sense.  the hacky way
		
		/*
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Room");
		query.whereEqualTo("Room", JoinRoom.roomName());
		query.getFirstInBackground(new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (object == null) {
		      Log.d("score", "The getFirst request failed.");
		    } else {
		    	counter = object.getInt("Counter");
		       	threshold = object.getDouble("Threshold");
		    	numStudents = object.getDouble("NumberStudents");
		    	Log.w("myApp", "grabbed Obejects");
		    }
		  }
		});
		
		
		//Goes to TestPage.class after button is clicked "Panic" button is clicked
		*/
		Log.w("myApp", "clicked");
		Intent intent = new Intent(getBaseContext(), com.example.panictest.DataGrabber.class);
		startService(intent);
		Log.w("myApp", "started service");
		
		
		
		
	}

}
