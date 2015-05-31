package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class UpdateListItemStatus implements Serializable{

	private Integer listItemId;
	private String status;
	
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
