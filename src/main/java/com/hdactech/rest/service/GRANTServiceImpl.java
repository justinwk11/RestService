package com.hdactech.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hdactech.command.GrantCommand;
import com.hdactech.command.HdacCommand;
import com.hdactech.command.HdacException;
import com.hdactech.object.Permission;
import com.hdactech.rest.RestServiveApplication;

@Service
public class GRANTServiceImpl implements GRANTService {

	static GrantCommand mRpcCmd = null;

	static {
		mRpcCmd = (new HdacCommand(RestServiveApplication.FULL_NODE_IP, RestServiveApplication.FULL_NODE_PORT, RestServiveApplication.RPC_USER, RestServiveApplication.RPC_PW)).getGrantCommand();
	}

	@Override
	public String grant(String address, String permission) throws HdacException {

		int i = 0;
		
		String persmission[];

		switch ("spliteStr[i]") {
		case "connect":
			i += 1;
			break;
		case "send":
			i += 2;
			break;
		case "receive":
			i += 4;
			break;
		case "issue":
			i += 8;
			break;
		case "mine":
			i += 16;
			break;
		case "activate":
			i += 32;
			break;
		case "admin":
			i += 64;
			break;
		case "create":
			i += 128;
		default:
			break;
		}
		
		// 전체 소문자로
		// return mRpcCmd.grant(address, GrantCommand.SEND | GrantCommand.RECEIVE);
		return mRpcCmd.grant(address, permission);
		/*
		 * for test, createt grant(String address, String permission) at GrantCommand
		 */
	}
	@Override
	public String grantFrom(String addressFrom, String address, int permissions) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.grantFrom(addressFrom, address, permissions);
	}

	@Override
	public List<Permission> listPermissions(byte permissions, String address, boolean verbose) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.listPermissions(permissions, address, verbose);
	}
	@Override
	public String grantWithData(String address, int permissions, String dataHex) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.grantWithData(address, permissions, dataHex);
	}
	@Override
	public String grantWithDataFrom(String fromAddress, String address, int permissions, String dataHex)
			throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.grantWithDataFrom(fromAddress, address, permissions, dataHex);
	}
	@Override
	public String revoke(String address, int permissions) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.revoke(address, permissions);
	}
	@Override
	public String revokeFrom(String addressFrom, String address, int permissions) throws HdacException {
		// TODO Auto-generated method stub
		return mRpcCmd.revokeFrom(addressFrom, address, permissions);
	}
}
