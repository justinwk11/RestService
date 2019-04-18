package com.hdactech.rest.model;

import java.io.Serializable;

public class HelpWithCmdResBean extends CommonResponseBean implements Serializable {
	
	private final String command;

	public HelpWithCmdResBean(long id, String command) {
		super(id);
		this.command = command;
		// TODO Auto-generated constructor stub
	}

	public String getCommand() {
		return command;
	}
}
