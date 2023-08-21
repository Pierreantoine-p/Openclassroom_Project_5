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

	public List<MedicalRecords> getAll() {
		return medicalRecordsRepository.getAll();
	}

	public List<MedicalRecords> findByName(String firstname, String lastname)  {
		return medicalRecordsRepository.findByName(firstname, lastname);
	}

	public Optional<MedicalRecords> save(MedicalRecords medicalRecord)  {
		return medicalRecordsRepository.save(medicalRecord);
	}

	public boolean delete(String firstname, String lastname)  {
		boolean result = false;
		result = medicalRecordsRepository.delete(firstname, lastname);
		return result;
	}

	public boolean update(String firstname, String lastname, MedicalRecords medicalRecord)  {
		boolean result = false;
		result = medicalRecordsRepository.update(firstname,lastname, medicalRecord);
		return result;
	}

	public List<MedicalRecords> getMedicalByName(String firstname, String lastname)  {
		return medicalRecordsRepository.getMedicalByName(firstname, lastname);
	}

}
