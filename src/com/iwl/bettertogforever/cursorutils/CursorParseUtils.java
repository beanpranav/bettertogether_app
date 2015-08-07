package com.iwl.bettertogforever.cursorutils;

import java.util.ArrayList;
import java.util.List;

import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.model.response.WishListItem;

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
	
	public Integer getWishlistIdFromDesc(Cursor cursor) {
		if(cursor == null || cursor.isAfterLast())
			return 0;
		
		cursor.moveToFirst();
		Integer id = new Integer(cursor.getInt(0));
		cursor.moveToNext();
		if(!cursor.isAfterLast()){
			throw new RuntimeException("Found more than 1 secretMsg rows");
		}
		return id;
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
	
	public List<WishList> getAllWishlist(Cursor cursor) {
		List<WishList> wishLists = new ArrayList<WishList>();
		if(cursor == null || cursor.isAfterLast())
			return wishLists;
		
		while(cursor.moveToNext()){
			WishList wishList = new WishList();
			Integer id = cursor.getInt(0);
			String desc = cursor.getString(1);
			wishList.setId(id);
			wishList.setDescription(desc);
			wishLists.add(wishList);
		}
		return wishLists;
	}
	
	public List<WishListItem> getWishlistItems(Cursor cursor) {
		List<WishListItem> wishLists = new ArrayList<WishListItem>();
		if(cursor == null || cursor.isAfterLast())
			return wishLists;
		
		while(cursor.moveToNext()){
			WishListItem wishListItem = new WishListItem();
			Integer id = cursor.getInt(0);
			String name = cursor.getString(1);
			String desc = cursor.getString(2);
			String status = cursor.getString(3);
			wishListItem.setItemId(id);
			wishListItem.setItemDescription(desc);
			wishListItem.setItemName(name);
			wishListItem.setItemStatus(status);
			
			wishLists.add(wishListItem);
		}
		return wishLists;
	}
	
	public Integer getWishlistId(Cursor cursor) {
		if(cursor == null || cursor.isAfterLast())
			return null;
		
		cursor.moveToFirst();
		Integer listId = cursor.getInt(0);
		cursor.moveToNext();
		if(!cursor.isAfterLast()){
			throw new RuntimeException("Found more than 1 list id status rows");
		}
		return listId;
	}
	
	public String getWishlistName(Cursor cursor) {
		if(cursor == null || cursor.isAfterLast())
			return null;
		
		cursor.moveToFirst();
		String listName = cursor.getString(0);
		cursor.moveToNext();
		if(!cursor.isAfterLast()){
			throw new RuntimeException("Found more than 1 list id status rows");
		}
		return listName;
	}
}
