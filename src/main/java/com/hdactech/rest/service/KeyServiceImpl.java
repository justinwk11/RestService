package com.hdactech.rest.service;

import org.springframework.stereotype.Service;

import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.command.KeyCommand;
import com.hdactech.rest.RestServiveApplication;

@Service
public class KeyServiceImpl implements KeyService {
	
	static KeyCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getKeyCommand();
	}
	@Override
	public Object importPrivkey(String privkey) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.importPrivkey(privkey);
	}
	@Override
	public Object dumpPrivkey(String adress) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getPrivkey(adress);
	}
	
}
