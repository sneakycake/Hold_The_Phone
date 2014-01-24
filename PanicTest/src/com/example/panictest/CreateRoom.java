package com.example.panictest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.parse.Parse;
import com.parse.ParseObject;

public class CreateRoom extends Activity {
	ParseObject Room = new ParseObject("Room");
	static String roomName;
	String roomPassword;
	static Double threshold;
	static Double numberStudents;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_room);
		
		Parse.initialize(this, "63Yf8EYNRDGDn9UH2wsOpIHqV2XbP65bXx0jtI5l", "YLj93NTJHyoDEO36k5s6rJyt3aiWSQNeOeDm7Uxr"); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_create_room, menu);
		return true;
	}
	//What happens after you create a room through the app
	public void confirmCreateRoom (View view)
	{
		Intent intent = new Intent(this, RoomCreateSuccess.class);
		
		EditText x = (EditText) findViewById(R.id.createRoomName);
    	roomName = x.getText().toString(); 
    	
    	EditText y = (EditText) findViewById(R.id.createRoomPassword);
		roomPassword = y.getText().toString();
    	
		EditText z = (EditText) findViewById(R.id.CreateRoomThreshold);
		threshold = Double.parseDouble(z.getText().toString());
		
		
		EditText roomNum = (EditText) findViewById(R.id.CreateRoomNumberStudents);
		numberStudents = Double.parseDouble(roomNum.getText().toString());
		
		
		
		threshold = threshold/100;
		
		
		Log.w("myApp", roomName);
    	//Saves the room name, room password and creates a room counter starting at 0. 
    	Room.put("Counter", 0);
    	Room.put("Room",roomName);
		Room.put("Password", roomPassword);
		Room.put("Threshold", threshold);
		Room.put("NumberStudents", numberStudents);
    	Room.saveInBackground();
    	Log.w("myApp", "hit put");
		startActivity(intent);
		
	}
	public static String roomName()
	{
		return roomName;
	}
	public static Double threshold()
	{
		return threshold;
	}
	public static Double numStudents()
	{
		return numberStudents;
	}
}	
