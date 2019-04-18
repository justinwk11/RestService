package com.hdactech.rest.service;

import java.util.List;

import com.hdactech.command.HdacException;
import com.hdactech.object.BalanceAsset;
import com.hdactech.object.UnspentList;
import com.hdactech.object.queryobjects.AssetParams;
import com.hdactech.object.queryobjects.CustomParamString;

public interface IssueService {
	
	public abstract List<BalanceAsset> listAssets(String asset, boolean verbose) throws HdacException;

	public abstract List<BalanceAsset> getAssetBalances() throws HdacException;
	
	public abstract String issue(String address,
			AssetParams assets,
			float quantity,
			float unit,
			float amount,
			List<CustomParamString> customFields) throws HdacException;
	
	public abstract String issueFrom(String fromAddress,
			String toAddress,
			AssetParams assets,
			float quantity,
			float unit,
			float amount,
			List<CustomParamString> customFields) throws HdacException;
	
	public abstract String issueMore(String address, String assetName, int quantity) throws HdacException;
	
	public abstract String issueMoreFrom(String fromAddress,
			String toAddress,
			String assetName,
			int quantity) throws HdacException;
	
	public abstract String sendAssetToAddress(String address, String assetName, float quantity) throws HdacException;
	
	public abstract String sendAssetFrom(String fromAddress,
			String toAddress,
			String assetName,
			float quantity) throws HdacException;
	
	public abstract List<UnspentList> listUnspent(int minconf, int maxconf, String[] addresses) throws HdacException;
	
}
