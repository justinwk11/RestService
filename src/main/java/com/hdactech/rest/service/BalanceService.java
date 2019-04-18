package com.hdactech.rest.service;

import java.util.List;

import com.hdactech.command.HdacException;
import com.hdactech.object.BalanceAssetGeneral;

public interface BalanceService {
	
	public abstract List<BalanceAssetGeneral> getTotalBalances() throws HdacException;
	
}
