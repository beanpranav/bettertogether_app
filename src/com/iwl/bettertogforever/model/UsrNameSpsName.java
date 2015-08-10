package com.iwl.bettertogforever.model;

public class UsrNameSpsName {

	String usrName;
	String spsName;
	
	public UsrNameSpsName(String usrName, String spsName){
		this.usrName = usrName;
		this.spsName = spsName;
	}
	
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getSpsName() {
		return spsName;
	}

	public void setSpsName(String spsName) {
		this.spsName = spsName;
	}
}
