package com.hdactech.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.command.StreamCommand;
import com.hdactech.object.Stream;
import com.hdactech.object.StreamKey;
import com.hdactech.object.StreamKeyItem;
import com.hdactech.rest.RestServiveApplication;

@Service
public class StreamServiceImpl implements StreamService {

	static StreamCommand mRpcCmd = null;
	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getStreamCommand();
	}
	
	@Override
	public List<Stream> listStreams(String[] streams, boolean verbose, long count, long start) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listStreams(streams, verbose, count, start);
	}

	@Override
	public String publish(String streamName, String key, String dataHex) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.publish(streamName, key, dataHex);
	}

	@Override
	public String publishFrom(String addressFrom, String streamName, String key, String dataHex) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.publishFrom(addressFrom, streamName, key, dataHex);
	}

	@Override
	public String create(String entityType, String streamName, boolean open) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.create(entityType, streamName, open);
	}

	@Override
	public String createFrom(String addressFrom, String entityType, String streamName, boolean open) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.createFrom(addressFrom, entityType, streamName, open);
	}
	
	@Override
	public StreamKeyItem getStreamItems(String streamname, String txid, boolean verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.getStreamItem(streamname, txid, verbose);
	}

	@Override
	public void subscribe(String streamName) throws HdacException {
		// TODO Auto-generated method stub
		mRpcCmd.subscribe(streamName);
		
	}

	@Override
	public void unsubscribe(String streamName) throws HdacException {
		// TODO Auto-generated method stub
		mRpcCmd.unsubscribe(streamName);
	}

	@Override
	public List<StreamKeyItem> listStreamItems(String streamName, boolean verbose, int count, int start)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listStreamItems(streamName,verbose,count,start);
	}

	@Override
	public List<StreamKey> listStreamKeys(String streamName, String key, boolean verbose, int count, int start)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listStreamKeys(streamName,key,verbose,count,start);
	}

	
	@Override
	public List<StreamKeyItem> listStreamPublisherItems(String streamName, String address, boolean verbose, int count,
			int start) throws HdacException {
		return mRpcCmd.listStreamPublisherItems(streamName, address, verbose,count,start);
	}

	@Override
	public List<StreamKeyItem> listStreamKeyItems(String streamName, String key, boolean verbose, int count, int start)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listStreamKeyItems(streamName, key, verbose,count,start);
	}

	/*@Override
	public List<StreamKeyItems> listStreamitemFalse(String streamName, boolean verbose, int count, int start)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listStreamitemFalse(streamName,verbose,count,start);
	}*/

	
}
