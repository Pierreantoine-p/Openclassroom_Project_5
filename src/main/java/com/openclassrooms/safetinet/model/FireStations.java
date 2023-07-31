package com.openclassrooms.safetinet.model;

import lombok.Data;

@Data
public class FireStations {
	
	
	private String address;
	
	private String station;

	public FireStations() {
		
	}
	
	public FireStations(String address, String station) {
		this.address = address;
		this.station = station;	
	}
	
}
