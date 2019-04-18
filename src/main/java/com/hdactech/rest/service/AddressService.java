package com.hdactech.rest.service;

import java.util.List;

import com.hdactech.command.HdacException;
import com.hdactech.object.Address;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.object.KeyPairs;
import com.hdactech.object.MultiBalance;
import com.hdactech.object.MultiSigAddress;

public interface AddressService {
	
	public abstract MultiSigAddress addMultiSigAddress(int nrequired, String[] keys) throws HdacException;
	
	public abstract List<KeyPairs> createKeyPairs() throws HdacException;
	
	public abstract Address createMultiSig(int numberOfSigRequired, String[] publicKeys) throws HdacException;
	
	public abstract String getNewAddress() throws HdacException;
	
	public abstract void importAddress(String address, String label, boolean rescan) throws HdacException;
	
	public abstract List<String> getAddresses() throws HdacException;
	
	public abstract Address validateAddress(String stringAddress) throws HdacException;
	
	public abstract List<BalanceAssetGeneral> getAddressBalances(String address, int minconf, boolean includelocked) throws HdacException;
	
	public abstract MultiBalance getMultiBalances(String[] addresses, String[] assets) throws HdacException;
	
	public abstract List<Object> listAddresses(String[] addresses, boolean verbose, long count, long start) throws HdacException;
	
}
