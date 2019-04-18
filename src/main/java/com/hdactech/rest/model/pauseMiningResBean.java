package com.hdactech.rest.model;

import java.io.Serializable;

public class pauseMiningResBean extends CommonResponseBean implements Serializable {

	private final String pause;
	
	public pauseMiningResBean(long id, String pause) {
		super(id);
		this.pause = pause;
		// TODO Auto-generated constructor stub
	}

	public String getPause() {
		return pause;
	}
}
