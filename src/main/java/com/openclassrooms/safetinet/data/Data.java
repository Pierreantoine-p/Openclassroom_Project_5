package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetinet.model.*;

import lombok.Getter;
import lombok.Setter;

public class Data {
	
	private static List<Person> persons = new ArrayList<Person>();
	private static List<MedicalRecords> medicalrecords = new ArrayList<MedicalRecords>();
	private static List<FireStations> firestations = new ArrayList<FireStations>();

	
	public static List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}


	public static List<MedicalRecords> getMedicalRecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}


	public static List<FireStations> getFireStations() {
		return firestations;
	}

	public void setFirestations(List<FireStations> firestations) {
		this.firestations = firestations;
	}
	
}
