package com.hdactech.rest.model;

import java.io.Serializable;

public class SendAssetToAddressResBean extends CommonResponseBean implements Serializable {
	
	private final String sendAssetToAddress;

	public SendAssetToAddressResBean(long id, String sendAssetToAddress) {
		super(id);
		this.sendAssetToAddress = sendAssetToAddress;
		// TODO Auto-generated constructor stub
	}

	public String getSendAssetToAddress() {
		return sendAssetToAddress;
	}
}
