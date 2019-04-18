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
import com.hdactech.object.Address;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.object.KeyPairs;
import com.hdactech.object.MultiBalance;
import com.hdactech.object.MultiSigAddress;
import com.hdactech.rest.model.AddMultiSigAddressResBean;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.CreateKeyPairsResBean;
import com.hdactech.rest.model.CreateMultiSigResBean;
import com.hdactech.rest.model.GetAddressBalancesResBean;
import com.hdactech.rest.model.GetAddressesResBean;
import com.hdactech.rest.model.GetMultiBalancesResBean;
import com.hdactech.rest.model.GetNewAddressResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.ImportAddressResBean;
import com.hdactech.rest.model.ListAddressesResBean;
import com.hdactech.rest.model.ValidateAddressResBean;
import com.hdactech.rest.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService Service;

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/addmultisigaddress", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonAMSA(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		int nrequired = jsonObject.get("nrequired").getAsInt();
		JsonArray jsonKeys = jsonObject.get("keys").getAsJsonArray();
		
		String keys[] = new String[jsonKeys.size()];
		
		int cnt = 0;
		for(JsonElement jsonKey : jsonKeys) {
			keys[cnt++] = jsonKey.getAsString();
		}
		
		MultiSigAddress addMultiSigAddress = null;
		try {
			addMultiSigAddress = Service.addMultiSigAddress(nrequired, keys);
			
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new AddMultiSigAddressResBean(counter.incrementAndGet(), addMultiSigAddress);
	}
	
	@RequestMapping(value = "/createkeypairs", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonCP() {
		List<KeyPairs> createKeyPairs = null;
		try {
			createKeyPairs = Service.createKeyPairs();
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new CreateKeyPairsResBean(counter.incrementAndGet(), createKeyPairs);
	}
	
	@RequestMapping(value = "/createmultisig", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonCMS(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		int nrequired = jsonObject.get("nrequired").getAsInt();
		JsonArray jsonKeys = jsonObject.get("keys").getAsJsonArray();
		
		String keys[] = new String[jsonKeys.size()];
		int cnt = 0;
		for(JsonElement jsonKey : jsonKeys) {
			keys[cnt++] = jsonKey.getAsString();
		}
		
		Address createmultisig = null;
		try {
			createmultisig = Service.createMultiSig(nrequired, keys);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new CreateMultiSigResBean(counter.incrementAndGet(), createmultisig);
	}
	
	@RequestMapping(value = "/getnewaddress", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGNA() {

		String getNewAddress = null;
		
		try {
			getNewAddress = Service.getNewAddress();
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetNewAddressResBean(counter.incrementAndGet(), getNewAddress);
	}
	
	@RequestMapping(value = "/importaddress", method = RequestMethod.POST)
	@ResponseBody

	public ICommonResponseBean jsonIA(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		String label = jsonObject.get("label").getAsString();
		boolean rescan = jsonObject.get("rescan").getAsBoolean();
		
		try {
			 Service.importAddress(address, label, rescan);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		
		return new ImportAddressResBean(counter.incrementAndGet());
	}
	
	@RequestMapping(value = "/getaddresses", method = RequestMethod.POST)
	@ResponseBody

	public ICommonResponseBean jsonGA() {
		
		List<String> getAddressesStringList = null;
		
		try {
			getAddressesStringList = Service.getAddresses();
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetAddressesResBean(counter.incrementAndGet(), getAddressesStringList);
	}
	
	@RequestMapping(value = "/validateaddress", method = RequestMethod.POST)
	@ResponseBody

	public ICommonResponseBean jsonVA(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String stringAddress = jsonObject.get("stringaddress").getAsString();
		
		Address validateAddress = null;
		try {
			validateAddress = Service.validateAddress(stringAddress);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ValidateAddressResBean(counter.incrementAndGet(), validateAddress);
	}
	
	@RequestMapping(value = "/getaddressbalances", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonAB(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		int minconf = jsonObject.get("minconf").getAsInt();
		boolean includelocked = jsonObject.get("includelocked").getAsBoolean();
		
		List<BalanceAssetGeneral> getAddressBalances = null;
		
		try {
			getAddressBalances = Service.getAddressBalances(address, minconf,includelocked);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(),-500, e.getMessage());
		}
		return new GetAddressBalancesResBean(counter.incrementAndGet(), getAddressBalances);
	}
	
	@RequestMapping(value = "/getmultibalances", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGMB(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		JsonArray jsonAddresses = jsonObject.get("addresses").getAsJsonArray();
		String addresses[] = new String[jsonAddresses.size()];
		int n = 0;
		for(JsonElement jsonAddress : jsonAddresses) {
			addresses[n++] = jsonAddress.getAsString();
		}
		
		JsonArray jsonAssets = jsonObject.get("assets").getAsJsonArray();
		String assets[] = new String[jsonAssets.size()];
		int m = 0;
		for(JsonElement jsonAsset : jsonAssets) {
			assets[m++] = jsonAsset.getAsString();
		}
		
		 MultiBalance getMultiBalances = null;
		
		try {
			getMultiBalances = Service.getMultiBalances(addresses, assets);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(),-500, e.getMessage());
		}
		return new GetMultiBalancesResBean(counter.incrementAndGet(), getMultiBalances);
	}
	
	@RequestMapping(value = "/listaddresses", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLA(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		JsonArray jsonAddresses = null;
		boolean verbose = false;
		long count = 0;
		long start = 0;
		
		String addresses[] = null;
		
		if(jsonObject.has("addresses")) {
			jsonAddresses = jsonObject.get("addresses").getAsJsonArray();
			addresses = new String[jsonAddresses.size()];
			
			int n = 0;
			for(JsonElement jsonAddress : jsonAddresses) {
				addresses[n++] = jsonAddress.getAsString();
			}
		}
		
		if(jsonObject.has("verbose")) {
			verbose = jsonObject.get("verbose").getAsBoolean();
		}
		
		if(jsonObject.has("count")) {
			count = (long) jsonObject.get("count").getAsLong();
		}
		
		if(jsonObject.has("start")) {
			start = (long) jsonObject.get("start").getAsLong();
		}
		
		List<Object> listAddresses = null;
		
		try {
			listAddresses = Service.listAddresses(addresses, verbose, count, start);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(),-500, e.getMessage());
		}
		return new ListAddressesResBean(counter.incrementAndGet(), listAddresses);
	}
}