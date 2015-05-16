package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class RegIdAdd implements Serializable{

	Integer usrId;
	String regId;
	String phoneNo;
	
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Integer getUsrId() {
		return usrId;
	}
	public String getRegId() {
		return regId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
}
