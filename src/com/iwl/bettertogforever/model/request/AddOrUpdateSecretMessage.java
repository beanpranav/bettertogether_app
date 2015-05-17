package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class AddOrUpdateSecretMessage implements Serializable{

	private Integer usrId;
	private String msg;
	private Integer cplId;
	
	public Integer getCplId() {
		return cplId;
	}
	public void setCplId(Integer cplId) {
		this.cplId = cplId;
	}
	public Integer getUsrId() {
		return usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
