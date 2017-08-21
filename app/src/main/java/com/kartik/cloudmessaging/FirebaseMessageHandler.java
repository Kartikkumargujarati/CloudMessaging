package com.kartik.cloudmessaging;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by kartik.gujarati on 7/17/17.
 */

public class FirebaseMessageHandler extends FirebaseMessagingService {

	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {

		// Received notification from:
		Log.d("From: ", remoteMessage.getFrom());

		// Body of the notification received
		if (remoteMessage.getNotification() != null) {
			Log.d("MessageNotification: ", remoteMessage.getNotification().getBody());
		}

		showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
	}

	public void showNotification(final String from, final String message){
		final NotificationBuilder notificationBuilder = new NotificationBuilder(getApplicationContext());
		notificationBuilder.buildNotification(from, message, new Intent(getApplicationContext(), MainActivity.class));
	}
}
