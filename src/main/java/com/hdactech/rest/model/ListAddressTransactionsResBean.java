package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.ListAddressTxsDetailed;

public class ListAddressTransactionsResBean extends CommonResponseBean implements Serializable {
	
	private final List<ListAddressTxsDetailed> listAddressTransactions;
	
	public ListAddressTransactionsResBean(long id, List<ListAddressTxsDetailed> listAddressTransactions) {
		super(id);
		this.listAddressTransactions = listAddressTransactions;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the listAddressTransactions
	 */
	public List<ListAddressTxsDetailed> getListAddressTransactions() {
		return listAddressTransactions;
	}
}
