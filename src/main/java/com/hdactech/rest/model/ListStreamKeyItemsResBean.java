package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.StreamKeyItem;

public class ListStreamKeyItemsResBean extends CommonResponseBean implements Serializable {
	
	private final List<StreamKeyItem> streamKeyItems;

	public ListStreamKeyItemsResBean(long id, List<StreamKeyItem> streamKeyItems) {
		super(id);
		this.streamKeyItems = streamKeyItems;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the listStreams
	 */
	public List<StreamKeyItem> getstreamKeyItems() {
		return streamKeyItems;
	}
}