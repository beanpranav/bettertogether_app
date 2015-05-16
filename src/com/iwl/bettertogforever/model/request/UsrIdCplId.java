package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class UsrIdCplId implements Serializable{

	private Integer usrId;
	private Integer cplId;
	
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}
	public void setCplId(Integer cplId) {
		this.cplId = cplId;
	}
	public Integer getUsrId() {
		return usrId;
	}
	public Integer getCplId() {
		return cplId;
	}
}
