/**
 * OWASP GoatDroid Project
 * 
 * This file is part of the Open Web Application Security Project (OWASP)
 * GoatDroid project. For details, please see
 * https://www.owasp.org/index.php/Projects/OWASP_GoatDroid_Project
 *
 * Copyright (c) 2012 - The OWASP Foundation
 * 
 * GoatDroid is published by OWASP under the GPLv3 license. You should read and accept the
 * LICENSE before you use, modify, and/or redistribute this software.
 * 
 * @author Jack Mannino (Jack.Mannino@owasp.org https://www.owasp.org/index.php/User:Jack_Mannino)
 * @author Walter Tighzert
 * @created 2012
 */
package org.owasp.goatdroid.fourgoats.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import org.owasp.goatdroid.fourgoats.R;
import org.owasp.goatdroid.fourgoats.activities.DoAdminPasswordResetActivity;
import org.owasp.goatdroid.fourgoats.adapter.SearchForFriendsAdapter;
import org.owasp.goatdroid.fourgoats.request.AdminRequest;
import org.owasp.goatdroid.fourgoats.responseobjects.Admin;
import org.owasp.goatdroid.fourgoats.responseobjects.Login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public class ResetUserPasswords extends SherlockFragment {

	Context context;
	ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		context = this.getActivity();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.reset_user_passwords, container,
				false);

		listView = (ListView) v.findViewById(R.id.usersListView);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> myAdapter, View myView,
					int myItemInt, long mylng) {
				String selectedFromList = (String) (listView
						.getItemAtPosition(myItemInt));
				String[] splitList = selectedFromList.split("\n");
				String userName = splitList[1];
				Intent intent = new Intent(getActivity(),
						DoAdminPasswordResetActivity.class);
				Bundle userNameBundle = new Bundle();
				userNameBundle.putString("userName", userName);
				intent.putExtras(userNameBundle);
				startActivity(intent);
			}
		});

		SearchForUsers search = new SearchForUsers();
		search.execute(null, null);
		return v;
	}

	public String[] bindListView(Admin userData) {

		// userName, firstName, lastName
		ArrayList<String> userArray = new ArrayList<String>();
		for (HashMap<String, String> user : userData.getUsers()) {
			if ((user.get("firstName") != null)
					&& (user.get("lastName") != null)
					&& (user.get("userName") != null))

				userArray.add(user.get("firstName") + " "
						+ user.get("lastName") + "\n" + user.get("userName"));
		}
		String[] users = new String[userArray.size()];
		users = userArray.toArray(users);
		return users;
	}

	private class SearchForUsers extends AsyncTask<Void, Void, String[]> {
		protected String[] doInBackground(Void... params) {

			Admin admin = new Admin();
			AdminRequest rest = new AdminRequest(context);
			try {

				admin = rest.getUsers();
				if (!admin.getUsers().isEmpty()) {
					return bindListView(admin);
				}
			} catch (Exception e) {
				Intent intent = new Intent(context, Login.class);
				startActivity(intent);
			}
			return new String[0];
		}

		public void onPostExecute(String[] users) {
			if (getActivity() != null) {
				listView.setAdapter(new SearchForFriendsAdapter(getActivity(),
						users));
			}
		}
	}
}
