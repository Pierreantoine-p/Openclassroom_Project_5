package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetinet.model.DataFireStations;
import com.openclassrooms.safetinet.model.DataMedicalRecords;
import com.openclassrooms.safetinet.model.DataPersons;

public class Data {
	
	public static List<DataPersons> getPersons() {
		return persons;
	}
	public static void setPersons(List<DataPersons> persons) {
		Data.persons = persons;
	}
	public static List<DataMedicalRecords> getMedicalRecords() {
		return medicalRecords;
	}
	public static void setMedicalRecords(List<DataMedicalRecords> medicalRecords) {
		Data.medicalRecords = medicalRecords;
	}
	public static List<DataFireStations> getFireStations() {
		return fireStations;
	}
	public static void setFireStations(List<DataFireStations> fireStations) {
		Data.fireStations = fireStations;
	}
	private static List<DataPersons> persons = new ArrayList<DataPersons>();
	private static List<DataMedicalRecords> medicalRecords = new ArrayList<DataMedicalRecords>();
	private static List<DataFireStations> fireStations = new ArrayList<DataFireStations>();
	
}
