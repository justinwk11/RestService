package com.hdactech.rest.model;

import java.io.Serializable;

public class SendAssetFromResBean extends CommonResponseBean implements Serializable {

	private final String sendAssetFrom;

	public SendAssetFromResBean(long id, String sendAssetFrom) {
		super(id);
		this.sendAssetFrom = sendAssetFrom;
		// TODO Auto-generated constructor stub
	}
	
	public String getSendAssetFrom() {
		return sendAssetFrom;
	}
}
