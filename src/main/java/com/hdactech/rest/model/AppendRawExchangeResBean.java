package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.SignedTransactionRAW;

public class AppendRawExchangeResBean extends CommonResponseBean implements Serializable {
	
	SignedTransactionRAW appendRawExchange;

	public AppendRawExchangeResBean(long id, SignedTransactionRAW appendRawExchange) {
		super(id);
		this.appendRawExchange = appendRawExchange;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the appendRawExchange
	 */
	public SignedTransactionRAW getAppendRawExchange() {
		return appendRawExchange;
	}

	/**
	 * @param appendRawExchange the appendRawExchange to set
	 */
	public void setAppendRawExchange(SignedTransactionRAW appendRawExchange) {
		this.appendRawExchange = appendRawExchange;
	}
}
