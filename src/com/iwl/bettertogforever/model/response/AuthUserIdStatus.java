package com.iwl.bettertogforever.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class AuthUserIdStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private boolean isAuthorized;
	private Integer userId;
	
	public AuthUserIdStatus(){}
	
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public AuthUserIdStatus(boolean authorized, Integer userId){
		this.isAuthorized = authorized;
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public boolean isAuthorized() {
		return isAuthorized;
	}
}
