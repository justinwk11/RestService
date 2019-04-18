package com.hdactech.rest.model;

import java.io.Serializable;

public class HelpResBean extends CommonResponseBean implements Serializable {

	private final String help;
	
	public HelpResBean(long id, String help) {
		super(id);
		this.help = help;
		// TODO Auto-generated constructor stub
	}

	public String getHelp() {
		return help;
	}
}
