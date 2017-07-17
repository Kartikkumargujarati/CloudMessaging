package com.kartik.cloudmessaging;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by kartik.gujarati on 7/17/17.
 */

public class FirebaseTokenGenerator extends FirebaseInstanceIdService{


	@Override
	public void onTokenRefresh() {
		// Get updated InstanceID token.
		String registrationToken = FirebaseInstanceId.getInstance().getToken();
		Log.d("RegistrationToken: ", registrationToken);

	}
}
