package com.hdactech.rest.model;

import java.io.Serializable;

/**
 * @author Justin
 *
 */
public class AppendRawTransactionWithSendResBean extends CommonResponseBean implements Serializable {
	
	String signedTransactionRAW;

	public AppendRawTransactionWithSendResBean(long id, String signedTransactionRAW) {
		super(id);
		this.signedTransactionRAW = signedTransactionRAW;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the signedTransactionRAW
	 */
	public String getSignedTransactionRAW() {
		return signedTransactionRAW;
	}

}
