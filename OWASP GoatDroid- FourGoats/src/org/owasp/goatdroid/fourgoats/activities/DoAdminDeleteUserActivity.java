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
package org.owasp.goatdroid.fourgoats.activities;

import java.util.HashMap;

import org.owasp.goatdroid.fourgoats.R;
import org.owasp.goatdroid.fourgoats.base.BaseActivity;
import org.owasp.goatdroid.fourgoats.misc.Constants;
import org.owasp.goatdroid.fourgoats.misc.Utils;
import org.owasp.goatdroid.fourgoats.request.AdminRequest;
import org.owasp.goatdroid.fourgoats.responseobjects.GenericResponseObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DoAdminDeleteUserActivity extends BaseActivity {

	Context context;
	Bundle bundle;
	TextView userNameTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.do_delete_user);
		context = this.getApplicationContext();
		bundle = getIntent().getExtras();
		userNameTextView = (TextView) findViewById(R.id.userNameTextView);
		userNameTextView.setText(bundle.getString("userName"));
	}

	public void doDeleteUser(View v) {

		DeleteUserAsyncTask task = new DeleteUserAsyncTask();
		task.execute(null, null);
	}

	public void launchAdminHome() {
		Intent intent = new Intent(DoAdminDeleteUserActivity.this,
				AdminHomeActivity.class);
		startActivity(intent);
	}

	public void launchAdminHome(View v) {
		Intent intent = new Intent(DoAdminDeleteUserActivity.this,
				AdminHomeActivity.class);
		startActivity(intent);
	}

	public void launchHome() {
		Intent intent = new Intent(context, AdminHomeActivity.class);
		startActivity(intent);
	}

	public void launchLogin() {
		Intent intent = new Intent(context, LoginActivity.class);
		startActivity(intent);
	}

	private class DeleteUserAsyncTask extends
			AsyncTask<Void, Void, GenericResponseObject> {
		protected GenericResponseObject doInBackground(Void... params) {

			GenericResponseObject response = new GenericResponseObject();
			AdminRequest rest = new AdminRequest(context);

			try {
				response = rest.deleteUser(bundle.getString("userName"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;

		}

		public void onPostExecute(GenericResponseObject results) {
			/*
			 * if (results.get("success").equals("true")) {
			 * Utils.makeToast(context, Constants.DELETION_SUCCESS,
			 * Toast.LENGTH_LONG); launchAdminHome(); } else if
			 * (results.get("errors").equals(Constants.INVALID_SESSION)) {
			 * Utils.makeToast(context, Constants.INVALID_SESSION,
			 * Toast.LENGTH_LONG); launchLogin(); } else {
			 * Utils.makeToast(context, results.get("errors"),
			 * Toast.LENGTH_LONG); }
			 */
		}
	}

}
