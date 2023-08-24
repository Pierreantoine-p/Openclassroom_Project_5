package com.openclassrooms.safetinet.model.DTO;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonByStationDTO {

	private String firstName;
	
	private String lastName;

	private String address;
	
	private String phone;
		
	@JsonCreator
	public PersonByStationDTO(@JsonProperty("address")String address,@JsonProperty("firstName") String firstName,@JsonProperty("lastName") String lastName,@JsonProperty("phone") String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	

	
}
