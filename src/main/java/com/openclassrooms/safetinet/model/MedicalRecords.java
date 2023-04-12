package com.openclassrooms.safetinet.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

public class MedicalRecords {
	
	/*
	@Getter@Setter
	private String firstName;
	
	@Getter@Setter
	private String lastName;
	
	@Getter@Setter
	private String birthdate;
	
	@Getter@Setter
	private ArrayList<String> medications;
	
	@Getter@Setter
	private ArrayList<String> allergies;
	*/
	
	private String firstName;
	private String lastName;
	private String birthdate;
	private ArrayList<String> medications;
	private ArrayList<String> allergies;
	
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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public ArrayList<String> getMedications() {
		return medications;
	}

	public void setMedications(ArrayList<String> medications) {
		this.medications = medications;
	}

	public ArrayList<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}

	
	
}
