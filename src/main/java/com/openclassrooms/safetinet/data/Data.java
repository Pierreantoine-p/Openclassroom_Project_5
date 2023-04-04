package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetinet.model.*;

import lombok.Getter;
import lombok.Setter;

public class Data {
	
	private List<Person> persons = new ArrayList<Person>();
	private List<MedicalRecords> medicalrecords = new ArrayList<MedicalRecords>();
	private List<FireStations> firestations = new ArrayList<FireStations>();

	
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}


	public List<MedicalRecords> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}


	public List<FireStations> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<FireStations> firestations) {
		this.firestations = firestations;
	}
	
}
