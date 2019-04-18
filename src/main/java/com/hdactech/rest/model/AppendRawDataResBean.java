package com.hdactech.rest.model;

import java.io.Serializable;

public class AppendRawDataResBean extends CommonResponseBean implements Serializable {
	
	private final String hex;
	
	public AppendRawDataResBean(long id, String hex) {
		super(id);
		this.hex = hex;
	}
	
	public String gethex() {
		return hex;
	}
}
