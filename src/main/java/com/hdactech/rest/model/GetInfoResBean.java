package com.hdactech.rest.model;

import java.io.Serializable;

public class GetInfoResBean extends CommonResponseBean implements Serializable {
	
	private final String getInfo;

	public GetInfoResBean(long id, String getInfo) {
		super(id);
		this.getInfo = getInfo;
		// TODO Auto-generated constructor stub
	}

	public String getGetInfo() {
		return getInfo;
	}
	
}
