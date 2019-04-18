package com.hdactech.rest.model;

import java.io.Serializable;

public class GrantWithDataFromResBean extends CommonResponseBean implements Serializable {
	
	private final String grantWithDataFrom;

	public GrantWithDataFromResBean(long id, String grantWithDataFrom) {
		super(id);
		this.grantWithDataFrom = grantWithDataFrom;
		// TODO Auto-generated constructor stub
	}

	public String getGrantWithDataFrom() {
		return grantWithDataFrom;
	}
}
