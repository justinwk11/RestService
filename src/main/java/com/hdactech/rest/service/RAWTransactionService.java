package com.hdactech.rest.service;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hdactech.command.HdacException;
import com.hdactech.object.AddressBalance;
import com.hdactech.object.PrevTx;
import com.hdactech.object.PreviousTx;
import com.hdactech.object.SignedTransactionRAW;
import com.hdactech.object.TransactionRAW;
import com.hdactech.object.queryobjects.DataParam;
import com.hdactech.object.queryobjects.RawParam;
import com.hdactech.object.queryobjects.TxIdVout;

public interface RAWTransactionService {

	public abstract SignedTransactionRAW createRawSendFrom(String address, List<RawParam> rawParams, String[] data, String action) throws HdacException;

	public abstract String createRawSendFromWithSend(String address, List<RawParam> rawParams, String[] data) throws HdacException;
	
	public abstract SignedTransactionRAW signRawTransaction(String hexString) throws HdacException;
	
	public abstract SignedTransactionRAW signRawTransaction(String txhex, List<PrevTx> prevtxs, List<String> privatekeys, String sighashtype) throws HdacException;
	
	public abstract TransactionRAW decoderawTransaction(String txHex) throws HdacException;
	
	public abstract Object sendRawTransaction(String hexString) throws HdacException;

	public abstract String createRawTransaction(List<TxIdVout> inputs, List<AddressBalance> addessBalances, List<DataParam> data) throws HdacException;
	
	public abstract String createRawTransaction(List<TxIdVout>inputs, JsonObject addresses, JsonArray data, String action) throws HdacException;
	
	public abstract String appendRawData(String txHex, String dataHex) throws HdacException;
	
	public abstract String appendRawData(String txHex, JsonObject issueDetails) throws HdacException;
	
	public abstract String appendRawMetaData(String txHex, String dataHex) throws HdacException;

	public abstract Object getRawTransaction(String txid, int verbose) throws HdacException;

	public abstract Object decodeRawExchange(String txHex, boolean verbose) throws HdacException;

	public abstract Object disableRawTransaction(String txHex) throws HdacException;
	
	public abstract String createRawExchange(String txid, int vout, JsonObject jsonAssets) throws HdacException;
	
	public abstract String completeRawExchange(String hexString, String txid, int vout, JsonObject assets) throws HdacException;
	
	public abstract TxIdVout prepareLockUnspent(JsonObject assets, boolean lock) throws HdacException;
	
	public abstract TxIdVout prepareLockUnspentFrom(String fromAddress, JsonObject assets, boolean lock) throws HdacException;
	
	public abstract SignedTransactionRAW appendRawExchange(String hexString, String txid, int vout, JsonObject assets) throws HdacException;
	
	public abstract SignedTransactionRAW appendRawTransaction(String txHex, List<PreviousTx> previousTx, List<RawParam> rawParams, String[] data,
			String action) throws HdacException;
	
	public abstract String appendRawTransactionWithSend(String txHex, List<PreviousTx> previousTx, List<RawParam> rawParams, String[] data,
			String action) throws HdacException;
	
}