package com.kodesearch;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListResults extends ListActivity {

	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://searchcode.com/api/search_IV/?q=";

	// JSON Node names
	private static final String ARR_RESULTS = "results";
	private static final String OBJ_NAME = "name";
	private static final String OBJ_DESCRIPTION = "description";
	private static final String OBJ_SYNOPSIS = "synopsis";
	private static final String OBJ_DISPLAY_NAME = "displayname";
	private static final String OBJ_URL = "url";

	// Seach term
	private static final String SEARCH_KEYWORD = "seachterm";

	TextView synopView;

	// contacts JSONArray
	JSONArray contacts = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> resultsList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		

		resultsList = new ArrayList<HashMap<String, String>>();

		ListView lv = getListView();

		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name))
						.getText().toString();
				String desc = ((TextView) view.findViewById(R.id.description))
						.getText().toString();
				String synop = ((TextView) view.findViewById(R.id.synopsis))
						.getText().toString();
				synopView = (TextView) view.findViewById(R.id.synopsis);
				synopView.setVisibility(View.GONE);
				String url = ((TextView) view.findViewById(R.id.url)).getText()
						.toString();

				// Starting single contact activity
				Intent in = new Intent(getApplicationContext(),
						ViewResult.class);
				in.putExtra(OBJ_NAME, name);
				in.putExtra(OBJ_DESCRIPTION, desc);
				in.putExtra(OBJ_SYNOPSIS, synop);
				in.putExtra(OBJ_URL, url);
				startActivity(in);

			}
		});

		// getting intent data
		Intent intt = getIntent();

		// get search term
		String search_term = intt.getStringExtra(SEARCH_KEYWORD);

		// Calling async task to get json
		new GetContacts().execute(search_term);
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(ListResults.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(String... search_term) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url+URLEncoder.encode(search_term[0]), ServiceHandler.GET);

			Log.d("search term: ", "> " + search_term[0]);
			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					contacts = jsonObj.getJSONArray(ARR_RESULTS);

					// looping through All Contacts
					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);

						String name = c.getString(OBJ_NAME);
						String desc = c.getString(OBJ_DESCRIPTION);
						String synop = c.getString(OBJ_SYNOPSIS);
						String icon = c.getString(OBJ_DISPLAY_NAME);
						String url = c.getString(OBJ_URL);

						// tmp hashmap for single contact
						HashMap<String, String> single_result = new HashMap<String, String>();

						single_result.put(OBJ_NAME, name);
						single_result.put(OBJ_DESCRIPTION, desc);
						single_result.put(OBJ_SYNOPSIS, synop);
						single_result.put(OBJ_DISPLAY_NAME, icon);
						single_result.put(OBJ_URL, url);

						// adding contact to contact list
						resultsList.add(single_result);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			ListAdapter adapter = new SimpleAdapter(ListResults.this,
					resultsList, R.layout.list_item, new String[] { OBJ_NAME,
							OBJ_DESCRIPTION, OBJ_SYNOPSIS, OBJ_DISPLAY_NAME,
							OBJ_URL }, new int[] { R.id.name, R.id.description,
							R.id.synopsis, R.id.type, R.id.url });

			setListAdapter(adapter);
		}

	}

}