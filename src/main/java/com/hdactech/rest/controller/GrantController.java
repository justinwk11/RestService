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
import com.hdactech.object.Permission;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.GrantFromResBean;
import com.hdactech.rest.model.GrantResBean;
import com.hdactech.rest.model.GrantWithDataFromResBean;
import com.hdactech.rest.model.GrantWithDataResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.ListPermissionsResBean;
import com.hdactech.rest.model.RevokeFromResBean;
import com.hdactech.rest.model.RevokeResBean;
import com.hdactech.rest.service.GRANTService;

@RestController
@RequestMapping("/grant")
public class GrantController {

	@Autowired
	GRANTService Service;

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "grant", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonG(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String address = jsonObject.get("address").getAsString();
		String permission = jsonObject.get("permission").getAsString();
		/*int nativeAmount = jsonObject.get("nativeamount").getAsInt();
		int startblock = jsonObject.get("startblock").getAsInt();
		int endblock = jsonObject.get("endblock").getAsInt();
		String comment = jsonObject.get("comment").getAsString();
		String commentTo = jsonObject.get("commentto").getAsString();*/
		
		String grant = null;
		
		try {
			grant = Service.grant(address, permission);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new GrantResBean(counter.incrementAndGet(), grant);
	}
	
	@RequestMapping(value = "grantfrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String addressFrom = jsonObject.get("addressFrom").getAsString();
		String address = jsonObject.get("address").getAsString();
		int permissions = jsonObject.get("permissions").getAsInt();
		
		String grantFrom = null;
		
		try {
			grantFrom = Service.grantFrom(addressFrom, address, permissions);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new GrantFromResBean(counter.incrementAndGet(), grantFrom);
	}
	
	@RequestMapping(value = "listPermissions", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLP(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		byte permissions = jsonObject.get("permissions").getAsByte();
		String address = jsonObject.get("address").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		
		List<Permission> listPermissions = null;
		
		try {
			listPermissions = Service.listPermissions(permissions, address, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new ListPermissionsResBean(counter.incrementAndGet(), listPermissions);
	}
	
	@RequestMapping(value = "grantwithdata", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGWD(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		int permissions = jsonObject.get("permissions").getAsInt();
		String dataHex = jsonObject.get("dataHex").getAsString();
		
		String grantWithData = null;
		
		try {
			grantWithData = Service.grantWithData(address, permissions, dataHex);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new GrantWithDataResBean(counter.incrementAndGet(), grantWithData);
	}
	
	@RequestMapping(value = "grantwithdatafrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGWDF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
	
		String fromAddress = jsonObject.get("fromAddress").getAsString();
		String address = jsonObject.get("address").getAsString();
		int permissions = jsonObject.get("permissions").getAsInt();
		String dataHex = jsonObject.get("dataHex").getAsString();
		
		String grantWithDataFrom = null;
		
		try {
			grantWithDataFrom = Service.grantWithDataFrom(fromAddress, address, permissions, dataHex);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new GrantWithDataFromResBean(counter.incrementAndGet(), grantWithDataFrom);
	}
	
	@RequestMapping(value = "revoke", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonR(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
	
		String address = jsonObject.get("address").getAsString();
		int permissions = jsonObject.get("permissions").getAsInt();
		
		String revoke = null;
		
		try {
			revoke = Service.revoke(address, permissions);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new RevokeResBean(counter.incrementAndGet(), revoke);
	}
	
	@RequestMapping(value = "revokefrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonRF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
	
		String addressFrom = jsonObject.get("addressFrom").getAsString();
		String address = jsonObject.get("address").getAsString();
		int permissions = jsonObject.get("permissions").getAsInt();
		
		String revokeFrom = null;
		
		try {
			revokeFrom = Service.revokeFrom(addressFrom, address, permissions);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new RevokeFromResBean(counter.incrementAndGet(), revokeFrom);
	}
	
}
