package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.StreamKeyItem;

public class ListStreamPublisherItemsResBean extends CommonResponseBean implements Serializable {
	
	private final List<StreamKeyItem> listStreamPublisherItems;

	public ListStreamPublisherItemsResBean(long id, List<StreamKeyItem> listStreamPublisherItems) {
		super(id);
		this.listStreamPublisherItems = listStreamPublisherItems;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the listStreams
	 */
	public List<StreamKeyItem> getListStreamPublisherItems() {
		return listStreamPublisherItems;
	}
}