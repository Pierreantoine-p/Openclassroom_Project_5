package com.openclassrooms.safetinet.model;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Person {
	

	private String firstName;
	
	private String lastName;

	private String address;

	private String city;
	
	private String zip;

	private String phone;
	
	private String email;

	
	
	@JsonCreator
	public Person( @JsonProperty("firstName")String firstName, @JsonProperty("lastName")String lastName,  @JsonProperty("address")String address,  @JsonProperty("city")String city, @JsonProperty("zip")String zip,@JsonProperty("phone")String phone, @JsonProperty("email")String email ) {
		this.firstName = firstName;
		this.lastName = lastName ;
		this.address = address ;
		this.city = city ;
		this.zip = zip ;
		this.phone = phone ;
		this.email = email ;
		
	}
	
	
}


