package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.queryobjects.TxIdVout;

public class PrepareLockUnspentResBean extends CommonResponseBean implements Serializable {
	
	private final TxIdVout prepareLockUnspent;

	public PrepareLockUnspentResBean(long id, TxIdVout prepareLockUnspent) {
		super(id);
		this.prepareLockUnspent = prepareLockUnspent;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the prepareLockUnspent
	 */
	public TxIdVout getPrepareLockUnspent() {
		return prepareLockUnspent;
	}
}
