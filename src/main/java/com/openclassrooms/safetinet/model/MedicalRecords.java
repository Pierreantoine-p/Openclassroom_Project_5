 package com.openclassrooms.safetinet.model;

import java.util.ArrayList;
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
	private ArrayList<String> medications;
	private ArrayList<String> allergies;
	
	
	

	@JsonCreator
	public MedicalRecords(@JsonProperty("firstName")String firstName,@JsonProperty("lastName") String lastName,@JsonProperty("birthdate") String birthdate,@JsonProperty("medications") ArrayList<String> medications,@JsonProperty("allergies") ArrayList<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;	
		this.birthdate = birthdate;	
		this.medications = medications;	
		this.allergies = allergies;	
	}
	
	
}
