package com.example.panictest;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class JoinRoom extends Activity {
	static String roomName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_room);
		
		Log.d("roomname", "JoinRoom ");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_join_room, menu);
		return true;
	}
	
	public void joinRoom(View view)
	{
		Log.w("myApp", "JOIN ROOM");
		
		EditText x = (EditText) findViewById(R.id.RoomName);
    	roomName = x.getText().toString(); 
    	EditText y = (EditText) findViewById(R.id.RoomNamePassword);
    	String roomPass = y.getText().toString();
		
    	ParseInstallation installation = ParseInstallation.getCurrentInstallation();
		//installation.increment("Counter");
		installation.put("Room",roomName);
		installation.saveInBackground();
    	
    	
    	
    	final Intent intent = new Intent(this, PanicPage.class);
    	final Intent intent1 = new Intent(this, JoinRoomFailed.class);
    	Log.w("myApp", "JOIN ROOM1");
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("Room");
    	query.whereEqualTo("Room", roomName);
    	query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> room, ParseException e) {
				 if (e == null) {
					 Log.w("myApp", "JOIN ROOM2"+room.size());
			            if(room.size()==1)
			    		startActivity(intent);
			            else
			            startActivity(intent1);
			        } else {
			        	Log.w("myApp", "JOIN ROOM3");
			            startActivity(intent1);
			        }
				
			}
    	});
    	
    	
	}
	public static String roomName()
	{
		return roomName;
	}
}
