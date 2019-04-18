package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.Address;

public class ValidateAddressResBean extends CommonResponseBean implements Serializable {

	private final Address address;
	public ValidateAddressResBean(long id, Address address) {
		super(id);
		this.address = address;
		
		
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}	
}
