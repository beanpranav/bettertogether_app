package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class EditListItem implements Serializable{

	private Integer wishlistId;
	private Integer listItemId;
	private String status;
	private String listName;
	private String listDescription;
	
	public Integer getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getListDescription() {
		return listDescription;
	}
	public void setListDescription(String listDescription) {
		this.listDescription = listDescription;
	}
	public Integer getListItemId() {
		return listItemId;
	}
	public void setListItemId(Integer listItemId) {
		this.listItemId = listItemId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
