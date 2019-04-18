package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.BalanceAsset;

public class GetAssetBalancesResBean extends CommonResponseBean implements Serializable {

	private final List<BalanceAsset> listBalanceAsset;
	
	public GetAssetBalancesResBean(long id, List<BalanceAsset> listBalanceAsset) {
		super(id);
		this.listBalanceAsset = listBalanceAsset;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the listBalanceAsset
	 */
	public List<BalanceAsset> getListBalanceAsset() {
		return listBalanceAsset;
	}	
}
