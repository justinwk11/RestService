package com.hdactech.rest.model;

import java.io.Serializable;

public class GetBlockHashResBean extends CommonResponseBean implements Serializable {
	
	String getBlockHash;

	public GetBlockHashResBean(long id, String getBlockHash) {
		super(id);
		this.getBlockHash = getBlockHash;
		// TODO Auto-generated constructor stub
	}

	public String getGetBlockHash() {
		return getBlockHash;
	}	
}
