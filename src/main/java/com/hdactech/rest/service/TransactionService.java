package com.hdactech.rest.service;

import com.hdactech.command.HdacException;
import com.hdactech.object.TransactionWallet;

public interface TransactionService {
	
	public abstract TransactionWallet getAddressTransaction(String address, String txId, boolean verbose) throws HdacException;

	//public abstract String prepareLockUnspent(JsonObject jsonAssetquantities, boolean lock) throws HdacException;
	
}
