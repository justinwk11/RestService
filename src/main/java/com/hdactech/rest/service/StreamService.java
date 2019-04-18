package com.hdactech.rest.service;

import java.util.List;

import com.hdactech.command.HdacException;
import com.hdactech.object.Stream;
import com.hdactech.object.StreamKey;
import com.hdactech.object.StreamKeyItem;

public interface StreamService {
	
	public abstract List<Stream> listStreams(String[] streams, boolean verbose, long count, long start)
			throws HdacException;
	
	public abstract String publish(String streamName, String key, String dataHex) throws HdacException;
	
	public abstract String publishFrom(String addressFrom, String streamName, String key, String dataHex) throws HdacException;
	
	public abstract String create(String entityType, String streamName, boolean open) throws HdacException;
	
	public abstract String createFrom(String addressFrom, String entityType, String streamName, boolean open) throws HdacException;

	public abstract StreamKeyItem getStreamItems(String streamname, String txid, boolean verbose) throws HdacException;

	public abstract void subscribe(String streamName) throws HdacException;

	public abstract void unsubscribe(String streamName) throws HdacException;

	public abstract List<StreamKeyItem> listStreamItems(String streamName, boolean verbose, int count, int start) throws HdacException;

	public abstract List<StreamKey> listStreamKeys(String streamName, String key, boolean verbose, int count,
			int start) throws HdacException;

	public abstract List<StreamKeyItem> listStreamPublisherItems(String streamName, String address, boolean verbose,
			int count, int start) throws HdacException;

	public abstract List<StreamKeyItem> listStreamKeyItems(String streamName, String key, boolean verbose, int count,
			int start) throws HdacException;

	//public abstract List<StreamKeyItems> listStreamitemFalse(String streamName, boolean verbose, int count, int start)throws HdacException;

}
