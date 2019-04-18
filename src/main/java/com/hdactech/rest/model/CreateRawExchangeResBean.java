package com.hdactech.rest.model;

import java.io.Serializable;

public class CreateRawExchangeResBean extends CommonResponseBean implements Serializable {
	
	private final String createRawExchange;

	public CreateRawExchangeResBean(long id, String createRawExchange) {
		super(id);
		this.createRawExchange = createRawExchange;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the createRawExchange
	 */
	public String getCreateRawExchange() {
		return createRawExchange;
	}
}
