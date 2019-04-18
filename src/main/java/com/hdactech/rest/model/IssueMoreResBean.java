package com.hdactech.rest.model;

import java.io.Serializable;

public class IssueMoreResBean extends CommonResponseBean implements Serializable {
	private final String txid;
	public IssueMoreResBean(long id, String txid) {
		super(id);
		this.txid = txid;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the txid
	 */
	public String getTxid() {
		return txid;
	}
}
