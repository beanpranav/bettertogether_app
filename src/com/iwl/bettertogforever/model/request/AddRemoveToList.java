package com.iwl.bettertogforever.model.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddRemoveToList implements Serializable{

	private Integer cplId;
	private Integer listId;
	private List<String> itemsToAdd;
	private List<Integer> itemsToRemove;
	
	public Integer getCplId() {
		return cplId;
	}
	public void setCplId(Integer cplId) {
		this.cplId = cplId;
	}
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}

	public List<String> getItemsToAdd() {
		return itemsToAdd;
	}
	public void setItemsToAdd(List<String> itemsToAdd) {
		this.itemsToAdd = itemsToAdd;
	}
	public List<Integer> getItemsToRemove() {
		return itemsToRemove;
	}
	public void setItemsToRemove(List<Integer> itemsToRemove) {
		this.itemsToRemove = itemsToRemove;
	}
	public void setItemsToAdd(ArrayList<String> itemsToAdd) {
		this.itemsToAdd = itemsToAdd;
	}
	public void setItemsToRemove(ArrayList<Integer> itemsToRemove) {
		this.itemsToRemove = itemsToRemove;
	}
	
}
