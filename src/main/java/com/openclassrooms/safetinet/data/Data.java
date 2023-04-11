package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetinet.model.*;

import lombok.Getter;
import lombok.Setter;

public class Data {
	
	private static List<Person> persons = new ArrayList<Person>();
	private static List<MedicalRecordsModel> medicalrecords = new ArrayList<MedicalRecordsModel>();
	private static List<FireStationsModel> firestations = new ArrayList<FireStationsModel>();

	
	public static List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}


	public static List<MedicalRecordsModel> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<MedicalRecordsModel> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}


	public static List<FireStationsModel> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<FireStationsModel> firestations) {
		this.firestations = firestations;
	}
	
}
