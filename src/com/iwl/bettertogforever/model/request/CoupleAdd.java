package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class CoupleAdd implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String spouseEmail;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setSpouseEmail(String spouseEmail) {
		this.spouseEmail = spouseEmail;
	}
	public String getSpouseEmail() {
		return spouseEmail;
	}
}
