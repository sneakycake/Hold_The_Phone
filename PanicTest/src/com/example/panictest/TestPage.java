package com.example.panictest;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class TestPage extends Activity {
	double counter;
	double threshold;
	double numStudents;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_page);
		Parse.initialize(this, "63Yf8EYNRDGDn9UH2wsOpIHqV2XbP65bXx0jtI5l", "YLj93NTJHyoDEO36k5s6rJyt3aiWSQNeOeDm7Uxr"); 
	
		
		//For now, I am just using the ParseObject ID of the room "working?" This finds the ParseObject in the ParseClass "Room" and then increments the counter. 
		//Bassically, the counter for room "working" goes up by 1
		
		
		//Newest Version
		Log.w("myApp", Double.toString(DataGrabber.counter()));
		Log.w("myApp", String.valueOf(DataGrabber.overThreshold()));
		counter = DataGrabber.counter()+1;
		TextView numberPanic = (TextView)findViewById(R.id.TestPageNumberPanic);
		numberPanic.setText("# Panicking: "+counter);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Room");
		query.whereEqualTo("Room", JoinRoom.roomName());
		query.getFirstInBackground(new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (object == null) {
		      Log.d("score", "The getFirst request failed.");
		    } else {
		    	object.increment("Counter");
		    	object.saveInBackground();
		    	Log.w("myApp", "Increased Counter");
		    }
		  }
		});
		
		
		
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
		
		*/
		
		
		//Old Version with only Object ID
		
		/*
		 ParseQuery<ParseObject> query = ParseQuery.getQuery("Room");
	 		//query.whereEqualTo("Room","working");
	 		// Retrieve the object by id
	 		query.getInBackground("tLgPT1KSxx", new GetCallback<ParseObject>() {
	 		  public void done(ParseObject room, ParseException e) {
	 		    if (e == null) {
	 		    	room.increment("Counter");
	 				room.saveInBackground();
	 		    }
	 		  
	 		}
	 		});
		
		*/
		
		
		
		Log.w("myApp", "skipped increment");
		
		
		//After 10 sec, it will decrease that counter by 1.
		new CountDownTimer(10000, 1000) {
			TextView countdown = (TextView)findViewById(R.id.textCountdown); 
		     public void onTick(long millisUntilFinished) {
		    	 
		    	
		    	 countdown.setText("seconds remaining: " + millisUntilFinished / 1000);
		     }

		     public void onFinish() {
		         countdown.setText("Decremented");
		         
		         
		        ParseQuery<ParseObject> query = ParseQuery.getQuery("Room");
		 		query.whereEqualTo("Room", JoinRoom.roomName());
		 		query.getFirstInBackground(new GetCallback<ParseObject>() {
		 		  public void done(ParseObject object, ParseException e) {
		 		    if (object == null) {
		 		      Log.d("score", "The getFirst request failed.");
		 		    } else {
		 		    	object.increment("Counter",-1);
		 		    	object.saveInBackground();
		 		    	Log.w("myApp", "Decreased Counter");
		         
		 		    }
				  }
				});
		         
		         
		         
		     //Old decrement    
		         
		 		
		 		/*
		        ParseQuery<ParseObject> query = ParseQuery.getQuery("Room");
		 		//query.whereEqualTo("Room","working");
		 		// Retrieve the object by id
		 		query.getInBackground("tLgPT1KSxx", new GetCallback<ParseObject>() {
		 		  public void done(ParseObject room, ParseException e) {
		 		    if (e == null) {
		 		    	room.increment("Counter", -1);
		 				room.saveInBackground();
		 		    }
		 		  
		 		}
		 		});
		         */
		     }
		  }.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_test_page, menu);
		return true;
	}

}
