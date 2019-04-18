package com.hdactech.rest.model;

import java.io.Serializable;

/**
 * @author Patched by Justin
 *
 */
public class GetRAWTransactionResBean extends CommonResponseBean implements Serializable {

	private final Object getRawTransaction;
	
	public GetRAWTransactionResBean(long id, Object getRawTransaction) {
		super(id);
		this.getRawTransaction = getRawTransaction;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the getRawTransaction
	 */
	public Object getGetRawTransaction() {
		return getRawTransaction;
	}
}
