package com.hdactech.rest.service;

import java.util.List;

import com.hdactech.command.HdacException;
import com.hdactech.object.Permission;

public interface GRANTService {
	
	public abstract String grant(String address, String permission) throws HdacException;
	
	public abstract String grantFrom(String addressFrom, String address, int permissions) throws HdacException;
	
	public abstract List<Permission> listPermissions(byte permissions, String address, boolean verbose) throws HdacException;
	
	public abstract String grantWithData(String address, int permissions, String dataHex) throws HdacException;
	
	public abstract String grantWithDataFrom(String fromAddress, String address, int permissions, String dataHex) throws HdacException;
	
	public abstract String revoke(String address, int permissions) throws HdacException;
	
	public abstract String revokeFrom(String addressFrom, String address, int permissions) throws HdacException;

}
