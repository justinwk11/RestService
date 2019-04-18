package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.UnspentList;

public class ListUnspentResBean extends CommonResponseBean implements Serializable {
	
	private List<UnspentList> listUnSpent;

	public ListUnspentResBean(long id, List<UnspentList> listUnSpent) {
		super(id);
		this.listUnSpent = listUnSpent;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the listUnSpent
	 */
	public List<UnspentList> getListUnSpent() {
		return listUnSpent;
	}
}

