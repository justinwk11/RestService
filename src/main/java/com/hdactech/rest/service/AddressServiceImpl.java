package com.hdactech.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdactech.command.AddressCommand;
import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.object.Address;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.object.KeyPairs;
import com.hdactech.object.MultiBalance;
import com.hdactech.object.MultiSigAddress;
import com.hdactech.rest.RestServiveApplication;

@Service
public class AddressServiceImpl implements AddressService {
	
	static AddressCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getAddressCommand();
	}
	
	@Override
	public MultiSigAddress addMultiSigAddress(int nrequired, String[] keys) throws HdacException {
		return mRpcCmd.addMultiSigAddress(nrequired, keys);
	}
	
	@Override
	public List<KeyPairs> createKeyPairs() throws HdacException {
		return mRpcCmd.createKeyPairs();
	}
	
	@Override
	public Address createMultiSig(int numberOfSigRequired, String[] publicKeys) throws HdacException {
		return mRpcCmd.createMultiSig(numberOfSigRequired, publicKeys);
	}

	@Override
	public String getNewAddress() throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getNewAddress();
	}

	@Override
	public void importAddress(String address, String label, boolean rescan) throws HdacException {
		// TODO Auto-generated method stub
		mRpcCmd.importAddress(address, label, rescan);
	}

	@Override
	public List<String> getAddresses() throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getAddresses();
	}

	@Override
	public Address validateAddress(String stringAddress) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.validateAddress(stringAddress);
	}
	
	@Override
	public List<BalanceAssetGeneral> getAddressBalances(String address, int minconf, boolean includelocked)	throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getAddressBalances(address,minconf,includelocked);
	}

	@Override
	public MultiBalance getMultiBalances(String[] addresses, String[] assets) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getMultiBalances(addresses, assets);
	}

	@Override
	public List<Object> listAddresses(String[] addresses, boolean verbose, long count, long start) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listAddresses(addresses, verbose, count, start);
	}

}
