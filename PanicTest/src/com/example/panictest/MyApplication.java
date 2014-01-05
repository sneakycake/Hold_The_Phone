package com.example.panictest;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class MyApplication extends Application
{
	
	
	//This "Application" page is for push notifications so far. However, I've read that you can do a bit more with it.
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	@Override
	public void onCreate()
	{
		Log.w("myApp", "hit MyApp");
		super.onCreate();
		
		Parse.initialize(this, "63Yf8EYNRDGDn9UH2wsOpIHqV2XbP65bXx0jtI5l", "YLj93NTJHyoDEO36k5s6rJyt3aiWSQNeOeDm7Uxr"); 	
		PushService.setDefaultPushCallback(this, HomePage.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		Log.w("myApp", "hit MyApp");
	}
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
 
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
 



}
