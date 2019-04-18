package com.hdactech.rest.model;

import java.io.Serializable;

public class CompleteRawExchangeResBean extends CommonResponseBean implements Serializable {
	
	private final String completeRawExchange;

	public CompleteRawExchangeResBean(long id, String completeRawExchange) {
		super(id);
		this.completeRawExchange = completeRawExchange;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the completeRawExchange
	 */
	public String getCompleteRawExchange() {
		return completeRawExchange;
	}
}
