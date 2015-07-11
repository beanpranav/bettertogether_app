package com.iwl.bettertogforever.model;

import java.io.Serializable;

public class WishList implements Serializable{

	Integer id;
	String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
