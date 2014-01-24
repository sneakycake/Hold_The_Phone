package com.example.panictest;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

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
		
		
		if (DataGrabber.overThreshold()==true)
		{
			JSONObject object = new JSONObject();
			  try {
			    object.put("action", "com.example.UPDATE_STATUS");
			    
			  } catch (JSONException e) {
			    e.printStackTrace();
			  }
			
			  
			// Send push notification to query
			ParseQuery<ParseInstallation> pushQuery = ParseInstallation.getQuery();
			pushQuery.whereEqualTo("Room", JoinRoom.roomName());
			pushQuery.whereEqualTo("class", true);
			
			ParsePush push = new ParsePush();
			push.setQuery(pushQuery); // Set our Installation query
			push.setMessage("it works!");
			push.setData(object);
			push.sendInBackground();
		}
		
		
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
