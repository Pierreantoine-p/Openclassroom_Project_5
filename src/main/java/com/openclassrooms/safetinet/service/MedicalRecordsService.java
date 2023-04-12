package com.openclassrooms.safetinet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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


}
