package com.hdactech.rest.model;

import java.io.Serializable;

public class CreateFromResBean extends CommonResponseBean implements Serializable {
	
	String stringCreate;

	public CreateFromResBean(long id, String stringCreate) {
		super(id);
		this.stringCreate = stringCreate;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the stringCreate
	 */
	public String getStringCreate() {
		return stringCreate;
	}
}