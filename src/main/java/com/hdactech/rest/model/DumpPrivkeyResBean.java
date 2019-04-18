package com.hdactech.rest.model;

import java.io.Serializable;

public class DumpPrivkeyResBean extends CommonResponseBean implements Serializable {
	
	private final String key;
	
	public DumpPrivkeyResBean(long id,String key) {
		super(id);
		this.key = key;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the address
	 */
	public String getKey() {
		return key;
	}
}