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
import com.hdactech.object.PrevTx;
import com.hdactech.object.PreviousTx;
import com.hdactech.object.SignedTransactionRAW;
import com.hdactech.object.TransactionRAW;
import com.hdactech.object.queryobjects.RawParam;
import com.hdactech.object.queryobjects.TxIdVout;
import com.hdactech.rest.model.AppendRawDataResBean;
import com.hdactech.rest.model.AppendRawExchangeResBean;
import com.hdactech.rest.model.AppendRawMetaDataResBean;
import com.hdactech.rest.model.AppendRawTransactionResBean;
import com.hdactech.rest.model.AppendRawTransactionWithSendResBean;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.CompleteRawExchangeResBean;
import com.hdactech.rest.model.CreateRAWSendResBean;
import com.hdactech.rest.model.CreateRAWSendWithSendResBean;
import com.hdactech.rest.model.CreateRAWTxResBean;
import com.hdactech.rest.model.CreateRawExchangeResBean;
import com.hdactech.rest.model.DecodeRawExchangeResBean;
import com.hdactech.rest.model.DecodeRawTransactionResBean;
import com.hdactech.rest.model.DisableTransactionRAWResBean;
import com.hdactech.rest.model.GetRAWTransactionResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.model.PrepareLockUnspentFromResBean;
import com.hdactech.rest.model.PrepareLockUnspentResBean;
import com.hdactech.rest.model.SendTransactionRAWResBean;
import com.hdactech.rest.model.SignRAWTransactionResBean;
import com.hdactech.rest.service.RAWTransactionService;

@RestController
@RequestMapping("/raw")
public class RAWTransactionController {

	@Autowired
	RAWTransactionService Service;

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/createrawsendfrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonCSF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address;
		JsonArray jsonRawparams;
		JsonArray jsonDatas = null;
		String action = null;
		

		address = jsonObject.get("fromaddress").getAsString();
		jsonRawparams = jsonObject.get("addresses").getAsJsonArray();
		
		List<RawParam> rawParams = new ArrayList<>();
		Gson gson = new Gson();
		for (JsonElement jsonRawparam : jsonRawparams) {
			rawParams.add(gson.fromJson(jsonRawparam, RawParam.class));
		}

		String datas[] = null;
		
		if(jsonObject.has("data")) {
			jsonDatas = jsonObject.get("data").getAsJsonArray();
			
			datas = new String[jsonDatas.size()];
			int cnt = 0;
			for (JsonElement jsonData : jsonDatas) {
				datas[cnt++] = jsonData.getAsString();
			}
		}
			
		if(jsonObject.has("action")) {
			action = jsonObject.get("action").getAsString();
		}
		
		if ("send".equals(action)) {
			String signTransactionRAW = null;
			try {
				signTransactionRAW = Service.createRawSendFromWithSend(address, rawParams, datas);
			} catch (HdacException e) {
				return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
			}
			return new CreateRAWSendWithSendResBean(counter.incrementAndGet(), signTransactionRAW);
		}

		SignedTransactionRAW signTransactionRAW = null;

		try {
			signTransactionRAW = Service.createRawSendFrom(address, rawParams, datas, action);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new CreateRAWSendResBean(counter.incrementAndGet(), signTransactionRAW.getHex(),
				signTransactionRAW.isComplete());
	}

