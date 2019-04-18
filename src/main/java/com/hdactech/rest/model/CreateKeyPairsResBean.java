package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.KeyPairs;

public class CreateKeyPairsResBean extends CommonResponseBean implements Serializable {
	private final List<KeyPairs> listKeyPairs;
	
	public CreateKeyPairsResBean(long id, List<KeyPairs> listKeyPairs) {
		super(id);
		this.listKeyPairs = listKeyPairs;
	}

	/**
	 * @return the listKeyPairs
	 */
	public List<KeyPairs> getListKeyPairs() {
		return listKeyPairs;
	}
}
