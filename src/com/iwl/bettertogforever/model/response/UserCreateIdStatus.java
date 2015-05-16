package com.iwl.bettertogforever.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class UserCreateIdStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private boolean createdUser;
	private Integer id;
	
	public UserCreateIdStatus(){}

	public UserCreateIdStatus(boolean createdUser, Integer id){
		this.createdUser = createdUser;
		this.id = id;
	}
	
	public boolean isCreatedUser() {
		return createdUser;
	}

	public Integer getId() {
		return id;
	}

	public void setCreatedUser(boolean createdUser) {
		this.createdUser = createdUser;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
