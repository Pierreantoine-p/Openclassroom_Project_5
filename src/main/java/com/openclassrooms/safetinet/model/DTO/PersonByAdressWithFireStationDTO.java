package com.openclassrooms.safetinet.model.DTO;

import java.util.ArrayList;

public class PersonByAdressWithFireStationDTO {

	private String fireStation;
	
	private String lastName;
	
	private String phone; 
	
	private String age;

	private ArrayList<String> medications;
	
	private ArrayList<String> allergies;
	
	public String getFireStation() {
		return fireStation;
	}

	public void setFireStation(String fireStation) {
		this.fireStation = fireStation;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String firstName) {
		this.lastName = firstName;
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
