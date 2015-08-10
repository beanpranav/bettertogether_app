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
	
	//These fields are set only when the status of the user trying to login is pending in couples table
	private Integer cplId;
	private String spouseEmail;
	private Integer cplStatus;
	
	private String spouseName;
	private String userName;
	
	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCplStatus() {
		return cplStatus;
	}

	public void setCplStatus(Integer cplStatus) {
		this.cplStatus = cplStatus;
	}

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

	public Integer getCplId() {
		return cplId;
	}

	public void setCplId(Integer cplId) {
		this.cplId = cplId;
	}

	public String getSpouseEmail() {
		return spouseEmail;
	}

	public void setSpouseEmail(String spouseEmail) {
		this.spouseEmail = spouseEmail;
	}
}
