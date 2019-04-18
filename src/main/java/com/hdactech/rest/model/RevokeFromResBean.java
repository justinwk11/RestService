package com.hdactech.rest.model;

import java.io.Serializable;

public class RevokeFromResBean extends CommonResponseBean implements Serializable {
	
	private final String revokeFrom;

	public RevokeFromResBean(long id, String revokeFrom) {
		super(id);
		this.revokeFrom = revokeFrom;
		// TODO Auto-generated constructor stub
	}
	
	public String getRevokeFrom() {
		return revokeFrom;
	}
}
