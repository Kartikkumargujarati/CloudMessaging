package com.kartik.cloudmessaging;

import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by kartik.gujarati on 7/17/17.
 */

public class FirebaseTokenGenerator extends FirebaseInstanceIdService{

public static final String TOKEN = "RegistrationToken";
	@Override
	public void onTokenRefresh() {
		// Get updated InstanceID token.
		String registrationToken = FirebaseInstanceId.getInstance().getToken();
		Log.d(TOKEN , registrationToken);
		PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
				.edit()
				.putString(TOKEN, registrationToken)
				.apply();
	}
}
