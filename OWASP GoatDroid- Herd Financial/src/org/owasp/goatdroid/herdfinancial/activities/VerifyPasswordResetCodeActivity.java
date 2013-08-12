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
 * @created 2012
 */
package org.owasp.goatdroid.herdfinancial.activities;

import java.util.HashMap;

import org.owasp.goatdroid.herdfinancial.R;
import org.owasp.goatdroid.herdfinancial.base.BaseActivity;
import org.owasp.goatdroid.herdfinancial.db.UserInfoDBHelper;
import org.owasp.goatdroid.herdfinancial.misc.Constants;
import org.owasp.goatdroid.herdfinancial.misc.Utils;
import org.owasp.goatdroid.herdfinancial.request.ForgotPasswordRequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class VerifyPasswordResetCodeActivity extends BaseActivity {

	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verify_password_reset_code);
		context = getApplicationContext();
	}

	public void submitCode(View v) {

		String passwordResetCode = ((EditText) findViewById(R.id.passwordResetCodeEditText))
				.getText().toString();
		UserInfoDBHelper uidh = new UserInfoDBHelper(context);
		String userName = uidh.getUserName();
		ForgotPasswordRequest rest = new ForgotPasswordRequest(context);
		HashMap<String, String> verifyCodeData = new HashMap<String, String>();

		try {
			verifyCodeData = rest.verifyCode(userName, passwordResetCode);
			if (verifyCodeData.get("success").equals("true")) {
				Bundle bundle = new Bundle();
				bundle.putString("userName", userName);
				bundle.putString("passwordResetCode", passwordResetCode);
				Intent intent = new Intent(VerifyPasswordResetCodeActivity.this,
						ResetPasswordActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			} else
				Utils.makeToast(context, Constants.INVALID_RESET_CODE,
						Toast.LENGTH_LONG);
		} catch (Exception e) {
			Utils.makeToast(context, Constants.WEIRD_ERROR, Toast.LENGTH_LONG);
		} finally {
			uidh.close();
		}
	}
}
