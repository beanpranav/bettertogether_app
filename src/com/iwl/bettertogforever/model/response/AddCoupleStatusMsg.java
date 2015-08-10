package com.iwl.bettertogforever.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class AddCoupleStatusMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean coupleAdded;
	private String msg;
	private Integer coupleId;
	private boolean sentToCoupleForApproval;
	private String acceptStatus;
	
	private String usrName;
	private String cplName;
	
	public AddCoupleStatusMsg(){}
	
	public AddCoupleStatusMsg(boolean coupleAdded, String msg, Integer coupleId, String acceptStatus, String usrName, String cplName){
		this.coupleAdded = coupleAdded;
		this.msg = msg;
		this.coupleId = coupleId;
		this.acceptStatus = acceptStatus;
		this.usrName = usrName;
		this.cplName = cplName;
	}
	
	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getCplName() {
		return cplName;
	}

	public void setCplName(String cplName) {
		this.cplName = cplName;
	}

	public String getAcceptStatus() {
		return acceptStatus;
	}

	public boolean isSentToCoupleForApproval() {
		return sentToCoupleForApproval;
	}

	public void setSentToCoupleForApproval(boolean sentToCoupleForApproval) {
		this.sentToCoupleForApproval = sentToCoupleForApproval;
	}

	public boolean isCoupleAdded() {
		return coupleAdded;
	}

	public void setCoupleAdded(boolean coupleAdded) {
		this.coupleAdded = coupleAdded;
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
}