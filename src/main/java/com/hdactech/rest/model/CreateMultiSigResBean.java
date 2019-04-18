package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.Address;

public class CreateMultiSigResBean extends CommonResponseBean implements Serializable {
	private final Address address;
	
	public CreateMultiSigResBean(long id, Address address) {
		super(id);
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
}
