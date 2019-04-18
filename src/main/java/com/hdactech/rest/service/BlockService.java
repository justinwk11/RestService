package com.hdactech.rest.service;

import java.util.List;

import com.hdactech.command.HdacException;
import com.hdactech.object.Block;

public interface BlockService {
	
	public abstract Block getBlock(String blockHash) throws HdacException;
	
	public abstract List<Block> listBlocksList(String blockidentifiers, long verbose) throws HdacException;
	
	public abstract String getBlockHash(long index) throws HdacException;

}
