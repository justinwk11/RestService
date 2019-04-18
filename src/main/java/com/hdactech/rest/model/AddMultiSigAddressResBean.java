package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.MultiSigAddress;

public class AddMultiSigAddressResBean extends CommonResponseBean implements Serializable {
	
	private final MultiSigAddress address;
	
	public AddMultiSigAddressResBean(long id, MultiSigAddress address) {
		super(id);
		this.address = address;
	}
	
	public MultiSigAddress getAddress() {
		return address;
	}
}