	@RequestMapping(value = "/createrawtx", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonCreateRawTx(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		JsonArray jsonTxs = jsonObject.get("transactions").getAsJsonArray();
		JsonObject jsonAddresses = jsonObject.get("addresses").getAsJsonObject();
		JsonArray jsonDatas = jsonObject.get("data").getAsJsonArray();
		String action = jsonObject.get("action").getAsString();

		List<TxIdVout> txList = new ArrayList();
		Gson gson = new Gson();
		for (JsonElement jsonTx : jsonTxs) {
			txList.add(gson.fromJson(jsonTx, TxIdVout.class));
		}
		String result;
		try {
			result = Service.createRawTransaction(txList, jsonAddresses, jsonDatas, action);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new CreateRAWTxResBean(counter.incrementAndGet(), result);
	}

	/*
	 * @RequestMapping(value = " /createrawtransaction" , method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public ICommonResponseBean jsonCRT(@RequestBody String payload)
	 * {
	 * 
	 * JsonParser parser = new JsonParser();
	 * 
	 * JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
	 * 
	 * JsonArray jsonInputs = jsonObject.get("inputs").getAsJsonArray(); JsonArray
	 * jsonAddressBalances = jsonObject.get("addressbalances").getAsJsonArray();
	 * JsonArray jsonDatas = jsonObject.get("data").getAsJsonArray();
	 */
	/*
	 * List<TxIdVout> inputs = new ArrayList<>(); Gson gson = new Gson(); for
	 * (JsonElement jsonInput : jsonInputs) {
	 * jsonInputs.add(gson.fromJson(jsonInput, TxIdVout.class)); }
	 */

	/*
	 * String signTransactionRAW = null;
	 * 
	 * try { signTransactionRAW = Service.createRawTransaction(inputs, addresses,
	 * datas); } catch (HdacException e) { return new
	 * CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage()); } return
	 * new CreateRAWSendWithSendResBean(counter.incrementAndGet(),
	 * signTransactionRAW);
	 * 
	 * }
	 */
//	@RequestMapping(value = "/signrawtransaction", method = RequestMethod.POST)
//	@ResponseBody
//	public ICommonResponseBean jsonST(@RequestBody String payload) {
//
//		JsonParser parser = new JsonParser();
//
//		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
//		String hexString = jsonObject.get("txhex").getAsString();
//
//		SignedTransactionRAW signTransactionRAW = null;
//
//		try {
//			signTransactionRAW = Service.signRawTransaction(hexString);
//		} catch (HdacException e) {
//			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
//		}
//
//		return new SignRAWTransactionResBean(counter.incrementAndGet(), signTransactionRAW.getHex(),
//				signTransactionRAW.isComplete());
//	}

	@RequestMapping(value = "/signrawtx", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonST2(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		String hexString = jsonObject.get("txhex").getAsString();
		JsonElement temp = jsonObject.get("prevtxs");

		// 풀노드 내부에 저장되어 있는 개인키로 자동서명하는 경우에는 txhex만 바디에 들어오므로 이것 만 체크해서 파라미터 1개 함수 호출.
		// hjs0317
		if (temp == null) {
			SignedTransactionRAW signTransactionRAW = null;
			try {
				signTransactionRAW = Service.signRawTransaction(hexString);
			} catch (HdacException e) {
				return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
			}
			return new SignRAWTransactionResBean(counter.incrementAndGet(), signTransactionRAW.getHex(),
					signTransactionRAW.isComplete());
		}

		// 이후 파라미터 4개 짜리 함수 호출. (외부 개인키로 서명하기 위한 용도) hjs0317
		JsonArray prevTxsJsonArray = temp.getAsJsonArray();
		JsonArray privateKeysJsonArray = jsonObject.get("privatekeys").getAsJsonArray();
		String sigHashType = jsonObject.get("sighashtype").getAsString();

		List<PrevTx> prevTxs = new ArrayList<PrevTx>();
		Gson gson = new Gson();
		for (JsonElement jsonPrevTx : prevTxsJsonArray) {
			prevTxs.add(gson.fromJson(jsonPrevTx, PrevTx.class));
		}

		List<String> privateKeys = new ArrayList<String>();
		for (JsonElement jsonPrivKey : privateKeysJsonArray) {
			privateKeys.add(jsonPrivKey.getAsString());
		}

		SignedTransactionRAW signTransactionRAW = null;

		try {
			signTransactionRAW = Service.signRawTransaction(hexString, prevTxs, privateKeys, sigHashType);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new SignRAWTransactionResBean(counter.incrementAndGet(), signTransactionRAW.getHex(),
				signTransactionRAW.isComplete());
	}

	@RequestMapping(value = "/decoderawtx", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonDRT(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String txHex = jsonObject.get("txhex").getAsString();

		TransactionRAW transactionRAW = null;

		try {
			transactionRAW = Service.decoderawTransaction(txHex);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new DecodeRawTransactionResBean(counter.incrementAndGet(), transactionRAW.getTxid(),
				transactionRAW.getVersion(), transactionRAW.getLocktime(), transactionRAW.getVin(),
				transactionRAW.getVout(), transactionRAW.getIssue(), transactionRAW.getData());
	}

	/**
	 * Created by Justin
	 * 
	 * @value decoderawexchange
	 * @return
	 */
	@RequestMapping(value = "/decoderawexchange", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonDRE(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String txHex = jsonObject.get("txhex").getAsString();
		boolean verbose = jsonObject.get("verbose").getAsBoolean();

		Object transactionRAW = null;

		try {
			transactionRAW = Service.decodeRawExchange(txHex, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new DecodeRawExchangeResBean(counter.incrementAndGet(), transactionRAW);
	}

	@RequestMapping(value = "/sendrawtx", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonSRT(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String txHex = jsonObject.get("txhex").getAsString();

		Object sendTransactionRAW = null;

		try {
			sendTransactionRAW = Service.sendRawTransaction(txHex);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new SendTransactionRAWResBean(counter.incrementAndGet(), sendTransactionRAW.toString());
	}

	@RequestMapping(value = "/appendrawdata", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonARD(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String txHex = jsonObject.get("txhex").getAsString();
		String dataHex = null;
		JsonObject issueDetails;

		String appendRawData = null;

		if (jsonObject.get("datahex") != null) {
			dataHex = jsonObject.get("datahex").getAsString();

			try {
				appendRawData = Service.appendRawData(txHex, dataHex);
			} catch (HdacException e) {
				return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
			}

			return new AppendRawDataResBean(counter.incrementAndGet(), appendRawData.toString());

		} else {
			issueDetails = jsonObject.get("issueDetails").getAsJsonObject();
			
			try {
				appendRawData = Service.appendRawData(txHex, issueDetails);
			} catch (HdacException e) {
				return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
			}

			return new AppendRawDataResBean(counter.incrementAndGet(), appendRawData.toString());
		}

		
	}

	@RequestMapping(value = "/appendrawmetadata", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonARMD(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String txHex = jsonObject.get("txhex").getAsString();
		String dataHex = jsonObject.get("datahex").getAsString();

		String appendRawMetaData = null;

		try {
			appendRawMetaData = Service.appendRawMetaData(txHex, dataHex);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new AppendRawMetaDataResBean(counter.incrementAndGet(), appendRawMetaData.toString());
	}

	/**
	 * Patched by Justin
	 * 
	 * @param payload
	 * @return
	 */
	@RequestMapping(value = "/getrawtx", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGRT(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String txid = jsonObject.get("txid").getAsString();
		int verbose = jsonObject.get("verbose").getAsInt();

		Object getRawTransaction = null;

		try {
			getRawTransaction = Service.getRawTransaction(txid, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new GetRAWTransactionResBean(counter.incrementAndGet(), getRawTransaction);
	}

	@RequestMapping(value = "/disablerawtx", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonDART(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String txHex = jsonObject.get("txhex").getAsString();

		Object sendTransactionRAW = null;

		try {
			sendTransactionRAW = Service.disableRawTransaction(txHex);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new DisableTransactionRAWResBean(counter.incrementAndGet(), sendTransactionRAW.toString());
	}

	@RequestMapping(value = "/createrawexchange", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonCRE(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		// Object AssetParamsObjcet = new Object();

		String txid = jsonObject.get("txid").getAsString();
		int vout = jsonObject.get("vout").getAsInt();
		JsonObject jsonAssets = jsonObject.get("assets").getAsJsonObject();

		String createRawExchange = null;

		try {
			createRawExchange = Service.createRawExchange(txid, vout, jsonAssets);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new CreateRawExchangeResBean(counter.incrementAndGet(), createRawExchange);
	}

	@RequestMapping(value = "/completerawexchange", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonCORE(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String hex = jsonObject.get("hex").getAsString();
		String txid = jsonObject.get("txid").getAsString();
		int vout = jsonObject.get("vout").getAsInt();
		JsonObject assets = jsonObject.get("assets").getAsJsonObject();

		String completeRawExchange = null;

		try {
			completeRawExchange = Service.completeRawExchange(hex, txid, vout, assets);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new CompleteRawExchangeResBean(counter.incrementAndGet(), completeRawExchange);
	}

	@RequestMapping(value = "/preparelockunspent", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonPLU(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		JsonObject jsonAssets = jsonObject.get("assets").getAsJsonObject();
		boolean lock = jsonObject.get("lock").getAsBoolean();
		
		TxIdVout prepareLockUnspent = null;

		try {
			prepareLockUnspent = Service.prepareLockUnspent(jsonAssets, lock);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new PrepareLockUnspentResBean(counter.incrementAndGet(), prepareLockUnspent);
	}

	@RequestMapping(value = "/preparelockunspentfrom", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonPLUF(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String fromAddress = jsonObject.get("fromaddress").getAsString();
		JsonObject jsonAssets = jsonObject.get("assets").getAsJsonObject();
		boolean lock = jsonObject.get("lock").getAsBoolean();

		TxIdVout prepareLockUnspentFrom = null;

		try {
			prepareLockUnspentFrom = Service.prepareLockUnspentFrom(fromAddress, jsonAssets, lock);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new PrepareLockUnspentFromResBean(counter.incrementAndGet(), prepareLockUnspentFrom);
	}

	@RequestMapping(value = "/appendrawexchange", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonARE(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String hexString = jsonObject.get("hex").getAsString();
		String txid = jsonObject.get("txid").getAsString();
		int vout = jsonObject.get("vout").getAsInt();
		JsonObject jsonAssets = jsonObject.get("assets").getAsJsonObject();

		SignedTransactionRAW appendRawExchange = null;

		try {
			appendRawExchange = Service.appendRawExchange(hexString, txid, vout, jsonAssets);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new AppendRawExchangeResBean(counter.incrementAndGet(), appendRawExchange);
	}

	@RequestMapping(value = "/appendrawtx", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonART(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		String txHex = jsonObject.get("txhex").getAsString();
		JsonArray jsonPreviousTx = jsonObject.get("transactions").getAsJsonArray();
		JsonArray jsonRawparams = jsonObject.get("addresses").getAsJsonArray();
		JsonArray jsonDatas = jsonObject.get("data").getAsJsonArray();
		String action = jsonObject.get("action").getAsString();

		List<PreviousTx> previousTx = new ArrayList<>();
		Gson gson = new Gson();
		for (JsonElement jsonTx : jsonPreviousTx) {
			previousTx.add(gson.fromJson(jsonTx, PreviousTx.class));
		}

		List<RawParam> rawParams = new ArrayList<>();
		Gson gsonRaw = new Gson();
		for (JsonElement jsonRawparam : jsonRawparams) {
			rawParams.add(gsonRaw.fromJson(jsonRawparam, RawParam.class));
		}

		String datas[] = new String[jsonDatas.size()];
		int cnt = 0;
		for (JsonElement jsonData : jsonDatas) {
			datas[cnt++] = jsonData.getAsString();
		}

		if (action.equals("") || action.equals("lock") || action.equals("send")) {

			String appendRawTransaction = null;

			try {
				appendRawTransaction = Service.appendRawTransactionWithSend(txHex, previousTx, rawParams, datas,
						appendRawTransaction);
			} catch (HdacException e) {
				return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
			}

			return new AppendRawTransactionWithSendResBean(counter.incrementAndGet(), appendRawTransaction);
		}

		SignedTransactionRAW appendRawTransaction = null;

		try {
			appendRawTransaction = Service.appendRawTransaction(txHex, previousTx, rawParams, datas, action);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}

		return new AppendRawTransactionResBean(counter.incrementAndGet(), appendRawTransaction);
	}

}
