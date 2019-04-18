package com.hdactech.rest.service;

import org.springframework.stereotype.Service;

import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.command.WalletTransactionCommand;
import com.hdactech.object.TransactionWallet;
import com.hdactech.rest.RestServiveApplication;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	static WalletTransactionCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getWalletTransactionCommand();
	}
	
	@Override
	public TransactionWallet getAddressTransaction(String address, String txId, boolean verbose) throws HdacException {
		return mRpcCmd.getAddressTransaction(address, txId, verbose);
	}

	/*@Override
	public String prepareLockUnspent(JsonObject jsonAssetquantities, boolean lock) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.prepareLockUnspent(jsonAssetquantities, lock);
	}*/
}
