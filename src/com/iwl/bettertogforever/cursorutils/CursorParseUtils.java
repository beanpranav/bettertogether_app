package com.iwl.bettertogforever.cursorutils;

import com.iwl.bettertogforever.model.UserIdCoupleIdPair;

import android.database.Cursor;

public class CursorParseUtils {

	public UserIdCoupleIdPair getUsrCoupleId(Cursor cursor) {
		if(cursor == null || cursor.isAfterLast())
			return null;
		
		cursor.moveToFirst();
		UserIdCoupleIdPair userCoupleIds = new UserIdCoupleIdPair(cursor.getInt(0), cursor.getInt(1));
		cursor.moveToNext();
		if(!cursor.isAfterLast()){
			throw new RuntimeException("Found more than 1 userId rows");
		}
		return userCoupleIds;
	}
	
	public String getRegId(Cursor cursor) {
		if(cursor == null || cursor.isAfterLast())
			return null;
		
		cursor.moveToFirst();
		String regId = new String(cursor.getString(0));
		cursor.moveToNext();
		if(!cursor.isAfterLast()){
			throw new RuntimeException("Found more than 1 regId rows");
		}
		return regId;
	}
	
	public String getSecretMsg(Cursor cursor) {
		if(cursor == null || cursor.isAfterLast())
			return null;
		
		cursor.moveToFirst();
		String secretMsg = new String(cursor.getString(0));
		cursor.moveToNext();
		if(!cursor.isAfterLast()){
			throw new RuntimeException("Found more than 1 secretMsg rows");
		}
		return secretMsg;
	}
	
	public Integer getAddSpouseRequestStatus(Cursor cursor) {
		if(cursor == null || cursor.isAfterLast())
			return null;
		
		cursor.moveToFirst();
		Integer addRequestStatus = cursor.getInt(0);
		cursor.moveToNext();
		if(!cursor.isAfterLast()){
			throw new RuntimeException("Found more than 1 add user request status rows");
		}
		return addRequestStatus;
	}
	
	public String getAddCoupleRequestReceived(Cursor cursor) {
		if(cursor == null || cursor.isAfterLast())
			return null;
		
		cursor.moveToFirst();
		String coupleTryingToAddEmail = cursor.getString(0);
		cursor.moveToNext();
		if(!cursor.isAfterLast()){
			throw new RuntimeException("Found more than 1 addCoupleEmails rows");
		}
		return coupleTryingToAddEmail;
	}
}