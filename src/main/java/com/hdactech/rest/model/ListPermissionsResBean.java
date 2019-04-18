package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.Permission;

public class ListPermissionsResBean extends CommonResponseBean implements Serializable{
	
	private final List<Permission> listPermissions;
	
	public ListPermissionsResBean(long id, List<Permission> listPermissions) {
		super(id);
		this.listPermissions = listPermissions;
		// TODO Auto-generated constructor stub
	}

	public List<Permission> getListPermissions() {
		return listPermissions;
	}
}
