package com.openclassrooms.safetinet.model.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HouseholdByStationDTO {
	
	
	private String lastname;
	
	private String phone;
	
	private int age;
	
	private String firstname;
	
	private List<String> medications;
	
	private List<String> allergies;
	
	@JsonCreator
	public HouseholdByStationDTO (String firstname, String lastname, String phone, int age, List<String> medications,List<String> allergies) {
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;

	}

}
