package com.hdactech.rest.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hdactech.command.HdacException;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.HelpResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.service.ChainService;

@RestController
@RequestMapping("/chain")
public class ChainController {

	@Autowired
	ChainService Service;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/getinfo", method = { RequestMethod.POST, RequestMethod.GET}, produces="application/json")
	@ResponseBody
	public String jsonGI() {
		
		Object getInfo = null;
		
		try {
			getInfo = Service.ObjectGetInfo(); //일정 부족으로 임시로 이렇게 고침. 완전 바이패스. hjs0317
			//SDK 자체가 String 리턴이였으므로 결국 SDK 래밸에서 Info 객체 정의가 필요할 것으로 보임. hjs0317 2018.12.18
		} catch (HdacException e) {
			return CommonResponseBean.createResponse(counter.incrementAndGet(), -500, e.getMessage(), null);
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonResult = gson.toJson(getInfo);
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonResult);
		return CommonResponseBean.createResponse(counter.incrementAndGet(), 0, null, jsonObject);
	}
	
	@RequestMapping(value = "/getpeerinfo", method = { RequestMethod.POST, RequestMethod.GET}, produces="application/json")
	@ResponseBody
	public String jsonGPI() {
		
		Object getPeerInfo = null;
		
		try {
			getPeerInfo = Service.getPeerInfo(); 
		} catch (HdacException e) {
			return CommonResponseBean.createResponse(counter.incrementAndGet(), -500, e.getMessage(), null);
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonResult = gson.toJson(getPeerInfo);
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonResult);
		return CommonResponseBean.createResponse(counter.incrementAndGet(), 0, null, jsonObject);
	}
	
	
	@RequestMapping(value = "/help", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonH(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		/*if(payload != null) {
			String command = jsonObject.get("command").getAsString();
			
			String help = null;
			
			try {
				help = Service.help(command);
			} catch (HdacException e) {
				return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
			}
			return new HelpWithCmdResBean(counter.incrementAndGet(), help);
		}*/
		
		String help = null;
		
		try {
			help = Service.help();
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new HelpResBean(counter.incrementAndGet(), help);
	}
	
	@RequestMapping(value = "/getblockchainparams", method = { RequestMethod.POST, RequestMethod.GET}, produces="application/json")
	@ResponseBody
	public String jsonGBP() {
		
		Object getblockchainParams = null;
		
		try {
			getblockchainParams = Service.getblockchainParams(); //일정 부족으로 임시로 이렇게 고침. 완전 바이패스. hjs0317
		} catch (HdacException e) {
			return CommonResponseBean.createResponse(counter.incrementAndGet(), -500, e.getMessage(), null);
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonResult = gson.toJson(getblockchainParams);
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonResult);
		return CommonResponseBean.createResponse(counter.incrementAndGet(), 0, null, jsonObject);
	}
}
