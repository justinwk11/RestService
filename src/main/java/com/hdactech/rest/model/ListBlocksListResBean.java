package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.Block;

public class ListBlocksListResBean extends CommonResponseBean implements Serializable {
	
	private final List<Block> listBlocksList;

	public ListBlocksListResBean(long id, List<Block> listBlocksList) {
		super(id);
		this.listBlocksList = listBlocksList;
		// TODO Auto-generated constructor stub
	}

	public List<Block> getListBlocksList() {
		return listBlocksList;
	}

}
