package com.iwl.bettertogforever.sqllite.db;

import java.util.ArrayList;
import java.util.List;

import com.iwl.bettertogforever.cursorutils.CursorParseUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.model.UsrNameSpsName;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.model.response.WishListItem;

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
	
	public void toggleItemStatus(Integer listId, Integer itemId,String status){
		ContentValues statusContent = new ContentValues();
		statusContent.put(SqlQueries.WISHLIST_ITEM_STATUS_COLUMN, status);
		database.update(SqlQueries.WISHLIST_ITEMS_TABLE, statusContent, SqlQueries.WISHLIST_ITEM_ID_COLUMN + "=" + itemId + " and " + SqlQueries.WISHLIST_ID_WI_COLUMN + "=" + listId, null);
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
	
	public Integer getIdFromDesc(String desc){
		Cursor cursor = database.query(SqlQueries.WISHLIST_TABLE, 
				new String[]{ SqlQueries.WISHLIST_ID_COLUMN},
				SqlQueries.WISHLIST_DESCRIPTION_COLUMN + "='" + desc + "'", null, null, null, null);
		Integer result = parseUtils.getWishlistIdFromDesc(cursor);
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
	
	public void insertNewWishlist(Integer id, String desc){
		ContentValues newWishlist = new ContentValues();
		newWishlist.put(SqlQueries.WISHLIST_ID_COLUMN, id);
		newWishlist.put(SqlQueries.WISHLIST_DESCRIPTION_COLUMN, desc);
		database.insert(SqlQueries.WISHLIST_TABLE, null, newWishlist);
	}
	
	public void insertNewWishlistItem(Integer wishListId, WishListItem item){
		ContentValues newWishlist = new ContentValues();
		newWishlist.put(SqlQueries.WISHLIST_ID_WI_COLUMN, wishListId);
		newWishlist.put(SqlQueries.WISHLIST_ITEM_ID_COLUMN, item.getItemId());
		newWishlist.put(SqlQueries.WISHLIST_ITEM_NAME_COLUMN, item.getItemName());
		newWishlist.put(SqlQueries.WISHLIST_ITEM_DESCRIPTION_COLUMN, item.getItemDescription());
		newWishlist.put(SqlQueries.WISHLIST_ITEM_STATUS_COLUMN, item.getItemStatus());
		database.insert(SqlQueries.WISHLIST_ITEMS_TABLE, null, newWishlist);
	}
	
	public void insertCplNameUsrName(String usrName, String cplName){
		ContentValues cplAndUsrName = new ContentValues();
		cplAndUsrName.put(SqlQueries.COUPLE_ADD_USER_NAME_COLUMN, usrName);
		cplAndUsrName.put(SqlQueries.COUPLE_ADD_CPL_NAME_COLUMN, cplName);
		database.update(SqlQueries.USER_ID_COUPLE_ID_TABLE, cplAndUsrName, null, null);
	}
	
	public void deleteOldWishlist(Integer id){
		database.delete(SqlQueries.WISHLIST_TABLE, SqlQueries.WISHLIST_ID_COLUMN + "=" + id, null);
	}
	
	public void deleteOldWishlistItem(Integer wishlistId, Integer itemId){
		database.delete(SqlQueries.WISHLIST_ITEMS_TABLE, SqlQueries.WISHLIST_ID_WI_COLUMN + "=" + wishlistId + " and " + SqlQueries.WISHLIST_ITEM_ID_COLUMN + "=" + wishlistId, null);
	}
	
	public List<WishList> getAllWishList(){
		Cursor cursor = database.query(SqlQueries.WISHLIST_TABLE, 
				new String[]{ SqlQueries.WISHLIST_ID_COLUMN, SqlQueries.WISHLIST_DESCRIPTION_COLUMN},
   				null, null, null, null, null);
		List<WishList> result = parseUtils.getAllWishlist(cursor);
		cursor.close();
		return result;
	}
	
	public Integer getWishListId(String name){
		Cursor cursor = database.query(SqlQueries.WISHLIST_TABLE, 
				new String[]{ SqlQueries.WISHLIST_ID_COLUMN},
   				SqlQueries.WISHLIST_DESCRIPTION_COLUMN + "=" + name, null, null, null, null);
		
		Integer result = parseUtils.getWishlistId(cursor);
		cursor.close();
		return result;
	}
	
	public String getWishListName(Integer id){
		Cursor cursor = database.query(SqlQueries.WISHLIST_TABLE, 
				new String[]{ SqlQueries.WISHLIST_DESCRIPTION_COLUMN},
   				SqlQueries.WISHLIST_ID_COLUMN + "=" + id, null, null, null, null);
		
		String result = parseUtils.getWishlistName(cursor);
		cursor.close();
		return result;
	}
	
	public List<WishListItem> getWishListItems(Integer wishListId){
		Cursor cursor = database.query(SqlQueries.WISHLIST_ITEMS_TABLE, 
				new String[]{ SqlQueries.WISHLIST_ITEM_ID_COLUMN, SqlQueries.WISHLIST_ITEM_NAME_COLUMN, SqlQueries.WISHLIST_ITEM_DESCRIPTION_COLUMN,
				SqlQueries.WISHLIST_ITEM_STATUS_COLUMN},
				SqlQueries.WISHLIST_ID_WI_COLUMN + "=" + wishListId, null, null, null, null);
		
		List<WishListItem> result = parseUtils.getWishlistItems(cursor);
		cursor.close();
		return result;
	}

	public void updateWishListItems(Integer wishlistId, List<WishListItem> wishListItems){
		
		database.beginTransaction();
		try{
			database.delete(SqlQueries.WISHLIST_ITEMS_TABLE, SqlQueries.WISHLIST_ID_WI_COLUMN + "=" + wishlistId, null);
			for(WishListItem item: wishListItems){
				insertNewWishlistItem(wishlistId, item);
			}
			database.setTransactionSuccessful();
		}finally{
			database.endTransaction();
		}
		
		
//		List<WishListItem> currentWishLists = getWishListItems(wishlistId);
//		
//		List<Integer> currentWishlistIds = getItemsIds(currentWishLists);
//		List<Integer> allWishListIds = getItemsIds(wishListItems);
//		
//		List<Integer> newWishlistIds = getNewIdsAdded(currentWishlistIds, allWishListIds);
//		List<Integer> deletedLists = getDeletedIds(currentWishlistIds, allWishListIds);
//		
//		
//		for(Integer id: deletedLists){
//			deleteOldWishlistItem(wishlistId, id);
//		}
//		
		
	}
	
	public void updateWishLists(List<WishList> wishLists){
		
		database.beginTransaction();
		try{
			database.execSQL("delete from " + SqlQueries.WISHLIST_TABLE);
			
			for(WishList list: wishLists){
				insertNewWishlist(list.getId(), list.getDescription());
			}
			database.setTransactionSuccessful();
		}finally{
			database.endTransaction();
		}

		
//		List<WishList> currentWishLists = getAllWishList();
//		
//		List<Integer> currentWishlistItemIds = getIds(currentWishLists);
//		List<Integer> newWishListItemIds = getIds(wishLists);
//		
//		List<Integer> newWishlistIds = getNewIdsAdded(currentWishlistItemIds, newWishListItemIds);
//		List<Integer> deletedLists = getDeletedIds(currentWishlistItemIds, newWishListItemIds);
//		
//		for(Integer id: deletedLists){
//			deleteOldWishlist(id);
//		}
//		
//		for(WishList list: wishLists){
//			if(newWishlistIds.contains(list.getId())){
//				insertNewWishlist(list.getId(), list.getDescription());
//			}
//		}
	}
	
	public void updateListItem(Integer itemIdCopy, Integer listId, String listNameCopy, String descCopy, String statusCopy){
		ContentValues itemContent = new ContentValues();
		itemContent.put(SqlQueries.WISHLIST_ITEM_NAME_COLUMN, listNameCopy);
		itemContent.put(SqlQueries.WISHLIST_ITEM_DESCRIPTION_COLUMN, descCopy);
		itemContent.put(SqlQueries.WISHLIST_ITEM_STATUS_COLUMN, statusCopy);
		database.update(SqlQueries.WISHLIST_ITEMS_TABLE, itemContent, SqlQueries.WISHLIST_ID_WI_COLUMN + "=" + listId + " and " + SqlQueries.WISHLIST_ITEM_ID_COLUMN + "=" + itemIdCopy, null);
	}

	private List<Integer> getDeletedIds(List<Integer> currentWishlistIds, List<Integer> allWishListIds) {
		List<Integer> oldIds = new ArrayList<Integer>();
		for(Integer id: currentWishlistIds){
			if(!allWishListIds.contains(id)){
				oldIds.add(id);
			}
		}
		return oldIds;
	}

	private List<Integer> getNewIdsAdded(List<Integer> currentWishlistIds, List<Integer> allWishListIds) {
		List<Integer> newIds = new ArrayList<Integer>();
		for(Integer id: allWishListIds){
			if(!currentWishlistIds.contains(id)){
				newIds.add(id);
			}
		}
		return newIds;
	}

	private List<Integer> getIds(List<WishList> currentWishLists) {
		List<Integer> ids = new ArrayList<Integer>();
		for(WishList list: currentWishLists){
			ids.add(list.getId());
		}
		return ids;
	}
	
	private List<Integer> getItemsIds(List<WishListItem> wishListItems) {
		List<Integer> ids = new ArrayList<Integer>();
		for(WishListItem list: wishListItems){
			ids.add(list.getItemId());
		}
		return ids;
	}
	
	public void editListName(Integer listId, String newDescription){
		ContentValues coupleIdContent = new ContentValues();
		coupleIdContent.put(SqlQueries.WISHLIST_DESCRIPTION_COLUMN, newDescription);
		database.update(SqlQueries.WISHLIST_TABLE, coupleIdContent,SqlQueries.WISHLIST_ID_COLUMN + " = " + listId , null);
	}
	
	public UsrNameSpsName getUsrNameSpsName(){
		Cursor cursor = database.query(SqlQueries.USER_ID_COUPLE_ID_TABLE, 
				new String[]{ SqlQueries.COUPLE_ADD_USER_NAME_COLUMN, SqlQueries.COUPLE_ADD_CPL_NAME_COLUMN},
   				null, null, null, null, null);
		
		UsrNameSpsName result = parseUtils.getUsrNameSpsName(cursor);
		cursor.close();
		return result;
	}
}
