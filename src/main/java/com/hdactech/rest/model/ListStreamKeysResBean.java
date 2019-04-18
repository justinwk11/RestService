package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.StreamKey;

public class ListStreamKeysResBean extends CommonResponseBean implements Serializable {
	
	private final List<StreamKey> listStreamKeys;

	public ListStreamKeysResBean(long id, List<StreamKey> listStreamKeys) {
		super(id);
		this.listStreamKeys = listStreamKeys;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the listStreams
	 */
	public List<StreamKey>  getListStreamKeys() {
		return listStreamKeys;
	}
}
