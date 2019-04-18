package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.StreamKeyItem;

public class GetStreamItemResBean extends CommonResponseBean implements Serializable {
	
	private final StreamKeyItem getStreamItem;

	public GetStreamItemResBean(long id, StreamKeyItem getStreamItem) {
		super(id);
		this.getStreamItem = getStreamItem;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the listStreams
	 */
	public StreamKeyItem getStreamItem() {
		return getStreamItem;
	}
}