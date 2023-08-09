package com.openclassrooms.safetinet.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FireStations {
	
	
	private String address;
	
	private String station;


	@JsonCreator
	public FireStations(@JsonProperty("address")String address,@JsonProperty("station") String station) {
		this.address = address;
		this.station = station;	
	}
	
}
