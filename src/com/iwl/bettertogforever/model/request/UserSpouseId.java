package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class UserSpouseId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	
	private Integer spouseId;
	
	public UserSpouseId(){}
	
	public UserSpouseId(Integer userId, Integer spouseId){
		this.userId = userId;
		this.spouseId = spouseId;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getSpouseId() {
		return spouseId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setSpouseId(Integer spouseId) {
		this.spouseId = spouseId;
	}
}
