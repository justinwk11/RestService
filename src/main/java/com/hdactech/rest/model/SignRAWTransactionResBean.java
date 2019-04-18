package com.hdactech.rest.model;

import java.io.Serializable;

public class SignRAWTransactionResBean extends CommonResponseBean implements Serializable {
	
	private final String hexString;
	private final boolean complete;
	
	public SignRAWTransactionResBean(long id, String hexString, boolean complete) {
		super(id);
		this.hexString = hexString;
		this.complete = complete;
	}
	
	public String getHexString() {
        return hexString;
    }
    
    public boolean getComplete() {
        return complete;
    }
}
