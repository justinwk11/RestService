package com.hdactech.rest.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hdactech.command.HdacException;
import com.hdactech.object.Block;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.GetBlockHashResBean;
import com.hdactech.rest.model.GetBlockResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.ListBlocksListResBean;
import com.hdactech.rest.service.BlockService;

@RestController
@RequestMapping("/block")
public class BlockController {

	@Autowired
	BlockService Service;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/getblock", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGB(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String blockHash = jsonObject.get("blockhash").getAsString();
		Block getBlock = null;
		
		try {
			getBlock = Service.getBlock(blockHash);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetBlockResBean(counter.incrementAndGet(), getBlock);
	}
	
	@RequestMapping(value = "/listblocks", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLBL(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
				
		String blockidentifiers = jsonObject.get("blockhash").getAsString();
		long verbose = jsonObject.get("verbose").getAsLong();
		
		List<Block> listBlocksList = null;
		
		try {
			listBlocksList = Service.listBlocksList(blockidentifiers, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListBlocksListResBean(counter.incrementAndGet(), listBlocksList);
	}
	
	@RequestMapping(value = "/getblockhash", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGBH(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		long index = (long) jsonObject.get("index").getAsInt();
		//parameter should be checked
		
		String getBlockHash = null;
		
		try {
			getBlockHash = Service.getBlockHash(index);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetBlockHashResBean(counter.incrementAndGet(), getBlockHash);
	}
}