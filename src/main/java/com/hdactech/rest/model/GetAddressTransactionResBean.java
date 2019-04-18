package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.AssetWalletTransaction;
import com.hdactech.object.BalanceWalletTransaction;
import com.hdactech.object.PermissionDetailed;

public class GetAddressTransactionResBean extends CommonResponseBean implements Serializable {
	private final BalanceWalletTransaction balnace;
	private final List<String> myAddress;
	private final List<String> addresses;
	private final List<PermissionDetailed> permissions;
	private final AssetWalletTransaction issue;
	private final List<String> data;
	private final Long confirmations;
	private final String blockhash;
	private final Long blockindex;
	private final String txid;
	private final Long time;
	private final Long timereceived;
	public GetAddressTransactionResBean(long id, BalanceWalletTransaction balnace, List<String> myAddress,
			List<String> addresses, List<PermissionDetailed> permissions, AssetWalletTransaction issue,
			List<String> data, Long confirmations, String blockhash, Long blockindex, String txid, Long time,
			Long timereceived) {
		super(id);
		this.balnace = balnace;
		this.myAddress = myAddress;
		this.addresses = addresses;
		this.permissions = permissions;
		this.issue = issue;
		this.data = data;
		this.confirmations = confirmations;
		this.blockhash = blockhash;
		this.blockindex = blockindex;
		this.txid = txid;
		this.time = time;
		this.timereceived = timereceived;
	}
	public BalanceWalletTransaction getBalnace() {
		return balnace;
	}
	public List<String> getMyAddress() {
		return myAddress;
	}
	public List<String> getAddresses() {
		return addresses;
	}
	public List<PermissionDetailed> getPermissions() {
		return permissions;
	}
	public AssetWalletTransaction getIssue() {
		return issue;
	}
	public List<String> getData() {
		return data;
	}
	public Long getConfirmations() {
		return confirmations;
	}
	public String getBlockhash() {
		return blockhash;
	}
	public Long getBlockindex() {
		return blockindex;
	}
	public String getTxid() {
		return txid;
	}
	public Long getTime() {
		return time;
	}
	public Long getTimereceived() {
		return timereceived;
	}
}


