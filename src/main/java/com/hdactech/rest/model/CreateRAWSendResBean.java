package com.hdactech.rest.model;

import java.io.Serializable;

public class CreateRAWSendResBean extends CommonResponseBean implements Serializable {

    private final String hex;
    private final boolean complete;

	

    public CreateRAWSendResBean(long id, String hex, boolean complete) {
    	super(id);
        this.hex = hex;
        this.complete = complete;
    }

    public String getHex() {
        return hex;
    }
    
    public boolean getComplete() {
        return complete;
    }
    

}
