package com.hdactech.rest.model;

import java.io.Serializable;

public class GetNewAddressResBean extends CommonResponseBean implements Serializable {
	
	private final String address;

	public GetNewAddressResBean(long id, String address) {
		super(id);
		this.address = address;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
}
