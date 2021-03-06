package com.iwl.bettertogforever;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.iwl.bettertogforever.constants.AddSpouseRequestStatusConstants;
import com.iwl.bettertogforever.constants.MessageTypesConstants;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

	BetterTogForeverSqlliteDao db;
	
	@Override
	public void onReceive(Context context, Intent intent) {
				
		Bundle extras = intent.getExtras();
		try{
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
 
        String messageType = gcm.getMessageType(intent);
 
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
            	Toast.makeText(context, "you had a message but GCM bailed out", Toast.LENGTH_LONG).show();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
            	Toast.makeText(context, "message already deleted", Toast.LENGTH_LONG).show();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            	handleMessage(context, extras);
            }
        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void handleMessage(Context context, Bundle extras) {
		db = new ActivityImpl().getDataSourceFromContext(context);
		
    	CharSequence msgType = extras.getString(MessageTypesConstants.MSG_TYPE_TAG);
    	if(msgType.equals(MessageTypesConstants.ADD_COUPLE_MSG_TAG)){
    		updateDbWithReceivedRequest(AddSpouseRequestStatusConstants.PENDING_ACCEPTANCE);
    		String email = (String)extras.get(MessageTypesConstants.ADD_SPOUSE_EMAIL_MSG_TAG);
    		Integer coupleId = (Integer)extras.get(MessageTypesConstants.ADD_SPOUSE_CPL_ID_TAG);
    		Toast.makeText(context, (CharSequence)email  + " wants to add you as spouse", Toast.LENGTH_LONG).show();
    		updateCoupleIdAndEmailOfRequestingUserToDb(email, coupleId);
    		
    		// Notification and vibration
    		displayNotificationOfUserTryingToadd(context, email, coupleId);
    		
    	} else if(msgType.equals(MessageTypesConstants.ADD_SECRET_MSG_TAG)){
    		Toast.makeText(context, (CharSequence) extras.get(MessageTypesConstants.SECRET_MSG_TAG), Toast.LENGTH_LONG).show();
    		//mBuilder.setContentIntent(pendingIntent);
    		updateSecretMsgTodb((String)extras.get(MessageTypesConstants.SECRET_MSG_TAG));
    	} else if(msgType.equals(MessageTypesConstants.SHARED_LIST_MODIFIED_MSG_TAG)){
    		Toast.makeText(context, "received updated list " + (CharSequence) extras.get(MessageTypesConstants.SHARED_LIST_DATA), Toast.LENGTH_LONG).show();
    	} else if(msgType.equals(MessageTypesConstants.ACCEPT_DECLINE_MSG_TAG)){
    		Toast.makeText(context, "received accept decline status " + (CharSequence) extras.get(MessageTypesConstants.ACCEPT_DECLINE_EMAIL_DATA), Toast.LENGTH_LONG).show();
    		receivedAddStatusHandling(context, (String)extras.get(MessageTypesConstants.ACCEPT_DECLINE_EMAIL_DATA), (String)extras.get(MessageTypesConstants.ACCEPT_DECLINE_STATUS_DATA));
    	} else if(msgType.equals(MessageTypesConstants.NEW_SHARED_LIST_ADDED_TAG)){
    		Toast.makeText(context, "received new list added ", Toast.LENGTH_LONG).show();
    		receivedAddListMesssage(context, (String)extras.get(MessageTypesConstants.NEW_LIST_ADDED_ID), (String)extras.get(MessageTypesConstants.NEW_LIST_ADDED_DESC));
    	} else if(msgType.equals(MessageTypesConstants.LIST_NAME_EDITED_TAG)){
    		Toast.makeText(context, "received list name edited notification ", Toast.LENGTH_LONG).show();
    		receivedEditedListNameMessage(context, (String)extras.get(MessageTypesConstants.LIST_CHANGED_LIST_ID_DATA), (String)extras.get(MessageTypesConstants.LIST_CHANGED_NEW_DESCRIPTION_DATA));
    	} else {
    		Toast.makeText(context, "received some message", Toast.LENGTH_LONG).show();
    		
    	}
	}
	
	private void receivedEditedListNameMessage(Context context, String listId, String listDescription) {
		editListDescription(Integer.parseInt(listId), listDescription);
		
		Notification.Builder mBuilder = new Notification.Builder(context).setAutoCancel(true).setSmallIcon(R.drawable.ic_launcher).setContentTitle("list name edited to ").setContentText(" " + listDescription);
		Intent mainActivity = new Intent(context, SecretMessageActivity.class);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainActivity, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(pendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = {0, 1000, 500, 1000, 500, 1000};
		vibrator.vibrate(pattern, -1);
	}

	private void receivedAddListMesssage(Context context, String newListAddedId,
			String newListAddedDesc) {
		Integer listId = Integer.parseInt(newListAddedId);
		insertNewListAdded(listId, newListAddedDesc);
		
		Notification.Builder mBuilder = new Notification.Builder(context).setAutoCancel(true).setSmallIcon(R.drawable.ic_launcher).setContentTitle("New list added").setContentText("list desc: " + newListAddedDesc);
		Intent mainActivity = new Intent(context, SecretMessageActivity.class);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainActivity, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(pendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = {0, 1000, 500, 1000, 500, 1000};
		vibrator.vibrate(pattern, -1);
	}

	private void receivedAddStatusHandling(Context context, String email, String accepted) {
		if(accepted.equals("true")){
			updateDbWithReceivedRequest(AddSpouseRequestStatusConstants.ACCEPTED);
		} else{
			updateDbWithReceivedRequest(AddSpouseRequestStatusConstants.DECLINED);
		}
		Notification.Builder mBuilder = new Notification.Builder(context).setAutoCancel(true).setSmallIcon(R.drawable.ic_launcher).setContentTitle("your better half add status!").setContentText("status " + accepted);
		Intent mainActivity = new Intent(context, MainActivity.class);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainActivity, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(pendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = {0, 1000, 500, 1000, 500, 1000};
		vibrator.vibrate(pattern, -1);
	}

	private void displayNotificationOfUserTryingToadd(Context context, String email, Integer coupleId) {
		Notification.Builder mBuilder = new Notification.Builder(context).setAutoCancel(true).setSmallIcon(R.drawable.ic_launcher).setContentTitle("My Success Mantra!").setContentText("Time to review...");
		Intent acceptAddedCouple = new Intent(context, AcceptAddedCoupleActivity.class);
		acceptAddedCouple.putExtra("coupleEmail", email);
		acceptAddedCouple.putExtra("coupleId", coupleId);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, acceptAddedCouple, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(pendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(0, mBuilder.build());
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		long[] pattern = {0, 1000, 500, 1000, 500, 1000};
		vibrator.vibrate(pattern, -1);
	}

	private void updateCoupleIdAndEmailOfRequestingUserToDb(String email, Integer coupleId) {
		db.open();
		db.insertReceivedAddCoupleRequest(email);
		db.insertCoupleId(coupleId);
		db.close();
	}
	
	private void editListDescription(Integer listId, String newDescription) {
		db.open();
		db.editListName(listId, newDescription);
		db.close();
	}

	private void updateSecretMsgTodb(String msg) {
		db.open();
		db.insertSecretMsg(msg);
		db.close();
	}
	

	private void updateDbWithReceivedRequest(Integer status) {
		db.open();
		db.insertAddSpouseRequestStatus(status);
		db.close();
	}
	
	private void insertNewListAdded(Integer listid, String description) {
		db.open();
		db.insertNewWishlist(listid, description);
		db.close();
	}
}
