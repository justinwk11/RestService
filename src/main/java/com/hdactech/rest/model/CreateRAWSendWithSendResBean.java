package com.hdactech.rest.model;

import java.io.Serializable;

public class CreateRAWSendWithSendResBean extends CommonResponseBean implements Serializable{

    private final String txid;

	

    public CreateRAWSendWithSendResBean(long id, String txid) {
    	super(id);
        this.txid = txid;

    }

    public String getTxid() {
        return txid;
    }
    

}
