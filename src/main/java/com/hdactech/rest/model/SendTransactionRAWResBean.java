package com.hdactech.rest.model;

import java.io.Serializable;

public class SendTransactionRAWResBean extends CommonResponseBean implements Serializable {
	
	private final String txid;
	
	public SendTransactionRAWResBean(long id, String txid) {
		super(id);
		this.txid = txid;
	}
	
	public String getTxid() {
		return txid;
	}
}
