package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.TxOut;

public class TransactionOutResBean extends CommonResponseBean implements Serializable {
	
	private final TxOut txOut;

	public TransactionOutResBean(long id, TxOut txOut) {
		super(id);
		this.txOut = txOut;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the txOut
	 */
	public TxOut getTxOut() {
		return txOut;
	}
}
