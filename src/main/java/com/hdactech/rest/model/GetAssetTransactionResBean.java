package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.TransactionWallet;

public class GetAssetTransactionResBean extends CommonResponseBean implements Serializable {
	
	private final TransactionWallet getAssetTransaction;

	public GetAssetTransactionResBean(long id, TransactionWallet getAssetTransaction) {
		super(id);
		this.getAssetTransaction = getAssetTransaction;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the getAssetTransaction
	 */
	public TransactionWallet getGetAssetTransaction() {
		return getAssetTransaction;
	}
}
