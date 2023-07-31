 package com.openclassrooms.safetinet.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class MedicalRecords {
	
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private ArrayList<String> medications;
	private ArrayList<String> allergies;
	
	public MedicalRecords() {
		
	}
	
	public MedicalRecords(String string, String string2, String string3, List<String> medications2,
			List<String> allergies2) {
		// TODO Auto-generated constructor stub
	}

	
	
	
}
