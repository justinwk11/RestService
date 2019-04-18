package com.hdactech.rest.model;

import java.io.Serializable;

public class CreateRAWTransactionResBean extends CommonResponseBean implements Serializable {
	private final String txid;
	
	public CreateRAWTransactionResBean(long id, String txid) {
		super(id);
		this.txid = txid;
	}
	
	public String getTxId() {
		return txid;
	}
}
