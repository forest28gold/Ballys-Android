package com.ballyscolombo.ballys;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

@SuppressLint("NewApi") public class SplashActivity extends Activity {
	
	private static final String TAG = "SplashActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		WaitThread waitThread = new WaitThread();
		waitThread.execute("");
		
	}
	
	class WaitThread extends AsyncTask<String, Void, JSONObject> {
		@Override
		protected JSONObject doInBackground(String... urls) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
			return null;
		}

		@Override
		protected void onPostExecute(JSONObject result) {

			Intent intent = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			Log.d(TAG, "Start!");
			finish();
				
		}

	}
	
}
