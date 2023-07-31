package com.openclassrooms.safetinet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetinet.model.MedicalRecords;
import com.openclassrooms.safetinet.model.Person;
import com.openclassrooms.safetinet.repository.MedicalRecordsRepository;

@Service
public class MedicalRecordsService {

	public MedicalRecordsRepository medicalRecordsRepository;

    private static final Logger logger = LogManager.getLogger(MedicalRecordsService.class);


	public MedicalRecordsService(MedicalRecordsRepository medicalRecordsRepository) {
		this.medicalRecordsRepository = medicalRecordsRepository;
	}



    //GET
	public List<MedicalRecords> getAll() {
		try {
			return medicalRecordsRepository.getAll();
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return new ArrayList<>();
		}

	}

	//GET ONE
	public Optional<MedicalRecords> findByName(String firstname, String lastname)  {
		try {
			return medicalRecordsRepository.findByName(firstname, lastname);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
	}

	//POST
	public Optional<MedicalRecords> save(MedicalRecords medicalRecord)  {
		try {
			medicalRecordsRepository.save(medicalRecord);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
		return null;
 	}


	//DELETE
	public Optional<Person>  delete(String firstname, String lastname)  {
		try {
			List<MedicalRecords> medicalRecords = medicalRecordsRepository.getAll();
			for(MedicalRecords medicalRecord : medicalRecords) {
				if(medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)) {
					 medicalRecordsRepository.delete(medicalRecord);
				}
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
		return null;
	}


	//PATCH

	public Optional<MedicalRecords> update(String firstname, String lastname, MedicalRecords medicalRecord)  {
		try {
			List<MedicalRecords> medicalRecords = medicalRecordsRepository.getAll();
			for(MedicalRecords medicalRecord1 : medicalRecords) {
				if(medicalRecord1.getFirstName().equals(firstname) && medicalRecord1.getLastName().equals(lastname)) {
					 medicalRecordsRepository.update(medicalRecord);
				}
			}
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
		 return null;
	}

	public Optional<MedicalRecords> getMedicalByName(String firstname, String lastname)  {
		//MedicalRecords medicalRecords = new MedicalRecords();
		try {
			return medicalRecordsRepository.getMedicalByName(firstname, lastname);
		}catch(Exception e) {
			logger.error("Error : " + e);
	        return Optional.empty();
		}
	}



}
