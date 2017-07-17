package com.kartik.cloudmessaging;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by kartik.gujarati on 7/17/17.
 */

public class NotificationBuilder {

	private Context context;
	private final int NOTIFICATION_ID = 1;


	public NotificationBuilder(Context context){
		this.context = context;
	}


	public void buildNotification(String from, String message, Intent intent){

		PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

		Notification notification = builder.setContentIntent(pendingIntent).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(from).setContentText(message).build();

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID, notification);
	}
}
