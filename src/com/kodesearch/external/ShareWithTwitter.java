package com.kodesearch.external;

import com.kodesearch.ListResults;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ShareWithTwitter extends Activity {
	// Progress dialog
	ProgressDialog pDialog;

	public ShareWithTwitter() {
		String status = "My first tweet";
		new updateTwitterStatus().execute(status);
	}

	/*
	 * public void share() { String CONSUMER_KEY = "RNWvy9zoLDwEhhD2STObvA";
	 * String CONSUMER_SECRET = "2SwMHtda6JjwqlbOWG0qff2NsoYB41RO36f0FJGk";
	 * String ACCESS_SECRET = "b9jqUu536MlJiqq7wPNXHZyg6tpbJdMFIx7XSY2piv8Hr";
	 * String ACCESS_TOKEN =
	 * "558343026-9Yx52NwwSdjJihfJ3JKMtrrJSPnYeTGPZUD2H9Qn";
	 * 
	 * // Consumer Twitter twitter = new TwitterFactory().getInstance();
	 * twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
	 * 
	 * // Access Token AccessToken accessToken = null; accessToken = new
	 * AccessToken(ACCESS_TOKEN, ACCESS_SECRET);
	 * twitter.setOAuthAccessToken(accessToken);
	 * 
	 * // Posting Status Status status = null; try { status =
	 * twitter.updateStatus("YOUR_STATUS"); } catch (TwitterException e) {
	 * e.printStackTrace(); }
	 * System.out.println("Successfully updated the status: " +
	 * status.getText()); }
	 */

	class updateTwitterStatus extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ShareWithTwitter.this);
			pDialog.setMessage("Updating to twitter...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting Places JSON
		 * */
		protected String doInBackground(String... args) {
			String CONSUMER_KEY = "RNWvy9zoLDwEhhD2STObvA";
			String CONSUMER_SECRET = "2SwMHtda6JjwqlbOWG0qff2NsoYB41RO36f0FJGk";
			String ACCESS_SECRET = "b9jqUu536MlJiqq7wPNXHZyg6tpbJdMFIx7XSY2piv8Hr";
			String ACCESS_TOKEN = "558343026-9Yx52NwwSdjJihfJ3JKMtrrJSPnYeTGPZUD2H9Qn";

			String status = args[0];

			// Consumer
			try {
				ConfigurationBuilder builder = new ConfigurationBuilder();
				builder.setOAuthConsumerKey(CONSUMER_KEY);
				builder.setOAuthConsumerSecret(CONSUMER_SECRET);

				// Access Token
				String access_token = ACCESS_TOKEN;
				// Access Token Secret
				String access_token_secret = ACCESS_SECRET;

				AccessToken accessToken = new AccessToken(access_token,
						access_token_secret);
				Twitter twitter = new TwitterFactory(builder.build())
						.getInstance(accessToken);

				// Update status
				twitter4j.Status response = twitter.updateStatus(status);

				Log.d("Status", "> " + response.getText());
			} catch (TwitterException e) {
				// Error in updating status
				Log.d("Twitter Update Error", e.getMessage());
			}
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog and show
		 * the data in UI Always use runOnUiThread(new Runnable()) to update UI
		 * from background thread, otherwise you will get error
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(getApplicationContext(),
							"Status tweeted successfully", Toast.LENGTH_SHORT)
							.show();

				}
			});
		}

	}

}