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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hdactech.command.HdacException;
import com.hdactech.object.ListAddressTxsDetailed;
import com.hdactech.object.ListWalletTxs;
import com.hdactech.object.TransactionWallet;
import com.hdactech.object.TransactionWalletDetailed;
import com.hdactech.object.TxOut;
import com.hdactech.object.queryobjects.AssetQuantity;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.GetAssetTransactionResBean;
import com.hdactech.rest.model.GetWalletTransactionResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.ListAddressTransactionsResBean;
import com.hdactech.rest.model.ListWalletTransactionResBean;
import com.hdactech.rest.model.SendFromAddressResBean;
import com.hdactech.rest.model.SendResBean;
import com.hdactech.rest.model.SendWithMetaDataResBean;
import com.hdactech.rest.model.TransactionOutResBean;
import com.hdactech.rest.service.WalletTransactionService;

@RestController
@RequestMapping("/wallettransaction")
public class WalletTransactionController {

	@Autowired
	WalletTransactionService Service;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/sendwithmetadata", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonSWMD(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		JsonArray jsonAssets = jsonObject.get("assets").getAsJsonArray();
		String hexMetaData = jsonObject.get("hexmetadata").getAsString();
			
		List<AssetQuantity> assets = new ArrayList<>();
		Gson gson = new Gson();
		for (JsonElement jsonAsset : jsonAssets) {
			assets.add(gson.fromJson(jsonAsset, AssetQuantity.class));
		}
	
		String sendWithMetaData = null;
		
		try {
			sendWithMetaData = Service.sendWithMetaData(address, assets, hexMetaData);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new SendWithMetaDataResBean(counter.incrementAndGet(), sendWithMetaData);
	}
	
