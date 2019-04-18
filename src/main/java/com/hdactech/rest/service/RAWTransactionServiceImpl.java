package com.hdactech.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.command.RAWTransactionCommand;
import com.hdactech.object.AddressBalance;
import com.hdactech.object.PrevTx;
import com.hdactech.object.PreviousTx;
import com.hdactech.object.SignedTransactionRAW;
import com.hdactech.object.TransactionRAW;
import com.hdactech.object.queryobjects.DataParam;
import com.hdactech.object.queryobjects.RawParam;
import com.hdactech.object.queryobjects.TxIdVout;
import com.hdactech.rest.RestServiveApplication;

@Service
public class RAWTransactionServiceImpl implements RAWTransactionService {

	static RAWTransactionCommand mRpcCmd = null;
	static {
		//mRpcCmd = (new HdacCommand("13.125.145.98", "4260", "hdacrpc", "1234")).getRawTransactionCommand();
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getRawTransactionCommand();
		
	}
	
	@Override
	public SignedTransactionRAW createRawSendFrom(String address, List<RawParam> rawParams, String[] data,
			String action) throws HdacException {
		return mRpcCmd.createRawSendFrom(address, rawParams, data, action);
	}

	@Override
	public String createRawSendFromWithSend(String address, List<RawParam> rawParams, String[] data) throws HdacException {
		return mRpcCmd.createRawSendFromWithSend(address, rawParams, data, "send");
	}
	
	@Override
	public SignedTransactionRAW signRawTransaction(String hexString) throws HdacException {
		return mRpcCmd.signRawTransaction(hexString);
	}
	
	@Override
	public SignedTransactionRAW signRawTransaction(String txhex, List<PrevTx> prevtxs, List<String> privatekeys,
			String sighashtype) throws HdacException {
		return mRpcCmd.signRawTransaction(txhex, prevtxs, privatekeys, sighashtype);
	}

	@Override
	public TransactionRAW decoderawTransaction(String txHex) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.decodeRawTransaction(txHex);
	}
	
	@Override
	public Object sendRawTransaction(String hexString) throws HdacException {
		return mRpcCmd.sendRawTransaction(hexString);
	}
	
	@Override
	public String createRawTransaction(List<TxIdVout> inputs, List<AddressBalance> addessBalances, List<DataParam> data) throws HdacException {
		return mRpcCmd.createRawTransaction(inputs, addessBalances, data);
	}
	
	@Override
	public String createRawTransaction(List<TxIdVout>inputs, JsonObject addresses, JsonArray data, String action) throws HdacException{
		return mRpcCmd.createRawTransaction(inputs, addresses, data, action);
	}
	
	@Override
	public String appendRawData(String txHex, String dataHex) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.appendRawData(txHex, dataHex);
	}
	
	@Override
	public String appendRawData(String txHex, JsonObject issueDetails) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.appendRawData(txHex, issueDetails);
	}
	
	@Override
	public String appendRawMetaData(String txHex, String dataHex) throws HdacException {
		return mRpcCmd.appendRawMetaData(txHex, dataHex);
	}

	@Override
	public Object getRawTransaction(String txid, int verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getRawTransaction(txid, verbose);
	}

	@Override
	public Object decodeRawExchange(String txHex, boolean verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.decodeRawExchange(txHex,verbose);
	}

	@Override
	public Object disableRawTransaction(String txHex) throws HdacException{
		// TODO Auto-generated method stub
		return mRpcCmd.disableRawTransaction(txHex);
	}

	@Override
	public String createRawExchange(String txid, int vout, JsonObject jsonAssets) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.createRawExchange(txid, vout, jsonAssets);
	}

	@Override
	public String completeRawExchange(String hexString, String txid, int vout, JsonObject assets)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.completeRawExchange(hexString, txid, vout, assets);
	}

	@Override
	public TxIdVout prepareLockUnspent(JsonObject assets, boolean lock) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.prepareLockUnspent(assets, lock);
	}

	@Override
	public TxIdVout prepareLockUnspentFrom(String fromAddress, JsonObject assets, boolean lock) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.prepareLockUnspentFrom(fromAddress, assets, lock);
	}

	@Override
	public SignedTransactionRAW appendRawExchange(String hexString, String txid, int vout, JsonObject assets)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.appendRawExchange(hexString, txid, vout, assets);
	}

	@Override
	public SignedTransactionRAW appendRawTransaction(String txHex, List<PreviousTx> previousTx,
			List<RawParam> rawParams, String[] data, String action) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.appendRawTransaction(txHex, previousTx, rawParams, data, action);
	}

	@Override
	public String appendRawTransactionWithSend(String txHex, List<PreviousTx> previousTx, List<RawParam> rawParams,
			String[] data, String action) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.appendRawTransactionWithSend(txHex, previousTx, rawParams, data, action);
	}
}