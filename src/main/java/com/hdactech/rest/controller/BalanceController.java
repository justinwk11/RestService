package com.hdactech.rest.controller;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hdactech.command.HdacException;
import com.hdactech.object.BalanceAssetGeneral;
import com.hdactech.rest.GetTotalBalances;
import com.hdactech.rest.model.CommonResponseBean;
import com.hdactech.rest.model.ICommonResponseBean;
import com.hdactech.rest.service.BalanceService;

@RestController
@RequestMapping("/balance")
public class BalanceController {

	@Autowired
	BalanceService Service;
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value = "/gettotalbalances", method = RequestMethod.POST)
	@ResponseBody
	public ICommonResponseBean jsonGTB() {

		List<BalanceAssetGeneral> listBalanceAsset = null;
		
		try {
			listBalanceAsset = Service.getTotalBalances();
		} catch (HdacException e) {
			return new CommonResponseBean(counter.incrementAndGet(), -500, e.getMessage());
		}
		return new GetTotalBalances(counter.incrementAndGet(), listBalanceAsset);
	}
}