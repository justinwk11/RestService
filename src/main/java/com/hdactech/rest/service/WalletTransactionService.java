package com.hdactech.rest.service;

import java.util.List;

import com.hdactech.command.HdacException;
import com.hdactech.object.ListAddressTxsDetailed;
import com.hdactech.object.ListWalletTxs;
import com.hdactech.object.TransactionWallet;
import com.hdactech.object.TransactionWalletDetailed;
import com.hdactech.object.TxOut;
import com.hdactech.object.queryobjects.AssetQuantity;

public interface WalletTransactionService {

	public abstract String sendWithMetaData(String address, List<AssetQuantity> assets, String hexMetaData) throws HdacException;
	
	public abstract String sendWithDataFrom(String fromAddress, String toAddress, String assetName, Integer assetValue, String metadata) throws HdacException;
	
	public abstract List<ListAddressTxsDetailed> listAddressTransactions(String address, long count, long skip, boolean verbose) throws HdacException;
	
	public abstract List<ListWalletTxs> listWalletTransaction(long count, long skip, boolean includeWatchonly, boolean verbose) throws HdacException;
	
	public abstract TransactionWalletDetailed getWalletTransaction(String txid, boolean includeWatchOnly, boolean verbose) throws HdacException;
	
	public abstract TxOut getTxOut(String txid, int vout, boolean includemempool) throws HdacException;
	
	/*public abstract Transaction getTransaction(String txId, boolean includeWatchOnly) throws HdacException;*/
	
	public Object getTransaction(String txid, boolean includeWatchOnly) throws HdacException;
	
	public abstract String sendFromAddress(String fromAddress, String toAddress, List<AssetQuantity> assets) throws HdacException;
	
	public abstract String send(String address, List<AssetQuantity> assets) throws HdacException;
	
	TransactionWallet getAssetTransaction(String assetName, String txid, boolean verbose) throws HdacException;
	
}
