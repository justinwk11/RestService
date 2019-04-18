package com.hdactech.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdactech.command.BalanceCommand;
import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.rest.RestServiveApplication;

@Service
public class BalanceServiceImpl implements BalanceService{
	
	static BalanceCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getBalanceCommand();
	}

	@Override
	public List<BalanceAssetGeneral> getTotalBalances() throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getTotalBalances();
	}
}
