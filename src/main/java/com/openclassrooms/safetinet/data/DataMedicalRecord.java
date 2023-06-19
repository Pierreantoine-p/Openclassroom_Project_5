package com.openclassrooms.safetinet.data;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetinet.model.MedicalRecords;

public class DataMedicalRecord {

	private static List<MedicalRecords> medicalrecords = new ArrayList<MedicalRecords>();

	
	// MEDICAL RECORDS 
		public static List<MedicalRecords> getMedicalRecords() {
			return medicalrecords;
		}

		public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
			this.medicalrecords = medicalrecords;
		}

	
}
