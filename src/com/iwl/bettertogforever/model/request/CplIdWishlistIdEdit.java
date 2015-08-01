package com.iwl.bettertogforever.model.request;

public class CplIdWishlistIdEdit {

	Integer usrId;
	Integer cplId;
	Integer listId;
	String newDescription;
	
	public Integer getUsrId() {
		return usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}
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
	public String getNewDescription() {
		return newDescription;
	}
	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}
}
