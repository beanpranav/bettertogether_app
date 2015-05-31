package com.iwl.bettertogforever.model.request;

import java.io.Serializable;
import java.util.List;

public class GetNewAddedListItems implements Serializable{

	private List<Integer> existingListItems;
	private Integer listId;
	private Integer cplId;
	
	public Integer getCplId() {
		return cplId;
	}
	public void setCplId(Integer cplId) {
		this.cplId = cplId;
	}
	public List<Integer> getExistingListItems() {
		return existingListItems;
	}
	public void setExistingListItems(List<Integer> existingListItems) {
		this.existingListItems = existingListItems;
	}
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
}
