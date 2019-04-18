package com.hdactech.rest.service;

import org.springframework.stereotype.Service;

import com.hdactech.command.ChainCommand;
import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.rest.RestServiveApplication;

@Service
public class ChainServiceImpl implements ChainService {

	static ChainCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getChainCommand();
	}
	
	@Override
	public Object ObjectGetInfo() throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.ObjectGetInfo();
	}
	
	@Override
	public String getInfo() throws HdacException {
		return mRpcCmd.getInfo();
	}

	@Override
	public String help() throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.help();
	}

	@Override
	public String help(String command) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.help(command);
	}

	@Override
	public Object getblockchainParams() throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getblockchainParams();
	}

	@Override
	public Object getPeerInfo() throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getPeerInfo();
	}
}
