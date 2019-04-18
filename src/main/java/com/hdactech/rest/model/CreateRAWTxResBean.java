package com.hdactech.rest.model;

import java.io.Serializable;

public class CreateRAWTxResBean extends CommonResponseBean implements Serializable {

	private final String result;
	
	public CreateRAWTxResBean(long id, String result) {
		super(id);
		this.result = result;
	}
	
	public String getResult() {
		return this.result;
	}
}
