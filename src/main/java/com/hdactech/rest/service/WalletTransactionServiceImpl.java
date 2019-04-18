package com.hdactech.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.command.WalletTransactionCommand;
import com.hdactech.object.ListAddressTxsDetailed;
import com.hdactech.object.ListWalletTxs;
import com.hdactech.object.TransactionWallet;
import com.hdactech.object.TransactionWalletDetailed;
import com.hdactech.object.TxOut;
import com.hdactech.object.queryobjects.AssetQuantity;
import com.hdactech.rest.RestServiveApplication;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

	static WalletTransactionCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getWalletTransactionCommand();
	}
	
	@Override
	public String sendWithMetaData(String address, List<AssetQuantity> assets, String hexMetaData)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.sendWithMetaData(address, assets, hexMetaData);
	}

	@Override
	public String sendWithDataFrom(String fromAddress, String toAddress, String assetName, Integer assetValue,
			String metadata) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.sendWithDataFrom(fromAddress, toAddress, assetName, assetValue, metadata); 
	}

	@Override
	public List<ListAddressTxsDetailed> listAddressTransactions(String address, long count, long skip,
			boolean verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listAddressTransactions(address, count, skip, verbose);
	}

	@Override
	public List<ListWalletTxs> listWalletTransaction(long count, long skip, boolean includeWatchonly,
			boolean verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listWalletTransaction(count, skip, includeWatchonly, verbose);
	}

	@Override
	public TransactionWalletDetailed getWalletTransaction(String txid, boolean includeWatchOnly, boolean verbose)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getWalletTransaction(txid, includeWatchOnly, verbose);
	}

	@Override
	public TxOut getTxOut(String txid, int vout, boolean includemempool) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getTxOut(txid, vout, includemempool);
	}
	
	/*@Override 
	public Transaction getTransaction(String txid, boolean includeWatchOnly) throws HdacException {
		return mRpcCmd.getTransaction(txid, includeWatchOnly);
	}*/

	@Override
	public Object getTransaction(String txid, boolean includeWatchOnly) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getTransaction(txid, includeWatchOnly);
	}

	@Override
	public String sendFromAddress(String fromAddress, String toAddress, List<AssetQuantity> assets)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.sendFromAddress(fromAddress, toAddress, assets);
	}

	@Override
	public String send(String address, List<AssetQuantity> assets) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.send(address, assets);
	}

	@Override
	public TransactionWallet getAssetTransaction(String assetName, String txid, boolean verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getAssetTransaction(assetName, txid, verbose);
	}
}
