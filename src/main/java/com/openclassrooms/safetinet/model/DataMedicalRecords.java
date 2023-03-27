package com.openclassrooms.safetinet.model;

import java.util.ArrayList;

public class DataMedicalRecords {

	private String firstName;
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
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	private String lastName;
	private String birthdate;
	private ArrayList<String> medications;
	private String allergies;
	
}
