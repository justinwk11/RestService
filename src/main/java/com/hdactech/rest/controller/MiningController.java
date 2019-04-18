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
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.pauseMiningResBean;
import com.hdactech.rest.service.MiningService;

@RestController
@RequestMapping("/mining")
public class MiningController {

	@Autowired
	MiningService Service;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGB(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String task = jsonObject.get("task").getAsString();

		Object pauseMining = null;
		
		try {
			pauseMining = Service.pauseMining(task);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new pauseMiningResBean(counter.incrementAndGet(), pauseMining.toString());
	}
}