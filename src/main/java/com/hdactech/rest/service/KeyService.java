package com.hdactech.rest.service;

import com.hdactech.command.HdacException;

public interface KeyService {
	
	public abstract Object importPrivkey(String privkey) throws HdacException;

	public abstract Object dumpPrivkey(String adress) throws HdacException;
}
