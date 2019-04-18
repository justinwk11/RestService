package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.BalanceAssetGeneral;

public class GetAddressBalancesResBean extends CommonResponseBean implements Serializable {
	
	private final List<BalanceAssetGeneral> getAddressBalances;
	
	public GetAddressBalancesResBean(long id, List<BalanceAssetGeneral> getAddressBalances) {
		super(id);
		this.getAddressBalances = getAddressBalances;
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the listBalanceAsset
	 */
	public List<BalanceAssetGeneral> getListBalanceAsset() {
		return getAddressBalances;
	}
}
