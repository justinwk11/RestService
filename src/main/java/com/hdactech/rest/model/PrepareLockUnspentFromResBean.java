package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.queryobjects.TxIdVout;

public class PrepareLockUnspentFromResBean extends CommonResponseBean implements Serializable {
	
	private final TxIdVout prepareLockUnspentFrom;

	public PrepareLockUnspentFromResBean(long id, TxIdVout prepareLockUnspentFrom) {
		super(id);
		this.prepareLockUnspentFrom = prepareLockUnspentFrom;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the prepareLockUnspent
	 */
	public TxIdVout getPrepareLockUnspentFrom() {
		return prepareLockUnspentFrom;
	}
}
