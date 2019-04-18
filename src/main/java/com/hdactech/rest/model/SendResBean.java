package com.hdactech.rest.model;

import java.io.Serializable;

public class SendResBean extends CommonResponseBean implements Serializable {

	private final String stringSend;
	
	public SendResBean(long id, String stringSend) {
		super(id);
		this.stringSend = stringSend;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the stringSend
	 */
	public String getStringSend() {
		return stringSend;
	}
}
