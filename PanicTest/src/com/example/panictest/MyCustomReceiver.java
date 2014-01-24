package com.example.panictest;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyCustomReceiver extends BroadcastReceiver {

	private static final String TAG = "MyCustomReceiver";
	PendingIntent pendingIntent;  
	Context mContext;  
  @Override
  public void onReceive(Context context, Intent intent) {
	  
	  Log.w("myApp", "hit onRecieve");
	  
	  mContext = context;  
     
      Intent intent1 = new Intent();  
      intent1.setClass(mContext,ThresholdHit.class);  
      pendingIntent =  PendingIntent.getActivity(mContext, 0, intent1, 0); 
      try {
		pendingIntent.send(mContext, 0, intent1);
	      Log.w("myApp", "SENT");
	} catch (CanceledException e) {
	      Log.w("myApp", "CAUGHT");
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
      Log.w("myApp", "hit pendingintent");
	  /*
	  
	  try {
      String action = intent.getAction();
      String channel = intent.getExtras().getString("com.parse.Channel");
      JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));
 
      Log.d(TAG, "got action " + action + " on channel " + channel + " with:");
      Iterator itr = json.keys();
      while (itr.hasNext()) {
        String key = (String) itr.next();
        Log.d(TAG, "..." + key + " => " + json.getString(key));
      }
    } catch (JSONException e) {
      Log.d(TAG, "JSONException: " + e.getMessage());
    }
  
  */
	  
	
  }	  
	  
}
  
  
  