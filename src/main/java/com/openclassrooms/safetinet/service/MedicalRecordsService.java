package com.openclassrooms.safetinet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {
	
	public MedicalRecordsRepository medicalRecordsRepository;
	
	public MedicalRecordsService(MedicalRecordsRepository medicalRecordsRepository) {
		this.medicalRecordsRepository = medicalRecordsRepository;
	}
	
	public List<MedicalRecords> getMedicalRecords(){
		return medicalRecordsRepository.getMedicalRecords();
	}
	
	public void save(MedicalRecords medicalRecords) {
		medicalRecordsRepository.save(medicalRecords);
	}
	
	
	public void deleteMedicalRecords(String firstname ) {
		medicalRecordsRepository.delete(firstname);
	}
	
	
	public MedicalRecords updateMedicalRecords(MedicalRecords medicalRecords) {
		medicalRecordsRepository.update(medicalRecords);
		return medicalRecords;
	}
	
	public Optional<MedicalRecords> findMedicalRecordsByName(String firstName) {
		return medicalRecordsRepository.findByName(firstName);

	}

}
