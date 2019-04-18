package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

public class ListAddressesResBean extends CommonResponseBean implements Serializable {

	private final List<Object> listAddresses;
	
	public ListAddressesResBean(long id, List<Object> listAddresses) {
		super(id);
		this.listAddresses = listAddresses;
		// TODO Auto-generated constructor stub
	}

	public List<Object> getListAddresses() {
		return listAddresses;
	}
}
