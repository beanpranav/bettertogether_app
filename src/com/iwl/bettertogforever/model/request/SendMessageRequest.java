package com.iwl.bettertogforever.model.request;

import java.io.Serializable;

public class SendMessageRequest implements Serializable{

	private String msg; 
	private Integer coupleId;
	private Integer usrId;
	
	public SendMessageRequest(){}
	
	public SendMessageRequest(String msg, Integer coupleId, Integer usrId){
		this.msg = msg;
		this.coupleId = coupleId;
		this.usrId = usrId;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCoupleId() {
		return coupleId;
	}
	public void setCoupleId(Integer coupleId) {
		this.coupleId = coupleId;
	}
	public Integer getUsrId() {
		return usrId;
	}
	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}
}
