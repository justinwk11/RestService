package com.hdactech.rest.service;

import com.hdactech.command.HdacException;

public interface ChainService {
	
	public String getInfo() throws HdacException;
	
	public Object ObjectGetInfo() throws HdacException;
	
	public abstract String help() throws HdacException;
	
	public abstract String help(String command) throws HdacException;

	public Object getblockchainParams() throws HdacException;

	public Object getPeerInfo() throws HdacException;

}
