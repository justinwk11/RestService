package com.hdactech.rest.model;

import java.io.Serializable;

/**
 * @author Justin
 *
 */
public class DecodeRawExchangeResBean extends CommonResponseBean implements Serializable {
	
	Object decodeRawExchange;

	public DecodeRawExchangeResBean(long id, Object decodeRawExchange) {
		super(id);
		this.decodeRawExchange = decodeRawExchange;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the decodeRawExchange
	 */
	public Object getDecodeRawExchange() {
		return decodeRawExchange;
	}

}