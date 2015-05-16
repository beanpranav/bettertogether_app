package com.iwl.bettertogforever.model;

public class UserIdCoupleIdPair {
	private Integer userId;
	private Integer coupleId;
	
	public UserIdCoupleIdPair(Integer userId, Integer coupleId){
		this.userId = userId;
		this.coupleId = coupleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getCoupleId() {
		return coupleId;
	}
}
