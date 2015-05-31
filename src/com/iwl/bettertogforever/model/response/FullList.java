package com.iwl.bettertogforever.model.response;

import java.util.List;

public class FullList {

	private List<WishListItem> wishListItems;
	private Integer listId;
	
	public List<WishListItem> getWishListItems() {
		return wishListItems;
	}
	public void setWishListItems(List<WishListItem> wishListItems) {
		this.wishListItems = wishListItems;
	}
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
}
