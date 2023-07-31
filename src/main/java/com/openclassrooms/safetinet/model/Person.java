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
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}


