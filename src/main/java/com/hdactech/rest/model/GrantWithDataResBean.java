package com.hdactech.rest.model;

import java.io.Serializable;

public class GrantWithDataResBean extends CommonResponseBean implements Serializable {
	
	private final String grantWithData;

	public GrantWithDataResBean(long id, String grantWithData) {
		super(id);
		this.grantWithData = grantWithData;
		// TODO Auto-generated constructor stub
	}

	public String getGrantWithData() {
		return grantWithData;
	}
}
