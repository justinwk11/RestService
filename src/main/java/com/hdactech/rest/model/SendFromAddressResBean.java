package com.hdactech.rest.model;

import java.io.Serializable;

public class SendFromAddressResBean extends CommonResponseBean implements Serializable {
	
	private final String sendFromAddress;
	
	public SendFromAddressResBean(long id, String sendFromAddress) {
		super(id);
		this.sendFromAddress = sendFromAddress;
		// TODO Auto-generated constructor stub
	}
	public String getSendFromAddress() {
		return sendFromAddress;
	}
}
