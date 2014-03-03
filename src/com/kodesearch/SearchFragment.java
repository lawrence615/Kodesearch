package com.kodesearch;

import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchFragment extends Fragment {
	// Search button
	private Button btnSearch;
	private EditText txt_search;
	private static final String SEARCH_KEYWORD = "seachterm";
	public static final String ARG_SEARCH = "search_arg";

	/*
	 * public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * setContentView(R.layout.activity_search);
	 * 
	 * btnSearch = (Button) findViewById(R.id.btn_search); txt_search =
	 * (EditText) findViewById(R.id.search_term);
	 * 
	 * btnSearch.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View arg0) { String search_term =
	 * txt_search.getText().toString();
	 * 
	 * if (search_term.trim().length() > 0) { // Starting an intent Intent
	 * intent = new Intent(getApplicationContext(), ListResults.class);
	 * intent.putExtra(SEARCH_KEYWORD, search_term); startActivity(intent); }
	 * else { // EditText is empty Toast.makeText(getApplicationContext(),
	 * "Please enter something to search", Toast.LENGTH_SHORT).show(); }
	 * 
	 * } });
	 * 
	 * }
	 */

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View searchView = inflater.inflate(R.layout.activity_search, container,
				false);

		btnSearch = (Button) searchView.findViewById(R.id.btn_search);
		txt_search = (EditText) searchView.findViewById(R.id.search_term);

		btnSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String search_term = txt_search.getText().toString();

				if (search_term.trim().length() > 0) {
					// Starting an intent
					Intent intent = new Intent(getActivity(), ListResults.class);
					intent.putExtra(SEARCH_KEYWORD, search_term);
					startActivity(intent);
				} else {
					// EditText is empty
					Toast.makeText(getActivity(),
							"Please enter something to search",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		/*
		 * int i = getArguments().getInt(ARG_SEARCH); String layout_title =
		 * getResources().getStringArray( R.array.actions_titles)[i]; String
		 * layout_name = getResources().getStringArray(
		 * R.array.actions_layout)[i];
		 * 
		 * int layoutId = getResources().getIdentifier(
		 * layout_name.toLowerCase(Locale.getDefault()), "layout",
		 * getActivity().getPackageName());
		 */

		return searchView;
	}

}
