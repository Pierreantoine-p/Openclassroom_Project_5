package com.openclassrooms.safetinet.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {
	
	public MedicalRecordsRepository medicalRecordsRepository;
	
    private static final Logger logger = LogManager.getLogger(MedicalRecordsService.class);

	
	public MedicalRecordsService(MedicalRecordsRepository medicalRecordsRepository) {
		this.medicalRecordsRepository = medicalRecordsRepository;
	}
	
	
	
    //GET
	public List<MedicalRecords> getAll()throws IOException{
		try {
			return medicalRecordsRepository.getAll();
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
		
	}
	
	//GET ONE
	public Optional<MedicalRecords> findByName(String firstname, String lastname) throws IOException{
		try {
			return medicalRecordsRepository.findByName(firstname, lastname);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
	}

	//POST
	public void save(MedicalRecords medicalRecord)throws IOException {
		try {
			medicalRecordsRepository.save(medicalRecord);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
 	}
	
	
	//DELETE		
	public boolean  delete(String firstname, String lastname)throws IOException {
		try {
			List<MedicalRecords> medicalRecords = medicalRecordsRepository.getAll();
			for(MedicalRecords medicalRecord : medicalRecords) {
				if(medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)) {
					return medicalRecordsRepository.delete(medicalRecord);
				}
			}
			return false;
		
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
	} 
	
	
	//PATCH
	
	public boolean update(String firstname, String lastname, MedicalRecords medicalRecord)throws IOException {
		try {
			List<MedicalRecords> medicalRecords = medicalRecordsRepository.getAll();
			for(MedicalRecords medicalRecord1 : medicalRecords) {
				if(medicalRecord1.getFirstName().equals(firstname) && medicalRecord1.getLastName().equals(lastname)) {
					return medicalRecordsRepository.update(medicalRecord);
				}
			}
			return false;
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
		 
	}
	
	public List<MedicalRecords> getMedicalByName(String firstname, String lastname)throws IOException {
		try {
			return medicalRecordsRepository.getMedicalByName(firstname, lastname);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        throw new IOException();
		}
	}
	
	
	
}
