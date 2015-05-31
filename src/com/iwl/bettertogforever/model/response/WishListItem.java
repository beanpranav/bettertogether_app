package com.iwl.bettertogforever.model.response;

import java.io.Serializable;

public class WishListItem implements Serializable{

	private Integer itemId;
	private String itemName;
	private String itemDescription;
	private String itemStatus;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	@Override
	public String toString() {
		return "WishListItem [itemId=" + itemId + ", itemName=" + itemName
				+ ", itemDescription=" + itemDescription + ", itemStatus="
				+ itemStatus + "]";
	}
}
