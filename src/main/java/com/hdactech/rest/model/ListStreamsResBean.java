package com.hdactech.rest.model;

import java.io.Serializable;
import java.util.List;

import com.hdactech.object.Stream;

public class ListStreamsResBean extends CommonResponseBean implements Serializable {
	
	private List<Stream> listStreams;

	public ListStreamsResBean(long id, List<Stream> listStreams) {
		super(id);
		this.listStreams = listStreams;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param listStreams the listStreams to set
	 */
	public void setListStreams(List<Stream> listStreams) {
		this.listStreams = listStreams;
	}

	/**
	 * @return the listStreams
	 */
	public List<Stream> getListStreams() {
		return listStreams;
	}
}
