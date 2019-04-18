package com.hdactech.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdactech.command.BlockCommand;
import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.object.Block;
import com.hdactech.rest.RestServiveApplication;

@Service
public class BlockServiceImpl implements BlockService {

	static BlockCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getBlockCommand();
	}
	
	@Override
	public Block getBlock(String blockHash) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getBlock(blockHash);
	}

	@Override
	public List<Block> listBlocksList(String blockidentifiers, long verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listBlocksList(blockidentifiers, verbose);
	}

	@Override
	public String getBlockHash(long index) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getBlockHash(index);
	}

}
