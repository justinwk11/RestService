package com.hdactech.rest.model;

import java.io.Serializable;

public class DisableTransactionRAWResBean extends CommonResponseBean implements Serializable {
	
	private final String txid;
	
	public DisableTransactionRAWResBean(long id, String txid) {
		super(id);
		this.txid = txid;
	}
	
	public String getTxid() {
		return txid;
	}
}
