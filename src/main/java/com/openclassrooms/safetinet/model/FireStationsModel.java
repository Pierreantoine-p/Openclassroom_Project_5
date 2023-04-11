package com.openclassrooms.safetinet.model;

import lombok.Getter;
import lombok.Setter;

public class FireStationsModel {
	
	/*
	private String address;
	
	private String station;
	*/
	
	private String address;
	private String station;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	

	
}
