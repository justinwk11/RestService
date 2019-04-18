package com.hdactech.rest.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hdactech.command.HdacException;
import com.hdactech.object.Stream;
import com.hdactech.object.StreamKey;
import com.hdactech.object.StreamKeyItem;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.CreateFromResBean;
import com.hdactech.rest.model.CreateResBean;
import com.hdactech.rest.model.GetStreamItemResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.ListStreamItemsResBean;
import com.hdactech.rest.model.ListStreamKeyItemsResBean;
import com.hdactech.rest.model.ListStreamKeysResBean;
import com.hdactech.rest.model.ListStreamPublisherItemsResBean;
import com.hdactech.rest.model.ListStreamsResBean;
import com.hdactech.rest.model.PublishFromResBean;
import com.hdactech.rest.model.PublishResBean;
import com.hdactech.rest.model.SubscribeResBean;
import com.hdactech.rest.service.StreamService;

@RestController
@RequestMapping("/stream")
public class StreamController {
	
	@Autowired
	StreamService Service;
	
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/liststreams", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLS(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		JsonArray jsonStreams = null;
		Boolean verbose = false;
		long count = 0;
		long start = 0;
		
		String streams[] = null;
		
		if(jsonObject.has("streamidentifier")) {
			jsonStreams = jsonObject.get("streamidentifier").getAsJsonArray();
			streams = new String[jsonStreams.size()];
			
			int n = 0;
			for(JsonElement jsonStream : jsonStreams) {
				streams[n++] = jsonStream.getAsString();
			}
		}
		if(jsonObject.has("verbose")) {
			verbose = jsonObject.get("verbose").getAsBoolean();
		}
		if(jsonObject.has("count")) {
			count = jsonObject.get("count").getAsLong();
		}
		if(jsonObject.has("start")) {
			start = jsonObject.get("start").getAsLong();
		}
		
		List<Stream> listStreams = null;
		
		try {
			listStreams = Service.listStreams(streams, verbose, count, start);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListStreamsResBean(counter.incrementAndGet(), listStreams);
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonP(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String streamName = jsonObject.get("streamname").getAsString();
		String key = jsonObject.get("key").getAsString();
		String dataHex = jsonObject.get("datahex").getAsString();
		
		String stringPublish = null;
		
		try {
			stringPublish = Service.publish(streamName, key, dataHex);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new PublishResBean(counter.incrementAndGet(), stringPublish);
	}
	
	@RequestMapping(value = "/publishfrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonPF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String addressFrom = jsonObject.get("addressfrom").getAsString();
		String streamName = jsonObject.get("streamname").getAsString();
		String key = jsonObject.get("key").getAsString();
		String dataHex = jsonObject.get("datahex").getAsString();
		
		String stringPublish = null;
		
		try {
			stringPublish = Service.publishFrom(addressFrom, streamName, key, dataHex);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new PublishFromResBean(counter.incrementAndGet(), stringPublish);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonC(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String entityType = jsonObject.get("entitytype").getAsString();
		String streamName = jsonObject.get("streamname").getAsString();
		boolean open = jsonObject.get("open").getAsBoolean();
		
		String stringCreate = null;
		
		try {
			stringCreate = Service.create(entityType, streamName, open);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new CreateResBean(counter.incrementAndGet(), stringCreate);
	}
	
	@RequestMapping(value = "/createfrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonCF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String addressFrom = jsonObject.get("addressFrom").getAsString();
		String entityType = jsonObject.get("entitytype").getAsString();
		String streamName = jsonObject.get("streamName").getAsString();
		boolean open = jsonObject.get("open").getAsBoolean();
		
		String stringCreate = null;
		
		try {
			stringCreate = Service.createFrom(addressFrom, entityType,streamName, open);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new CreateFromResBean(counter.incrementAndGet(), stringCreate);
	}

	/**
	 * should be checked
	 * @param payload
	 * @return
	 */
	
	@RequestMapping(value = "/getstreamitem", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGSI(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String streamname = jsonObject.get("streamidentifier").getAsString();
		String txid = jsonObject.get("txid").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		
		StreamKeyItem getStreamItem = null;
		
		try {
			getStreamItem = Service.getStreamItems(streamname, txid, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetStreamItemResBean(counter.incrementAndGet(), getStreamItem);
	}
	
	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonSUB(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String streamName = jsonObject.get("streamname").getAsString();
		//boolean rescan = jsonObject.get("rescan").getAsBoolean();
		
		try {
			 Service.subscribe(streamName);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new SubscribeResBean(counter.incrementAndGet());
	}
	
	@RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonUNSUB(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String streamName = jsonObject.get("streamidentifier").getAsString();
		
		try {
			 Service.unsubscribe(streamName);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new SubscribeResBean(counter.incrementAndGet());
	}
	
	@RequestMapping(value = "/liststreamitems", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLSI(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String streamName = jsonObject.get("streamidentifier").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		int count = jsonObject.get("count").getAsInt();
		int start = jsonObject.get("start").getAsInt();
		
		List<StreamKeyItem> listStreamItems = null;
		
		try {
			listStreamItems = Service.listStreamItems(streamName, verbose, count, start);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListStreamItemsResBean(counter.incrementAndGet(), listStreamItems);
	}
		
		
	
	@RequestMapping(value = "/liststreampublisheritems", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLSPI(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String streamName = jsonObject.get("streamidentifier").getAsString();
		String address = jsonObject.get("address").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		int count = jsonObject.get("count").getAsInt();
		int start = jsonObject.get("start").getAsInt();
		
		List<StreamKeyItem> listStreamPublisherItems = null;
		
		try {
			listStreamPublisherItems = Service.listStreamPublisherItems(streamName,address,verbose, count, start);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListStreamPublisherItemsResBean(counter.incrementAndGet(), listStreamPublisherItems);
	}
	
	@RequestMapping(value = "/liststreamkeys", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLSK(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String streamName = jsonObject.get("streamidentifier").getAsString();
		String key = jsonObject.get("key").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		int count = jsonObject.get("count").getAsInt();
		int start = jsonObject.get("start").getAsInt();
		
		List<StreamKey> listStreamKeys = null;
		
		try {
			listStreamKeys = Service.listStreamKeys(streamName, key,verbose, count, start);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListStreamKeysResBean(counter.incrementAndGet(), listStreamKeys);
	}
	
	@RequestMapping(value = "/liststreamkeyitems", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLSKI(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String streamName = jsonObject.get("streamidentifier").getAsString();
		String key = jsonObject.get("key").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		int count = jsonObject.get("count").getAsInt();
		int start = jsonObject.get("start").getAsInt();
		
		List<StreamKeyItem> streamKeyItems = null;
		
		try {
			streamKeyItems = Service.listStreamKeyItems(streamName, key,verbose,count,start);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListStreamKeyItemsResBean(counter.incrementAndGet(), streamKeyItems);
	}

}
