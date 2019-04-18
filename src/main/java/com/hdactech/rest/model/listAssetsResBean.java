package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.BalanceAsset;

public class listAssetsResBean extends CommonResponseBean implements Serializable {
	
	private final List<BalanceAsset> listAssets;
	
	public listAssetsResBean(long id, List<BalanceAsset> listAssets) {
		super(id);
		this.listAssets = listAssets;
		// TODO Auto-generated constructor stub
	}
	public List<BalanceAsset> getListAssets() {
		return listAssets;
	}
}