package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.SignedTransactionRAW;

/**
 * @author Justin
 *
 */
public class AppendRawTransactionResBean extends CommonResponseBean implements Serializable {
	
	SignedTransactionRAW appendRawTransaction;

	public AppendRawTransactionResBean(long id, SignedTransactionRAW appendRawTransaction) {
		super(id);
		this.appendRawTransaction = appendRawTransaction;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the appendRawTransaction
	 */
	public SignedTransactionRAW getAppendRawTransaction() {
		return appendRawTransaction;
	}
}
