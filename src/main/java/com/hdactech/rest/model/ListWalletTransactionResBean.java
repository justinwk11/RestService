package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.ListWalletTxs;

public class ListWalletTransactionResBean extends CommonResponseBean implements Serializable {
	
	private final List<ListWalletTxs> listWalletTransaction;
	
	public ListWalletTransactionResBean(long id, List<ListWalletTxs> listWalletTransaction) {
		super(id);
		this.listWalletTransaction = listWalletTransaction;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the listAddressTransactions
	 */
	public List<ListWalletTxs> getListWalletTransaction() {
		return listWalletTransaction;
	}
}
