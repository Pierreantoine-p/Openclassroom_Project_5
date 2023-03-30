package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetinet.model.DataFireStations;
import com.openclassrooms.safetinet.model.DataMedicalRecords;
import com.openclassrooms.safetinet.model.DataPersons;

public class Data {
	
	public List<DataPersons> getPersons() {
		return this.persons;
	}
	public void setPersons(List<DataPersons> persons) {
		this.persons = persons;
	}
	public List<DataMedicalRecords> getMedicalRecords() {
		return this.medicalrecords;
	}
	public void setMedicalRecords(List<DataMedicalRecords> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
	public List<DataFireStations> getFireStations() {
		return this.firestations;
	}
	public void setFireStations(List<DataFireStations> firestations) {
		this.firestations = firestations;
	}
	
	
	private List<DataPersons> persons = new ArrayList<DataPersons>();
	private List<DataMedicalRecords> medicalrecords = new ArrayList<DataMedicalRecords>();
	private List<DataFireStations> firestations = new ArrayList<DataFireStations>();
	
}
