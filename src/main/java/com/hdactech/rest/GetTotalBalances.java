package com.hdactech.rest;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.rest.model.CommonResponseBean;

public class GetTotalBalances extends CommonResponseBean implements Serializable {
	
	private final List<BalanceAssetGeneral> listBalanceAsset;

	public GetTotalBalances(long id, List<BalanceAssetGeneral> listBalanceAsset) {
		super(id);
		this.listBalanceAsset = listBalanceAsset;
		// TODO Auto-generated constructor stub
	}

	public List<BalanceAssetGeneral> getListBalanceAsset() {
		return listBalanceAsset;
	}
}
