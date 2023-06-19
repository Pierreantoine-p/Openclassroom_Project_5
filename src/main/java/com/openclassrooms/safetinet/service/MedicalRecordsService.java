package com.openclassrooms.safetinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {
	
	public MedicalRecordsRepository medicalRecordsRepository;
	
	public MedicalRecordsService(MedicalRecordsRepository medicalRecordsRepository) {
		this.medicalRecordsRepository = medicalRecordsRepository;
	}
	
    //GET
	public List<MedicalRecords> getAll(){
		return medicalRecordsRepository.getAll();
	}
	
	//GET ONE
	public Optional<MedicalRecords> findByName(String firstname, String lastname) {
		return medicalRecordsRepository.findByName(firstname, lastname);

	}

	//POST
	public void save(MedicalRecords medicalRecord) {
		medicalRecordsRepository.save(medicalRecord);
	}
	
	
	//DELETE		
	public boolean  delete(String firstname, String lastname) {
		List<MedicalRecords> medicalRecords = medicalRecordsRepository.getAll();
		for(MedicalRecords medicalRecord : medicalRecords) {
			if(medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)) {
				return medicalRecordsRepository.delete(medicalRecord);
			}
		}
		return false;
		}
	
	
	//PATCH
	
	public boolean update(String firstname, String lastname, MedicalRecords medicalRecord) {
		List<MedicalRecords> medicalRecords = medicalRecordsRepository.getAll();
		for(MedicalRecords medicalRecord1 : medicalRecords) {
			if(medicalRecord1.getFirstName().equals(firstname) && medicalRecord1.getLastName().equals(lastname)) {
				return medicalRecordsRepository.update(medicalRecord);
			}
		}
		return false;
	}
	
	
	
}
