package com.example.panictest;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DataGrabber extends IntentService{
	Context mContext;
	PendingIntent pendingIntent; 
	
	static double counter;
	static double threshold;
	static double numStudents;
	
	public DataGrabber() {
		super("DataGrabber");
		Log.w("myApp", "started1");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.w("myApp", "started");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Room");
		query.whereEqualTo("Room", JoinRoom.roomName());
		query.getFirstInBackground(new GetCallback<ParseObject>(){ 

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
		
		
		  mContext = MyApplication.getAppContext();  
	     
	      Intent intent1 = new Intent();  
	      intent1.setClass(mContext,TestPage.class);  
	      pendingIntent =  PendingIntent.getActivity(mContext, 0, intent1, 0); 
	      try {
			pendingIntent.send(mContext, 0, intent1);
		      Log.w("myApp", "SENT");
		} catch (CanceledException e) {
		      Log.w("myApp", "CAUGHT");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	public static double counter()
	{
		return counter;
	}
	public double threshold()
	{
		return threshold;
	}
	public static double numStudents()
	{
		return numStudents;
	}
	public static boolean overThreshold()
	{
		Double percent = (counter()+1)/numStudents();
		if (percent>=threshold)
		{
			return true;			
		}
		return false;
	}

}
