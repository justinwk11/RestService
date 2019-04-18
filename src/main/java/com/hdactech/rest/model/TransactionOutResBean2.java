package com.hdactech.rest.model;

import java.io.Serializable;

public class TransactionOutResBean2 extends CommonResponseBean implements Serializable {
	
	private final String result;

	public TransactionOutResBean2(long id, String result) {
		super(id);
		this.result = result;
		// TODO 
	}

	public String getResult() {
		return result;
	}


}
