package com.hdactech.rest.model;

import java.io.Serializable;

public class AppendRawChangeResBean extends CommonResponseBean implements Serializable {
	
	private final String txidWithData;
	
	public AppendRawChangeResBean(long id, String txidWithData) {
		super(id);
		this.txidWithData = txidWithData;
	}
	
	public String gettxidWithMetaData() {
		return txidWithData;
	}
}
