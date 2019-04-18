package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

public class GetAddressesResBean extends CommonResponseBean implements Serializable {
	
	private final List<String> getAddressesStringList;

	public GetAddressesResBean(long id, List<String> getAddressesStringList) {
		super(id);
		this.getAddressesStringList = getAddressesStringList;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the getAddressesStringList
	 */
	public List<String> getGetAddressesStringList() {
		return getAddressesStringList;
	}
}
