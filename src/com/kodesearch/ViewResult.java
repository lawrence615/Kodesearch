package com.kodesearch;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;


import com.kodesearch.external.AlertDialogManager;
import com.kodesearch.external.CleanHtml;
import com.kodesearch.external.ConnectionDetector;

@SuppressLint("NewApi")
public class ViewResult extends ActionBarActivity {

	private static final String OBJ_NAME = "name";
	private static final String OBJ_DESCRIPTION = "description";
	private static final String OBJ_SYNOPSIS = "synopsis";
	private static final String OBJ_URL = "url";
	
	/*
	 * Twitter API Keys
	 */
	private String CONSUMER_KEY = "RNWvy9zoLDwEhhD2STObvA";
	private String CONSUMER_SECRET = "2SwMHtda6JjwqlbOWG0qff2NsoYB41RO36f0FJGk";
	private String ACCESS_SECRET = "b9jqUu536MlJiqq7wPNXHZyg6tpbJdMFIx7XSY2piv8Hr";
	private String ACCESS_TOKEN = "558343026-9Yx52NwwSdjJihfJ3JKMtrrJSPnYeTGPZUD2H9Qn";

	// Internet Connection detector
	private ConnectionDetector cd;

	// Alert Dialog Manager
	AlertDialogManager alert = new AlertDialogManager();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_result);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		// getting intent data
		Intent intn = getIntent();

		// get JSON data from previous data
		String name = intn.getStringExtra(OBJ_NAME);
		String desc = intn.getStringExtra(OBJ_DESCRIPTION);
		String synop = intn.getStringExtra(OBJ_SYNOPSIS);
		String url = intn.getStringExtra(OBJ_URL);

		// displaying all values on the screen
		TextView lblName = (TextView) findViewById(R.id.name_label);
		WebView webDesc = (WebView) findViewById(R.id.description_web);
		WebView webSynop = (WebView) findViewById(R.id.synopsis_web);
		TextView lblUrl = (TextView) findViewById(R.id.url_label);
		
		CleanHtml cleanHtml = new CleanHtml();

		lblName.setText(name);
		webDesc.loadData(desc, "text/html", "utf-8");
		webSynop.loadData(synop, "text/html", "utf-8");
		lblUrl.setText(url);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.view_result_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_facebook:
			Toast toast = Toast.makeText(getApplicationContext(),
					"post on facebook", Toast.LENGTH_SHORT);
			toast.show();
			return true;
		case R.id.action_twitter:
			TextView lblDesc = (TextView) findViewById(R.id.description_web);
			TextView lblUrl = (TextView) findViewById(R.id.url_label);
			
			String descript = lblDesc.getText().toString();
			String info_url = lblUrl.getText().toString();

			try {
				Twitter twitter = new TwitterFactory().getInstance();
				AccessToken a = new AccessToken(ACCESS_TOKEN, ACCESS_SECRET);
				twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
				twitter.setOAuthAccessToken(a);
				twitter.updateStatus(descript+"\n"+info_url);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Toast toast2 = Toast.makeText(getApplicationContext(), "tweeted successfully",
					Toast.LENGTH_SHORT);
			toast2.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
