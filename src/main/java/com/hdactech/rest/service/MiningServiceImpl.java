package com.hdactech.rest.service;

import org.springframework.stereotype.Service;

import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.command.MiningCommand;
import com.hdactech.rest.RestServiveApplication;

@Service
public class MiningServiceImpl implements MiningService {
	
	static MiningCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getMiningCommand();
	}
	
//pause command is should be changed(task is required) 
	@Override
	public Object pauseMining(String task) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.pauseMining();
	}
	
}
