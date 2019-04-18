package com.hdactech.rest.service;

import com.hdactech.command.HdacException;

public interface MiningService {

	public abstract Object pauseMining(String task) throws HdacException;

}
