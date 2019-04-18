package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.TransactionWalletDetailed;

public class GetWalletTransactionResBean extends CommonResponseBean implements Serializable {
	
	TransactionWalletDetailed transactionWalletDetailed;

	public GetWalletTransactionResBean(long id, TransactionWalletDetailed transactionWalletDetailed) {
		super(id);
		this.transactionWalletDetailed = transactionWalletDetailed;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the transactionWalletDetailed
	 */
	public TransactionWalletDetailed getTransactionWalletDetailed() {
		return transactionWalletDetailed;
	}
}
