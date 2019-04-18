package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.StreamKeyItem;

public class ListStreamItemsResBean extends CommonResponseBean implements Serializable {
	
	private final List<StreamKeyItem> listStreamItems;

	public ListStreamItemsResBean(long id, List<StreamKeyItem> listStreamItems) {
		super(id);
		this.listStreamItems = listStreamItems;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the listStreams
	 */
	public List<StreamKeyItem> getListStreamItem() {
		return listStreamItems;
	}
}