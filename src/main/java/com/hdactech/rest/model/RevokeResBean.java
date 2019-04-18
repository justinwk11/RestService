package com.hdactech.rest.model;

import java.io.Serializable;

public class RevokeResBean extends CommonResponseBean implements Serializable {
	
	private final String revoke;

	public RevokeResBean(long id, String revoke) {
		super(id);
		this.revoke = revoke;
		// TODO Auto-generated constructor stub
	}
	
	public String getRevoke() {
		return revoke;
	}
}
