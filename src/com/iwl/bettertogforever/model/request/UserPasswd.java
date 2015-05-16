package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class UserPasswd implements Serializable{

	private static final long serialVersionUID = 1L;

	private String email;
	
	private String passwd;
	
	private Integer appId;
	
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public String getPasswd() {
		return passwd;
	}
}