	@RequestMapping(value = "/sendwithdatafrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonSWDF(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String fromAddress = jsonObject.get("fromaddress").getAsString();
		String toAddress = jsonObject.get("toaddress").getAsString();
		String assetName = jsonObject.get("assetname").getAsString();
		Integer assetValue = jsonObject.get("assetvalue").getAsInt();
		String metadata = jsonObject.get("metadata").getAsString();
		
		String sendWithDataFrom = null;
		
		try {
			sendWithDataFrom = Service.sendWithDataFrom(fromAddress, toAddress, assetName, assetValue, metadata);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new SendWithMetaDataResBean(counter.incrementAndGet(), sendWithDataFrom);
	}
	
	@RequestMapping(value = "/listaddresstransactions", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLAT(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		long count = jsonObject.get("count").getAsLong();
		long skip = jsonObject.get("skip").getAsLong();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		
		List<ListAddressTxsDetailed> listAddressTransactions = null;
		
		try {
			listAddressTransactions = Service.listAddressTransactions(address, count, skip, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListAddressTransactionsResBean(counter.incrementAndGet(), listAddressTransactions);
	}
	
	@RequestMapping(value = "/listwallettransaction", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonLWT(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		long count = jsonObject.get("count").getAsLong();
		long skip = jsonObject.get("skip").getAsLong();
		boolean includeWatchonly = jsonObject.get("includewatchonly").getAsBoolean();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		
		List<ListWalletTxs> listWalletTransaction = null;
		
		try {
			listWalletTransaction = Service.listWalletTransaction(count, skip, includeWatchonly, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new ListWalletTransactionResBean(counter.incrementAndGet(), listWalletTransaction);
	}
	
	@RequestMapping(value = "/getwallettransaction", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGWT(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String txid = jsonObject.get("txid").getAsString();
		boolean includeWatchOnly = jsonObject.get("includewatchonly").getAsBoolean();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		
		TransactionWalletDetailed transactionWalletDetailed = null;
		
		try {
			transactionWalletDetailed = Service.getWalletTransaction(txid, includeWatchOnly, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetWalletTransactionResBean(counter.incrementAndGet(), transactionWalletDetailed);
	}
	
	@RequestMapping(value = "/getassettransaction", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGAT(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String assetidentifier = jsonObject.get("assetidentifier").getAsString();
		String txid = jsonObject.get("txid").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();
		
		TransactionWallet getAssetTransaction = null;
		
		try {
			getAssetTransaction = Service.getAssetTransaction(assetidentifier, txid, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetAssetTransactionResBean(counter.incrementAndGet(), getAssetTransaction);
	}
	
	@RequestMapping(value = "/gettransactionout", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGTO(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String txid = jsonObject.get("txid").getAsString();
		int n = jsonObject.get("n").getAsInt();
		boolean includemempool = jsonObject.get("includemempool").getAsBoolean();
		
		TxOut txOut = null;
		
		try {
			txOut = Service.getTxOut(txid, n, includemempool);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new TransactionOutResBean(counter.incrementAndGet(), txOut);
	}
	

	/*@RequestMapping(value = "/gettransaction", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGT(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String txId = jsonObject.get("txid").getAsString();
		Boolean includeWatchOnly = jsonObject.get("includewatchonly").getAsBoolean();
		
		Transaction transaction = null;
		
		try {
			transaction = Service.getTransaction(txId, includeWatchOnly);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(),-500, e.getMessage());
		}
		return new GetTransactionResBean(counter.incrementAndGet(), transaction.getAmount(), transaction.getFee(), transaction.getConfirmations(),
				transaction.getBlockhash(), transaction.getBlockindex(), transaction.getBlocktime(),
				transaction.getTxid(), transaction.getTime(), transaction.getTimereceived(),transaction.getWalletconflicts());
	}*/
	
	@RequestMapping(value = "/gettx", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public String jsonGT(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String txid = jsonObject.get("txid").getAsString();
		Boolean includeWatchOnly = jsonObject.get("includewatchonly").getAsBoolean();
		
		Object gettransaction = null;
		
		try {
			gettransaction = Service.getTransaction(txid, includeWatchOnly); //일정 부족으로 임시로 이렇게 고침. 완전 바이패스. hjs0317
		} catch (HdacException e) {
			return CommonResponseBean.createResponse(counter.incrementAndGet(), -500, e.getMessage(), null);
		}
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonResult = gson.toJson(gettransaction);
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonResultObject = (JsonObject) jsonParser.parse(jsonResult);
		return CommonResponseBean.createResponse(counter.incrementAndGet(), 0, null, jsonResultObject);
	}

	
	
	@RequestMapping(value = "/gettxout", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public String jsonGTO2(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String txid = jsonObject.get("txid").getAsString();
		int vout = jsonObject.get("vout").getAsInt();//why vout??? it's definetly n
		boolean includemempool = jsonObject.get("includemempool").getAsBoolean();
		
		Object result = null;
		
		try {
			result = Service.getTxOut(txid, vout, includemempool);
		} catch (HdacException e) {
			return CommonResponseBean.createResponse(counter.incrementAndGet(), -500, e.getMessage(), null);
		}
		String jsonResult = null;
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		jsonResult = gson.toJson(result);
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonResultObject = (JsonObject) jsonParser.parse(jsonResult);
		return CommonResponseBean.createResponse(counter.incrementAndGet(), 0, null, jsonResultObject);
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonS(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String fromAddress = jsonObject.get("address").getAsString();
		JsonElement assetsJElement = jsonObject.get("assets");
		
		JsonArray assetsJsonArray = assetsJElement.getAsJsonArray();
		List<AssetQuantity> assets = new ArrayList<AssetQuantity>();
		
		Gson gson = new Gson();
		for (JsonElement jsonAssets : assetsJsonArray) {
			assets.add(gson.fromJson(jsonAssets, AssetQuantity.class));
		}
		
		String stringSend = null;
		
		try {
			stringSend = Service.send(fromAddress, assets);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new SendResBean(counter.incrementAndGet(), stringSend);
	}
	
	@RequestMapping(value = "/sendfromaddress", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonSFA(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();
		
		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String fromAddress = jsonObject.get("fromaddress").getAsString();
		String toAddress = jsonObject.get("toaddress").getAsString();
		JsonElement assetsJElement = jsonObject.get("assets");
		
		JsonArray assetsJsonArray = assetsJElement.getAsJsonArray();
		List<AssetQuantity> assets = new ArrayList<AssetQuantity>();
		
		Gson gson = new Gson();
		for (JsonElement jsonAssets : assetsJsonArray) {
			assets.add(gson.fromJson(jsonAssets, AssetQuantity.class));
		}
		
		String sendFromAddress = null;
		
		try {
			sendFromAddress = Service.sendFromAddress(fromAddress, toAddress, assets);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new SendFromAddressResBean(counter.incrementAndGet(), sendFromAddress);
	}
	
}
