package com.hdactech.rest.model;

import java.io.Serializable;

public class GrantFromResBean extends CommonResponseBean implements Serializable {

	private final String grantFrom;
	
	public GrantFromResBean(long id, String grantFrom) {
		super(id);
		this.grantFrom = grantFrom;
		// TODO Auto-generated constructor stub
	}
	public String getGrantFrom() {
		return grantFrom;
	}
}
