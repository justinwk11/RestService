package com.hdactech.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.command.IssueCommand;
import com.hdactech.object.BalanceAsset;
import com.hdactech.object.UnspentList;
import com.hdactech.object.queryobjects.AssetParams;
import com.hdactech.object.queryobjects.CustomParamString;
import com.hdactech.rest.RestServiveApplication;

@Service
public class IssueServiceImpl implements IssueService {

	static IssueCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getIssueCommand();
	}
	
	@Override
	public List<BalanceAsset> listAssets(String asset, boolean verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listAssets(asset, verbose);
	}

	@Override
	public List<BalanceAsset> getAssetBalances() throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getAssetBalances();
	}

	@Override
	public String issue(String address, AssetParams assets, float quantity, float unit, float amount,
			List<CustomParamString> customFields) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.issue(address, assets, quantity, unit, amount, customFields);
	}

	@Override
	public String issueFrom(String fromAddress, String toAddress, AssetParams assets, float quantity, float unit,
			float amount, List<CustomParamString> customFields) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.issueFrom(fromAddress, toAddress, assets, quantity, unit, amount, customFields);
	}

	@Override
	public String issueMore(String address, String assetName, int quantity) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.issueMore(address, assetName, quantity);
	}

	@Override
	public String issueMoreFrom(String fromAddress, String toAddress, String assetName, int quantity)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.issueMoreFrom(fromAddress, toAddress, assetName, quantity);
	}

	@Override
	public String sendAssetToAddress(String address, String assetName, float quantity) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.sendAssetToAddress(address, assetName, quantity);
	}

	@Override
	public String sendAssetFrom(String fromAddress, String toAddress, String assetName, float quantity)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.sendAssetFrom(fromAddress, toAddress, assetName, quantity);
	}

	@Override
	public List<UnspentList> listUnspent(int minconf, int maxconf, String[] addresses) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listUnspent(minconf, maxconf, addresses);
	}
}
