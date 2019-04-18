package com.hdactech.rest.model;

import java.io.Serializable;

public class AppendRawMetaDataResBean extends CommonResponseBean implements Serializable {
	private final String txidWithMetaData;
	
	public AppendRawMetaDataResBean(long id, String txidWithMetaData) {
		super(id);
		this.txidWithMetaData = txidWithMetaData;
	}
	
	public String gettxidWithMetaData() {
		return txidWithMetaData;
	}
}
