package com.hdactech.rest.model;

import java.io.Serializable;

import com.hdactech.object.MultiBalance;

public class GetMultiBalancesResBean extends CommonResponseBean implements Serializable {
	
	MultiBalance getMultiBalances;

	public GetMultiBalancesResBean(long id, MultiBalance getMultiBalances) {
		super(id);
		this.getMultiBalances = getMultiBalances;
		// TODO Auto-generated constructor stub
	}

	public MultiBalance getGetMultiBalances() {
		return getMultiBalances;
	}

	public void setGetMultiBalances(MultiBalance getMultiBalances) {
		this.getMultiBalances = getMultiBalances;
	}
}
