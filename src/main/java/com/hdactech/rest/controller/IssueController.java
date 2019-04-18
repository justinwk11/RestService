package com.hdactech.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hdactech.command.HdacException;
import com.hdactech.object.BalanceAsset;
import com.hdactech.object.UnspentList;
import com.hdactech.object.queryobjects.AssetParams;
import com.hdactech.object.queryobjects.CustomParamString;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.GetAssetBalancesResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.IssueFromResBean;
import com.hdactech.rest.model.IssueMoreFromResBean;
import com.hdactech.rest.model.IssueMoreResBean;
import com.hdactech.rest.model.IssueResBean;
import com.hdactech.rest.model.ListUnspentResBean;
import com.hdactech.rest.model.SendAssetFromResBean;
import com.hdactech.rest.model.SendAssetToAddressResBean;
import com.hdactech.rest.model.listAssetsResBean;
import com.hdactech.rest.service.IssueService;

@RestController
@RequestMapping("/issue")
public class IssueController {

	@Autowired
	IssueService Service;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/issue", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonI(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		JsonObject AssetParamsJson = new JsonObject();
		
		String address = jsonObject.get("address").getAsString();
		AssetParamsJson = jsonObject.get("assets").getAsJsonObject();
		AssetParams assets = new AssetParams(AssetParamsJson.get("name").getAsString(), AssetParamsJson.get("open").getAsBoolean());
		
		float quantity = jsonObject.get("quantity").getAsFloat();
		float unit = jsonObject.get("unit").getAsFloat();
		float amount = jsonObject.get("amount").getAsFloat();
		JsonElement customFieldsJElement = jsonObject.get("customFields");
		
		JsonArray customFieldsJsonArray = customFieldsJElement.getAsJsonArray();
		List<CustomParamString> customFields = new ArrayList<CustomParamString>();
		
		Gson gson = new Gson();
		for (JsonElement jsonCustomFields : customFieldsJsonArray) {
			customFields.add(gson.fromJson(jsonCustomFields, CustomParamString.class));
		}
		
		String issue = null;
		
		try {
			issue = Service.issue(address, assets, quantity, unit, amount, customFields);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new IssueResBean(counter.incrementAndGet(), issue);
	}
	
	@RequestMapping(value = "/issuefrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonIF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		JsonObject AssetParamsJson = new JsonObject();
		
		String fromAddress = jsonObject.get("fromaddress").getAsString();
		String toAddress = jsonObject.get("toaddress").getAsString();
		AssetParamsJson = jsonObject.get("assets").getAsJsonObject();
		AssetParams assets = new AssetParams(AssetParamsJson.get("name").getAsString(), AssetParamsJson.get("open").getAsBoolean());
		
		float quantity = jsonObject.get("quantity").getAsFloat();
		float unit = jsonObject.get("unit").getAsFloat();
		float amount = jsonObject.get("amount").getAsFloat();
		JsonElement customFieldsJElement = jsonObject.get("customFields");
		
		JsonArray customFieldsJsonArray = customFieldsJElement.getAsJsonArray();
		List<CustomParamString> customFields = new ArrayList<CustomParamString>();
		
		Gson gson = new Gson();
		for (JsonElement jsonCustomFields : customFieldsJsonArray) {
			customFields.add(gson.fromJson(jsonCustomFields, CustomParamString.class));
		}
		
		String issueFrom = null;
		
		try {
			issueFrom = Service.issueFrom(fromAddress, toAddress, assets, quantity, unit, amount, customFields);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new IssueFromResBean(counter.incrementAndGet(), issueFrom);
	}
	
	@RequestMapping(value = "/issuemore", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonIM(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		String assetName = jsonObject.get("assetname").getAsString();
		int quantity = jsonObject.get("quantity").getAsInt();
		
		String issueMore = null;
		
		try {
			issueMore = Service.issueMore(address, assetName, quantity);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new IssueMoreResBean(counter.incrementAndGet(), issueMore);
	}
	
	@RequestMapping(value = "/issuemorefrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonIMF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String fromAddress = jsonObject.get("fromaddress").getAsString();
		String toAddress = jsonObject.get("toaddress").getAsString();
		String assetName = jsonObject.get("assetname").getAsString();
		int quantity = jsonObject.get("quantity").getAsInt();
		
		String issuemorefrom = null;
		
		try {
			issuemorefrom = Service.issueMoreFrom(fromAddress, toAddress, assetName, quantity);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new IssueMoreFromResBean(counter.incrementAndGet(), issuemorefrom);
	}

	@RequestMapping(value = "/listassets", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLA(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String asset = jsonObject.get("asset").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		
		List<BalanceAsset> listAssets = null;
		
		try {
			listAssets = Service.listAssets(asset, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new listAssetsResBean(counter.incrementAndGet(), listAssets);
	}
	
	@RequestMapping(value = "/getassetbalances", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGAB() {
		
		List<BalanceAsset> listBalanceAsset = null;
		
		try {
			listBalanceAsset = Service.getAssetBalances();
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetAssetBalancesResBean(counter.incrementAndGet(), listBalanceAsset);
	}
	
	@RequestMapping(value = "/sendassettoaddress", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonSATA(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		String assetName = jsonObject.get("assetName").getAsString();
		float quantity = jsonObject.get("quantity").getAsFloat();

		String sendAssetToAddress = null;
		
		try {
			sendAssetToAddress = Service.sendAssetToAddress(address, assetName, quantity);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new SendAssetToAddressResBean(counter.incrementAndGet(), sendAssetToAddress);
	}
	
	@RequestMapping(value = "/sendassetfrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonSAF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String fromAddress = jsonObject.get("fromAddress").getAsString();
		String toAddress = jsonObject.get("toAddress").getAsString();
		String assetName = jsonObject.get("assetName").getAsString();
		float quantity = jsonObject.get("quantity").getAsFloat();

		String sendAssetFrom = null;
		
		try {
			sendAssetFrom = Service.sendAssetFrom(fromAddress, toAddress, assetName, quantity);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new SendAssetFromResBean(counter.incrementAndGet(), sendAssetFrom);
	}
	
	@RequestMapping(value = "/listunspent", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLU(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		int minconf = jsonObject.get("minconf").getAsInt();
		int maxconf = jsonObject.get("maxconf").getAsInt();
		JsonArray jsonAddresses = jsonObject.get("addresses").getAsJsonArray();
		String addresses[] = new String[jsonAddresses.size()];
		int n = 0;
		for(JsonElement jsonAddress : jsonAddresses) {
			addresses[n++] = jsonAddress.getAsString();
		}
		
		List<UnspentList> listUnspent = null;
		
		try {
			listUnspent = Service.listUnspent(minconf, maxconf, addresses);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListUnspentResBean(counter.incrementAndGet(), listUnspent);
	}
}
