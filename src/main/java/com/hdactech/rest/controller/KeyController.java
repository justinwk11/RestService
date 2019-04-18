package com.hdactech.rest.controller;

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
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.DumpPrivkeyResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.ImportPrivkeyResBean;
import com.hdactech.rest.service.KeyService;

@RestController
@RequestMapping("/key")
public class KeyController {

	@Autowired
	KeyService Service;

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value ="/importprivkey", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonIPK(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String privkey = jsonObject.get("privkey").getAsString();
		//String label = jsonObject.get("label").getAsString();
		//boolean rescan = jsonObject.get("rescan").getAsBoolean();
		
		Object importPrivkey = null;
		
		try {
			importPrivkey = Service.importPrivkey(privkey);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new ImportPrivkeyResBean(counter.incrementAndGet());	
	}
	
	@RequestMapping(value ="/dumpprivkey", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonDPK(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String adress = jsonObject.get("address").getAsString();
		
		Object getPrivkey = null;
		
		try {
			getPrivkey = Service.dumpPrivkey(adress);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new DumpPrivkeyResBean(counter.incrementAndGet(),getPrivkey.toString());	
	}
}