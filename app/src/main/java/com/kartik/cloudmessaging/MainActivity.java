package com.kartik.cloudmessaging;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import static com.kartik.cloudmessaging.FirebaseTokenGenerator.TOKEN;

public class MainActivity extends AppCompatActivity {

/* -------------------------------------- Lifecycle Methods -------------------------------------- */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("Token: ", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(TOKEN, "0"));
	}
/* ------------------------------------ End Lifecycle Methods ------------------------------------ */
}
