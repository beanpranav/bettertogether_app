package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class GetFullList implements Serializable{

	private Integer cplId;
	private Integer listId;
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
}
