 package com.openclassrooms.safetinet.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MedicalRecords {
	
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	
	
	

	@JsonCreator
	public MedicalRecords(@JsonProperty("firstName")String firstName,@JsonProperty("lastName") String lastName,@JsonProperty("birthdate") String birthdate,@JsonProperty("medications") List<String> medications,@JsonProperty("allergies") List<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;	
		this.birthdate = birthdate;	
		this.medications = medications;	
		this.allergies = allergies;	
	}

}
