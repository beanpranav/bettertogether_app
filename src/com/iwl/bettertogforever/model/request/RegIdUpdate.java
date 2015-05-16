package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class RegIdUpdate implements Serializable{

	Integer usrId;
	String regId;
	
	public Integer getUsrId() {
		return usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
}
