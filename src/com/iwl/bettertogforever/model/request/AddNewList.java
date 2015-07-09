package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class AddNewList implements Serializable{

	private Integer cplId;
	private String listDescription;
	private Integer usrId;
	
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
	public String getListDescription() {
		return listDescription;
	}
	public void setListDescription(String listDescription) {
		this.listDescription = listDescription;
	}
}
