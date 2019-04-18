package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.AssetWalletTransaction;
import com.hdactech.object.TransactionRAWVin;
import com.hdactech.object.TransactionRAWVout;

public class DecodeRawTransactionResBean extends CommonResponseBean implements Serializable {
	private final String txid;
	private final int version;
	private final long locktime;
	private final List<TransactionRAWVin> vin;
	private final List<TransactionRAWVout> vout;
	AssetWalletTransaction issue;
	List<String> data;
	
	public DecodeRawTransactionResBean(long id, String txid, int version, long locktime, List<TransactionRAWVin> vin, List<TransactionRAWVout> vout, AssetWalletTransaction issue,
	List<String> data) {
		super(id);
		this.txid = txid;
		this.version = version;
		this.locktime = locktime;
		this.vin = vin;		
		this.vout = vout;
		this.issue = issue;
		this.data = data;
	}

	/**
	 * @return the vin
	 */
	
	public String getTxid() {
		return txid;
	}
	
	public int getVersion() {
		return version;
	}
	
	public long getLocktime() {
		return locktime;
	}
	
	public List<TransactionRAWVin> getVin() {
		return vin;
	}
	
	public List<TransactionRAWVout> getVout() {
		return vout;
	}

	public AssetWalletTransaction getIssue() {
		return issue;
	}

	public List<String> getData() {
		return data;
	}
}
