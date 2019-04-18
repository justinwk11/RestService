package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

public class GetTransactionResBean extends CommonResponseBean implements Serializable {
	private final Double amount;
	private final Double fee;
	private final Long confirmations;
	private final String blockhash;
	private final Long blockindex;
	private final Long blocktime;
	private final String txid;
	private final Long time;
	private final Long timereceived;
	private final List<String> getWalletconflicts;
	
	public GetTransactionResBean(long id, Double amount, Double fee, Long confirmations, String blockhash,
			Long blockindex, Long blocktime, String txid, Long time, Long timereceived, List<String> getWalletconflicts) {
		super(id);
		this.amount = amount;
		this.fee = fee;
		this.confirmations = confirmations;
		this.blockhash = blockhash;
		this.blockindex = blockindex;
		this.blocktime = blocktime;
		this.txid = txid;
		this.time = time;
		this.timereceived = timereceived;
		this.getWalletconflicts = getWalletconflicts;
	}
	/**
	 * @return the balance
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @return the balance
	 */
	public Double getFee() {
		return fee;
	}
	/**
	 * @return the confirmations
	 */
	public Long getConfirmations() {
		return confirmations;
	}
	/**
	 * @return the blockhash
	 */
	public String getBlockhash() {
		return blockhash;
	}
	/**
	 * @return the blockindex
	 */
	public Long getBlockindex() {
		return blockindex;
	}
	/**
	 * @return the blocktime
	 */
	public Long getBlocktime() {
		return blocktime;
	}
	/**
	 * @return the txid
	 */
	public String getTxid() {
		return txid;
	}
	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}
	/**
	 * @return the timereceived
	 */
	public Long getTimereceived() {
		return timereceived;
	}
	/**
	 * @return the timereceived
	 */
	public List<String> getGetWalletconflicts() {
		return getWalletconflicts;
	}
}
