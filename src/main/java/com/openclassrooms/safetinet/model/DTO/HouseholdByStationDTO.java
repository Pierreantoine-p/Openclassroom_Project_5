package com.openclassrooms.safetinet.model.DTO;

import java.util.ArrayList;

public class HouseholdByStationDTO {
	
	private String fireStation;
	
	private String lastname;
	
	private String phone;
	
	private String age;
	
	private String firstname;
	
	private ArrayList<String> medications;
	
	private ArrayList<String> allergies;
	
	public String getFireStation() {
		return fireStation;
	}

	public void setFireStation(String fireStation) {
		this.fireStation = fireStation;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
