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
import com.hdactech.object.TransactionWallet;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.GetAddressTransactionResBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	TransactionService Service;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/getaddresstx", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGAT(@RequestBody String payload) {
		
		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();
		
		String address = jsonObject.get("address").getAsString();
		String txId = jsonObject.get("txid").getAsString();
		Boolean verbose = jsonObject.get("verbose").getAsBoolean();
		
		TransactionWallet transactionWallet = null;
		
		try {
			transactionWallet = Service.getAddressTransaction(address, txId, verbose);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(),-500, e.getMessage());
		}
		return new GetAddressTransactionResBean(counter.incrementAndGet(), transactionWallet.getBalance(), transactionWallet.getMyaddresses(),
				transactionWallet.getAddresses(), transactionWallet.getPermissions(),transactionWallet.getIssue(), transactionWallet.getData(),
				transactionWallet.getConfirmations(),transactionWallet.getBlockhash(),transactionWallet.getBlockindex(),transactionWallet.getTxid(),
				transactionWallet.getTime(),transactionWallet.getTimereceived());
	}
	
	/*@RequestMapping(value = "/preparelockunspent", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonPLU(@RequestBody String payload) {

		JsonParser parser = new JsonParser();

		JsonObject jsonObject = parser.parse(payload).getAsJsonObject();

		JsonObject jsonAssetquantities = jsonObject.get("assetquantities").getAsJsonObject();
		boolean lock = jsonObject.get("lock").getAsBoolean();

		String result;
		try {
			result = Service.prepareLockUnspent(jsonAssetquantities, lock);
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new CreateRAWTxResBean(counter.incrementAndGet(), result);
	}*/
}