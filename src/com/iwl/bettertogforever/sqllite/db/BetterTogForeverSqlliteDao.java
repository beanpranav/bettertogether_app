package com.iwl.bettertogforever.sqllite.db;

import com.iwl.bettertogforever.cursorutils.CursorParseUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BetterTogForeverSqlliteDao {

	private SQLiteDatabase database;
	private BetterTogForeverSqlliteDb dbHelper;
	private CursorParseUtils parseUtils = new CursorParseUtils();
	
	public BetterTogForeverSqlliteDao(Context context) {
		dbHelper = new BetterTogForeverSqlliteDb(context);
	}
	  
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public Long insertUserId(Integer userId){
		database.delete(SqlQueries.USER_ID_COUPLE_ID_TABLE, null, null);
		ContentValues usrId = new ContentValues();
		usrId.put(SqlQueries.USER_ID_COLUMN, userId);
		//userCoupleIds.put(SqlQueries.COUPLE_ID_COLUMN, coupleId);
		Long id = database.insert(SqlQueries.USER_ID_COUPLE_ID_TABLE, SqlQueries.COUPLE_ID_COLUMN, usrId);
		return id;
	}
	
	public Long insertSecretMsg(String secretMsg){
		database.delete(SqlQueries.SECRET_MSG_TABLE, null, null);
		ContentValues scrtMsg = new ContentValues();
		scrtMsg.put(SqlQueries.SECRET_MSG_COLUMN, secretMsg);
		Long id = database.insert(SqlQueries.SECRET_MSG_TABLE, SqlQueries.SECRET_MSG_COLUMN, scrtMsg);
		return id;
	}
	
	//always delete all prev regIds and then insert
	public Long insertRegId(String regId){
		database.delete(SqlQueries.USER_REG_ID_TABLE, null, null);
		ContentValues userRegId = new ContentValues();
		userRegId.put(SqlQueries.USER_REG_ID_COLUMN, regId);
		Long id = database.insert(SqlQueries.USER_REG_ID_TABLE, SqlQueries.USER_REG_ID_COLUMN, userRegId);
		return id;
	}
	
	public void updateRegId(String regId){
		ContentValues userRegId = new ContentValues();
		userRegId.put(SqlQueries.USER_REG_ID_COLUMN, regId);
		database.update(SqlQueries.USER_REG_ID_TABLE, userRegId, null, null);
	}
	
	public String getRegId(){
		Cursor cursor = database.query(SqlQueries.USER_REG_ID_TABLE, 
				new String[]{ SqlQueries.USER_REG_ID_COLUMN},
   				null, null, null, null, null);
		String result = parseUtils.getRegId(cursor);
		cursor.close();
		return result;
	}
	
	public String getSecretMsg(){
		Cursor cursor = database.query(SqlQueries.SECRET_MSG_TABLE, 
				new String[]{ SqlQueries.SECRET_MSG_COLUMN},
   				null, null, null, null, null);
		String result = parseUtils.getSecretMsg(cursor);
		cursor.close();
		return result;
	}
	
	public void insertCoupleId(Integer coupleId){
		ContentValues coupleIdContent = new ContentValues();
		coupleIdContent.put(SqlQueries.COUPLE_ID_COLUMN, coupleId);
		database.update(SqlQueries.USER_ID_COUPLE_ID_TABLE, coupleIdContent, null, null);
	}
	
	public void insertReceivedAddCoupleRequest(String coupleEmail){
		ContentValues coupleIdContent = new ContentValues();
		coupleIdContent.put(SqlQueries.COUPLE_EMAIL_COLUMN, coupleEmail);
		database.update(SqlQueries.USER_ID_COUPLE_ID_TABLE, coupleIdContent, null, null);
	}
	
	public void insertAddSpouseRequestStatus(Integer status){
		ContentValues coupleIdContent = new ContentValues();
		coupleIdContent.put(SqlQueries.COUPLE_ADD_STATUS_COLUMN, status);
		database.update(SqlQueries.USER_ID_COUPLE_ID_TABLE, coupleIdContent, null, null);
	}
	
	public Integer getAddSpouseRequestStatus(){
		Cursor cursor = database.query(SqlQueries.USER_ID_COUPLE_ID_TABLE, 
				new String[]{ SqlQueries.COUPLE_ADD_STATUS_COLUMN},
   				null, null, null, null, null);
		Integer result = parseUtils.getAddSpouseRequestStatus(cursor);
		cursor.close();
		return result;
	}
	
	public void deleteSpouseEmail(){
		ContentValues coupleIdContent = new ContentValues();
		String nullString = null;
		coupleIdContent.put(SqlQueries.COUPLE_EMAIL_COLUMN, nullString);
		database.update(SqlQueries.USER_ID_COUPLE_ID_TABLE, coupleIdContent, null, null);
	}
	
	public String getReceivedAddRequestId(){
		Cursor cursor = database.query(SqlQueries.USER_ID_COUPLE_ID_TABLE, 
				new String[]{ SqlQueries.COUPLE_EMAIL_COLUMN},
   				null, null, null, null, null);
		String coupleTryingToAddEmail = parseUtils.getAddCoupleRequestReceived(cursor);
		cursor.close();
		return coupleTryingToAddEmail;
	}
	
	public void deleteUserSignedIn(){
		database.delete(SqlQueries.USER_ID_COUPLE_ID_TABLE, null, null);
	}
	
	public UserIdCoupleIdPair  getUserIdCoupleIdPair(){
		Cursor cursor = database.query(SqlQueries.USER_ID_COUPLE_ID_TABLE, 
					new String[]{ SqlQueries.USER_ID_COLUMN, SqlQueries.COUPLE_ID_COLUMN},
	   				null, null, null, null, null);
		UserIdCoupleIdPair result = parseUtils.getUsrCoupleId(cursor);
		cursor.close();
		return result;
	}
}
